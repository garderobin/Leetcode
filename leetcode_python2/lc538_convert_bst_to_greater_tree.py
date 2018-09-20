# coding=utf-8
class ConvertBSTToGreaterTree(object):
    def convert_bst(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        self.reverse_in_order_traversal(0, root)
        return root

    def reverse_in_order_traversal(self, cur_sum, root):
        """
        Traverse order: right - root - left
        """
        if not root:
            return cur_sum
        cur_sum = self.reverse_in_order_traversal(cur_sum, root.right)  # 一定要有return，否则就要把cur_sum变成全局变量
        cur_sum += root.val
        root.val = cur_sum
        cur_sum = self.reverse_in_order_traversal(cur_sum, root.left)
        return cur_sum
