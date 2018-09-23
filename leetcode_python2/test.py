# coding=utf-8
import math
from _bisect import bisect_right
from collections import deque, defaultdict

from mock0915_1_sum_of_subarray_sums import SumOfAllSubarraySumsImplSlidingWindow, SumOfAllSubarraySumsImplCounter

if __name__ == "__main__":
    envelopes = [[5, 4], [6, 4], [6, 7], [2, 3]]
    # sorted_envelopes = sorted(envelopes, key=lambda (w, h): (w, -h))
    # print sorted_envelopes
    envelopes.sort(key=lambda x: x[1], reverse=True)
    envelopes.sort(key=lambda x: x[0], reverse=False)
    print envelopes
