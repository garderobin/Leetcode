from abc import ABCMeta, abstractmethod


class IslandPerimeter:
    __metaclass__ = ABCMeta

    @abstractmethod
    def calc_island_perimeter(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """


class IslandPerimeterImplementation2(IslandPerimeter):

    def calc_island_perimeter(self, grid):
        if not grid or len(grid) == 0:
            return 0

        row_count = len(grid)
        col_count = len(grid[0])

        perimeter = 0

        for r in xrange(row_count):
            for c in xrange(col_count):
                if grid[r][c] == 1:
                    perimeter += 4
                    if r > 0 and grid[r-1][c]:
                        perimeter -= 2
                    if c > 0 and grid[r][c-1]:
                        perimeter -= 2

        return perimeter


class IslandPerimeterImplementation1(IslandPerimeter):
    def calc_island_perimeter(self, grid):
        if not grid or len(grid) == 0:
            return 0

        row_count = len(grid)
        col_count = len(grid[0])

        island_perimeter = 0

        for r in xrange(row_count):
            for c in xrange(col_count):
                if not self.is_waster_position(grid, r, c):
                    island_perimeter += self.get_number_of_water_neighbors(grid, r, c)

        return island_perimeter

    def get_number_of_water_neighbors(self, grid, r, c):
        water_neighbor_count = 0
        neighbor_position_delta = [
            (1, 0),
            (-1, 0),
            (0, 1),
            (0, -1)
        ]

        for delta in neighbor_position_delta:
            if self.is_waster_position(grid, r + delta[0], c + delta[1]):
                water_neighbor_count += 1
        return water_neighbor_count

    @staticmethod
    def is_waster_position(grid, row, col):
        if not grid:
            return True
        elif row < 0 or col < 0:
            return True
        elif row >= len(grid) or col >= len(grid[0]):
            return True
        else:
            return grid[row][col] == 0
