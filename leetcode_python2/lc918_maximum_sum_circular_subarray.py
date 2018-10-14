# coding=utf-8
from abc import ABCMeta, abstractmethod


class MaximumSumCircularSubarray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_subarray_sum_circular(self, A):
        """
        :type A: List[int]
        :rtype: int
        """


class Solution(MaximumSumCircularSubarray):
    """
    处理循环数组常见的三中套路：
    1. minimax处理区间问题 (比如这道题，max_circular_subarray_sum = sum - min_subarray_sum
    2. 蛇咬尾巴， 处理相邻问题
    3. 万能模板: B = A + A
    """
    def max_subarray_sum_circular(self, A):
        if not A:
            return 0

        max_subarray_sum, max_subarray_sum_fix_end = -900000001, 0  # max的初始化是system possible min - 1
        min_subarray_sum, min_subarray_sum_fix_end = 900000001, 0   # min的初始化失system possible max + 1
        for num in A:
            max_subarray_sum_fix_end = max(0, max_subarray_sum_fix_end) + num
            max_subarray_sum = max(max_subarray_sum, max_subarray_sum_fix_end)
            min_subarray_sum_fix_end = min(0, min_subarray_sum_fix_end) + num
            min_subarray_sum = min(min_subarray_sum, min_subarray_sum_fix_end)

        # 一定要检查全是负数的情况，就算想不到这一点，在面试设计test case的时候要试一试全为负担情况
        return max_subarray_sum if max_subarray_sum < 0 else max(max_subarray_sum, sum(A) - min_subarray_sum)
