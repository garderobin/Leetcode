# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class TargetSum:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_target_sum_ways(self, nums, S):
        """
        :type nums: List[int]
        :type S: int
        :rtype: int
        """


class TargetSumImplSubsetSumBackpack(TargetSum):
    """
    类似题目：https://leetcode.com/problems/partition-equal-subset-sum/description/
    TODO: 背下来subset sum求法
    """
    def find_target_sum_ways(self, nums, S):
        total_sum = sum(nums)
        if total_sum < S or (total_sum + S) % 2 > 0:
            return 0
        subset_sum_target = (total_sum + S) // 2

        f = defaultdict(int)
        f[0] = 1
        for num in nums:
            for subset_sum in xrange(subset_sum_target, num - 1, -1):
                f[subset_sum] += f[subset_sum - num]
        return f[subset_sum_target]


class TargetSumImplMemoSearch(TargetSum):
    """
    https://leetcode.com/problems/target-sum/description/
    Time: O(N * S) memo search极限情况就是转换成dp以后的复杂度
    Space: O(N * S)
    """
    def __init__(self):
        self.prefix_sums = []
        self.memo = {}

    def find_target_sum_ways(self, nums, S):
        self.prefix_sums = self.get_prefix_sums(nums)
        self.memo = {}
        return self.dfs(nums, 0, S)

    def dfs(self, nums, index, target):
        if (index, target) not in self.memo:
            if index == len(nums) - 1:
                self.memo[(index, target)] = [target + nums[-1], target - nums[-1]].count(nums[-1])
            elif self.prefix_sums[-1] - self.prefix_sums[index] < min(target, -target):  # 利用边界剪枝，这一步不是非做不可
                self.memo[(index, target)] = 0
            else:   # +/-两种情况都要考察同等长度的序列，这是memo search能发挥作用的主要原因
                self.memo[(index, target)] = self.dfs(nums, index + 1, target + nums[index]) + \
                                             self.dfs(nums, index + 1, target - nums[index])
        return self.memo[(index, target)]

    def get_prefix_sums(self, nums):
        prefix_sums = [0]
        for num in nums:
            prefix_sums.append(prefix_sums[-1] + num)
        return prefix_sums
