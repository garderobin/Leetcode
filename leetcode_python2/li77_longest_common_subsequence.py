# coding=utf-8
from abc import ABCMeta, abstractmethod


class LongestCommonSubsequence:
    __metaclass__ = ABCMeta

    @abstractmethod
    def lcs(self, A, B):
        """
        @param A: A string
        @param B: A string
        @return: The length of longest common subsequence of A and B
        """


class LongestCommonSubsequenceImplDP(LongestCommonSubsequence):
    """
    最容易出错的是(m+1), 因为能够在B数组停留的位置总共有m+1而不是m种，完全不取也算数（是0， 可以用在迭代后续计算）
    之所以i的遍历可以走(1, n+1)也可以走(0, n)是因为两个数组谁都一个位置也不取的这种完全启示状态没有必要考虑
    因此从1开始遍历和从零开始遍历这种区别无非就是奇偶顺序在滚动数组中的区别，不影响大局
    只不过从零开始遍历要多写一句f[i % 2][0] = 0
    """
    def lcs(self, A, B):
        if not A or not B:
            return 0
        n, m = len(A), len(B)
        f = [[0] * (m + 1), [0] * (m + 1)]  # f[i%2][j] = the lcs of A[:i+1] and B[:j]
        for i in xrange(n):
            for j in xrange(1, m + 1):
                if A[i] == B[j - 1]:
                    f[i % 2][j] = 1 + f[(i - 1) % 2][j - 1]
                else:
                    f[i % 2][j] = max(f[(i - 1) % 2][j], f[i % 2][j - 1])
        return f[(n - 1) % 2][m]
