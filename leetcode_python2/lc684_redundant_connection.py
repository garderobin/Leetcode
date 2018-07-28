from abc import ABCMeta, abstractmethod


class RedundantConnection:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_redundant_connection(self, edges):
        """
        :type edges: List[List[int]]
        :rtype: List[int]
        """


class RedundantConnectionImplWeightedQuickUnion(RedundantConnection):
    """
    Union Find: https://blog.csdn.net/dm_vincent/article/details/7655764
    Time: O(E) (amortized)
    Space: O(E)
    """
    def find_redundant_connection(self, edges):
        n = len(edges)
        parents = [x for x in xrange(n + 1)]
        weights = [1 for _ in xrange(n + 1)]

        for e in edges:
            p, q = self.find_tree(e[0], parents), self.find_tree(e[1], parents)
            if p == q:
                return e
            else:
                (small_tree, large_tree) = (q, p) if weights[q] < weights[p] else (p, q)
                parents[small_tree] = large_tree
                weights[large_tree] += weights[small_tree]
        return edges[-1]

    def find_tree(self, node, parents):
        cur_node = node
        while cur_node != parents[cur_node]:
            parents[cur_node] = parents[parents[cur_node]]
            cur_node = parents[cur_node]
        return cur_node
