from abc import ABCMeta, abstractmethod
from math import factorial


class UniquePaths:
    __metaclass__ = ABCMeta

    @abstractmethod
    def unique_paths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        """


class UniquePathsImpl1(UniquePaths):
    def unique_paths(self, m, n):
        return self.count_combination(m - 1, m + n - 2)

    def count_combination(self, r, n):
        return factorial(n) / (factorial(r) * factorial(n - r))


class UniquePathsImpl2(UniquePaths):
    def unique_paths(self, m, n):
        return self.count_combination(m - 1, m + n - 2)

    def count_combination(self, r, n):
        small, large = min(r, n - r), max(r, n - r)
        divisor, dividend = 1, 1
        for i in xrange(large + 1, n + 1):
            dividend *= i
        for i in xrange(2, small + 1):
            divisor *= i
        return dividend // divisor
