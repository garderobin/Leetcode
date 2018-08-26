# coding=utf-8
from abc import ABCMeta, abstractmethod


class MaximumSubarray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_sub_array(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class MaximumSubarrayImplDP(MaximumSubarray):
    """
    如果题目不要求maximum subarray containing at least one number
    y[j]: max subarray using nums[j] and j is the largest index in the subarray
    y[0] = nums[0]
    y[j] = max(y[j-1] + nums[j], nums[j]) = max(0, y[j-1]) + nums[j]

    f[0] = max(0, y[0])
    f[i] = max(f[i-1], y[i])

    max_subarray_fix_ending, max_subarray = 0, 0
    i: 0->n-1
    max_subarray_fix_ending = max(0, max_subarray_fix_ending) + nums[i]
    max_subarray = max(max_subarray, max_subarray_fix_ending)

    Time: O(N)
    Space: O(N)
    """
    def max_sub_array(self, nums):
        # 一定要问清楚面试官 subarray是否允许为空
        array_max = -2147483648
        max_subarray_sum, max_subarray_sum_fix_end = 0, 0
        for num in nums:
            array_max = max(array_max, num)
            max_subarray_sum_fix_end = max(0, max_subarray_sum_fix_end) + num
            max_subarray_sum = max(max_subarray_sum, max_subarray_sum_fix_end)
        return array_max if array_max < 0 else max_subarray_sum
