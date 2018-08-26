# coding=utf-8
from abc import ABCMeta, abstractmethod


class EditDistance:
    __metaclass__ = ABCMeta

    @abstractmethod
    def min_distance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        """


class EditDistanceImplDP(EditDistance):

    def min_distance(self, word1, word2):
        if not word1 and not word2:
            return 0
        elif not word1:
            return len(word2)
        elif not word2:
            return len(word1)
        else:
            n, m = len(word1), len(word2)

            f = [[0] * (m + 1), [0] * (m + 1)]  # f[i][j] = min edit distance of word1[:i] and word2[:j]
            for j in xrange(1, m + 1):  # init这一步虽然有些时候可以一步到位，但不是每次都可以，要在心里严格按照DP模板跑一次，看有没有哪步漏了
                f[0][j] = j

            for i in xrange(1, n + 1):
                f[i % 2][0] = i
                for j in xrange(1, m + 1):
                    if word1[i - 1] == word2[j - 1]:
                        f[i % 2][j] = f[(i - 1) % 2][j - 1]
                    else:
                        f[i % 2][j] = 1 + min(f[(i - 1) % 2][j - 1], f[(i - 1) % 2][j], f[(i % 2)][j - 1])
            return f[n % 2][m]