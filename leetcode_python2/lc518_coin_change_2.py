# coding=utf-8
from abc import ABCMeta, abstractmethod


class CoinChange2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def change(self, amount, coins):
        """
        :type amount: int
        :type coins: List[int]
        :rtype: int
        """


class CoinChange2DP(CoinChange2):

    def change(self, amount, coins):
        dp = [0] * (amount + 1)
        dp[0] = 1
        for i in coins:
            for j in range(1, amount + 1):
                if j >= i:
                    dp[j] += dp[j - i]
        return dp[amount]


class CoinChange2MemoSearch(CoinChange2):
    """
    这题memo search不会变快，因为没有任何剪枝空间，这不是将区间划分成一大一小到类型
    """
    def __init__(self):
        self.memo = {}
        self.coins = []

    def change(self, amount, coins):
        if not amount:
            return 1
        if not coins:
            return 0

        self.memo, self.coins = dict(), coins

        val_sum = coins[0]
        while val_sum <= amount:
            self.memo[(0, val_sum)] = 1
            val_sum += coins[0]
        # for a in xrange(amount + 1):
        #     self.memo[(0, a)] = int(not (a % coins[0]))

        return self.memo_search(len(coins) - 1, amount)

    def memo_search(self, max_coin_idx, amount):
        if (max_coin_idx, amount) in self.memo:
            return self.memo[(max_coin_idx, amount)]
        elif max_coin_idx == 0:
            return int(not (amount % self.coins[0]))
        else:
            result = sum(self.memo_search(max_coin_idx - 1, amount - k * self.coins[max_coin_idx])
                         for k in xrange(1 + (amount // self.coins[max_coin_idx])))
            self.memo[(max_coin_idx, amount)] = result
            return result
