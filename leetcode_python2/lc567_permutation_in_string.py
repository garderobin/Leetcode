# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class PermutationInString:
    __metaclass__ = ABCMeta

    @abstractmethod
    def check_inclusion(self, s1, s2):
        """
        :type s1: str
        :type s2: str
        :rtype: bool
        """


class PermutationInStringImplTwoPointersSimplify(PermutationInString):
    """
    这个题能简化的关键在于只有26个lower letters.
    这个解法要背下来，稍更多的空间，但是写起来不容易错
    用defaultdict无理由地只要动左指针就+, 只要动右指针就减
    来自这个java答案
    https://leetcode.com/problems/permutation-in-string/discuss/102642/Java-Solution-Two-pointers
    """
    def check_inclusion(self, s1, s2):
        if not s1 or (not s2) or len(s1) > len(s2):
            return False
        window = defaultdict(int)
        for c in s1:
            window[c] += 1

        len1, len2 = len(s1), len(s2)
        start, count = 0, len1
        for end in xrange(len2):
            if window[s2[end]] > 0:
                count -= 1
            window[s2[end]] -= 1

            while count == 0:
                if end - start + 1 == len1:
                    return True
                if window[s2[start]] == 0:
                    count += 1
                window[s2[start]] += 1
                start += 1

        return False


class PermutationInStringImplTwoPointers(PermutationInString):
    """
    我的出事提交通过的版本，经过了多次的debug, 离one-time bug free差得非常远
    这种写法好处是O(n)时间能s1, s2 charset可取范围极大的情况下依然保持
    其他O(n)的简单写法是利用了只有26个lower letter来把比较dict的步骤做成O(1)来实现
    TODO：搞清楚另外一个参考答案也用Two pointers的方法：
    https://leetcode.com/problems/permutation-in-string/discuss/102642/Java-Solution-Two-pointers
    """
    def check_inclusion(self, s1, s2):
        if not s1 or (not s2) or len(s1) > len(s2):
            return False
        unique_chars_s2 = set(s2)
        window = {}
        for c in s1:
            if c not in unique_chars_s2:
                return False
            window[c] = window.get(c, 0) + 1

        start_index = max(s2.find(s1[0]) - len(s1) + 1, 0)
        i = start_index
        left_limit = len(s2) - len(s1)
        for j in xrange(start_index, len(s2)):
            if s2[j] in window:
                if window[s2[j]] == 0:
                    while s2[i] != s2[j]:
                        window[s2[i]] += 1
                        i += 1
                        if i > left_limit - 1:
                            return False
                    i += 1
                else:
                    if j - i + 1 == len(s1):
                        return True
                    window[s2[j]] -= 1
            else:
                if j > left_limit:
                    return False
                while i < j:
                    if s2[i] in window:
                        window[s2[i]] += 1
                    i += 1
                i = j + 1
        return False

