# coding=utf-8
from abc import ABCMeta, abstractmethod


class LongestUnivaluePath:
    __metaclass__ = ABCMeta

    @abstractmethod
    def longest_univalue_path(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """


class LongestUnivaluePathImplDFS(LongestUnivaluePath):
    """
    TODO: 这种类型是我的弱点，需要经常复习
    """
    def __init__(self):
        self.result = 0

    def longest_univalue_path(self, root):
        if not root:
            return 0
        self.dfs(root)
        return self.result

    def dfs(self, root):    # 如果这里需要传参，要传得参数必须是只有一位的数组，不能直接传一个int var，那样传不了引用
        if (not root.left) and (not root.right):
            return 0

        left_to_leaf = self.dfs(root.left) if root.left else 0
        right_to_leaf = self.dfs(root.right) if root.right else 0

        here_to_leaf_through_left = left_to_leaf + 1 if root.left and root.left.val == root.val else 0
        here_to_leaf_through_right = right_to_leaf + 1 if root.right and root.right.val == root.val else 0

        self.result = max(self.result, here_to_leaf_through_left + here_to_leaf_through_right)
        return max(here_to_leaf_through_left, here_to_leaf_through_right)
