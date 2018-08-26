# coding=utf-8
from abc import ABCMeta, abstractmethod


class BinaryTreeUpsideDown:
    __metaclass__ = ABCMeta

    @abstractmethod
    def upside_down_binary_tree(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """


class BinaryTreeUpsideDownImplDFS(BinaryTreeUpsideDown):
    """
    还是没有完全理解
    TODO: 需要对照PPT反复琢磨推演
    """
    def upside_down_binary_tree(self, root):
        if not root or not root.left:
            return root
        cur_left = root.left
        cur_right = root.right
        new_root = self.upside_down_binary_tree(root.left)
        cur_left.left = cur_right
        cur_left.right = root
        root.left = None  # 学会充分利用空值
        root.right = None

        return new_root


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
