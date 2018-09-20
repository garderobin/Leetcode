# coding=utf-8
from abc import ABCMeta, abstractmethod


class DiameterOfBinaryTree:
    __metaclass__ = ABCMeta

    @abstractmethod
    def diameter_of_binary_tree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """


class DiameterOfBinaryTreeImpl(DiameterOfBinaryTree):
    def diameter_of_binary_tree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        return self.dfs(root, 0)[1]

    def dfs(self, node, global_diameter):
        if not node:
            return 0, global_diameter
        hl, dl = self.dfs(node.left, global_diameter)
        hr, dr = self.dfs(node.right, global_diameter)
        return 1 + max(hl, hr), max(global_diameter, hl + hr, dl, dr)   # 这么写千万别忘了带上dl, dr


class DiameterOfBinaryTreeDepth(DiameterOfBinaryTree):
    def __init__(self):
        self.diameter = 0

    def diameter_of_binary_tree(self, root):
        self.diameter = 0
        self.depth(root)
        return self.diameter

    def depth(self, node):
        if not node:
            return 0
        depth_left, depth_right = self.depth(node.left), self.depth(node.right)
        self.diameter = max(self.diameter, depth_left + depth_right)
        return max(depth_left, depth_right)


class DiameterOfBinaryTreeNaive(DiameterOfBinaryTree):
    def diameter_of_binary_tree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        return self.dfs(root, 0, 0)[1]

    def dfs(self, node, height, global_diameter):
        if not node:
            return height - 1, global_diameter
        hl, dl = self.dfs(node.left, height + 1, global_diameter)
        hr, dr = self.dfs(node.right, height + 1, global_diameter)
        return max(hl, hr), max(global_diameter, hl + hr - height - height, dl, dr)
