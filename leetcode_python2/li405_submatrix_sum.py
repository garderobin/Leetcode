# coding=utf-8
from abc import ABCMeta, abstractmethod


class SubMatrixSum:
    __metaclass__ = ABCMeta

    @abstractmethod
    def sub_matrix_sum(self, matrix):
        """
        @param: matrix: an integer matrix
        @return: the coordinate of the left-up and right-down number
        """


class SubMatrixSumImpl(SubMatrixSum):
    """
    矩阵的（top, left), (down, right) 每个边界都是内包含，所以非常容易在prefix减的时候忘记错位
    做图的题目，必须把临界条件在图上清楚显示，而且要清楚每个int代表的宽度为1的格，而不是一条位置线， 非常重要！
    注意这种以某种区间计算结果（比如prefix_sum）为hash key，以index为value的dict, 一定要在初始化的时候考虑是否需要构造一个dummy
    来针对起始位置, 尤其是左右端点重合的情况(假设使用双闭合区间[left, right]
    """
    def sub_matrix_sum(self, matrix):
        if not any(matrix):
            return None
        n, m = len(matrix), len(matrix[0])
        for up in xrange(n):
            col_sums = [0] * m
            for down in xrange(up, n):
                prefix_hash = {0: -1}  # key: prefix_sum, value: col index that makes the prefix_sum with current key
                prefix_sum = 0
                for right in xrange(m):
                    col_sums[right] += matrix[down][right]
                    prefix_sum += col_sums[right]
                    if prefix_sum in prefix_hash:
                        return [(up, prefix_hash[prefix_sum] + 1), (down, right)]
                    prefix_hash[prefix_sum] = right
        return None
