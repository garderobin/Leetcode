class Solution(object):
    def __init__(self):
        self.left_leaves_sum = 0

    def sumOfLeftLeaves(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if not root:
            return 0
        self.dfs(root, False)
        return self.left_leaves_sum

    def dfs(self, root, is_left_child):
        if (not root.left) and (not root.right) and is_left_child:
            self.left_leaves_sum += root.val
            return
        if root.left:
            self.dfs(root.left, True)
        if root.right:
            self.dfs(root.right, False)
