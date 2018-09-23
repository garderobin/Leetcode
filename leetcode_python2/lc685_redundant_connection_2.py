# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class RedundantConnection2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def findRedundantDirectedConnection(self, edges):
        """
        :type edges: List[List[int]]
        :rtype: List[int]
        """


class Solution2(RedundantConnection2):
    """
    TODO：没看懂这种解法为什么能加快
    """
    def findRedundantDirectedConnection(self, edges):
        N = len(edges)
        parent = {}
        candidates = []
        for u, v in edges:
            if v in parent:
                candidates.append((parent[v], v))
                candidates.append((u, v))
            else:
                parent[v] = u

        def orbit(node):
            seen = set()
            while node in parent and node not in seen:
                seen.add(node)
                node = parent[node]
            return node, seen

        root = orbit(1)[0]

        if not candidates:
            cycle = orbit(root)[1]
            for u, v in edges:
                if u in cycle and v in cycle:
                    ans = u, v
            return ans

        children = defaultdict(list)
        for v in parent:
            children[parent[v]].append(v)

        seen = [True] + [False] * N
        stack = [root]
        while stack:
            node = stack.pop()
            if not seen[node]:
                seen[node] = True
                stack.extend(children[node])
        # print(candidates)
        return candidates[all(seen)]


class Solution(RedundantConnection2):
    """
    There are two cases for the tree structure to be invalid.
    1) A node having two parents;
        including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
    2) A circle exists;
    3) both (1) and (2) exists: e.g. [[2,1],[3,1],[4,2],[1,4]] -> should remove [2, 1] instead of [3, 1]
    """

    def __init__(self):
        self.father = {}

    def findRedundantDirectedConnection(self, edges):
        """
        :type edges: List[List[int]]
        :rtype: List[int]
        """
        candidate1, candidate2 = self.remove_second_father_edge_if_exists(edges)
        return self.remove_circle_edge_if_exists(edges, candidate1, candidate2)

    def remove_second_father_edge_if_exists(self, edges):
        self.father = {}
        candidate1, candidate2 = None, None
        for i in xrange(len(edges)):
            parent, child = edges[i]
            if child in self.father:
                candidate1 = [self.father[child], child]
                candidate2 = [parent, child]
                edges[i][1] = 0
            else:
                self.father[child] = parent
        return candidate1, candidate2

    def remove_circle_edge_if_exists(self, edges, candidate1, candidate2):
        self.father = {}
        for i in xrange(len(edges) + 1):
            self.father[i] = i
        for parent, child in edges:
            fp, fc = self.find(parent), self.find(child)
            if fp == fc:
                if not candidate1:
                    return [parent, child]
                else:
                    return candidate1
            self.father[fc] = fp
        return candidate2

    def find(self, child):
        path = []
        node = child
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        return node
