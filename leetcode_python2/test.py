# coding=utf-8
import itertools
import math
from _bisect import bisect_right
from collections import deque, defaultdict, Counter

from mock0915_1_sum_of_subarray_sums import SumOfAllSubarraySumsImplSlidingWindow, SumOfAllSubarraySumsImplCounter

if __name__ == "__main__":
    # time_set = {(4, 26), (21, 2), (3, 55)}
    # for time in time_set:
    #     print time
    #
    # print set('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ')
    A = [14,2,3,1,2,3,1]
    c = Counter(A)
    res = 0
    for i, j in itertools.combinations_with_replacement(c, 2):
        print i, j