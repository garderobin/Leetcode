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


class LongestPalindromeSubstringManacher(LongestPalindromeSubstring):
    """
    使用 Manacher's Algorithm
    可以在 O(n) 的时间内解决问题
    参考资料：https://www.felix021.com/blog/read.php?2040
    TODO: 有点难 需要找时间再看
    """
    def longest_palindrome(self, s):
        if not s:
            return

            # Using manacher's algorithm
            # abba => #a#b#b#a#
        chars = []
        for c in s:
            chars.append('#')
            chars.append(c)
        chars.append('#')

        n = len(chars)
        palindrome = [0] * n
        palindrome[0] = 1

        mid, longest = 0, 1
        for i in range(1, n):
            length = 1
            if mid + longest > i:
                mirror = mid - (i - mid)
                length = min(palindrome[mirror], mid + longest - i)

            while i + length < len(chars) and i - length >= 0:
                if chars[i + length] != chars[i - length]:
                    break
                length += 1

            if length > longest:
                longest = length
                mid = i

            palindrome[i] = length

        # remove the extra #
        longest = longest - 1
        start = (mid - 1) // 2 - (longest - 1) // 2
        return s[start:start + longest]


class LongestPalindromeSubstringImplDP(LongestPalindromeSubstring):
    """
    一个很好的复习区间型动态规划的题目，不难，但是很有代表性
    区间型动态规划三种常用写法都要会，根据不同题目的场景选择合适的写法：
    1. 先循环区间长度，再循环起点位置
    2. 起点倒过来循环，终点正过去循环
    3. 记忆化搜索

    记忆化搜索的写法比较稳妥适合大部分题目，但是这题根据回文的特性选1是最容易写的，因为回文跟滑动窗口太适合了

    Time: 1+2+3+...+n-1 = n^2/2 = O(n^2)
    Space: O(n^2)
    并不比中心线枚举时间复杂度要低，相反中心线枚举还更省空间复杂度，所以这个做法并不值得提倡，只是用来复习区间型动态规划
    """
    def longest_palindrome(self, s):
        if not s:
            return ''
        lps_window = (0, 0)
        n = len(s)
        palindrome_windows = set()
        for window_size in xrange(1, n + 1):
            for start in xrange(n + 1 - window_size):
                end = start + window_size - 1
                if s[start] == s[end]:
                    if window_size < 3 or (start + 1, end - 1) in palindrome_windows:
                        palindrome_windows.add((start, end))
                        lps_window = (start, end)
        return s[lps_window[0]:lps_window[1] + 1]

    def longest_palindrome_alternative(self, s):
        if not s:
            return ''
        lps_window = (0, 0)
        n = len(s)
        palindrome_windows = set()
        for delta in xrange(n):                 # 区间型动态规划，即使用滑动窗口法，也不必要用window size， 用delta = window size - 1即可
            for start in xrange(n - delta):
                end = start + delta
                if s[start] == s[end]:
                    if delta < 2 or (start + 1, end - 1) in palindrome_windows:
                        palindrome_windows.add((start, end))
                        lps_window = (start, end)
        return s[lps_window[0]:lps_window[1] + 1]


class LongestPalindromeSubstringImplNaive(LongestPalindromeSubstring):
    """
    暴力解法，中心线枚举
    Time: O(N^2)
    Space: O(1)
    """
    def longest_palindrome(self, s):
        if not s:
            return ""

        longest = ""
        for middle in range(len(s)):
            sub = self.find_palindrome_from(s, middle, middle)
            if len(sub) > len(longest):
                longest = sub
            sub = self.find_palindrome_from(s, middle, middle + 1)
            if len(sub) > len(longest):
                longest = sub

        return longest

    @staticmethod
    def find_palindrome_from(string, left, right):
        while left >= 0 and right < len(string) and string[left] == string[right]:
            left -= 1
            right += 1

        return string[left + 1:right]

