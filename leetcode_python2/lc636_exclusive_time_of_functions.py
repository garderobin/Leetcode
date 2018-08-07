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
