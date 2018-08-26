# coding=utf-8
from abc import ABCMeta, abstractmethod
import heapq, sys


class FindTheMedianFromDataStream:
    """
    这题leetcode 和 lintcode 对median定义完全不同，两者代码不能互相替换
    lintcode: https://www.jiuzhang.com/solutions/data-stream-median/#tag-highlight-lang-python
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def add_num(self, num):
        """
        :type num: int
        :rtype: void
        """

    @abstractmethod
    def find_median(self):
        """
        :rtype: float
        """


class MedianFinderOptimize(FindTheMedianFromDataStream):
    def __init__(self):
        self.max_heap = []  # left
        self.min_heap = []  # right
        self.median = 0.0

    def add_num(self, num):
        if num > self.median:
            heapq.heappush(self.min_heap, float(num))
        else:
            heapq.heappush(self.max_heap, -float(num))

        # balance the heaps so that len(left) <= len(right)
        delta = len(self.min_heap) - len(self.max_heap)
        if delta == 0:
            self.median = (self.min_heap[0] - self.max_heap[0]) * 1.0 / 2
        elif delta == 2:
            heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))
            self.median = (self.min_heap[0] - self.max_heap[0]) * 1.0 / 2
        elif delta == 1:
            self.median = self.min_heap[0]
        else:
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
            self.median = self.min_heap[0]

    def find_median(self):
        return self.median


class MedianFinder(FindTheMedianFromDataStream):
    def __init__(self):
        self.max_heap = []  # left
        self.min_heap = []  # right
        self.median = 0.0

    def add_num(self, num):
        if num > self.median:
            heapq.heappush(self.min_heap, num)
        else:
            heapq.heappush(self.max_heap, -num)

        # balance the heaps so that len(left) >= len(right)
        delta = len(self.max_heap) - len(self.min_heap)
        if delta == 0:
            self.median = (self.min_heap[0] - self.max_heap[0]) * 1.0 / 2
        elif delta == 2:
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
            self.median = (self.min_heap[0] - self.max_heap[0]) * 1.0 / 2
        elif delta == 1:
            self.median = -self.max_heap[0]
        elif delta == -1:
            heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))
            self.median = -self.max_heap[0]

    def find_median(self):
        return self.median
