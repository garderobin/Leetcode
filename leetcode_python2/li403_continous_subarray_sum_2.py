# coding=utf-8
import sys
from abc import ABCMeta, abstractmethod


class ContinuousSubarraySum2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def continuous_subarray_sum_2(self, A):
        """
        :param A: List[int]
        :return: List[int]
        """


class ContinuousSubarraySum2Impl(ContinuousSubarraySum2):
    def continuous_subarray_sum_2(self, A):
        if not A:
            return None
        g_sum, max_start = 0, 0  # g is the subarray which ends at current index.
        f_sum, min_start = 0, 0
        max_sum, max_end = -2147483649, 0
        min_sum, min_end = sys.maxsize, 0
        total = 0
        for end, num in enumerate(A):
            total += num
            if g_sum > 0:
                g_sum += num
            else:
                g_sum, max_start = num, end

            if g_sum > max_sum:
                max_sum = g_sum
                max_end = end

            if f_sum <= 0:
                f_sum += num
            else:
                f_sum, min_start = num, end

            if f_sum < min_sum:
                min_sum = f_sum
                min_end = end
        return (max_start, max_end) if max_sum > total - min_sum else (min_end, min_start)
