# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class PartitionEqualSubsetSum:
    __metaclass__ = ABCMeta

    @abstractmethod
    def can_partition(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """


class PartitionEqualSubsetSumImplBackpack(PartitionEqualSubsetSum):
    def can_partition(self, nums):
        total_sum, max_element = 0, 0
        for num in nums:
            total_sum += num
            max_element = max(max_element, num)
        if total_sum % 2 > 0 or max_element << 1 > total_sum:
            return False

        subset_sum_target = total_sum // 2
        f = defaultdict(bool)
        # f[j] = can we get sum j in nums[:i], each element can be used once or not used.

        for num in nums:
            f[0] = True  # don't use any of the elements
            for j in xrange(subset_sum_target, num - 1, -1):  # 用reverse index保证f[j - num]读的一定是上一轮写的结果
                f[j] |= f[j - num]
        return f[subset_sum_target]

