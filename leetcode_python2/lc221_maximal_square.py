# coding=utf-8
from abc import ABCMeta, abstractmethod


class MaximalSquare:
    __metaclass__ = ABCMeta

    @abstractmethod
    def maximal_square(self, matrix):
        """
        :type matrix: List[List[str]]
        :rtype: int
        """


class MaximalSquareImplRotateArray(MaximalSquare):
    """
    DP巧思：连续的and条件想想是否可以转化为min, 连续的or条件想想是否可以转化为max
    这种滚动数组的构造方式，非常适合矩阵DP某个点的极值只跟自己左边和上边邻居相关，而不与右边或者下边邻居相关的情况
    如果与四个方向都相关，应该就用记忆化搜索（不管是递归还是排过序的非递归）
    另一题非常相似：li77: longest common subsequence
    """
    def maximal_square(self, matrix):
        if not any(matrix):
            return 0

        n, m = len(matrix), len(matrix[0])
        f = [[0] * m, [0] * m]  # f[i][j] = max square side length whose right bottom corner is position (i, j)
        # 这里绝对不能写成f = [0 for _ in xrange(m), 0 for _ in xrange(m)]
        # 一旦写成后一种，接下来就不能用二维数组正常引用f[a][b] = c去改了, 因为这个时候f[a][b]是一个immutable的int

        max_side_length = 0
        for i in xrange(n):
            f[i % 2][0] = int(matrix[i][0])
            max_side_length = max(max_side_length, f[i % 2][0])     # 多重循环的时候， 写完一定要仔细检查去max/min的位置是不是缺了少了
            for j in xrange(1, m):
                if matrix[i][j] == '1':
                    f[i % 2][j] = 1 + min(f[(i - 1) % 2][j], f[i % 2][j - 1], f[(i - 1) % 2][j - 1])  # j千万不要再除余啦
                    max_side_length = max(max_side_length, f[i % 2][j])
                else:
                    f[i % 2][j] = 0
        return max_side_length * max_side_length
