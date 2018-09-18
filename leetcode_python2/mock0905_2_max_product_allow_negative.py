# coding=utf-8
import sys
from abc import ABCMeta, abstractmethod

from data_structures.binary_tree import deserialize
from mock0915_1_sum_of_subarray_sums import TestCase


class MaxPathProduct2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_path_product(self, root):
        """
        Find the max path product of a binary tree whose node values are integers,
        which may be positive, zero or negative.
        :param root: TreeNode
        :return: int
        """


class MaxPathProductImplDFS(MaxPathProduct2):
    """
    Time: O(N)
    Space: O(logN)
    """
    def __init__(self):
        self.result = 1 - sys.maxsize   # change 1: possible output border

    def max_path_product(self, root):
        self.result = 0
        if not root:
            return 0
        self.dfs(root)
        return self.result

    def dfs(self, node):
        if not (node.left or node.right):
            return node.val, node.val

        (max_left_to_leaf, min_left_to_leaf) = self.dfs(node.left) if node.left else (1, 1)  # (1, 1)必须加括号否则有歧义
        (max_right_to_leaf, min_right_to_leaf) = self.dfs(node.right) if node.right else (1, 1)

        result_candidates = (left * node.val * right
                             for left in [1, max_left_to_leaf, min_left_to_leaf]
                             for right in [1, max_right_to_leaf, min_right_to_leaf])
        self.result = max(self.result, max(result_candidates))

        cur_to_leaf_candidates = [node.val * child_result for child_result in
                                  [max_left_to_leaf, min_left_to_leaf, max_right_to_leaf, min_right_to_leaf]]
        return max(cur_to_leaf_candidates), min(cur_to_leaf_candidates)


if __name__ == '__main__':
    test_cases = [
        TestCase(input_data='[null]', expected_output=0),
        TestCase(input_data='[1,2,3,null,null,4,null,null,5]', expected_output=120),
        TestCase(input_data='[1,2,3,null,null,4,null,9,7]', expected_output=252),
        TestCase(input_data='[2,1,3,1,7,9,1,2,null,1,1,null,null,8,8,null,null,null,null,7]', expected_output=2646),
        TestCase(input_data='[1,2,3,null,null,4,null,null,0]', expected_output=24),
        TestCase(input_data='[0,1,3,1,7,9,1,2,null,1,1,null,null,8,8,null,null,null,null,7]', expected_output=216),
        TestCase(input_data='[1,-2,3,null,null,4,null,null,-5]', expected_output=120),
        TestCase(input_data='[1,-2,3,null,null,4,-5]', expected_output=30),
        TestCase(input_data='[-1,-2,3,null,null,4,null,null,-5]', expected_output=60),
        TestCase(input_data='[-2,1,3,1,7,9,1,2,null,1,1,null,null,8,8,null,null,null,null,7]', expected_output=216)
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
