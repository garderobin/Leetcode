# coding=utf-8
from abc import ABCMeta, abstractmethod


class LongestWordInDictionaryThroughDeleting:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_longest_word(self, s, d):
        """
        :type s: str
        :type d: List[str]
        :rtype: str
        """


class LongestWordInDictionaryThroughDeletingImpl2(LongestWordInDictionaryThroughDeleting):
    def find_longest_word(self, s, d):
        if not d or not s:
            return ''

        words = sorted(d, key=lambda w: (-len(w), w))
        if words[-1] and (-len(s), s) > (-len(words[-1]), words[-1]):
            return ''
        for word in words:
            if self.match_through_delete(s, word):
                return word     # 已经把所有可能结果从大到小排过序了，这里直接返回毫无压力，千万别在继续了

        return ''

    def match_through_delete(self, s, word):
        i = 0
        for cs in s:
            if cs == word[i]:
                i += 1
                if i == len(word):
                    return True
        return False


class LongestWordInDictionaryThroughDeletingImpl(LongestWordInDictionaryThroughDeleting):
    def find_longest_word(self, s, d):
        if not d or not s:
            return ''

        words = sorted(d, key=lambda w: (-len(w), w))

        if words[-1] and (-len(s), s) > (-len(words[-1]), words[-1]):
            return ''

        for word in words:
            if self.match_through_delete(s, word):
                return word     # 已经把所有可能结果从大到小排过序了，这里直接返回毫无压力，千万别在继续了

        return ''

    def match_through_delete(self, s, word):
        j = 0
        for i, c in enumerate(word):
            while j < len(s) and s[j] != c:
                j += 1

            if j == len(s):
                return False

            j += 1
        return True


