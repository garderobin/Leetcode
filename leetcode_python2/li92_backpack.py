# coding=utf-8
"""
TODO： 这题还没有做，是抄的答案！
至少要熟练滚动数组的做法
https://www.jiuzhang.com/solution/backpack/#tag-highlight-lang-python
这里抄上去的是最优解
"""


class Solution:
    """
    @param m: An integer m denotes the size of a backpack
    @param A: Given n items with size A[i]
    @return: The maximum size
    """

    def backPack(self, m, A):
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