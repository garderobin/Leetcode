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
    """
    def find_redundant_connection(self, edges):
        n = len(edges)
        parents = [x for x in xrange(1, n+1)]
        weights = [1 for _ in xrange(1, n+1)]

        for e in edges:
            p, q = e
            p_root, q_root = self.find_tree(p, parents), self.find_tree(q, parents)
            if p_root == q_root:
                return e
            else:
                (small_tree, large_tree) = (q_root, p_root) if weights[q_root] < weights[p_root] else (p_root, q_root)
                parents[small_tree] = large_tree
                weights[large_tree] += weights[small_tree]
        return edges[-1]

    def find_tree(self, node, parents):
        cur_node = node
        while cur_node != parents[cur_node]:
            parents[cur_node] = parents[parents[cur_node]]
            cur_node = parents[cur_node]
        return cur_node
