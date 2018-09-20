# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class KnightProbabilityInChessboard:
    __metaclass__ = ABCMeta

    @abstractmethod
    def knight_probability(self, N, K, r, c):
        """
        :type N: int
        :type K: int
        :type r: int
        :type c: int
        :rtype: float
        """


class KnightProbabilityInChessboardImplDP3(KnightProbabilityInChessboard):
    """
    一道非常典型到把单源多终点问题转换成多源单终点问题，从而可以使用DP
    对于这道题，同样是DP， 正推和逆推时间和复杂度都不变，但倒推的DP好写，不容易出错
    如果原问题是多源单终点不好解决，也可以转换成单源多终点
    总之一看到图论就要想到地点终点倒退到思考方法，往往有突破
    Time: O(KNN)
    Space: O(N^2)
    """
    KNIGHT_DIRS = [(-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 1), (-1, 2), (-2, 1)]

    @staticmethod
    def inside(N, r, c):
        return 0 <= r < N and 0 <= c < N

    def knight_probability(self, N, K, r, c):
        if K == 0:
            return 1.0
        if N < 3:
            return 0.0

        # f[x][y] = num of paths that starts at any position on board that stops at (x, y) on board
        f = [[1] * N for _ in xrange(N)]
        total_num_of_paths = 1
        for _ in xrange(K):
            total_num_of_paths *= 8
            g = [[0] * N for _ in xrange(N)]
            for x in xrange(N):
                for y in xrange(N):
                    for dx, dy in self.KNIGHT_DIRS:
                        x_, y_ = x + dx, y + dy
                        if self.inside(N, x_, y_):
                            g[x][y] += f[x_][y_]
            f = g
        return f[r][c] / total_num_of_paths


class KnightProbabilityInChessboardImplDP2(KnightProbabilityInChessboard):
    """
    暴力解法
    不要试图用加法取代乘法，后果很严重，数学上不能自洽
    Time: O(KNN)
    Space: O(N^2)
    """
    @staticmethod
    def inside(N, r, c):
        return 0 <= r < N and 0 <= c < N

    KNIGHT_DIRS = [(-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 1), (-1, 2), (-2, 1)]

    def knight_probability(self, N, K, r, c):
        if K == 0:
            return 1.0
        if N < 3:
            return 0.0

        probs = defaultdict(float)
        probs[(r, c)] = 1.0
        stops = {(r, c)}

        for move in xrange(1, K + 1):
            new_stops = set([])
            new_probs = {}

            for x, y in stops:
                for dx, dy in self.KNIGHT_DIRS:
                    x_, y_ = x + dx, y + dy
                    new_probs[(x_, y_)] = probs[(x, y)] * 0.125 + new_probs.get((x_, y_), 0.0)
                    if self.inside(N, x_, y_):  # only in-board positions can move to change its position.
                        new_stops.add((x_, y_))

            stops = new_stops
            for (x, y), prob in new_probs.iteritems():
                probs[(x, y)] = prob

        return float(sum(probs[(x, y)] for x, y in stops))


class KnightProbabilityInChessboardImplDP(KnightProbabilityInChessboard):
    """
    wrong
    """
    @staticmethod
    def inside(N, r, c):
        return 0 <= r < N and 0 <= c < N

    @staticmethod
    def get_total_paths_per_move(move_count, path_per_move):
        total_paths_per_move = [1]
        for i in xrange(move_count):
            total_paths_per_move.append(total_paths_per_move[-1] * path_per_move)
        return total_paths_per_move

    KNIGHT_DIRS = [(-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 1), (-1, 2), (-2, 1)]

    def knight_probability(self, N, K, r, c):
        if N < 3:
            return 0.0

        paths_on_stops = defaultdict(int)
        paths_on_stops[(r, c)] = 1
        stops = {(r, c)}
        total_paths_per_move = self.get_total_paths_per_move(K, 8)

        for move in xrange(1, K + 1):
            new_stops = set([])
            for x, y in stops:
                for dx, dy in self.KNIGHT_DIRS:
                    x_, y_ = x + dx, y + dy
                    paths_on_stops[(x_, y_)] += total_paths_per_move[K - move]
                    if self.inside(N, x_, y_):    # only in-board positions can move to change its position.
                        new_stops.add((x_, y_))
            stops = new_stops

        return float(sum(paths_on_stops[(x, y)] for x, y in stops)) / total_paths_per_move[-1]
