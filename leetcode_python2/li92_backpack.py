# coding=utf-8
"""
TODO： 这题还没有做，是抄的答案！
至少要熟练滚动数组的做法
https://www.jiuzhang.com/solution/backpack/#tag-highlight-lang-python
这里抄上去的是最优解
"""
from abc import ABCMeta, abstractmethod


class Backpack:
    __metaclass__ = ABCMeta

    @abstractmethod
    def backpack(self, m, A):
        """
        @param m: An integer m denotes the size of a backpack
        @param A: Given n items with size A[i]
        @return: The maximum size
        """


class BackpackImplDP(Backpack):

    def backpack(self, m, A):
        if not A:
            return 0

        f = [False] * (m + 1)  # f[i] = the backpack can be filled with total weight i.
        f[0] = True
        result = 0
        for w in A:
            for i in xrange(m, w - 1, -1):
                if f[i - w]:
                    result = max(result, i)
                    f[i] = True
        return result


class BackpackImplMergeInterval(Backpack):

    def backpack(self, m, A):
        A.sort()

        intervals = [[0, 0]]
        for item in A:
            new_intervals = []
            for interval in intervals:
                new_intervals.append([interval[0] + item, interval[1] + item])

            intervals = self.merge_intervals(intervals, new_intervals)

        max_size = 0
        for interval in intervals:
            if interval[0] <= m <= interval[1]:
                return m
            if interval[0] > m:
                break
            max_size = max(max_size, interval[1])
        return max_size

    def merge_intervals(self, list1, list2):
        i, j = 0, 0
        intervals = []
        while i < len(list1) and j < len(list2):
            if list1[i] < list2[j]:
                self.push_to_intervals(intervals, list1[i])
                i += 1
            else:
                self.push_to_intervals(intervals, list2[j])
                j += 1

        while i < len(list1):
            self.push_to_intervals(intervals, list1[i])
            i += 1

        while j < len(list2):
            self.push_to_intervals(intervals, list2[j])
            j += 1

        return intervals

    def push_to_intervals(self, intervals, interval):
        if not intervals or intervals[-1][1] + 1 < interval[0]:
            intervals.append(interval)
            return

        intervals[-1][1] = max(intervals[-1][1], interval[1])