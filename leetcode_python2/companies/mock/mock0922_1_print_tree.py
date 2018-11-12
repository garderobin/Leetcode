from abc import ABCMeta, abstractmethod


class PrintTree:
    __metaclass__ = ABCMeta

    @abstractmethod
    def tree_str(self, root):
        """
        :param root: Node
        :return: string
        """


class Node(object):
    def __init__(self, val='', sons=None):
        self.val = val
        self.sons = sons if sons else []


class PrintTreeImplDFS(PrintTree):
    def __init__(self):
        self.str_builder_list = []

    def tree_str(self, root):
        self.str_builder_list = []
        self.dfs(root, 0)
        return ''.join(self.str_builder_list)

    def dfs(self, node, depth):
        for _ in xrange(depth):
            self.str_builder_list.append('\t')

        self.str_builder_list.append(node.val)
        for son in node.sons:
            self.str_builder_list.append('\n')
            self.dfs(son, depth + 1)


if __name__ == '__main__':
    nodes = [Node(str(x)) for x in xrange(10)]
    nodes[0].sons = [nodes[1], nodes[2]]
    nodes[1].sons = [nodes[3], nodes[4]]
    nodes[2].sons = [nodes[8]]
    nodes[3].sons = [nodes[5], nodes[6], nodes[7]]
    nodes[8].sons = [nodes[9]]
    print PrintTreeImplDFS().tree_str(nodes[0])
