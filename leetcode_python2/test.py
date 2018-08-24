# coding=utf-8
from collections import Counter


def my_max(a, b):
    print 'my_max', a, b
    return max(a, b)


if __name__ == "__main__":
    s = 'abccccdd'
    Counter(s).i
    board = [["O", "X", "O", "O"], ["X", "O", "X", "O"], ["X", "O", "X", "O"]]
    n, m, max_m_n = len(board), len(board[0]), max(len(board), len(board[0]))

    def is_water(x, y):
        return 0 <= x < n and 0 <= y < m and board[x][y] == 'O'

    q = [(i, j) for k in xrange(1, max(m, n) - 1) for (i, j) in [(0, k), (n - 1, k), (k, 0), (k, m - 1)] if is_water(i, j)]

    print q






