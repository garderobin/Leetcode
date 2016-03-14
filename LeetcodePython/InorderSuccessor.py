class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class InorderSuccessor:

    def inorderSuccessor(self, root, p):
        suc = None
        while root:
            if root.val > p.val:
                suc = root
                root = root.left
            else:
                root = root.right

        return suc
