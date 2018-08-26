# coding=utf-8
from abc import ABCMeta, abstractmethod


class GraphValidTree:
    __metaclass__ = ABCMeta

    @abstractmethod
    def valid_tree(self, n, edges):
        """
        :type n: int
        :type edges: List[List[int]]
        :rtype: bool
        """
# Thought 1: Valid Tree: vertexNum-1 = edgeNum && there is only one component.
# Thought 2: BFS start from any node can visit each node exactly once.


class GraphValidTreeImplUnionFind(GraphValidTree):
    def valid_tree(self, n, edges):
        if (not edges and n > 1) or len(edges) != n - 1:  # 当n = 1, edges = []也是合法情况，必须return True
            return False

        father = [x for x in xrange(n)]
        for e in edges:
            self.union(father, e[0], e[1])

        components = set([])
        for v in xrange(n):
            components.add(self.find(father, v))

        return len(components) == 1

    def union(self, father, v1, v2):
        father[self.find(father, v1)] = self.find(father, v2)

    def find(self, father, v):
        path = []
        while father[v] != v:
            path.append(v)
            v = father[v]
        for x in path:
            father[x] = v
        return v
