# coding=utf-8
import sys
from abc import ABCMeta, abstractmethod


class MaximumProductSubarray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_product(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class MaximumProductSubarrayImpl(MaximumProductSubarray):
    """
    易错点：乘法有从min乘个负数直接变最大的特性，和加法不同
    Time: O(N)
    Space: O(1)
    """

    def max_product(self, nums):
        if not nums:
            return 0
        max_product, max_product_end_here, min_product_end_here = nums[0], 1, 1  # 不要轻易引入sys.maxsize, 费时间且没有必要
        for num in nums:
            border_candidates = [max_product_end_here * num, min_product_end_here * num, num]
            max_product_end_here, min_product_end_here = max(border_candidates), min(border_candidates)
            max_product = max(max_product, max_product_end_here)
        return max_product
