from abc import ABCMeta, abstractmethod


class DecodeWays:
    __metaclass__ = ABCMeta

    @abstractmethod
    def num_decodings(self, s):
        """
        :type s: str
        :rtype: int
        """


class DecodeWaysImpl(DecodeWays):
    def num_decodings(self, s):
        p1, p2 = 1 if s[0] != '0' else 0, 1
        for i in xrange(1, len(s)):
            cur = p1 if s[i] != '0' else 0
            cur += p2 if 27 > int(s[i-1:i+1]) > 9 else 0
            p1, p2 = cur, p1
        return p1
