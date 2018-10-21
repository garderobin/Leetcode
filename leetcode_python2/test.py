# coding=utf-8
import itertools
import math
from _bisect import bisect_right
from collections import deque, defaultdict, Counter

from mock0915_1_sum_of_subarray_sums import SumOfAllSubarraySumsImplSlidingWindow, SumOfAllSubarraySumsImplCounter

if __name__ == "__main__":
    I = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
    A = [0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0,  1,  0,  0]
    one_start_index, total_ones = -1, 0
    one_count_ranges = {-1: (-1, -1)}
    for i, a in enumerate(A):
        if a == 1:
            if total_ones == 0:
                one_start_index = i
            one_count_ranges[total_ones] = (one_count_ranges[total_ones - 1][1] + 1, i - 1)
            total_ones += 1
        else:
            pass
    one_count_ranges[total_ones] = (one_count_ranges[total_ones - 1][1] + 1, len(A))

    ones_per_part = 1
    (i_start, i_end) = one_count_ranges[ones_per_part]
    (j_start, j_end) = (one_count_ranges[ones_per_part << 1][0] + 1, one_count_ranges[ones_per_part << 1][1] + 1)

    print i_start, i_end, j_start, j_end
