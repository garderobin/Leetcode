# coding=utf-8
from abc import ABCMeta, abstractmethod


class LongestPalindromeSubstring:
    __metaclass__ = ABCMeta

    @abstractmethod
    def longest_palindrome(self, s):
        """
        :type s: str
        :rtype: int
        """

# https://www.jiuzhang.com/solution/longest-palindromic-substring/#tag-highlight-lang-python


class LongestPalindromeSubstringImpl(LongestPalindromeSubstring):
    """
    一个很好的复习区间型动态规划的题目，不难，但是很有代表性
    区间型动态规划三种常用写法都要会，根据不同题目的场景选择合适的写法：
    1. 先循环区间长度，再循环起点位置
    2. 起点倒过来循环，终点正过去循环
    3. 记忆化搜索

    记忆化搜索的写法比较稳妥适合大部分题目，但是这题根据回文的特性选1是最容易写的的，因为回文跟滑动窗口太适合了
    """
    def longest_palindrome(self, s):
        pass