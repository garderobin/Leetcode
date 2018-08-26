# coding=utf-8
from abc import ABCMeta, abstractmethod


class BurstBalloons:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_coins(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class BurstBalloonsImplMemoSearch(BurstBalloons):
    def max_coins(self, nums):
        if not nums:
            return 0
        # start from pos -1 (0 in new array) and ends with pos n (n+1 in new array)
        return self.memo_search([1] + nums + [1], {}, 0, len(nums) + 1)

    def memo_search(self, balloons, memo, start, end):
        if start == end:
            return 0

        if (start, end) in memo:
            return memo[(start, end)]

        max_output = 0
        for i in (start + 1, end):
            left = self.memo_search(balloons, memo, start, i)
            right = self.memo_search(balloons, memo, i, end)  # 因为区间两头都不是实边界而是虚边界，所以这里Right不能从i+1开始
            max_output = max(max_output, left + right + (balloons[start] * balloons[i] * balloons[end]))

        memo[(start, end)] = max_output
        return max_output
