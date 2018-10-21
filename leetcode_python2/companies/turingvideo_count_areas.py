'''
Problem:
Given NxN matrix, you have NxN cells
for every cell, it has a line connecting either left-up to right-down or left-down to right-up.
Given the matrix and each cell with a crossing line, count the number of areas separated by crossing lines.
----
|\/|
|\\|
----

----
|//|
|\/|
----

gh@turingvideo.com

input: 0: '/', 1: '\'
1*1: output = 2

1*2: |//|, |/\|, |\/|, |\\|, output = 3

2*2: output = 4 or 5

(x, y), <= 4 crossings, <= 2 matrix border segments

'''


def count_areas(matrix):
    if not any(matrix):
        return 0

    n = len(matrix)
    if n == 1:
        return 2

    areas = set([])
    for x in xrange(n):
        for y in xrange(n):
            uid = x * n + y
            if matrix[x][y]:
                dfs(matrix, x, y, areas)
                dfs(matrix, x + 1, y + 1, areas)
            else:
                dfs(matrix, x, y + 1, areas)
                dfs(matrix, x + 1, y, areas)
    return len(areas)


def dfs(matrix, x, y, areas):
    pass


def area_str(unit_list):
    return ','.join(unit_list)
