# coding=utf-8
import sys
from abc import ABCMeta, abstractmethod


class StoneGame1:
    __metaclass__ = ABCMeta

    @abstractmethod
    def stone_game(self, A):
        """
        :type A: List[int]
        :rtype: int
        """


class StoneGameImpl(StoneGame1):
    """
    四边形不等式证明：https://blog.csdn.net/NOIAu/article/details/72514812
    原答案来自https://www.jiuzhang.com/solution/stone-game/#tag-highlight-lang-python
    Time: O(N^2)
    四边形不等式cut[i][j - 1] <= cut[i][j] <= cut[i + 1][j]
    todo: 还需要深入理解能够适用四边形不等式的条件
    """

    def stone_game(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        if not A:
            return 0
        n = len(A)
        prefix_sum, score, cut = self.init_helper_arrays(A)
        for delta in xrange(2, n):
            for i in xrange(n - delta):
                j = i + delta
                score[i][j] = sys.maxsize
                for mid in xrange(cut[i][j-1], cut[i+1][j] + 1):
                    cur_score = score[i][mid] + score[mid + 1][j] + prefix_sum[j + 1] - prefix_sum[i]
                    if cur_score < score[i][j]:
                        score[i][j] = cur_score
                        cut[i][j] = mid
        return score[0][n-1]

    @staticmethod
    def init_helper_arrays(A):
        """
        :param A: List[int]: the input numbers of stone game
        :return: List[List[int]], List[List[int]], List[List[int]]:
        prefix_sum[i] = sum(A[:i]) if i > 0 else 0
        score[i][j] = min score of merging A[i:j+1]
        cut[i][j] = the left index of the middle point that makes minimum score of merging A[i:j+1]
        """
        n = len(A)

        # prefix_sum[i] = sum(A[:i]) if i > 0 else 0
        cur_sum = 0
        prefix_sum = [0]
        for num in A:
            cur_sum += num
            prefix_sum.append(cur_sum)

        # score[i][j] = min score of merging A[i:j+1]
        score = [[0] * n for _ in xrange(n)]

        # cut[i][j] = the left index of the middle point that makes minimum score of merging A[i:j+1]
        cut = [[0] * n for _ in xrange(n)]

        for i in xrange(n - 1):
            score[i][i + 1] = A[i] + A[i + 1]
            cut[i][i + 1] = i

        return prefix_sum, score, cut


class StoneGame(StoneGame1):
    """
    四边形不等式证明：https://blog.csdn.net/NOIAu/article/details/72514812
    原答案来自https://www.jiuzhang.com/solution/stone-game/#tag-highlight-lang-python
    Time: O(N^2)
    四边形不等式cut[i][j - 1] <= cut[i][j] <= cut[i + 1][j]
    todo: 还需要深入理解能够适用四边形不等式的条件
    """

    def stone_game(self, A):
        """
        :type A: List[int]
        :rtype: int
        """
        if not A:
            return 0
        n = len(A)
        range_sum, score, cut = self.init_helper_arrays(A)
        for delta in xrange(2, n):
            for i in xrange(n - delta):
                j = i + delta
                score[i][j] = sys.maxsize       # 这一句千万不能少，否则初始化的时候应该将score初始化成maxsize
                for mid in xrange(cut[i][j - 1], cut[i + 1][j] + 1):
                    cur_score = score[i][mid] + score[mid + 1][j] + range_sum[i][j]
                    if cur_score < score[i][j]:
                        score[i][j] = cur_score
                        cut[i][j] = mid
        return score[0][n - 1]

    def init_helper_arrays(self, A):
        """
        :param A: List[int]: the input numbers of stone game
        :return: List[List[int]], List[List[int]], List[List[int]]:
        range_sum[i][j] = sum(A[i:j+1]
        score[i][j] = min score of merging A[i:j+1]
        cut[i][j] = the left index of the middle point that makes minimum score of merging A[i:j+1]
        """
        n = len(A)

        # range_sum[i][j] = sum(A[i:j+1]
        range_sum = self.get_range_sum(A)

        # score[i][j] = min score of merging A[i:j+1]
        score = [[0] * n for _ in xrange(n)]

        # cut[i][j] = the left index of the middle point that makes minimum score of merging A[i:j+1]
        cut = [[0] * n for _ in xrange(n)]

        for i in xrange(n - 1):
            score[i][i + 1] = A[i] + A[i + 1]
            cut[i][i + 1] = i

        return range_sum, score, cut

    @staticmethod
    def get_range_sum(A):
        """
        :param A: List[int]: the input numbers of stone game
        :return: List[List[int]]: range_sum[i][j] = sum(A[i:j+1]
        """
        n = len(A)
        range_sum = [[0] * n for _ in xrange(n)]  # è®°ä½è¿ç§äºç»´æ°ç»åå§åï¼
        for i in xrange(n):
            cur_sum = 0
            for j in xrange(i, n):
                cur_sum += A[j]
                range_sum[i][j] = cur_sum
        return range_sum
