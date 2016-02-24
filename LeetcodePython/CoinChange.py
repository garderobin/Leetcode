class Solution(object):
    def coinChange(self, coins, amount):
        """
        :type coins: List[int]
        :type amount: int
        :rtype: int
        """
        if amount == 0:
            return 0
        if not coins:
            return -1

        # sort the list
        coins.sort()

        # dynamic programming
        f = [-1] * (amount + 1)
        f[0] = 0
        for i in range(coins[0], amount+1):
            for v in coins:
                if v > i:
                    break
                else:
                    cur = f[i-v] + 1
                    if cur > 0 and (f[i] < 0 or f[i] > cur):
                        f[i] = cur
        return f[amount]


if __name__ == '__main__':
    coins = [2]
    amount = 3
    sol = Solution()
    print(sol.coinChange(coins, amount))
