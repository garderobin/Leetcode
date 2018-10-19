# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class MaximumLengthOfRepeatedSubarray:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_length(self, A, B):
        """
        :type A: List[int]
        :type B: List[int]
        :rtype: int
        """


class MaximumLengthOfRepeatedSubarrayImplDP2DArray(MaximumLengthOfRepeatedSubarray):
    """
    没有任何优化的匹配型动态规划
    面试的时候老老实实写这个，先不要想别的
    Time: O(MN)
    Space: O(MN)
    """
    def find_length(self, A, B):
        m, n = len(A), len(B)
        f = [[0 for _ in xrange(n + 1)] for _ in xrange(m + 1)]     # 一定记住是m+1, n+1，匹配问题需要往后错一位
        for i in xrange(m):
            for j in xrange(n):
                if A[i] == B[j]:
                    f[i + 1][j + 1] = f[i][j] + 1
        return max(max(row) for row in f)


class MaximumLengthOfRepeatedSubarrayImplDPRotateArray(MaximumLengthOfRepeatedSubarray):
    """
    错的，为什么匹配型动态规划这里用滚动数组是错得？
    example test case：
    [1,0,0,0,1,0,0,1,0,0]
    [0,1,1,1,0,1,1,1,0,0]
    """
    def find_length(self, A, B):
        la, lb = len(A), len(B)
        f = [[0] * (lb + 1), [0] * (lb + 1)]
        res = 0
        for i in xrange(la):
            for j in xrange(lb):
                if A[i] == B[j]:
                    f[(i + 1) % 2][j + 1] = f[i % 2][j] + 1
            res = max(res, max(f[i % 2]))
        return res


class MaximumLengthOfRepeatedSubarrayImplDPDefaultDict(MaximumLengthOfRepeatedSubarray):
    """
    这个方法不要用，
    用defaultdict会TLE!!!
    """
    def find_length(self, A, B):
        na, nb = len(A), len(B)
        f = defaultdict(int)    # f[(i, j)] = max len of common substring ending at A[i - 1] and B[j - 1]
        res = 0
        for i in xrange(na):
            for j in xrange(nb):
                if A[i] == B[j]:
                    f[(i + 1, j + 1)] = f[(i, j)] + 1
                    res = max(res, f[(i + 1, j + 1)])
        return res
