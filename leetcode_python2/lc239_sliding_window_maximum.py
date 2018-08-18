# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque


class SlidingWindowMaximum(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_sliding_window(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """


class SlidingWindowMaximumImplMonotonousStackOptimize(SlidingWindowMaximum):
    """
    Time: O(N)
    Space: O(N)
    应该要在审题的时候马上想到单调栈的适用场域: 区间极值
    """
    def max_sliding_window(self, nums, k):
        if k == 0:
            return []

        q = deque()
        window_max_nums = []

        for i, num in enumerate(nums):
            # remove numbers out of range k
            if q and q[0] < i - k + 1:
                q.popleft()

            # remove smaller numbers in k range as they are useless
            while q and nums[q[-1]] < num:
                q.pop()

            q.append(i)
            if i > k - 2:
                window_max_nums.append(nums[q[0]])

        return window_max_nums


class SlidingWindowMaximumImplMonotonousStack(SlidingWindowMaximum):
    def max_sliding_window(self, nums, k):
        if k == 0:
            return []

        monotonous_window_indexes = deque([0])

        for i in xrange(1, k):
            while monotonous_window_indexes and nums[monotonous_window_indexes[-1]] < nums[i]:
                monotonous_window_indexes.pop()
            monotonous_window_indexes.append(i)

        window_max_nums = [nums[monotonous_window_indexes[0]]]
        for i in xrange(k, len(nums)):
            if i - k == monotonous_window_indexes[0]:
                monotonous_window_indexes.popleft()
            while monotonous_window_indexes and nums[monotonous_window_indexes[-1]] < nums[i]:
                monotonous_window_indexes.pop()
            monotonous_window_indexes.append(i)
            window_max_nums.append(nums[monotonous_window_indexes[0]])

        return window_max_nums
