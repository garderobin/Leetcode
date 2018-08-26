# coding=utf-8
from abc import ABCMeta, abstractmethod


class LongestConsecutiveSequence:
    __metaclass__ = ABCMeta

    @abstractmethod
    def longest_consecutive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class LongestConsecutiveSequenceImplSet(LongestConsecutiveSequence):
    """
    Time O(N)
    妙处： set remove
    """
    def longest_consecutive(self, nums):
        not_visited_items = set(nums)
        res = 0
        for item in nums:
            if item in not_visited_items:
                not_visited_items.remove(item)
            left, right = item - 1, item + 1
            while left in not_visited_items:
                not_visited_items.remove(left)
                left -= 1
            while right in not_visited_items:
                not_visited_items.remove(right)
                right += 1
            res = max(res, right - left - 1)
        return res
