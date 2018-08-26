# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class SubarraySumEqualsK:
    __metaclass__ = ABCMeta

    @abstractmethod
    def subarray_sum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """


class SubarraySumEqualsKImplHashImpl(SubarraySumEqualsK):
    """
    Time: O(N)
    Space: O(N)
    """
    def subarray_sum(self, nums, k):
        prefix_sums = defaultdict(int)
        prefix_sums[0] = 1                 # 如果前缀和不从一个dummy的0开始，就不能算进去包含了第一个元素的情况
        cur_prefix_sum, result = 0, 0
        for num in nums:
            cur_prefix_sum += num
            result += prefix_sums[cur_prefix_sum - k]
            prefix_sums[cur_prefix_sum] += 1
        return result


class SubarraySumEqualsKImplHash(SubarraySumEqualsK):
    def subarray_sum(self, nums, k):
        prefix_sums = defaultdict(list)
        prefix_sums[0].append(-1)                  # 如果前缀和不从一个dummy的0开始，就不能算进去包含了第一个元素的情况
        cur_prefix_sum, result = 0, 0
        for index, num in enumerate(nums):
            cur_prefix_sum += num
            result += len(prefix_sums[cur_prefix_sum - k])
            prefix_sums[cur_prefix_sum].append(index)
        return result
