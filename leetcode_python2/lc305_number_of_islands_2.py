# coding=utf-8
from abc import ABCMeta, abstractmethod


class NumberOfIslands2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def num_of_islands_2(self, m, n, positions):
        """
        :type m: int
        :type n: int
        :type positions: List[List[int]]
        :rtype: List[int]
        """


# https://www.lintcode.com/submission/15648143/
class NumberOfIslands2ImplUnionFind(NumberOfIslands2):
    """
    FIXME
    有bug, 过不了这个test case:
3
3
[[0,0],[0,1],[2,2],[2,2]]
    """
    def num_of_islands_2(self, m, n, positions):
        union_find = UnionFindIsland2(n)
        land_set = set([])
        number_of_islands = []

        for position in positions:
            self.add_land(m, n, land_set, union_find, position)
            number_of_islands.append(union_find.component_size)

        return number_of_islands

    def add_land(self, m, n, land_set, union_find, position):
        x, y = position[0], position[1]
        land_set.add((x, y))
        union_find.add_component(x, y)
        for delta_x, delta_y in UnionFindIsland.DIRECTIONS:
            x_, y_ = x + delta_x, y + delta_y
            if self.inside(m, n, x_, y_) and (x_, y_) in land_set:
                union_find.union(x, y, x_, y_)

    def inside(self, m, n, x, y):
        return 0 <= x < m and 0 <= y < n


class UnionFindIsland2(object):
    DIRECTIONS = [(1, 0), (0, -1), (-1, 0), (0, 1)]

    def __init__(self, n):
        self.n = n
        self.father = dict()
        self.size = 0

    @property
    def component_size(self):
        return self.size

    def add_component(self, x, y):
        node = self.get_flat_index(x, y)
        self.father[node] = node
        self.size += 1

    def union(self, x1, y1, x2, y2):
        component_1, component_2 = self.find(x1, y1), self.find(x2, y2)
        if component_1 != component_2:
            self.father[component_1] = self.father[component_2]
            self.size -= 1

    def find(self, x, y):
        path = []
        node = self.get_flat_index(x, y)
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        return node

    def get_flat_index(self, x, y):
        return x * self.n + y


class NumberOfIslands2ImplUnionFindWithSet(NumberOfIslands2):

    def num_of_islands_2(self, m, n, positions):
        union_find = UnionFindIsland(n)
        land_set = set([])
        number_of_islands = []

        for position in positions:
            self.add_land(m, n, land_set, union_find, position)
            number_of_islands.append(union_find.component_size)

        return number_of_islands

    def add_land(self, m, n, land_set, union_find, position):
        x, y = position[0], position[1]
        land_set.add((x, y))
        for delta_x, delta_y in UnionFindIsland.DIRECTIONS:
            x_, y_ = x + delta_x, y + delta_y
            if self.inside(m, n, x_, y_) and (x_, y_) in land_set:
                union_find.union(x_, y_, x, y)

    def inside(self, m, n, x, y):
        return 0 <= x < m and 0 <= y < n


class UnionFindIsland(object):
    DIRECTIONS = [(0, 0), (1, 0), (0, -1), (-1, 0), (0, 1)]

    def __init__(self, n):
        self.n = n
        self.father = dict()
        self.components = set()

    @property
    def component_size(self):
        return len(self.components)

    def union(self, x1, y1, x2, y2):
        component_1, component_2 = self.find(x1, y1), self.find(x2, y2)
        # 这里不能再加if component_1 != component_2 的剪枝了，因为初始的components为空
        self.father[component_1] = self.father[component_2]
        if component_1 in self.components:
            self.components.remove(component_1)  # 如果key不存在会报keyError
        self.components.add(component_2)

    def find(self, x, y):
        path = []
        node = self.get_flat_index(x, y)
        while node != self.get_father(node):
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        return node

    def get_father(self, node):
        self.father[node] = self.father.get(node, node)
        return self.father[node]

    def get_flat_index(self, x, y):
        return x * self.n + y
