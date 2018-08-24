# coding=utf-8
import sys
from abc import ABCMeta, abstractmethod


class FindPeakElement2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_peak_2(self, A):
        """
        @param: A: An integer matrix
        @return: The index of the peak
        """


class FindPeakElement2ImplBinarySearchRecursion(FindPeakElement2):
    """
    TODO: 这种解法太难了，有时间再仔细看看，自己做一遍，并且需要熟练分析集中不同层次的解法：
    Find Peak Element 只会O(n) 只会写for循环
    Find Peak Element 会O(log(n)) 会优化
    Find Peak Element II 只会O(n^2) 会优化不会举一反三
    Find Peak Element II 会O(nlog(n)) 会优化会举一反三
    Find Peak Element II 会证明是O(n) 会举一反四
    https://www.jiuzhang.com/solutions/find-peak-element-ii/#tag-highlight-lang-python
    """
    def find_peak_2(self, A):
        if not A or not A[0]:
            return None

        return self.find_peak(A, 0, len(A) - 1, 0, len(A[0]) - 1)

    def find_peak(self, matrix, top, bottom, left, right):
        if top + 1 >= bottom and left + 1 >= right:
            for row in range(top, bottom + 1):
                for col in range(left, right + 1):
                    if self.is_peak(matrix, row, col):
                        return [row, col]
            return [-1, -1]

        if bottom - top < right - left:
            col = (left + right) // 2
            row = self.find_col_peak(matrix, col, top, bottom)
            if self.is_peak(matrix, row, col):
                return [row, col]
            if matrix[row][col - 1] > matrix[row][col]:
                return self.find_peak(matrix, top, bottom, left, col)
            return self.find_peak(matrix, top, bottom, col, right)

        row = (top + bottom) // 2
        col = self.find_row_peak(matrix, row, left, right)
        if self.is_peak(matrix, row, col):
            return [row, col]
        if matrix[row - 1][col] > matrix[row][col]:
            return self.find_peak(matrix, top, row, left, right)
        return self.find_peak(matrix, row, bottom, left, right)

    def is_peak(self, matrix, x, y):
        return matrix[x][y] == max(
            matrix[x][y],
            matrix[x - 1][y],
            matrix[x][y - 1],
            matrix[x][y + 1],
            matrix[x + 1][y],
        )

    def find_row_peak(self, matrix, row, left, right):
        peak_val = -sys.maxsize
        peak = []
        for col in range(left, right + 1):
            if matrix[row][col] > peak_val:
                peak_val = matrix[row][col]
                peak = col
        return peak

    def find_col_peak(self, matrix, col, top, bottom):
        peak_val = -sys.maxsize
        peak = None
        for row in range(top, bottom + 1):
            if matrix[row][col] > peak_val:
                peak_val = matrix[row][col]
                peak = row
        return peak


class FindPeakElement2ImplBinarySearchIteration(FindPeakElement2):
    """
    TODO: 这种解法太难了，有时间再仔细看看，自己做一遍，并且需要熟练分析集中不同层次的解法：
    Find Peak Element 只会O(n) 只会写for循环
    Find Peak Element 会O(log(n)) 会优化
    Find Peak Element II 只会O(n^2) 会优化不会举一反三
    Find Peak Element II 会O(nlog(n)) 会优化会举一反三
    Find Peak Element II 会证明是O(n) 会举一反四
    https://www.jiuzhang.com/solutions/find-peak-element-ii/#tag-highlight-lang-python
    """
    def find_peak_2(self, A):
        if len(A) == 0 or len(A[0]) == 0:
            return [-1, -1]

        left, up = 0, 0
        right, down = len(A[0]) - 1, len(A) - 1
        while left + 1 < right or up + 1 < down:
            if right - left > down - up:
                c = (left + right) / 2
                r = self.find_column_peak(A, c, up, down)
                if self.is_peak(A, r, c):
                    return [r, c]
                elif A[r][c] < A[r][c - 1]:
                    right = c
                else:
                    left = c
            else:
                r = (up + down) / 2
                c = self.find_row_peak(A, r, left, right)
                if self.is_peak(A, r, c):
                    return [r, c]
                elif A[r][c] < A[r - 1][c]:
                    down = r
                else:
                    up = r

        for r in [left, right]:
            for c in [up, down]:
                if self.is_peak(A, r, c):
                    return [r, c]
        return [-1, -1]

    @staticmethod
    def is_peak(A, r, c):
        return A[r][c] > max(A[r][c - 1], A[r][c + 1], A[r - 1][c], A[r + 1][c])

    @staticmethod
    def find_column_peak(A, c, up, down):
        value = max(A[r][c] for r in range(up, down + 1))
        for r in range(up, down + 1):
            if A[r][c] == value:
                return r

    @staticmethod
    def find_row_peak(A, r, left, right):
        value = max(A[r][c] for c in range(left, right + 1))
        for c in range(left, right + 1):
            if A[r][c] == value:
                return c
