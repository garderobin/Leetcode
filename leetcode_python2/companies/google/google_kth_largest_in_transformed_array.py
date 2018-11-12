# coding=utf-8
from abc import ABCMeta, abstractmethod


class KthLargestInTransformedArray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def kth_largest_in_transformed_array(self, nums, a, b, c):
        """
        LC360 改成求第k大的（而非原题那样排序），follow-up：比O(n)还要快
        :return:
        """


class KthLargestInTransformedArrayImpl(KthLargestInTransformedArray):
    """
    Time: O(K)
    Space: O(1)
    思路: a <= 0, 从两边开始向中间找齐n-k个最小值
         a > 0, 从两边开始向中间找齐k个最大值
    """
    def kth_largest_in_transformed_array(self, nums, a, b, c):
        pass


class KthLargestInTransformedArrayImpl2(KthLargestInTransformedArray):
    """
    Time: O(logK)
    Space: O(1)
    """
    def kth_largest_in_transformed_array(self, nums, a, b, c):
        """
        思路: 从极点(ex = -b/2a)左右两边开始, left = ex - k/2, right = ex + k/2
        if a < 0, looking for some i s.t. nums[i-1] <= nums[i+k-1] and nums[i] >= nums[i+k]
            if nums[i-1] > nums[i+k-1]: right += (right - ex) / 2
            if nums[i] < nums[i+k]: left -= (ex - left) / 2
        边界条件不好控制
        """
        pass