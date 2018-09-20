# coding=utf-8
from abc import ABCMeta, abstractmethod


class IncreasingSubsequences:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_subsequences(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """


class IncreasingSubsequencesImplDP(IncreasingSubsequences):
    """
    https://leetcode.com/problems/increasing-subsequences/description/
    下面做法通过了，但是我不是很确定我的复杂度对不对, 求帮看下Q_Q
    Time: O((N ^ 3) * (2 ^ N))
    Space: O((N ^ 2) * (2 ^ N))
    """
    def find_subsequences(self, nums):
        if not nums:
            return []

        result_set = set([])
        n = len(nums)

        # O(n)
        f = [set([]) for _ in xrange(n)]  # f[i] = set of increasing subsequence tuples whose first element is nums[i]
        # 这里绝对不能写成 f = [set([])] * n, 这里set的声明方式也不能用{[]}替代.

        for i in xrange(n - 2, -1, -1):     # O(n)
            for j in xrange(i + 1, n):      # O(n)
                if nums[j] < nums[i]:   # 这里不是increasing stack只是个简单点临界检查，白板多推几种情况就看出来了
                    continue

                f[i].add((nums[i], nums[j]))
                result_set.add((nums[i], nums[j]))

                # O((n - j) * 2 ^ (n - j)), result count += (2 ^ (n - j))
                for seq_tuple in f[j]:  # set可以直接iterate, 不用先转换为list
                    new_seq_tuple = (nums[i],) + seq_tuple  # 学会用 tuple concatenation
                    f[i].add(new_seq_tuple)
                    result_set.add(new_seq_tuple)

        # O(n * n * (2 ^ n))
        return list(result_set)   # 内部的tuple不需要重新转化为list


class IncreasingSubsequencesImplBacktrack(IncreasingSubsequences):
    """
    TODO: 自己动手写backtrack
    """
    def __init__(self):
        self.r = []
        self.path = []
        self.nums = []

    def find_subsequences(self, nums):
        self.r = []
        self.path = []
        self.nums = nums
        self.backtrack(0)
        return self.r

    def backtrack(self, i):
        # choose next number starting from the index i
        if i == len(self.nums):
            return
        # for j in index range [i, len), if nums[j] >= last in the
        # path and its value has not been used at this step, then use it
        s = set()  # check possible repetitions of this step
        for j in range(i, len(self.nums)):
            if (not self.path or self.nums[j] >= self.path[-1]) and (self.nums[j] not in s):
                s.add(self.nums[j])
                self.path.append(self.nums[j])  # a new value is appended
                if len(self.path) >= 2:
                    self.r.append(list(self.path))
                self.backtrack(j + 1)
                del self.path[-1]  # restore the state
