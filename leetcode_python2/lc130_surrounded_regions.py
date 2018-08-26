# coding=utf-8
from abc import ABCMeta, abstractmethod


class SurroundedRegions:
    __metaclass__ = ABCMeta

    @abstractmethod
    def solve(self, board):
        """
        :type board: List[List[str]]
        :rtype: void Do not return anything, modify board in-place instead.
        """


class SurroundedRegionsBFS(SurroundedRegions):
    """
    Time: O(NM)，同样是两边遍历board, 但是时间上卓越的地方在于，第一遍fill_water不是全遍历，如果用union find第一遍是要全遍历的
    Space: O(NM), 时间上是O（水的字数）， 因为有inplace要求可以大胆设辅助标记，从而节约了BFS原本要求的visited数组。
    注意python在border初始化上的妙用：引用辅助k来帮忙构造i,j，这样做会带来一些重复，但是不影响最终结果，瑕不掩瑜
    还要学会board[:]的in-place改法
    """
    DIRECTIONS = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    def solve(self, board):
        if not any(board):
            return
        water_mark = 'W'
        self.fill_water(board, water_mark)

        # 下面这里如果写成board = ... 就把board当成了local variable从没真正改过，必须写成board[:]
        board[:] = [['XO'[cell == water_mark] for cell in row] for row in board]

    def fill_water(self, board, mark):
        n, m = len(board), len(board[0])

        def is_water(x, y):
            return 0 <= x < n and 0 <= y < m and board[x][y] == 'O'

        # 放心， max(m, n）只会调用一次
        # 因为'W' != 'O' 所以巧妙避免了visited字典的使用，大大节省空间
        q = [(i, j) for k in xrange(max(m, n)) for (i, j) in [(0, k), (n-1, k), (k, 0), (k, m-1)] if is_water(i, j)]
        while q:
            i, j = q.pop()      # 一般来说BFS使用数组即可，因为是一层一层来，所以顺序无妨，就没有必要用deque
            board[i][j] = mark
            for di, dj in self.DIRECTIONS:
                i_, j_ = (i + di, j + dj)
                if is_water(i_, j_):
                    q.append((i_, j_))
            # q += [(i + di, j + dj) for di, dj in self.DIRECTIONS if is_water(i + di, j + dj)]
            # 能不用list拼接就不用


class SurroundedRegionsUnionFind(SurroundedRegions):
    """
    Time: O(MN)
    Space: O(MN)
    """
    @staticmethod
    def is_border(board, point):
        return point[0] == 0 or point[1] == 0 or point[0] == len(board) - 1 or point[1] == len(board[0]) - 1

    def __init__(self):
        self.father = {}
        self.surrounded = {}

    def solve(self, board):
        if not board or not board[0]:
            return board
        waters = set()
        for i, row_content in enumerate(board):
            for j, cell in enumerate(row_content):
                if cell == 'O':
                    point = (i, j)
                    waters.add(point)
                    self.father[point] = point
                    self.surrounded[point] = not self.is_border(board, point)
                    for adj in [(i-1, j), (i, j-1)]:
                        if adj in waters:
                            self.union(adj, point)

        for i, j in list(waters):
            if self.surrounded[self.find((i, j))]:
                board[i][j] = 'X'

    def union(self, a, b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            surrounded = self.surrounded[root_a] and self.surrounded[root_b]    # 这里是and不是or，逻辑上要自己想清楚！
            self.father[root_a] = root_b
            self.surrounded[root_b] = surrounded

    def find(self, point):
        path = []
        node = point
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        for p in path:
            self.father[p] = node
        return node
