# coding=utf-8
from abc import ABCMeta, abstractmethod


class SearchInsertPosition:
    __metaclass__ = ABCMeta

    @abstractmethod
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """


class SearchInsertPositionImplBinarySearch(SearchInsertPosition):
    """
    注意这道题possible range不是[0, n - 1]而是[0, n]
    """
    def searchInsert(self, nums, target):
        if not nums:
            return 0

        start, end = 0, len(nums)
        while start + 1 < end:
            mid = (start + end) // 2

            if nums[mid] == target:
                return mid
            elif nums[mid] > target:
                end = mid
            else:
                start = mid
        return start if nums[start] >= target else end
