# coding=utf-8
from abc import ABCMeta, abstractmethod


class CoinsInALine3:
    __metaclass__ = ABCMeta

    @abstractmethod
    def first_will_win(self, values):
        """
        :param values: List[int]
        :return: bool
        """


class CoinsInALine3Impl(CoinsInALine3):
    """
    Time: O(N^2)
    Space: O(N^2)
    """
    @staticmethod
    def get_prefix_sums(values):
        prefix_sums = [0]
        for v in values:
            prefix_sums.append(prefix_sums[-1] + v)
        return prefix_sums

    """
    State: f[i][j] = firstWillWin(values[i:j+1])
    Function: f[i][j] = prefix_sums[j+1] - prefix_sums[i] - min(f[i+1][j], f[i][j-1])
    Initialization: f[i][i] = values[i]
    Answer: (f[0][n-1] << 1) >= prefix_sums[n]
    """
    def first_will_win(self, values):
        if not values:
            return True
        n = len(values)
        prefix_sums = self.get_prefix_sums(values)

        f = [[0] * n for _ in xrange(n)]    # f[i][j] = firstWillWin(values[i:j+1])
        for i in xrange(n):
            f[i][i] = values[i]

        for delta in xrange(1, n):
            for i in xrange(n - delta):
                j = i + delta
                f[i][j] = prefix_sums[j + 1] - prefix_sums[i] - min(f[i + 1][j], f[i][j - 1])
        return f[0][n-1]


class CoinsInALine3Jiuzhang(CoinsInALine3):
    """
    @param values: a vector of integers
    @return: a boolean which equals to true if the first player will win
    """
    def first_will_win(self, values):
        if not values:
            return False

        n = len(values)
        dp = [[0] * n for _ in range(n)]
        sum = [[0] * n for _ in range(n)]

        for i in range(n):
            dp[i][i] = values[i]
            sum[i][i] = values[i]

        for i in range(n - 2, -1, -1):  # n-2 => 0
            for j in range(i + 1, n):  # i+1 => n-1
                sum[i][j] = sum[i + 1][j] + values[i]
                dp[i][j] = sum[i][j] - min(dp[i + 1][j], dp[i][j - 1])

        return dp[0][n - 1] > sum[0][n - 1] - dp[0][n - 1]