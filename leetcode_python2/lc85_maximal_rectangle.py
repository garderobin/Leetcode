# coding=utf-8
from abc import ABCMeta, abstractmethod


class MaximalRectangle:
    __metaclass__ = ABCMeta

    @abstractmethod
    def maximal_rectangle(self, matrix):
        """
        :type matrix: List[List[str]]
        :rtype: int
        """


class MaximalRectangleImplDP(MaximalRectangle):

    def maximal_rectangle(self, matrix):
        pass


class MaximalRectangleImplStackSimplify(MaximalRectangle):
    """
    Time: O(nm)
    Space: O(n)
    单调栈的简化，本质相同，只是边构造每行的histogram边算，减少了存储
    """
    def maximal_rectangle(self, matrix):
        if not matrix or not matrix[0]:
            return 0
        max_rect = 0
        histogram_prev_row = [0] * len(matrix[0])
        for row in matrix:
            histogram_cur_row = []
            for c, v in enumerate(row):
                histogram_cur_row.append(histogram_prev_row[c] + 1 if v == '1' else 0)
            max_rect = max(max_rect, self.largest_rectangle_area_in_histogram(histogram_cur_row))
            histogram_prev_row = histogram_cur_row
        return max_rect

    @staticmethod
    def largest_rectangle_area_in_histogram(heights):
        max_rect = 0
        index_stack = []
        for i, h in enumerate(heights + [0]):
            while index_stack and heights[index_stack[-1]] > h:
                max_rect = max(max_rect, heights[index_stack.pop()] * (i - index_stack[-1] - 1 if index_stack else i))
            index_stack.append(i)
        return max_rect


class MaximalRectangleImplStack(MaximalRectangle):
    """
    Time: O(nm)
    Space: O(nm)
    采用算法强化班中讲到的单调栈。
    要做这个题之前先做直方图最大矩阵（Largest Rectangle in Histogram） 这个题。
    这个题其实就是包了一层皮而已。一行一行的计算以当前行为矩阵的下边界时，最大矩阵是什么。
    计算某一行为下边界时的情况，就可以转换为直方图最大矩阵问题了。
    """
    def maximal_rectangle(self, matrix):
        histograms = self.construct_histograms_from_matrix(matrix)
        return max(self.largest_rectangle_area_in_histogram(heights) for heights in histograms)

    @staticmethod
    def construct_histograms_from_matrix(matrix):
        rows, cols = len(matrix), len(matrix[0])

        histogram_cur_row = [int(x) for x in matrix[0]]
        histograms = [histogram_cur_row]

        for r in xrange(1, rows):
            histogram_cur_row = []
            for c in xrange(cols):
                histogram_cur_row.append(histograms[-1][c] + 1 if matrix[r][c] == '1' else 0)
            histograms.append(histogram_cur_row)
        return histograms

    @staticmethod
    def largest_rectangle_area_in_histogram(heights):
        max_rect = 0
        index_stack = []
        for i, h in enumerate(heights + [0]):
            while index_stack and heights[index_stack[-1]] > h:
                max_rect = max(max_rect, heights[index_stack.pop()] * (i - index_stack[-1] - 1 if index_stack else i))
            index_stack.append(i)
        return max_rect