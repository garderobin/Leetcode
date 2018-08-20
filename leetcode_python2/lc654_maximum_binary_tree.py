# coding=utf-8
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from abc import ABCMeta, abstractmethod


class MaximumBinaryTree(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def construct_maximum_binary_tree(self, nums):
        """
        看到区间极值就应该想到单调栈
        :param nums:
        :return: 
        """
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        stack = []  # monotonous desc
        for num in nums:
            node = TreeNode(num)
            while stack and stack[-1].val < num:
                node.left = stack.pop()

            if stack:
                stack[-1].right = node

            stack.append(node)

        return stack[0]


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
