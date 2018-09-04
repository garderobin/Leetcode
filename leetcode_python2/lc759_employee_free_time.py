# coding=utf-8
import heapq
from abc import ABCMeta, abstractmethod
from itertools import chain


class EmployeeFreeTime:
    """
    TODO： 自己动手写K路归并
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def employee_free_time(self, schedule):
        """
        :type schedule: List[List[Interval]]
        :rtype: List[Interval]
        """


class EmployeeFreeTimeChainSort(EmployeeFreeTime):

    def employee_free_time(self, schedule):
        all_intervals = sorted(chain(*schedule), key=lambda itv: itv.start)

        work_times, free_times = [all_intervals[0]], []
        for interval in all_intervals[1:]:
            last_end_time = work_times[-1].end
            if interval.start > last_end_time:      # 写if/else的时候绝对不要不算等于的情况！！！
                free_times.append(Interval(last_end_time, interval.start))
                work_times.append(interval)
            else:
                work_times[-1].end = max(last_end_time, interval.end)
        return free_times


class EmployeeFreeTimeImplHeapBruteForce(EmployeeFreeTime):
    @staticmethod
    def get_merged_intervals_heap(schedule):
        all_intervals = []
        for intervals in schedule:
            for interval in intervals:
                heapq.heappush(all_intervals, [interval.start, interval.end])
        return all_intervals

    def employee_free_time(self, schedule):
        all_intervals = self.get_merged_intervals_heap(schedule)

        work_times, free_times = [], []
        while all_intervals:
            start, end = heapq.heappop(all_intervals)
            if not work_times:
                work_times.append(Interval(start, end))
            elif work_times[-1].end < start:
                free_times.append(Interval(work_times[-1].end, start))
                work_times.append(Interval(start, end))
            else:
                work_times[-1].end = max(work_times[-1].end, end)
        return free_times


class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e
