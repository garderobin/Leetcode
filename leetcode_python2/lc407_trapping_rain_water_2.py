# coding=utf-8
from abc import ABCMeta, abstractmethod
import heapq


class TrappingRainWater2(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def trap_rain_water(self, height_map):
        """
        :type height_map: List[List[int]]
        :rtype: int
        """


class TrappingRainWater2ImplMinHeap(TrappingRainWater2):
    DIRECTIONS = [(1, 0), (0, -1), (-1, 0), (0, 1)]

    def __init__(self):
        self.m = 0  # row count
        self.n = 0  # column count
        self.borders = []
        self.visited = set()

    def trap_rain_water(self, height_map):
        if not height_map or not height_map[0] or len(height_map) < 3 or len(height_map[0]) < 3:
            return 0

        self.initialize(height_map)

        water = 0
        while self.borders:
            height, r, c = heapq.heappop(self.borders)  # breach of wall
            for r_, c_ in self.get_unvisited_neighbours(r, c):
                water += max(0, height - height_map[r_][c_])
                self.add_border([max(height, height_map[r_][c_]), r_, c_])
        return water

    def initialize(self, height_map):
        self.m, self.n = len(height_map), len(height_map[0])
        # 老老实实写for循环, map func太容易出错了。
        # map(self.add_border, [(height_map[r][c], r, c) for r in [0, self.m - 1] for c in xrange(1, self.n - 1)]) #错
        # map(self.add_border, [(height_map[r][c], r, c) for c in [0, self.n - 1] for r in xrange(1, self.m - 1)]) #错
        # map(self.add_border, [(height_map[r][c], r, c) for r in [0, self.m - 1] for c in xrange(self.n)]) #错
        # map(self.add_border, [(height_map[r][c], r, c) for c in [0, self.n - 1] for r in xrange(self.m)]) #错
        for c in xrange(self.n):
            for r in [0, self.m - 1]:
                self.add_border([height_map[r][c], r, c])
        for r in xrange(self.m):
            for c in [0, self.n - 1]:
                self.add_border([height_map[r][c], r, c])

    def add_border(self, border_info_array):
        """
        :param border_info_array: [border_height, row_index, col_index]
        :return: void
        """
        heapq.heappush(self.borders, border_info_array)
        self.visited.add((border_info_array[1], border_info_array[2]))

    def get_unvisited_neighbours(self, r, c):
        return [(dr + r, dc + c) for dr, dc in self.DIRECTIONS
                if self.inside(dr + r, dc + c) and (dr + r, dc + c) not in self.visited]

    def inside(self, r, c):
        return 0 <= r < self.m and 0 <= c < self.n
