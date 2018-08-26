# coding=utf-8
from abc import ABCMeta, abstractmethod


class CopyBooks:
    __metaclass__ = ABCMeta

    @abstractmethod
    def copy_books(self, pages, k):
        """
        @param pages: an array of integers
        @param k: An integer
        @return: an integer
        """


class CopyBooksBinarySearchWithGreedyScan(CopyBooks):
    @staticmethod
    def get_least_people(pages, time_limit):        # todo: 为什么这里能用贪心？
        people, time_cost = 1, 0
        for page in pages:
            if time_cost + page > time_limit:
                people += 1
                time_cost = 0
            time_cost += page
        return people

    def copy_books(self, pages, k):
        if not pages:
            return 0
        start, end = max(pages), sum(pages)
        while start + 1 < end:
            mid = (start + end) // 2
            if self.get_least_people(pages, mid) > k:
                start = mid
            else:
                end = mid
        return end if self.get_least_people(pages, start) > k else start
