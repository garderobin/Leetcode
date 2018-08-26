from abc import ABCMeta, abstractmethod


class LongestSubstringWithoutRepeatingCharacters:
    __metaclass__ = ABCMeta

    @abstractmethod
    def length_of_longest_substring(self, s):
        """
        :type s: str
        :rtype: int
        """


class LongestSubstringWithoutRepeatingCharactersImpl(LongestSubstringWithoutRepeatingCharacters):
    """
    Time: O(N)
    Space: O(N)
    """
    def length_of_longest_substring(self, s):
        n, j, res = len(s), 0, 0
        window_chars_set = set([])
        for i, char in enumerate(s):
            while j < n and not s[j] in window_chars_set:
                window_chars_set.add(s[j])
                j += 1

            res = max(res, j - i)

            window_chars_set.remove(char)
        return res
