# coding=utf-8
from abc import ABCMeta, abstractmethod


class ExclusiveTimeOfFunctions:
    __metaclass__ = ABCMeta

    @abstractmethod
    def exclusive_time(self, n, logs):
        """
        :type n: int
        :type logs: List[str]
        :rtype: List[int]
        """


class ExclusiveTimeOfFunctionsImplStack4(ExclusiveTimeOfFunctions):
    """
    巧用prev = time + is_end 来保证'end of very end of a minute'能在计算时间差的时候被正确体现，把prev固定在开始时刻
    今日最佳，既用了最少的变量，逻辑又最清晰
    stack只存当前仍然在跑的function的id, 只在start的时候进栈，只在end的时候出栈
    TODO 背这个答案
    """
    def exclusive_time(self, n, logs):
        exclusive_times = [0] * n   # 比[0 for _ in xrange(n)] 要省时间，一维数组放心用
        stack = []  # ids of current running functions
        prev = 0
        for log in logs:
            info = str(log).split(':')
            fid, is_end, time = int(info[0]), info[1] == 'end', int(info[2])    # 这里用 'end' 比 "end" 省非常多时间！
            if is_end:
                exclusive_times[stack.pop()] += time - prev + 1
            else:
                if stack:
                    exclusive_times[stack[-1]] += time - prev
                stack.append(fid)
            prev = time + is_end
        return exclusive_times


class ExclusiveTimeOfFunctionsImplStack3(ExclusiveTimeOfFunctions):

    def exclusive_time(self, n, logs):
        exclusive_times = [0] * n
        stack = []  # ids of current running functions
        fid, time, is_end = 0, 0, False
        for log in logs:
            info = str(log).split(':')
            last_fid, last_is_end, last_time = fid, is_end, time
            fid, is_end, time = int(info[0]), info[1] == "end", int(info[2])
            if is_end:
                exclusive_times[fid] += time - last_time + (last_is_end ^ 1)
                if stack:
                    fid = stack.pop()
            else:
                exclusive_times[last_fid] += time - last_time - last_is_end
                stack.append(last_fid)
        return exclusive_times


class ExclusiveTimeOfFunctionsImplStack2(ExclusiveTimeOfFunctions):
    """
    我的这个思路是start, end操作都入栈
    坏处：stack读写操作多，时间慢
    好处：flag比较少，需要控制的变量少一些
    逻辑上与只把start操作入栈的情况差不多复杂，都不容易控制corner case.

    栈的题往往不难，难的是把所有case分析完全，一遍过很难。
    """
    @staticmethod
    def split_log_info(log):
        function_str, start_or_end_str, time_str = log.split(':')
        return [int(time_str), start_or_end_str == 'end', int(function_str)]

    def exclusive_time(self, n, logs):
        stack = [self.split_log_info(logs[0])]
        exclusive_times = [0] * n
        for log in logs[1:]:
            timestamp, is_end, fid = self.split_log_info(log)

            if is_end:
                prev_timestamp, prev_is_end, prev_fid = stack.pop()
                if prev_is_end:
                    exclusive_times[stack.pop()[2]] += timestamp - prev_timestamp
                else:
                    exclusive_times[prev_fid] += timestamp - prev_timestamp + 1

            else:
                if stack[-1][1]:
                    delta = timestamp - stack.pop()[0] - 1
                    if stack:
                        exclusive_times[stack[-1][2]] += delta
                else:
                    exclusive_times[stack[-1][2]] += timestamp - stack[-1][0]

            stack.append([timestamp, is_end, fid])
        return exclusive_times


class ExclusiveTimeOfFunctionsImplStack(ExclusiveTimeOfFunctions):
    def exclusive_time(self, n, logs):
        stack = []  # element structure: list [function_id, start_timestamp, current_exclusive_time]
        exclusive_time_dict = {}
        for log in logs:
            func, action, timestamp_str = log.split(':')
            timestamp = int(timestamp_str)
            if stack:
                last_func_time = stack.pop()  # 注意检查 不能允许pop from empty stack!
                last_func, last_start, last_exclusive_time = last_func_time[0], int(last_func_time[1]), last_func_time[2]
                if action == 'end':
                    exclusive_time_dict[last_func] = timestamp - last_start + last_exclusive_time
                    prev_func_time = stack.pop()
                    stack.append([prev_func_time[0], timestamp, prev_func_time[2]])
                else:
                    stack.append([last_func, last_start, timestamp - last_start + last_exclusive_time])
                    stack.append([func, timestamp, 0])
            else:
                stack.append([func, timestamp, 0])
        return [exclusive_time_dict[x] for x in xrange(n)]
