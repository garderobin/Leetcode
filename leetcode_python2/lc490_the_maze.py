from abc import ABCMeta, abstractmethod
from collections import deque


class TheMaze:
    __metaclass__ = ABCMeta

    @abstractmethod
    def has_path(self, maze, start, destination):
        """
        :type maze: List[List[int]]
        :type start: List[int]
        :type destination: List[int]
        :rtype: bool
        """


class TheMazeImplBFS(TheMaze):

    def has_path(self, maze, start, destination):
        rolling_stops = self.get_rolling_stop_positions(maze)
        queue = deque([start])
        while queue:
            r, c = queue.popleft()
            maze[r][c] = 2
            for stop_pos in rolling_stops[r][c]:
                if maze[stop_pos[0]][stop_pos[1]] == 2:
                    continue
                maze[stop_pos[0]][stop_pos[1]] = 2

                if stop_pos == destination:
                    return True

                queue.append(stop_pos)

        return False

    def get_rolling_stop_positions(self, maze):
        """
        For each position on board, for each direction,
        find the furthest of position the ball can roll to from current position on this direction before it hit a wall.
        :param maze: maze board with '0' as empty and '1' as wall.
        :return: 4 rolling stop integer lists in order: up, left, down, right.
        """
        rows, cols = len(maze), len(maze[0])
        [up, left, down, right] = [[[0 for _ in xrange(cols)] for _ in xrange(rows)] for _ in xrange(4)]
        stop_positions = [[[[0, 0] for _ in xrange(4)] for _ in xrange(cols)] for _ in xrange(rows)]
        for r in xrange(rows):
            for c in xrange(cols):
                up[r][c] = r if r == 0 or maze[r-1][c] == 1 else up[r-1][c]
                left[r][c] = c if c == 0 or maze[r][c-1] == 1 else left[r][c-1]

        for r in xrange(rows - 1, -1, -1):
            for c in xrange(cols - 1, -1, -1):
                down[r][c] = r if r == rows - 1 or maze[r+1][c] == 1 else down[r+1][c]
                right[r][c] = c if c == cols - 1 or maze[r][c+1] == 1 else right[r][c+1]
                stop_positions[r][c] = [[up[r][c], c], [r, left[r][c]], [down[r][c], c], [r, right[r][c]]]

        return stop_positions


class TheMazeDFSImpl2(TheMaze):
    """
    DFS with pruning.
    Pre-processing step using dynamic programming to optimize time complexity.
    Time: O(N * M) (total position numbers of the maze)
    """
    def has_path(self, maze, start, destination):
        return self.dfs(maze, start, destination, self.get_rolling_stops(maze))

    def dfs(self, maze, start, destination, rolling_borders):
        """
        DFS with marking '2' on the maze board for visited positions.
        """
        if start == destination:
            return True
        elif maze[start[0]][start[1]] > 0:
            return False
        else:
            r, c = start[0], start[1]
            up, left = rolling_borders[0][r][c], rolling_borders[1][r][c]
            down, right = rolling_borders[2][r][c], rolling_borders[3][r][c]
            maze[r][c] = 2

            return (up < r and self.dfs(maze, [up, c], destination, rolling_borders)) \
                or (left < c and self.dfs(maze, [r, left], destination, rolling_borders)) \
                or (down > r and self.dfs(maze, [down, c], destination, rolling_borders)) \
                or (right > c and self.dfs(maze, [r, right], destination, rolling_borders))

    def get_rolling_stops(self, maze):
        """
        For each position on board, for each direction,
        find the furthest of position the ball can roll to from current position on this direction before it hit a wall.
        :param maze: maze board with '0' as empty and '1' as wall.
        :return: 4 rolling stop integer lists in order: up, left, down, right.
        """
        rows, cols = len(maze), len(maze[0])
        [up, left, down, right] = [[[0 for _ in xrange(cols)] for _ in xrange(rows)] for _ in xrange(4)]
        for r in xrange(rows):
            for c in xrange(cols):
                up[r][c] = r if r == 0 or maze[r-1][c] == 1 else up[r-1][c]
                left[r][c] = c if c == 0 or maze[r][c - 1] == 1 else left[r][c - 1]

        for r in xrange(rows - 1, -1, -1):
            for c in xrange(cols - 1, -1, -1):
                down[r][c] = r if r == rows - 1 or maze[r+1][c] == 1 else down[r+1][c]
                right[r][c] = c if c == cols - 1 or maze[r][c+1] == 1 else right[r][c+1]

        return up, left, down, right


# https://blog.csdn.net/qibofang/article/details/51594673
class TheMazeDFSImpl(TheMaze):
    # Time: O(max(m,n)*m*n)?
    def has_path(self, maze, start, destination):
        return self.dfs(maze, start, destination)

    def dfs(self, maze, start, destination):
        if start == destination:
            return True
        elif maze[start[0]][start[1]] > 0:
            return False
        else:
            r, c, rows, cols = start[0], start[1], len(maze), len(maze[0])
            up, down, left, right = r, r, c, c
            maze[r][c] = 2

            while up > 0 and maze[up - 1][c] != 1:
                up -= 1
            if up < r and self.dfs(maze, [up, c], destination):
                return True

            while down + 1 < rows and maze[down + 1][c] != 1:
                down += 1
            if down > r and self.dfs(maze, [down, c], destination):
                return True

            while left > 0 and maze[r][left - 1] != 1:
                left -= 1
            if left < c and self.dfs(maze, [r, left], destination):
                return True

            while right + 1 < cols and maze[r][right + 1] != 1:
                right += 1
            return right > 0 and self.dfs(maze, [r, right], destination)


if __name__ == "__main__":
    # sol = TheMazeDFSImpl2()
    sol = TheMazeImplBFS()
    maze = [[0, 0, 1, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 1, 0], [1, 1, 0, 1, 1], [0, 0, 0, 0, 0]]
    start, destination = [0, 4], [4, 4]
    res = sol.has_path(maze, start, destination)
    print res
