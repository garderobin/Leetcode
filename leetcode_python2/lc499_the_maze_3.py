from heapq import heappush, heappop


DIRS = [('d', 1, 0), ('l', 0, -1), ('r', 0, 1), ('u', -1, 0)]


class Solution(object):
    def findShortestWay(self, maze, ball, hole):
        """
        :type maze: List[List[int]]
        :type ball: List[int]
        :type hole: List[int]
        :rtype: str
        """
        heap = [(0, '', ball[0], ball[1])]
        visited = set([])

        while heap:
            dist, path, x, y = heappop(heap)

            if (x, y) in visited:
                continue
            visited.add((x, y))

            if x == hole[0] and y == hole[1]:
                return path

            for dir_symbol, dx, dy in DIRS:
                (steps, i, j) = self.roll(x, y, dx, dy, maze, hole)
                if (i, j) in visited:
                    continue
                heappush(heap, (dist + steps, path + dir_symbol, i, j))

        return "impossible"

    def roll(self, x, y, dx, dy, maze, hole):
        rows, cols = len(maze), len(maze[0])
        i, j = x + dx, y + dy
        steps = 1
        while (i != hole[0] or j != hole[1]) and (0 <= i < rows and 0 <= j < cols) and maze[i][j] == 0:
            i += dx
            j += dy
            steps += 1

        if i != hole[0] or j != hole[1]:
            i -= dx
            j -= dy
            steps -= 1
        return steps, i, j
