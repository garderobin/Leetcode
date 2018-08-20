from collections import defaultdict


class FindLeavesOfBinaryTree(object):
    def find_leaves(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        depth_layers = defaultdict(list)
        self.dfs(depth_layers, root)
        return [leaves for depth, leaves in sorted(depth_layers.items())]

    def dfs(self, depth_layers, root):
        if not root:
            return 0

        cur_depth = 1 + max(self.dfs(depth_layers, root.left), self.dfs(depth_layers, root.right))
        depth_layers[cur_depth].append(root.val)
        return cur_depth


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
