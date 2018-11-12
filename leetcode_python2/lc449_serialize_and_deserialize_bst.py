# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque
from data_structures.binary_tree import TreeNode


class Codec:
    __metaclass__ = ABCMeta

    @abstractmethod
    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """

    @abstractmethod
    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """


class CodecImplBFS(Codec):
    """
    易错点：随时注意当前node是否为None的检查
    """
    def serialize(self, root):
        if not root:
            return 'N'
        str_list = []
        q = deque([root])
        while q:
            node = q.popleft()
            str_list.append(str(node.val) if node else 'N')     # 这里str()必不可少
            str_list.append(',')
            if node:
                q.append(node.left)
                q.append(node.right)
        str_list.pop()
        return ''.join(str_list)

    def deserialize(self, data):
        if data == 'N':
            return None
        nodes = [None if node_str == 'N' else TreeNode(int(node_str)) for node_str in data.split(',')]
        kids = nodes[::-1]
        root = kids.pop()
        for node in nodes:
            if node:
                if kids:
                    node.left = kids.pop()
                if kids:
                    node.right = kids.pop()
        return root
