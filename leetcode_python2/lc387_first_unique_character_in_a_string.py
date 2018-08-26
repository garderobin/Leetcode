from abc import ABCMeta, abstractmethod


class FirstUniqueCharacterInAString:
    __metaclass__ = ABCMeta

    @abstractmethod
    def first_unique_char_index(self, s):
        """
        :type s: str
        :rtype: int
        """


class Solution1(FirstUniqueCharacterInAString):
    @staticmethod
    def get_first_occurrence_index_of_unique_char_dict_for_string(s):
        char_first_occurrence_index_dict = {}
        char_set = set()
        for index, char in enumerate(s):
            if char in char_set:
                char_first_occurrence_index_dict.pop(char, None)
            else:
                char_set.add(char)
                char_first_occurrence_index_dict[char] = index
        return char_first_occurrence_index_dict

    @staticmethod
    def min_value_in_dict(input_dict, default_return_on_empty_dict):
        return min(input_dict.itervalues()) if input_dict else default_return_on_empty_dict

    # Time: O(N)
    # Space: O(N)
    def first_unique_char_index(self, s):
        """
        :type s: str
        :rtype: int
        """
        unique_char_first_occurrence_index_dict = self.get_first_occurrence_index_of_unique_char_dict_for_string(s)
        return self.min_value_in_dict(input_dict=unique_char_first_occurrence_index_dict,
                                      default_return_on_empty_dict=-1)


class Solution2(FirstUniqueCharacterInAString):
    """
    https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86351/Python-3-lines-beats-100-(~-60ms)-!
    """
    def first_unique_char_index(self, s):
        """
        :type s: str
        :rtype: int
        """
        letters = 'abcdefghijklmnopqrstuvwxyz'
        index = [s.index(l) for l in letters if s.count(l) == 1]
        return min(index) if len(index) > 0 else -1


if __name__ == "__main__":
    solution = Solution1()
    print solution.first_unique_char_index('leetcode')
