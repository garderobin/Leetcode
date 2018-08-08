# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class LongestSubstringWithAtMostKDistinctCharacters:
    __metaclass__ = ABCMeta

    @abstractmethod
    def length_of_longest_substring_k_distinct(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """


class LongestSubstringWithAtMostKDistinctCharactersImplStandard2Pointers(LongestSubstringWithAtMostKDistinctCharacters):
    """
    分析的时候longest subarray 和 shortest subarray往往收缩条件不同
    同向双指针有时候左指针也需要loop移动
    """
    def length_of_longest_substring_k_distinct(self, s, k):
        if not s:
            return 0
        counts = defaultdict(int)
        n, j, res = len(s), 0, 0
        for i, c in enumerate(s):
            while j < n and (len(counts) < k or len(counts) == k and s[j] in counts):
                counts[s[j]] += 1
                j += 1

            res = max(res, j - i)
            if c in counts:  # 无条件退位
                counts[c] -= 1
                if counts[c] == 0:
                    counts.pop(c)
        return res


class LongestSubstringWithAtMostKDistinctCharactersImpl(LongestSubstringWithAtMostKDistinctCharacters):
    """
    分析的时候longest subarray 和 shortest subarray往往收缩条件不同
    同向双指针有时候左指针也需要loop移动
    """
    def length_of_longest_substring_k_distinct(self, s, k):
        if not s:
            return 0
        window_distinct_chars = defaultdict(int)
        n, i, j, res = len(s), 0, 0, 0
        while i < n:
            while j < n and len(window_distinct_chars) <= k:
                window_distinct_chars[s[j]] += 1
                j += 1

            if len(window_distinct_chars) > k:  # 根据是否符合substring要求 处理差异非常大，主要在于i进位很容易多进或者少进
                res = max(res, j - i - 1)
                while i < j and len(window_distinct_chars) > k:
                    window_distinct_chars[s[i]] -= 1
                    if window_distinct_chars[s[i]] == 0:
                        window_distinct_chars.pop(s[i])
                    i += 1
            else:
                res = max(res, j - i)
                i += 1
        return res


if __name__ == "__main__":
    sol = LongestSubstringWithAtMostKDistinctCharactersImplStandard2Pointers()
    # s = 'eceba'
    # k = 2
    s = 'bacc'
    k = 2
    print sol.length_of_longest_substring_k_distinct(s, k)
