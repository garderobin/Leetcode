# coding=utf-8
from abc import ABCMeta, abstractmethod


class WoodCut:
    __metaclass__ = ABCMeta

    @abstractmethod
    def wood_cut(self, L, k):
        """
        @param L: Given n pieces of wood with length L[i]
        @param k: An integer
        @return: The maximum length of the small pieces
        """


class WoodCutImplBinarySearch(WoodCut):

    def wood_cut(self, L, k):
        if not L:
            return 0
        start, end = 0, max(L)      # start一定从零开始，二分法最容易错的地方就是边界
        while start + 1 < end:
            mid = (start + end) // 2
            if self.count_pieces(L, mid) < k:      # 这里等号千万不能少带，最容易出错的点
                end = mid
            else:
                start = mid
        return start if self.count_pieces(L, end) < k else end

    @staticmethod
    def count_pieces(L, piece_length):
        return sum([wood // piece_length for wood in L])
