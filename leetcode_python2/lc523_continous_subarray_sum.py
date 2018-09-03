from abc import ABCMeta, abstractmethod


class ContinuousSubarraySum:
    __metaclass__ = ABCMeta

    @abstractmethod
    def check_subarray_sum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """


class ContinuousSubarraySumImpl(ContinuousSubarraySum):
    def check_subarray_sum(self, nums, k):
        pass
