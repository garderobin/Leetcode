# coding=utf-8
from abc import ABCMeta, abstractmethod


class SubarraySum2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def subarray_sum_2(self, A, start, end):
        """
        :param A: List[int]
        :param start: int
        :param end: int
        :return: int
        """


class SubarraySum2ImplTwoPointers(SubarraySum2):
    """
    需要立刻联想到同向双指针的特性：全正数数组，具有单调性的区间问题等等
    有问题， 过不了【1，2，3，4】，【0，0】
    """
    def subarray_sum_2(self, A, start, end):
        if not A:
            return 0
        valid_subarray_count = 0
        n, left, right, sum_small, sum_large = len(A), 0, 0, 0, 0
        for num in A:
            while left < n and sum_small < start:
                left += 1
                sum_small += A[left - 1]

            if sum_small < start:
                return valid_subarray_count

            while right < n and sum_large <= end:
                right += 1
                sum_large += A[right - 1]

            if sum_large > end:
                right -= 1

            valid_subarray_count += right - left + 1

            sum_small -= num
        return valid_subarray_count
