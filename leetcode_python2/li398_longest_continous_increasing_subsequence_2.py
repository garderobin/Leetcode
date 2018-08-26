# coding=utf-8
from abc import ABCMeta, abstractmethod


class LongestContinuousIncreasingSubSequence2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def longest_continuous_increasing_sub_sequence_2(self, matrix):
        """
        @param matrix: A 2D-array of integers
        @return: an integer
        """


class LongestContinuousIncreasingSubSequence2ImplDP(LongestContinuousIncreasingSubSequence2):
    """
    用排序，起点从小到大， 终点从大到小的遍历方法或者记忆化搜索可以解决图的多源多终点类型的问题，是区间型动态规划的经典应用场景
    假设有两个点start, end, start高度小于end，那么
    lcis(start->end) = max(lcis(start->adj_of_end)) + 1
    这里之所以不用担心往外饶了一大圈再回到相邻点，是因为在start从小到大end从大到小遍历（排过序)。
    
    记忆化搜索的一个变体，如果不排序，那么就用常规的记忆化搜索递归来做。# TODO 用记忆化搜索再做一边，过程见第七章PPT
    
    Time: O(N^2logN)
    Space: O(N^2)
    """
    DIRECTIONS = [(1, 0), (0, -1), (-1, 0), (0, 1)]

    def longest_continuous_increasing_sub_sequence_2(self, matrix):
        if not any(matrix):
            return 0
        n, m = len(matrix), len(matrix[0])
        points = [(matrix[x][y], x, y) for x in xrange(n) for y in xrange(m)]
        end_lcis_hash = {}
        for point in sorted(points):
            position = point[1:]
            end_lcis_hash[position] = 1
            for dx, dy in self.DIRECTIONS:
                x, y = position[0] + dx, position[1] + dy
                if 0 <= x < n and 0 <= y < m and matrix[x][y] < point[0] and (x, y) in end_lcis_hash:   # 检查每一个if语句！
                    end_lcis_hash[position] = max(end_lcis_hash[position], 1 + end_lcis_hash[(x, y)])
        return max(end_lcis_hash.itervalues())
