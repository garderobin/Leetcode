# coding=utf-8
from abc import ABCMeta, abstractmethod


class ContinuousSubarraySum:
    __metaclass__ = ABCMeta

    @abstractmethod
    def continuous_subarray_sum(self, A):
        """
        :param A: List[int]
        :return: List[int]
        """


class ContinuousSubarraySumImpl(ContinuousSubarraySum):
    __metaclass__ = ABCMeta

    @abstractmethod
    def continuous_subarray_sum(self, A):
        """
        :param A: List[int]
        :return: List[int]
        """
        g_sum, g_start = 0, 0   # g is the subarray which ends at current index.
        max_sum, max_sum_range = -2147483649, (0, 0)
        for index, num in enumerate(A):
            if g_sum > 0:
                g_sum += num
            else:
                g_sum, g_start = num, index

            if g_sum > max_sum:
                max_sum = g_sum
                max_sum_range = (g_start, index)
        return max_sum_range


class ContinuousSubarraySumTwoPointers(ContinuousSubarraySum):
    __metaclass__ = ABCMeta

    @abstractmethod
    def continuous_subarray_sum(self, A):
        """
        :param A: List[int]
        :return: List[int]
        """
        ans = -0x7fffffff
        sum = 0
        start, end = 0, -1
        result = [-1, -1]
        for x in A:
            if sum < 0:
                sum = x
                start = end + 1
                end = start
            else:
                sum += x
                end += 1
            if sum > ans:
                ans = sum
                result = [start, end]

        return result
