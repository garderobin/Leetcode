from abc import ABCMeta, abstractmethod


class FindTheDifference:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_difference(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """


class FineTheDifferenceImpl1(FindTheDifference):

    # Time: O(N)
    # Space: O(N)
    def find_difference(self, s, t):
        char_occurrence_counts = {}

        for c in s:
            if c in char_occurrence_counts:
                char_occurrence_counts[c] += 1
            else:
                char_occurrence_counts[c] = 1

        for c in t:
            if c in char_occurrence_counts:
                char_occurrence_counts[c] -= 1
                if char_occurrence_counts[c] == 0:
                    char_occurrence_counts.pop(c)
            else:
                return c

        return char_occurrence_counts.iterkeys()[0]
