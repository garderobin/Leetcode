import collections
import copy


class ShortestDistanceFromAllBuildings(object):
    def shortestDistance(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        r, c, rst, walk = len(grid), len(grid[0]), -1, 0
        total = [[0] * c for k in range(r)]
        for i in range(r):
            for j in range(c):
                total[i][j] = grid[i][j]

        # def bfs(start_x, start_y, cur_walk, cur_grid, cur_total):
        #     q, dist, ret = collections.deque([(start_x, start_y)]), list(cur_grid), -1
        #     while q:
        #         x, y = q.popleft()
        #         for xm, ym in ((x+1, y), (x-1, y), (x, y+1), (x, y-1)):
        #             if 0 <= xm < r and 0 <= ym < c and cur_grid[xm][ym] == cur_walk:
        #                 cur_grid[xm][ym] -= 1
        #                 q.append((xm, ym))
        #                 dist[xm][ym] = cur_grid[x][y] + 1
        #                 cur_total[xm][ym] += dist[xm][ym] - 1   # why here is a minus 1???
        #                 if ret < 0 or ret > cur_total[xm][ym]:
        #                     ret = cur_total[xm][ym]
        #     return ret

        for i in range(r):
            for j in range(c):
                if grid[i][j] == 1:
                    # rst = bfs(i, j, --walk, grid, total)
                    # walk -= 1
                    q, dist, rst = collections.deque([(i, j)]), grid[:], -1
                    while q:
                        x, y = q.popleft()
                        for xm, ym in ((x+1, y), (x-1, y), (x, y+1), (x, y-1)):
                            if 0 <= xm < r and 0 <= ym < c and grid[xm][ym] == walk:
                                grid[xm][ym] -= 1
                                q.append((xm, ym))
                                dist[xm][ym] = grid[x][y] + 1
                                total[xm][ym] += dist[xm][ym] - 1   # why here is a minus 1???
                                if rst < 0 or rst > total[xm][ym]:
                                    rst = total[xm][ym]
                    walk -= 1

        return rst

if __name__ == '__main__':
    grid = [[1, 0, 2, 0, 1], [0, 0, 0, 0, 0], [0, 0, 1, 0, 0]]
    sol = ShortestDistanceFromAllBuildings()
    print(sol.shortestDistance(grid))
