# coding=utf-8


def add_border(cell_info_list):
    h, r, c = cell_info_list
    print h, r, c


if __name__ == "__main__":
    height_map = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    m = len(height_map)
    n = len(height_map[0])
    map(add_border, [(height_map[r][c], r, c) for r in [0, m - 1] for c in xrange(1, n - 1)])




