# coding=utf-8
from collections import Counter


from abc import ABCMeta, abstractmethod


class LongestPalindrome:
    __metaclass__ = ABCMeta

    @abstractmethod
    def longest_palindrome(self, s):
        """
        :type s: str
        :rtype: int
        """


class LongestPalindromeImpl3(LongestPalindrome):
    """
    不用辅助数据结构明显加快
    """
    def longest_palindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        counts = {}
        for c in s:
            if c in counts:
                counts[c] += 1
            else:
                counts[c] = 1

        # sum可以处理iterable, 所以这里为了加快一定要用itervalues()不用values()
        part = sum((count // 2) * 2 for count in counts.itervalues())
        return part + (part < len(s))


class LongestPalindromeImpl2(LongestPalindrome):
    """
    尴尬 更慢了
    """
    def longest_palindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        part = sum((count // 2) * 2 for count in Counter(s).itervalues())
        return part + (part < len(s))


class LongestPalindromeImpl1(LongestPalindrome):
    def longest_palindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        pl = 0
        for char, count in Counter(s).items():
            pl += count - (count % 2) * (pl % 2)
        return pl
