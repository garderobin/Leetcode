# coding=utf-8
from abc import ABCMeta, abstractmethod


class AllNodesDistanceKInBinaryTree:
    __metaclass__ = ABCMeta

    @abstractmethod
    def distance_k(self, root, target, K):
        """
        :type root: TreeNode
        :type target: TreeNode
        :type K: int
        :rtype: List[int]
        """


class AllNodesDistanceKInBinaryTreeImpl(AllNodesDistanceKInBinaryTree):
    def __init__(self):
        self.results = []
        self.visited = set([])
        self.parents = {}   # 使用parent dict结合visited set是一个很自然的想法

    def distance_k(self, root, target, K):
        """
        :type root: TreeNode
        :type target: TreeNode
        :type K: int
        :rtype: List[int]
        """
        if not root:
            return []
        self.mark_parents(root, target)

        self.search_results(target, K)
        return self.results

    def mark_parents(self, root, target):
        if root.val == target.val:
            return True

        if root.left:
            self.parents[root.left.val] = root
            if self.mark_parents(root.left, target):
                return True

        if root.right:
            self.parents[root.right.val] = root
            if self.mark_parents(root.right, target):
                return True

        return False

    def search_results(self, node, unresolved_distance):
        if node.val in self.visited:
            return

        self.visited.add(node.val)

        if unresolved_distance == 0:
            self.results.append(node.val)
            return

        if node.val in self.parents:
            self.search_results(self.parents[node.val], unresolved_distance - 1)

        if node.left:
            self.search_results(node.left, unresolved_distance - 1)

        if node.right:
            self.search_results(node.right, unresolved_distance - 1)
