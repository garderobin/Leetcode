# coding=utf-8
from abc import ABCMeta, abstractmethod


class ValidPermutationOfDISequence:
    __metaclass__ = ABCMeta

    @abstractmethod
    def num_perms_di_sequence(self, S):
        """
        :type S: str
        :rtype: int
        """


class ValidPermutationOfDISequenceImplDP(ValidPermutationOfDISequence):
    """
    https://leetcode.com/problems/valid-permutations-for-di-sequence/discuss/168278/C++JavaPython-DP-Solution-O(N2)
    TODO: 没看懂 需要再实现一遍 弄清原理
    """
    def num_perms_di_sequence(self, S):
        dp = [1] * (len(S) + 1)
        for c in S:
            if c == "I":
                dp = dp[:-1]
                for i in xrange(1, len(dp)):
                    dp[i] += dp[i - 1]
            else:
                dp = dp[1:]
                for i in xrange(len(dp) - 2, -1, -1):
                    dp[i] += dp[i + 1]
        return dp[0] % 1000000007
