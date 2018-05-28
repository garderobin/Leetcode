from abc import ABCMeta, abstractmethod


class FindAnagramMappings:
    __metaclass__ = ABCMeta

    @abstractmethod
    def anagram_mappings(self, A, B):
        raise NotImplementedError


class Solution1(FindAnagramMappings):

    # Temp array solution. Only works when list values have fixed range.
    # Time complexity: O(N)
    # Space complexity: 10^5 (the value arrange of A)
    def anagram_mappings(self, A, B):
        """
        :type A: List[int]
        :type B: List[int]
        :rtype: List[int]
        """
        value_index_mapping_list_of_b = [None] * 10001
        anagram_mapping_list = [None] * len(A)
        for b_index, b_value in enumerate(B):
            value_index_mapping_list_of_b[b_value] = b_index

        for a_index, a_value in enumerate(A):
            anagram_mapping_list[a_index] = value_index_mapping_list_of_b[a_value]

        return anagram_mapping_list


class Solution2(FindAnagramMappings):

    # Dict solution. ==> Java Hash table
    # Time complexity: O(N)
    # Space complexity: O(N)
    def anagram_mappings(self, A, B):
        """
        :type A: List[int]
        :type B: List[int]
        :rtype: List[int]
        """
        value_index_mapping_dict_of_b = {}
        anagram_mapping_list = []
        for b_index, b_value in enumerate(B):
            value_index_mapping_dict_of_b[b_value] = b_index

        for a_index, a_value in enumerate(A):
            anagram_mapping_list.append(value_index_mapping_dict_of_b[a_value])

        return anagram_mapping_list





