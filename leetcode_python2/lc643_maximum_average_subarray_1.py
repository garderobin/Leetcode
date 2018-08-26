from abc import ABCMeta, abstractmethod


class MaximumAverageSubarray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_max_average(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: float
        """


class MaximumAverageSubarrayImpl(MaximumAverageSubarray):
    def find_max_average(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: float
        """
        window_sum, n = sum(nums[:k]), len(nums)
        max_window_sum = window_sum
        for i in xrange(k, n):
            window_sum += nums[i] - nums[i-k]
            max_window_sum = max(max_window_sum, window_sum)
        return max_window_sum * 1.0 / k


class MaximumAverageSubarrayImplOneLine(MaximumAverageSubarray):
    """
    Time limit exceeded!
    """
    def find_max_average(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: float
        """
        return max(sum(nums[i-k:i]) for i in xrange(k, len(nums))) * 1.0 / k


