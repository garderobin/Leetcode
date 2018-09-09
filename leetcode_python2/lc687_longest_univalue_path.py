from abc import ABCMeta, abstractmethod
from collections import deque


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
    TODO: Wrong
    """
    def longest_univalue_path(self, root):
        return self.dfs(root, None, 0, 0)[1]

    def dfs(self, root, parent, cur_len, max_len):
        if not root:
            return cur_len, max_len

        if parent and parent.val == root.val:
            cur_len += 1
            max_len = max(cur_len, max_len)
        else:
            cur_len = 0

        new_len = cur_len
        left_len, max_len = self.dfs(root.left, root, new_len, max_len)
        right_len, max_len = self.dfs(root.right, root, new_len, max_len)
        if root.left and root.right and root.val == root.left.val == root.right.val:
            max_len = max(max_len, left_len + right_len - cur_len - cur_len)
        return max(cur_len, left_len, right_len), max_len
