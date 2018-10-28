from abc import ABCMeta, abstractmethod
from collections import defaultdict


class FindDuplicateSubtrees:
    __metaclass__ = ABCMeta

    @abstractmethod
    def findDuplicateSubtrees(self, root):
        """
        :type root: TreeNode
        :rtype: List[TreeNode]
        """


class FindDuplicateSubtreesImplDFSPreOrderTraversal(FindDuplicateSubtrees):
    def findDuplicateSubtrees(self, root):
        nodes = defaultdict(list)

        def pre_order_traversal(node):
            if not node:
                return 'null'
            structure = '%s, %s, %s' % (str(node.val), pre_order_traversal(node.left), pre_order_traversal(node.right))
            nodes[structure].append(node)
            return structure

        pre_order_traversal(root)
        return [nodes[key][0] for key in nodes if len(nodes[key]) > 1]
