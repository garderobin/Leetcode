# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class IsomorphicStrings:
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_isomorphic(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """


class IsomorphicStringsImpl(IsomorphicStrings):
    """
    Time: O(N)
    Space: O(N)
    """
    def is_isomorphic(self, s, t):
        if not s and not t:
            return True

        n = len(s)
        s_last_occur, t_last_occur = defaultdict(int), defaultdict(int)
        for i in xrange(n):
            if s_last_occur[s[i]] != t_last_occur[t[i]]:
                return False
            s_last_occur[s[i]] = i+1  # 切记在用defaultdict的时候不能用i，这时候分不清楚等于零到底是是不是表示没出现过
            t_last_occur[t[i]] = i+1
        return True
