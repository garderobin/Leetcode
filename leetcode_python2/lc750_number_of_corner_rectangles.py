# coding=utf-8
from abc import ABCMeta, abstractmethod


class NumberOfCornerRectangles:
    __metaclass__ = ABCMeta

    @abstractmethod
    def count_corner_rectangles(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """


class NumberOfCornerRectanglesImplSimplified(NumberOfCornerRectangles):
    """
    不仅仅要学会固定前面的（比如上面或者左边）去寻找合适的后面（比如下面或者右边），
    还要掌握固定现在的， 去遍历前面的 （维护一个prev_sets or prev_list的结构）。
    这种方法适用于需要对起点和终点进行预处理 (对于这题来说是筛选出来1的位置）
    """
    def count_corner_rectangles(self, grid):
        num_of_rows, num_of_cols = len(grid), len(grid[0])
        if num_of_rows < num_of_cols:
            return self._count_corner_rectangles_row_first(grid)
        else:
            return self._count_corner_rectangles_column_first(grid)

    def _count_corner_rectangles_row_first(self, grid):
        corner_rectangles_count = 0
        prev_sets = []
        for row in grid:
            one_indexes = {index for index, value in enumerate(row) if value == 1}
            if len(one_indexes) < 2:
                continue
            for prev in prev_sets:
                common_indexes = one_indexes & prev
                corner_rectangles_count += common_indexes * (common_indexes - 1) // 2
        return corner_rectangles_count

    def _count_corner_rectangles_column_first(self, grid):
        corner_rectangles_count = 0
        prev_sets = []
        for col_index in xrange(len(grid[0])):
            one_indexes = {row_index for row_index in xrange(len(grid)) if grid[row_index][col_index] == 1}
            if len(one_indexes) < 2:
                continue
            for prev in prev_sets:
                common_indexes = one_indexes & prev
                corner_rectangles_count += common_indexes * (common_indexes - 1) // 2
        return corner_rectangles_count


class NumberOfCornerRectanglesImpl(NumberOfCornerRectangles):
    """
    Time: O(N^3)
    Space: O(N^2)
    """
    def count_corner_rectangles(self, grid):
        if not any(grid):
            return 0

        corner_rectangles_count = 0
        one_indexes_in_rows, one_indexes_in_cols = self.get_one_indexes_for_each_row_and_col(grid)
        row_candidates = one_indexes_in_rows.keys()
        for i in xrange(len(row_candidates) - 1):
            top = row_candidates[i]

            # 这样的优化好无必要，因为可用的范围不过一两个元素的数列，在做任何优化之前先要分析清楚可能得得失，先决策，再动手写
            if len(one_indexes_in_rows[top]) == 1:
                only_valid_col = list(one_indexes_in_rows[top])[0]
                one_indexes_in_rows[top].remove(only_valid_col)
                one_indexes_in_cols[only_valid_col].remove(top)
                if len(one_indexes_in_cols[only_valid_col]) == 1:
                    only_valid_row = list(one_indexes_in_cols[only_valid_col])[0]
                    one_indexes_in_rows[only_valid_row].remove(only_valid_col)

            if len(one_indexes_in_rows[top]) == 0:
                continue

            # if len(one_indexes_in_rows[top]) < 2:
            #     continue

            for bottom in row_candidates[i + 1:]:
                if len(one_indexes_in_rows[bottom]) == 1:
                    only_valid_col = list(one_indexes_in_rows[bottom])[0]
                    one_indexes_in_rows[bottom].remove(only_valid_col)
                    one_indexes_in_cols[only_valid_col].remove(bottom)
                    if len(one_indexes_in_cols[only_valid_col]) == 1:
                        only_valid_row = list(one_indexes_in_cols[only_valid_col])[0]
                        one_indexes_in_rows[only_valid_row].remove(only_valid_col)

                if len(one_indexes_in_rows[bottom]) == 0:
                    continue

                # if len(one_indexes_in_rows[bottom]) < 2:
                #     continue

                common_count = self.count_common_elements(one_indexes_in_rows[top], one_indexes_in_rows[bottom])
                corner_rectangles_count += common_count * (common_count - 1) // 2

        return corner_rectangles_count

    def count_common_elements(self, set1, set2):
        """
        Time: O(N)
        Space: O(1)
        """
        short_set, long_set = (set1, set2) if len(set1) < len(set2) else (set2, set1)
        common_count = 0
        for element in list(short_set):
            if element in long_set:
                common_count += 1
        return common_count

    def get_one_indexes_for_each_row_and_col(self, grid):
        """
        Time: O(N^2)
        Space: O(N^2)
        """
        n, m = len(grid), len(grid[0])
        one_indexes_in_rows, one_indexes_in_cols = {}, {}
        for i in xrange(n):
            for j in xrange(m):
                if grid[i][j] == 1:
                    if i in one_indexes_in_rows:
                        one_indexes_in_rows[i].add(j)
                    else:
                        one_indexes_in_rows[i] = {j}

                    if j in one_indexes_in_cols:
                        one_indexes_in_cols[j].add(i)
                    else:
                        one_indexes_in_cols[j] = {i}
        return one_indexes_in_rows, one_indexes_in_cols
