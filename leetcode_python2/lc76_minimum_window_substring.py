# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class MinimumWindowSubstring:
    __metaclass__ = ABCMeta

    @abstractmethod
    def min_window(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """


class MinimumWindowSubstringImpl(MinimumWindowSubstring):
    """
    比较这一题和lc438: find_all_anagrams_in_a_string 区别
    """
    def min_window(self, s, t):
        if (not s and t) or len(s) < len(t):
            return ''

        delta_counts = defaultdict(int)
        window_missing_sum = len(t)
        for c in t:
            delta_counts[c] -= 1
        n, j, result_window_start_index, result_window_length = len(s), 0, -1, len(s) + 1
        for i, cs in enumerate(s):
            while j < n and window_missing_sum > 0:
                if s[j] in delta_counts:
                    window_missing_sum -= (delta_counts[s[j]] < 0)
                    delta_counts[s[j]] += 1
                j += 1

            if window_missing_sum == 0:
                cur_window_length = j - i
                if 0 < cur_window_length < result_window_length:
                    result_window_length = cur_window_length
                    result_window_start_index = i

            if cs in delta_counts:
                window_missing_sum += (delta_counts[cs] == 0)  # 这个边界条件非常容易错！
                delta_counts[cs] -= 1
        if result_window_start_index < 0:  # 为什么这里不用result_window_length, 因为result_window_length起始值不是负！
            return ''  # 每次写条件返回语句的时候都要和初始化语句对照起来看！不然很容易出错！
        else:
            return s[result_window_start_index:(result_window_start_index + result_window_length)]


if __name__ == "__main__":
    sol = MinimumWindowSubstringImpl()
    # s = "ADOBECODEBANC"
    # t = "ABC"
    s = 'a'
    t = 'b'
    print sol.min_window(s, t)
