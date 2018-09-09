# coding=utf-8
from abc import ABCMeta, abstractmethod


class PathSum:
    __metaclass__ = ABCMeta

    @abstractmethod
    def has_path_sum(self, root, target):
        """
        :type root: TreeNode
        :type target: int
        :rtype: bool
        """


class PathSumImpl2(PathSum):
    """
    参数少，调自己，非常快
    """
    def has_path_sum(self, root, sum):
        if not root:
            return False
        if (not root.left) and (not root.right):
            return root.val == sum
        else:
            return self.has_path_sum(root.left, sum - root.val) or self.has_path_sum(root.right, sum - root.val)


class PathSumImpl(PathSum):
    def has_path_sum(self, root, target):
        return self.dfs(target, root, 0)

    def dfs(self, target, node, path_sum):
        if not node:
            return False
        new_path_sum = path_sum + node.val
        if (not node.left) and (not node.right):
            return new_path_sum == target
        else:
            return self.dfs(target, node.left, new_path_sum) or self.dfs(target, node.right, new_path_sum)


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None