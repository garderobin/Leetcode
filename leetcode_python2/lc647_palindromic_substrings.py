# coding=utf-8
from abc import ABCMeta, abstractmethod


class PalindromicSubstrings:
    __metaclass__ = ABCMeta

    @abstractmethod
    def count_substrings(self, s):
        """
        :type s: str
        :rtype: int
        """


class PalindromicSubstringsImpl2(PalindromicSubstrings):
    """
    实在太优秀：
    把只包含一种字符的回文总数简单计算 m(m+1)/2
    把包含不同字符的回文从不对称的中心向两边扩展。
    实际上利用了回文的本质，以对称中心开始向两边发散, 更快
    Time: O(N^2) 但是因为剪枝，实际上比fixed size sliding window方法要省时
    Space: O(1)
    """
    def count_substrings(self, s):
        ans = 0
        left, right = 0, 0
        while left < len(s):
            # expand max right with same char as left
            while right < len(s) and s[right] == s[left]:
                right += 1
            same_len = right - left
            # add possible palindrom: 1 + 2 + ... + same_len
            ans += (same_len * (same_len + 1)) // 2

            # expand palindrom based on the consecutive chars
            l, r = left - 1, right
            while l >= 0 and r < len(s) and s[r] == s[l]:
                ans += 1
                l -= 1
                r += 1

            # slide to next index after consecutive chars
            left = right
        return ans


class PalindromicSubstringsImplSlidingWindow(PalindromicSubstrings):
    """
    恭喜自己，一遍bug free!
    但是只有粗暴dp, 没有剪枝，不够快
    Time: O(N^2)
    Space: O(N^2)
    """
    def count_substrings(self, s):
        n, res = len(s), len(s)
        is_palindromic = [[False for col in xrange(n)] for row in xrange(n)]
        for window in xrange(1, n):
            for start in xrange(n - window):
                end = start + window
                middle_is_palindromic = (window < 3) or is_palindromic[start + 1][end - 1]
                if s[start] == s[end] and middle_is_palindromic:
                    is_palindromic[start][end] = True
                    res += 1
        return res
