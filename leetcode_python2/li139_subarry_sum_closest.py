import sys
from abc import ABCMeta, abstractmethod


class SubarraySumClosest:
    __metaclass__ = ABCMeta

    @abstractmethod
    def subarray_sum_closest(self, nums):
        """
        @param: nums: A list of integers
        @return: A list of integers includes the index of the first number and the index of the last number
        """


class SubarraySumClosestImpl(SubarraySumClosest):

    def subarray_sum_closest(self, nums):
        res = (0, 0)
        prefix_hash = {}    # key: prefix_sum, value: the index that has the prefix value
        prefix_sum = 0
        min_subarray_sum = -sys.maxint
        for index, num in enumerate(nums):
            prefix_sum += num
            if prefix_sum in prefix_hash:
                return prefix_hash[prefix_sum] + 1, index
            else:
                pass


        return res
