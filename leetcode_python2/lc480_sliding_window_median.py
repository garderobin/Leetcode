# coding=utf-8
from abc import ABCMeta, abstractmethod
from bisect import bisect_left, insort_left

from data_structures.hashheap import HashHeap


class SlidingWindowMedian:
    __metaclass__ = ABCMeta

    @abstractmethod
    def median_sliding_window(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[float]
        """


class SlidingWindowMedianImplBisect(SlidingWindowMedian):
    """
    Bisect: http://kuanghy.github.io/2016/06/14/python-bisect
    Time: O((n-k + logk)k)
    Space: O(K)
    但是胜在bisect模块本身性能卓越
    """
    def median_sliding_window(self, nums, k):
        win = nums[:k]
        win.sort()
        res = [(win[k // 2] + win[(k - 1) // 2]) / 2.0]
        for i in range(k, len(nums)):
            index = bisect_left(win, nums[i - k])   # O(logK)
            win.pop(index)                          # O(K)
            insort_left(win, nums[i])               # O(K)
            res.append((win[k // 2] + win[(k - 1) // 2]) / 2.0)
        return res


class SlidingWindowMedianImplHeap(SlidingWindowMedian):
    """
    https://www.jiuzhang.com/solution/sliding-window-median/#tag-highlight-lang-python
    Time: O(Nlogk)，N是元素个数，k 是 window 的大小
    Space: O(N)
    """

    def __init__(self):
        self.max_heap = HashHeap(desc=True)
        self.min_heap = HashHeap()

    def median_sliding_window(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[float]
        """
        if not nums:
            return []

        self.max_heap, self.min_heap = HashHeap(desc=True), HashHeap()
        # right heap (min) should always smaller or equal to the size of left (max) heap.
        medians = []
        for index, num in enumerate(nums):
            self.add((float(num), index))

            remove_index = index - k
            if remove_index >= 0:
                remove_element = nums[remove_index]
                self.remove((remove_element, remove_index))

            if remove_index > -2:  # 这一点容易错！当index = k - 1时就需要有median了！
                medians.append(self.median)
        return medians

    def add(self, float_index_pair):
        if float_index_pair[1] > 0 and float_index_pair[0] > self.median:
            self.min_heap.push(float_index_pair)
        else:
            self.max_heap.push(float_index_pair)

        self._balance()

    def remove(self, item):
        self.max_heap.remove(item)
        self.min_heap.remove(item)

        self._balance()

    def _balance(self):
        delta = self.max_heap.size - self.min_heap.size
        if delta == 2:
            self.min_heap.push(self.max_heap.pop())
        elif delta < 0:
            self.max_heap.push(self.min_heap.pop())

    @property
    def median(self):
        if self.max_heap.size == self.min_heap.size:
            return (self.max_heap.top()[0] + self.min_heap.top()[0]) / 2
        else:
            return self.max_heap.top()[0]
