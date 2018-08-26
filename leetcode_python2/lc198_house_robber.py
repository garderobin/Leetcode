from abc import ABCMeta, abstractmethod


class HouseRobber:
    __metaclass__ = ABCMeta

    @abstractmethod
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """


class HouseRobberImplRotateArray(HouseRobber):
    def rob(self, nums):
        prev, cur = 0, 0
        for num in nums:
            prev, cur = cur, max(prev + num, cur)
        return cur
