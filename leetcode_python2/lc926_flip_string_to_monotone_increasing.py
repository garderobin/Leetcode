from abc import ABCMeta, abstractmethod


class FlipStringToMonotoneIncreasing:
    __metaclass__ = ABCMeta

    @abstractmethod
    def minFlipsMonoIncr(self, S):
        """
        :type S: str
        :rtype: int
        """


class FlipStringToMonotoneIncreasingImpl(FlipStringToMonotoneIncreasing):
    def minFlipsMonoIncr(self, S):
        s = S.lstrip('0')

        n, count_all_ones = len(s), s.count('1')
        if count_all_ones == 0 or count_all_ones == n:
            return 0

        left_count_one, right_counts = [0], [[0, 0] for _ in xrange(n + 1)]  # left exclusive, right inclusive
        for i in xrange(n):
            j = n - i - 1
            left_val, right_val = int(s[i]), int(s[j])
            right_counts[j] = right_counts[j + 1][:]
            left_count_one.append(left_count_one[-1] + left_val)
            right_counts[j][right_val] += 1

        min_flip = min(count_all_ones, n - count_all_ones)
        for i in xrange(1, n):
            # left 1->0 + min(right 1->0 or 0->1)
            min_flip = min(min_flip, left_count_one[i] + min(right_counts[i]))
        return min_flip
