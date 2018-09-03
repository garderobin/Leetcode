# coding=utf-8
import sys
from abc import ABCMeta, abstractmethod


class StoneGame2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def stone_game_2(self, A):
        """
        :param A: List[int]
        :return: int
        """


class StoneGame2Impl(StoneGame2):
    """
    这题不用上九章看答案了，我做得比答案好。
    """
    def stone_game_2(self, A):
        if not A:
            return 0
        prefix_sum, score, cut = self.init_helper_arrays(A)
        n, m = len(A), len(A) << 1

        for delta in xrange(2, n):
            for i in xrange(n):
                j = i + delta
                for mid in xrange(cut[i][j - 1], cut[i + 1][j] + 1):
                    cur_score = score[i][mid] + score[mid + 1][j] + prefix_sum[j + 1] - prefix_sum[i]
                    if cur_score < score[i][j]:
                        score[i][j] = cur_score
                        cut[i][j] = mid

        return min(score[i][i + n - 1] for i in xrange(n))

    def init_helper_arrays(self, A):
        """
        Return arrays in order of (prefix_sum, score, cut).
        prefix_sum[i] = sum(A[:i]) if i > 0 else 0
        score[i][j] = min score of merging A[i:j+1]
        cut[i][j] = the left index of the middle point that makes minimum score of merging A[i:j+1]
        :param A: List[int]: the input numbers of stone game
        :return: List[int], List[List[int]], List[List[int]]
        """
        # B = A + A, m = len(B)
        n, m = len(A), len(A) << 1

        # prefix_sum[i] = sum(A[:i]) if i > 0 else 0
        prefix_sum = self.get_prefix_sum(A)

        # score[i][j] = min score of merging A[i:j+1]
        score = [[sys.maxint] * m for _ in xrange(m)]

        # cut[i][j] = the left index of the middle point that makes minimum score of merging A[i:j+1]
        cut = [[0] * m for _ in xrange(m)]

        for i in xrange(m - 1):
            score[i][i + 1] = A[i % n] + A[(i + 1) % n]
            cut[i][i + 1] = i
            score[i][i] = 0
        score[m - 1][m - 1] = 0

        return prefix_sum, score, cut

    @staticmethod
    def get_prefix_sum(A):
        """
        prefix_sum[i] = sum((A + A)[:i]) if i > 0 else 0
        :param A: List[int]
        :return: List[int]
        """
        cur_sum = 0
        prefix_sum = [0]
        for cycle in xrange(2):
            for num in A:
                cur_sum += num
                prefix_sum.append(cur_sum)
        return prefix_sum
