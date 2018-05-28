from abc import ABCMeta, abstractmethod


class MaximumXOR:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_max_xor_in_two_numbers_in_array(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class MaximumXORImpl(MaximumXOR):
    def find_max_xor_in_two_numbers_in_array(self, nums):
        trie = init_binary_trie(nums)
        return get_max_xor_from_trie(trie, nums)


class BinaryTrie:
    def __init__(self):
        self.children = [None] * 2


def init_binary_trie(nums):
    root = BinaryTrie()
    for num in nums:
        node = root
        for power_to_two in xrange(31, -1, -1):
            cur_bit = (num >> power_to_two) & 1
            if not node.children[cur_bit]:
                node.children[cur_bit] = BinaryTrie()
            node = node.children[cur_bit]
    return root


def get_max_xor_from_trie(root, nums):
    max_xor = 0
    for num in nums:
        cur_xor = 0
        node = root
        for power_to_two in xrange(31, -1, -1):
            cur_bit = (num >> power_to_two) & 1
            cur_bit_sum = (1 << power_to_two)
            if node.children[cur_bit ^ 1]:
                cur_xor += cur_bit_sum
                node = node.children[cur_bit ^ 1]
            else:
                node = node.children[cur_bit]

            rest_max_possible_add = cur_bit_sum - 1
            if cur_xor + rest_max_possible_add <= max_xor:
                break
        max_xor = max(max_xor, cur_xor)
    return max_xor


if __name__ == "__main__":
    test_case = [3, 10, 5, 25, 2, 8]
    print test_case
    print MaximumXORImpl().find_max_xor_in_two_numbers_in_array(test_case)


