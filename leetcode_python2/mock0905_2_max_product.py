from abc import ABCMeta, abstractmethod

from data_structures.binary_tree import deserialize
from mock0915_1_sum_of_subarray_sums import TestCase


class MaxPathProduct:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_path_product(self, root):
        """
        Find the max path product of a binary tree whose node values are all positive integers.
        Follow up 1: what if the tree may contain nodes with 0 value?
        :param root: TreeNode
        :return: int
        """


class MaxPathProductImplDFS(MaxPathProduct):
    """
    Time: O(N)
    Space: O(logN)
    The algorithm works when all the nodes in the tree have non-negative value.
    """
    def __init__(self):
        self.result = 0

    def max_path_product(self, root):
        self.result = 0
        if not root:
            return 0
        self.dfs(root)
        return self.result

    def dfs(self, node):
        if not (node.left or node.right):
            return node.val

        left_to_leaf_max_product = self.dfs(node.left) if node.left else 1
        right_to_leaf_max_product = self.dfs(node.right) if node.right else 1

        self.result = max(self.result, left_to_leaf_max_product * node.val * right_to_leaf_max_product)
        return node.val * max(left_to_leaf_max_product, right_to_leaf_max_product)


if __name__ == '__main__':
    test_cases = [
        TestCase(input_data='[null]', expected_output=0),
        TestCase(input_data='[1,2,3,null,null,4,null,null,5]', expected_output=120),
        TestCase(input_data='[1,2,3,null,null,4,null,9,7]', expected_output=252),
        TestCase(input_data='[2,1,3,1,7,9,1,2,null,1,1,null,null,8,8,null,null,null,null,7]', expected_output=2646)
    ]
    solutions = [
        MaxPathProductImplDFS()
    ]
    for sol in solutions:
        print 'test solution: ', type(sol)
        all_tests_passed = True
        for test in test_cases:
            actual_output = sol.max_path_product(deserialize(test.input_data))
            if actual_output != test.expected_output:
                print 'Fail test case: \n%s\n\tActual Output:%s' % (str(test), actual_output)
                all_tests_passed = False
        if all_tests_passed:
            print '^_^ All test passed for this solution.'
