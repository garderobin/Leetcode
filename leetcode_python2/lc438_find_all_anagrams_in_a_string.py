# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class FindAllAnagramsInAString:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_anagrams(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: List[int]
        """


class FindAllAnagramsInAStringImplSlidingWindow(FindAllAnagramsInAString):
    """
    易错点1：window_abs_sum是该+1还是-1这里很容易出错；
    易错点2：
    """
    def find_anagrams(self, s, p):
        if (not s and p) or len(s) < len(p):
            return []

        all_anagrams_starts = []
        delta_counts, window_abs_sum = defaultdict(int), 0
        for i, cp in enumerate(p):
            delta_counts[cp] -= 1
            window_abs_sum += 1

        for i, cs in enumerate(s):
            if i >= len(p):
                left_out = s[i - len(p)]
                if left_out in delta_counts:
                    delta_counts[left_out] -= 1
                    window_abs_sum -= 1 if delta_counts[left_out] >= 0 else -1
            if cs in delta_counts:
                delta_counts[cs] += 1
                window_abs_sum += 1 if delta_counts[cs] > 0 else -1
                if i > len(p) - 2 and window_abs_sum == 0:
                    all_anagrams_starts.append(i - len(p) + 1)
            # print i, cs, delta_counts, window_abs_sum
        return all_anagrams_starts


