# coding=utf-8
import itertools
import math
from _bisect import bisect_right
from collections import deque, defaultdict, Counter

from mock0915_1_sum_of_subarray_sums import SumOfAllSubarraySumsImplSlidingWindow, SumOfAllSubarraySumsImplCounter

if __name__ == "__main__":
    s = 'aaabcc'
    counter = Counter(s)
    print counter.most_common()
