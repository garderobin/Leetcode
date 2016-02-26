class MinTotalDistance:
    # 这一段的连续的for没有看懂
    def minTotalDistanceDiscussion(self, grid):
        return sum(sum(abs(x - X[len(X)/2]) for x in X) for X in
                   ([x for x, row in enumerate(grid) for _ in range(sum(row))]
                    for grid in (grid, zip(*grid))))

    def minTotalDistance(self, grid):
        if not grid or not grid[0]:
            return 0
        xm = ym = []
        r = len(grid)
        c = len(grid[0])
        for row in range(r):
            for col in range(c):
                if grid[row][col] == 1:
                    xm += [row]

        for col in range(c):
            for row in range(r):
                if grid[row][col] == 1:
                    ym += [col]

        def minDistance(self, list):
            m = len(list) % 2
            n = len(list) / 2
            total = sum(list[:n+1])
            total -= sum(list[n+m:])
            return total

        return minDistance(self, xm) + minDistance(self, ym)
