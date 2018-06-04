from abc import ABCMeta, abstractmethod


class BombEnemy:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_killed_enemies(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """


class BombEnemyImpl3(BombEnemy):

    def max_killed_enemies(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        result = 0
        if not grid or not grid[0]:
            return result

        down = [[0 for _ in xrange(len(grid[0]))] for _ in xrange(len(grid))]
        right = [[0 for _ in xrange(len(grid[0]))] for _ in xrange(len(grid))]
        for i in reversed(xrange(len(grid))):
            for j in reversed(xrange(len(grid[0]))):
                if grid[i][j] != 'W':
                    if i + 1 < len(grid):
                        down[i][j] = down[i + 1][j]
                    if j + 1 < len(grid[0]):
                        right[i][j] = right[i][j + 1]
                    if grid[i][j] == 'E':
                        down[i][j] += 1
                        right[i][j] += 1

        up = [0 for _ in xrange(len(grid[0]))]
        for i in xrange(len(grid)):
            left = 0
            for j in xrange(len(grid[0])):
                if grid[i][j] == 'W':
                    up[j], left = 0, 0
                elif grid[i][j] == 'E':
                    up[j] += 1
                    left += 1
                else:
                    result = max(result, left + up[j] + right[i][j] + down[i][j])

        return result


class BombEnemyImpl2(BombEnemy):
    """
    Time: O(N^2)
    Space: O(N^2)
    Idea: cache with extra borders.
    Loop twice. First time from left up and 2nd time from right down, to get enemies on 4 directions by DP.
    """
    def max_killed_enemies(self, grid):
        if not grid or not grid[0]:
            return 0

        max_killed_enemies_total = 0

        height = len(grid)
        width = len(grid[0])
        killed_enemies = [[[0 for direction in xrange(4)] for row in xrange(width + 2)] for col in xrange(height + 2)]

        self.kill_left_up_enemies(grid, killed_enemies, height, width)
        self.kill_right_down_enemies(grid, killed_enemies, height, width)

        for row in xrange(height):
            for col in xrange(width):
                if grid[row][col] == '0':
                    max_killed_enemies_total = max(max_killed_enemies_total, sum(killed_enemies[row + 1][col + 1]))

        return max_killed_enemies_total

    def kill_left_up_enemies(self, grid, killed_enemies, height, width):
        for row in xrange(1, height + 1):
            for col in xrange(1, width + 1):
                if grid[row - 1][col - 1] != 'W':
                    enemy_count_this_cell = grid[row - 1][col - 1] == 'E'
                    killed_enemies[row][col][0] = killed_enemies[row][col - 1][0] + enemy_count_this_cell
                    killed_enemies[row][col][1] = killed_enemies[row - 1][col][1] + enemy_count_this_cell

    def kill_right_down_enemies(self, grid, killed_enemies, height, width):
        for row in xrange(height, 0, -1):
            for col in xrange(width, 0, -1):
                if grid[row - 1][col - 1] != 'W':
                    enemy_count_this_cell = grid[row - 1][col - 1] == 'E'
                    killed_enemies[row][col][2] = killed_enemies[row][col + 1][2] + enemy_count_this_cell
                    killed_enemies[row][col][3] = killed_enemies[row + 1][col][3] + enemy_count_this_cell


class BombEnemyImpl(BombEnemy):
    def max_killed_enemies(self, grid):
        if not grid or not grid[0]:
            return 0

        max_killed_enemies_total = 0

        height = len(grid)
        width = len(grid[0])
        killed_enemies = [[{0: 0, 2: 0, 1: 0, 3: 0}] * width] * height

        self.init_killed_enemies_dummy_borders(grid, killed_enemies, height, width)
        self.init_killed_enemies_left_and_up(grid, killed_enemies, height, width)
        self.init_killed_enemies_right_and_down(grid, killed_enemies, height, width)

        for row in xrange(height):
            for col in xrange(width):
                if grid[row][col] == '0':
                    max_killed_enemies_total = max(max_killed_enemies_total, sum(killed_enemies[row + 1][col + 1]))

        return max_killed_enemies_total

    def get_killed_enemy_count_in_single_cell(self, grid, row, col):
        return 1 if grid[row][col] == 'E' else 0

    def init_killed_enemies_dummy_borders(self, grid, killed_enemies, height, width):
        for row in xrange(-1, 1):
            for col in range(width):
                for direction in killed_enemies[row][col].iterkeys():
                    killed_enemies[row][col][direction] = self.get_killed_enemy_count_in_single_cell(grid, row, col)

        for col in xrange(-1, 1):
            for row in xrange(height):
                for direction in killed_enemies[row][col].iterkeys():
                    killed_enemies[row][col][direction] = self.get_killed_enemy_count_in_single_cell(grid, row, col)

    def init_killed_enemies_left_and_up(self, grid, killed_enemies, height, width):
        for row in xrange(1, height):
            for col in xrange(1, width):
                if grid[row][col] == 'W':
                    killed_enemies[row][col][0] = 0
                    killed_enemies[row][col][1] = 0
                else:
                    killed_enemies_this_cell = self.get_killed_enemy_count_in_single_cell(grid, row, col)
                    print killed_enemies
                    killed_enemies[row][col][0] = killed_enemies[row][col - 1][0] + killed_enemies_this_cell
                    killed_enemies[row][col][1] = killed_enemies[row - 1][col][1] + killed_enemies_this_cell

    def init_killed_enemies_right_and_down(self, grid, killed_enemies, height, width):
        for row in xrange(height - 2, -1, -1):
            for col in xrange(width - 2, -1, -1):
                if grid[row][col] == 'W':
                    killed_enemies[row][col][2] = 0
                    killed_enemies[row][col][3] = 0
                else:
                    killed_enemies_this_cell = self.get_killed_enemy_count_in_single_cell(grid, row, col)
                    killed_enemies[row][col][2] = killed_enemies[row][col + 1][2] + killed_enemies_this_cell
                    killed_enemies[row][col][3] = killed_enemies[row + 1][col][3] + killed_enemies_this_cell


if __name__ == "__main__":
    sol = BombEnemyImpl3()
    res = sol.max_killed_enemies([["0", "E", "0", "0"], ["E", "0", "W", "E"], ["0", "E", "0", "0"]])
    print res
