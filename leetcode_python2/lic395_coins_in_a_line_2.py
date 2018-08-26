# coding=utf-8
from abc import ABCMeta, abstractmethod


class CoinsInALine2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def first_will_win(self, values):
        """
        @param values: a vector of integers
        @return: a boolean which equals to true if the first player will win
        """


class CoinsInALine2ImplRotateArray(CoinsInALine2):
    """
    博弈型动态规划，基本都可以用滚动数组解决。
    原理是虽然起始时条件不同，但濒临结束时条件均等。
    所以从结束时的情况开始逆推，两个人可以共享区间最佳的数据，因为一个人采取某方案的所得是总数减去另一个人的最佳所得
    """
    def first_will_win(self, values):
        if not values:
            return False

        n = len(values)
        if n < 2:
            return True

        f = [0] * 3             # f[i] = the max gain of first hand in the part of game in (i, n-1) coin ranges.
        suffix_sums = [0] * 3   # suffix_sums[i] = sum(nums[i:])
        start_index = (n - 1) % 3
        f[start_index] = suffix_sums[start_index] = values[-1]
        for i in xrange(n - 2, -1, -1):
            suffix_sums[i % 3] = suffix_sums[(i + 1) % 3] + values[i]
            output_first_hand_takes_one_coin = values[i] + suffix_sums[(i + 1) % 3] - f[(i + 1) % 3]
            output_first_hand_takes_two_coins = values[i] + values[i + 1] + suffix_sums[(i + 2) % 3] - f[(i + 2) % 3]
            f[i % 3] = max(output_first_hand_takes_one_coin, output_first_hand_takes_two_coins)
        return f[0] > suffix_sums[0] - f[0]
