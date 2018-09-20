from abc import ABCMeta, abstractmethod


class InorderSuccessorInBSTImplIteration(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def inorder_successor(self, root, p):
        """
        :type root: TreeNode
        :type p: TreeNode
        :rtype: TreeNode
        """


class InorderSuccessorInBSTImplRecursion(InorderSuccessorInBSTImplIteration):
    def inorder_successor(self, root, p):
        """
        :type root: TreeNode
        :type p: TreeNode
        :rtype: TreeNode
        """
        if not root or not p:
            return None

        if root.val <= p.val:
            return self.inorder_successor(root.right, p)
        else:
            left = self.inorder_successor(root.left, p)
            return left if left else root
