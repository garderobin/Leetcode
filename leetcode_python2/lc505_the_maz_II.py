import heapq
from abc import ABCMeta, abstractmethod


class TheMazeII:
    __metaclass__ = ABCMeta

    @abstractmethod
    def shortest_distance(self, maze, start, destination):
        """
        :type maze: List[List[int]]
        :type start: List[int]
        :type destination: List[int]
        :rtype: int
        """


class TheMazeShortestDistanceImplDFS(TheMazeII):
    def shortest_distance(self, maze, start, destination):
        pass


class TheMazeShortestDistanceImplDijkstra(TheMazeII):
    """
    heapq: https://github.com/qiwsir/algorithm/blob/master/heapq.md
    dijkstra algorithm: https://blog.csdn.net/yalishadaa/article/details/55827681
    """
    def shortest_distance(self, maze, start, destination):
        pass


class TheMazeShortestDistanceImplBFS(TheMazeII):
    def shortest_distance(self, maze, start, destination):
        roll_once_lengths, stop_positions = self.get_rolling_stop_length_and_positions(maze)
        visited, q, res = set(), [], None
        heapq.heappush(q, (0, start))
        while q:
            length, pos = heapq.heappop(q)
            if tuple(pos) in visited:
                continue  # if cur is visited and with a shorter length, skip.
            visited.add(tuple(pos))
            if pos == destination:
                res = min(res, length) if res else length
            r, c = pos
            for dir_index in xrange(4):
                next_length, next_pos = roll_once_lengths[r][c][dir_index], stop_positions[r][c][dir_index]
                heapq.heappush(q, (length + next_length, next_pos))
        return res if res else -1

    def get_rolling_stop_length_and_positions(self, maze):
        """
        For each position on board, for each direction,
        find the furthest of position the ball can roll to from current position on this direction before it hit a wall.
        :param maze: maze board with '0' as empty and '1' as wall.
        :return: 4 rolling stop integer lists in order: up, left, down, right.
        """
        rows, cols = len(maze), len(maze[0])
        [up, left, down, right] = [[[0 for _ in xrange(cols)] for _ in xrange(rows)] for _ in xrange(4)]
        stop_positions = [[[[0, 0] for _ in xrange(4)] for _ in xrange(cols)] for _ in xrange(rows)]
        roll_once_lengths = [[[0 for _ in xrange(4)] for _ in xrange(cols)] for _ in xrange(rows)]
        for r in xrange(rows):
            for c in xrange(cols):
                up[r][c] = r if r == 0 or maze[r-1][c] == 1 else up[r-1][c]
                left[r][c] = c if c == 0 or maze[r][c-1] == 1 else left[r][c-1]

        for r in xrange(rows - 1, -1, -1):
            for c in xrange(cols - 1, -1, -1):
                down[r][c] = r if r == rows - 1 or maze[r+1][c] == 1 else down[r+1][c]
                right[r][c] = c if c == cols - 1 or maze[r][c+1] == 1 else right[r][c+1]
                roll_once_lengths[r][c] = [r - up[r][c], c - left[r][c], down[r][c] - r, right[r][c] - c]
                stop_positions[r][c] = [[up[r][c], c], [r, left[r][c]], [down[r][c], c], [r, right[r][c]]]

        return roll_once_lengths, stop_positions
