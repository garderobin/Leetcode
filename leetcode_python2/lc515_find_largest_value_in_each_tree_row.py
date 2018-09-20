# coding=utf-8
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from abc import ABCMeta, abstractmethod


class FindLargestValueInEachTreeRow:
    __metaclass__ = ABCMeta

    @abstractmethod
    def largest_values(self, root):
        """
        :param root:
        :return:
        """


class FindLargestValueInEachTreeRowImplDFS2(FindLargestValueInEachTreeRow):
    """
    要习惯用剪枝，哪怕对复杂度没有帮助，也可以减少递归深度，节约时间和空间
    Time: O(N)
    Space: O(N)
    """
    def __init__(self):
        self.row_max = []

    def largest_values(self, root):
        """
        :param root:
        :return:
        """
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        self.row_max = []
        if not root:    # 使用剪枝的话对root是否为空必须进行检查
            return []
        self.dfs(root, 0)
        return self.row_max

    def dfs(self, root, depth):
        if depth < len(self.row_max):
            self.row_max[depth] = max(self.row_max[depth], root.val)
        else:
            self.row_max.append(root.val)
        if root.left:   # 剪枝可以减少递归深度同时加快速度
            self.dfs(root.left, depth + 1)
        if root.right:
            self.dfs(root.right, depth + 1)


class FindLargestValueInEachTreeRowImplDFS(FindLargestValueInEachTreeRow):
    """
    Time: O(N)
    Space: O(N)
    """
    def __init__(self):
        self.row_max = []

    def largest_values(self, root):
        """
        :param root:
        :return:
        """
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        self.row_max = []
        self.dfs(root, 0)
        return self.row_max

    def dfs(self, root, depth):
        if not root:
            return
        if depth < len(self.row_max):
            self.row_max[depth] = max(self.row_max[depth], root.val)
        else:
            self.row_max.append(root.val)
        self.dfs(root.left, depth + 1)
        self.dfs(root.right, depth + 1)
