# coding=utf-8
from abc import ABCMeta, abstractmethod


class NumberOfIslands:
    __metaclass__ = ABCMeta

    @abstractmethod
    def num_of_islands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """


class NumberOfIslandsImplUnionFind(NumberOfIslands):
    def __init__(self):
        self.size = 0
        self.father = {}

    def num_of_islands(self, grid):
        if not grid or not grid[0]:
            return 0

        self.size = 0
        self.father = {}

        islands = set()  # 这一步可以同时有效省掉计算inside和content == '1'
        for row_index, row_content in enumerate(grid):
            for col_index, char in enumerate(row_content):
                if char == '1':
                    point = (row_index, col_index)  # 处理坐标问题的union find 用tuple远胜过自己写数据结构
                    islands.add(point)
                    self.father[point] = point
                    self.size += 1
                    for adj in [(row_index - 1, col_index), (row_index, col_index - 1)]:  # 只需要检查左和上，不需要检查右和下
                        if adj in islands:
                            self.union(point, adj)
        return self.size

    def union(self, point_a, point_b):
        root_a = self.find(point_a)
        root_b = self.find(point_b)
        if root_a != root_b:
            self.father[root_a] = root_b
            self.size -= 1

    def find(self, point):  # 在find过程中没有必要处理size
        path = []
        node = point
        while self.father[node] != node:
            path.append(node)
            node = self.father[node]  # 这一步千万记得是迭代node!, 不是迭代father!不能写反成self.father[node] = node
        for p in path:
            self.father[p] = node
        return node
