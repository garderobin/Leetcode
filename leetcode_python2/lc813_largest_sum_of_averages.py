from abc import ABCMeta, abstractmethod


class LargestSumOfAverages:
    __metaclass__ = ABCMeta

    @abstractmethod
    def largest_sum_of_averages(self, A, K):
        """
        :type A: List[int]
        :type K: int
        :rtype: float
        """
        return 0.0


class LargestSumOfAveragesDP(LargestSumOfAverages):
    def __init__(self):
        self.prefix_sums = []
        self.f = []

    def largest_sum_of_averages(self, A, K):
        if K == 1:
            return sum(A) * 1.0 / len(A)

        self.initialize(A, K)
        f = self.f
        for i in xrange(2, K + 1):
            for j in xrange(i, len(A) + 1):
                partition = max((f[(i - 1) % 2][x] + self.range_sum(x, j) * 1.0 / (j - x)) for x in xrange(i - 1, j))
                f[i % 2][j] = max(f[i % 2][j], partition)
        return f[K % 2][len(A)]

    def initialize(self, A, K):
        self.prefix_sums = self.get_prefix_sums(A)

        n = len(A)
        self.f = [[0] * (n + 1), [0] * (n + 1)]  # f[i % 2][j] = largest_sum_of_averages(A[:j], i)
        for j in xrange(1, n + 1):
            self.f[1][j] = self.prefix_sums[j] * 1.0 / j

    def get_prefix_sums(self, A):
        prefix_sums = [0]
        cur_sum = 0
        for a in A:
            cur_sum += a
            prefix_sums.append(cur_sum)
        return prefix_sums

    def range_sum(self, start_inclusive, end_exclusive):
        return self.prefix_sums[end_exclusive] - self.prefix_sums[start_inclusive]