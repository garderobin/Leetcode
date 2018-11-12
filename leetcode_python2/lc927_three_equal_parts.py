import itertools
from abc import ABCMeta, abstractmethod


class ThreeEqualParts:
    __metaclass__ = ABCMeta

    @abstractmethod
    def threeEqualParts(self, A):
        """
        :type A: List[int]
        :rtype: List[int]
        """


class ThreeEqualPartsImpl2(ThreeEqualParts):
    """
    """
    def threeEqualParts(self, A):
        n, total_ones = len(A), 0
        one_count_ranges = {-1: (-1, -1)}
        for i, a in enumerate(A):
            if a == 1:
                one_count_ranges[total_ones] = (one_count_ranges[total_ones - 1][1] + 1, i - 1)
                total_ones += 1
            else:
                pass
        one_count_ranges[total_ones] = (one_count_ranges[total_ones - 1][1] + 1, n)

        # check 1s can be split to 3 parts.
        if total_ones == 0:
            return [0, 2]
        if total_ones % 3 > 0:
            return [-1, -1]

        # check each part could have enough ending 0s.
        min_ending_zero = n - 1 - one_count_ranges[total_ones][0]
        ones_per_part = total_ones // 3
        (i_start, i_end) = (one_count_ranges[ones_per_part][0] + min_ending_zero,
                            one_count_ranges[ones_per_part][1])
        (j_start, j_end) = (one_count_ranges[ones_per_part << 1][0] + 1 + min_ending_zero,
                            one_count_ranges[ones_per_part << 1][1] + 1)
        if i_start > i_end or j_start > j_end:
            return [-1, -1]

        # match the part without left or right trailing 0s.
        parts = [
            (one_count_ranges[1][0], i_start),
            (i_end + 1, j_start - 1),
            (j_end, n - min_ending_zero - 1)
        ]
        no_trailing_zero_size = parts[0][1] - parts[0][0]
        if parts[1][1] - parts[1][0] != no_trailing_zero_size or parts[2][1] - parts[2][0] != no_trailing_zero_size:
            return [-1, -1]
        for i in xrange(no_trailing_zero_size):
            cl, cm, cr = A[parts[0][0] + i],  A[parts[1][0] + i], A[parts[2][0] + i]
            if cl != cm or cl != cr:
                return [-1, -1]

        return [i_start, j_start]


class ThreeEqualPartsImplNaive(ThreeEqualParts):
    """
    TLE
    Time: O(N ^ 2)
    """
    def threeEqualParts(self, A):
        n = len(A)

        left = [0 for _ in xrange(n)]  # left[i] = value(A[0], ..., A[i]), i: 0 -> n - 3
        right = [0 for _ in xrange(n)]  # right[j] = value(A[j], ..., A[n - 1]), j : 2 -> n - 1
        left[0], right[-1] = A[0], A[-1]

        for i in xrange(1, n - 2):
            j = n - 1 - i
            left[i] = (left[i - 1] << 1) + (A[i])
            right[j] = ((1 << i) if A[j] else 0) + right[j + 1]

        # print 'i', 'j', 'l', 'm', 'r'
        for i in xrange(n - 3, -1, -1):  # i = n - 3
            middle = A[i + 1]
            for j in xrange(i + 2, n):  # j = n - 1, [A[n - 2]]
                # print i, j, left[i], middle, right[j]
                if left[i] == right[j]:
                    if middle == left[i]:
                        return [i, j]
                middle = (middle << 1) + A[j]
        return [-1, -1]
