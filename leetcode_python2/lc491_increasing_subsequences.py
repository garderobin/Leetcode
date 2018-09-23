# coding=utf-8
from abc import ABCMeta, abstractmethod


class IncreasingSubsequences:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_subsequences(self, nums):
        """
        https://leetcode.com/problems/increasing-subsequences/description/
        :type nums: List[int]
        :rtype: List[List[int]]
        """


class IncreasingSubsequencesImplDP2(IncreasingSubsequences):
    """
    Time: O(N * (2 ^ N)): 1*(2^1) + 2*(2^2) + ... + (n-1)*(2^(n-1)) < n*(2 + 2^2 + ... + 2^(n-1)) = n*(2^n - 2)
    Space: O(2 ^ N): the total possible permutations of N nums
    TODO: 经常复习，这题要和lc300: Longest Increasing Subsequences, lc354 Russian Doll Envelope一起看
    """
    def find_subsequences(self, nums):
        if not nums:
            return []

        result_set = set([])
        working_set = set([])   # allows single element tuple

        for i, num in enumerate(nums):
            for seq_tuple in list(working_set):             # O(2^i), 循环内要修改working_set, 所以不能直接iterate set
                if seq_tuple[-1] <= num:
                    new_seq_tuple = seq_tuple + (num, )     # O(i)
                    working_set.add(new_seq_tuple)
                    result_set.add(new_seq_tuple)

            working_set.add((num,))

        # O(2 ^ n)
        return list(result_set)


class IncreasingSubsequencesImplDP(IncreasingSubsequences):
    """
    Time: O(N * (2 ^ N))
    Space: O(2 ^ N)
    从前向后遍历写起来要简单太多了不容易错，这种从后向前遍历在原数组wost case(两边大中间小)的时候有一定剪枝作用
    但写起来太容易错
    """
    def find_subsequences(self, nums):
        if not nums:
            return []

        result_set = set([])
        n = len(nums)

        # O(n)
        f = {n - 1: set([])}  # f[i] = set of increasing subsequence tuples whose first element is nums[i]
        # 这里绝对不能写成 f = [set([])] * n, 这里set的声明方式也不能用{[]}替代.

        # sum((n - i) * 2 ^ (n - i + 1)) < O(n * (2 ^ n))
        for i in xrange(n - 2, -1, -1):
            f[i] = set([])
            for j in xrange(i + 1, n):
                if nums[j] < nums[i]:   # 这里不是increasing stack只是个简单点临界检查，白板多推几种情况就看出来了
                    continue

                f[i].add((nums[i], nums[j]))
                result_set.add((nums[i], nums[j]))

                # previous tuples: 2 ^ (n - j)), length of each previous tuple: O(n - j)
                # result count += (2 ^ (n - j))
                for seq_tuple in f[j]:  # set可以直接iterate, 不用先转换为list
                    new_seq_tuple = (nums[i],) + seq_tuple  # 熟练用 tuple concatenation
                    f[i].add(new_seq_tuple)
                    result_set.add(new_seq_tuple)

        # O(2 ^ n)
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
