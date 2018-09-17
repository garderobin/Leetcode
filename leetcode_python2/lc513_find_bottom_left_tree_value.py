# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque


class FindBottomLeftTreeValue:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_bottom_left_value(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """


class FindBottomLeftTreeValueDFS(FindBottomLeftTreeValue):
    """
    DFS在不需要return的情况下比BFS快这么多。。。
    学会多用member少传参吧
    Time: O(N)
    Space # TODO dfs的空间复杂度
    """
    def __init__(self):
        self.max_depth = 0
        self.last_row_left_most_value = 0

    def find_bottom_left_value(self, root):
        self.max_depth = 0
        self.last_row_left_most_value = root.val
        self.dfs(root, 0)
        return self.last_row_left_most_value

    def dfs(self, root, depth):
        if depth > self.max_depth:
            self.max_depth = depth
            self.last_row_left_most_value = root.val
        if root.left:
            self.dfs(root.left, depth + 1)
        if root.right:
            self.dfs(root.right, depth + 1)


class FindBottomLeftTreeValueBFS(FindBottomLeftTreeValue):
    def find_bottom_left_value(self, root):
        q = deque([(root, 0)])
        last_row_left_most = root.val
        cur_max_depth = 0
        while q:
            node, depth = q.popleft()
            if depth > cur_max_depth:
                last_row_left_most = node.val
                cur_max_depth = depth
            if node.left:
                q.append((node.left, depth + 1))
            if node.right:
                q.append((node.right, depth + 1))
        return last_row_left_most
