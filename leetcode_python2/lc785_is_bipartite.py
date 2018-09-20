# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import deque


class IsBipartite:
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_bipartite(self, graph):
        """
        :type graph: List[List[int]]
        :rtype: bool
        """


class IsBipartiteImplBFS(IsBipartite):
    """
    无向图用adjacent list + BFS遍历的时候，没有被遍历过的key点一定与之前所有访问过到所有点不在同一个联通分量里，完全不用担心关系覆盖
    0， -1， 1染色法很好用
    """
    def is_bipartite(self, graph):
        if not graph:
            return True
        color = [0] * len(graph)   # 0: not colored; 1: red part; -1: blue part

        # 无向图用adjacent list + BFS遍历的时候，没有被遍历过的key点一定与之前所有访问过到所有点不在同一个联通分量里
        # 最外层的外界链表数组循环是用来保证每一个独立的联通分量都被照顾到了，而在联通分量内部的遍历使用BFS解决的。
        for i in xrange(len(graph)):
            if color[i]:
                continue
            q = deque([i])
            color[i] = 1    # 随便选个part就好了，不用担心不对，因为用BFS的话这个这个i所在的component一定还没有任何点被处理过
            while q:
                vertex = q.popleft()
                for adj in graph[vertex]:
                    if color[adj] == color[vertex]:
                        return False
                    elif not color[adj]:
                        color[adj] = -color[vertex]
                        q.append(adj)
        return True

