from abc import ABCMeta, abstractmethod


class StoneGame(object):
    """
    四边形不等式证明：https://blog.csdn.net/NOIAu/article/details/72514812
    原答案来自https://www.jiuzhang.com/solution/stone-game/#tag-highlight-lang-python
    TODO 自己打一遍
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def stone_game(self, A):
        """
        :type A: List[int]
        :rtype: bool
        """
        n = len(A)
        if n < 2:
            return 0

        # dp[i][j] => minimum cost merge from i to j
        dp = [[0] * n for _ in range(n)]
        # cut[i][j] => the best middle point that make dp[i][j] has the minimum cost
        cut = [[0] * n for _ in range(n)]
        # range_sum[i][j] => A[i] + A[i + 1] ... + A[j]
        range_sum = self.get_range_sum(A)

        for i in range(n - 1):
            dp[i][i + 1] = A[i] + A[i + 1]
            cut[i][i + 1] = i

        # enumerate the range size first, start point second
        for length in range(3, n + 1):
            for i in range(n - length + 1):
                j = i + length - 1
                dp[i][j] = sys.maxsize
                for mid in range(cut[i][j - 1], cut[i + 1][j] + 1):
                    if dp[i][j] > dp[i][mid] + dp[mid + 1][j] + range_sum[i][j]:
                        dp[i][j] = dp[i][mid] + dp[mid + 1][j] + range_sum[i][j]
                        cut[i][j] = mid

        return dp[0][n - 1]

    def get_range_sum(self, A):
        n = len(A)
        range_sum = [[0] * n for _ in range(len(A))]
        for i in range(n):
            range_sum[i][i] = A[i]
            for j in range(i + 1, n):
                range_sum[i][j] = range_sum[i][j - 1] + A[j]
        return range_sum