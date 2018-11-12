# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque, defaultdict
from data_structures.binary_tree import TreeNode


class CBTInserter:
    __metaclass__ = ABCMeta

    @abstractmethod
    def insert(self, v):
        """
        :type v: int
        :rtype: int
        """

    @abstractmethod
    def get_root(self):
        """
        :rtype: TreeNode
        """

# Your CBTInserter object will be instantiated and called as such:
# obj = CBTInserter(root)
# param_1 = obj.insert(v)
# param_2 = obj.get_root()


class CBTInserterImplBFS(CBTInserter):

    def __init__(self, root):
        """
        :type root: TreeNode
        """
        # 临时变量的好处是读写确实显著加快
        nodes_by_depths, max_depth, completeness_requirement = defaultdict(list), -1, 1
        q = deque([(0, root)])
        while q:
            depth, node = q.popleft()

            if len(nodes_by_depths[depth]) == 0:
                max_depth += 1
                completeness_requirement <<= int(max_depth > 0)
            nodes_by_depths[depth].append(node)

            if node.left:
                q.append((depth + 1, node.left))
            if node.right:
                q.append((depth + 1, node.right))

        move_on = int(len(nodes_by_depths[max_depth]) == completeness_requirement)

        self.next_insert_depth = max_depth + move_on
        self.completeness_requirement = completeness_requirement << move_on
        self.nodes_by_depths = nodes_by_depths
        self.root = root

    def insert(self, v):
        """
        :type v: int
        :rtype: int
        """
        node = TreeNode(v)
        index_in_this_depth = len(self.nodes_by_depths[self.next_insert_depth])
        self.nodes_by_depths[self.next_insert_depth].append(node)

        parent = self.nodes_by_depths[self.next_insert_depth - 1][index_in_this_depth // 2]
        if parent.left:
            parent.right = node
        else:
            parent.left = node

        if index_in_this_depth + 1 == self.completeness_requirement:
            self.next_insert_depth += 1
            self.completeness_requirement <<= 1

        return parent.val

    def get_root(self):
        """
        :rtype: TreeNode
        """
        return self.root

