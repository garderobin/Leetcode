# coding=utf-8
from _bisect import bisect_right
from collections import deque

from mock0915_1_sum_of_subarray_sums import SumOfAllSubarraySumsImplSlidingWindow, SumOfAllSubarraySumsImplCounter

if __name__ == "__main__":
    nums = [0, 1, 2, 3, 4, 5]
    for i1, e1 in enumerate(nums):
        for i2, e2 in enumerate(nums[i1 + 1:]):
            print i1, i2, e1, e2
