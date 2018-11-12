# coding=utf-8
from abc import ABCMeta, abstractmethod


class TrappingRainWater:
    __metaclass__ = ABCMeta

    @abstractmethod
    def trap(self, height):
        """
        :type height: List[int]
        :rtype: int
        """


class TrappingRainWaterImpl(TrappingRainWater):
    """
    Time: O(N)
    Space: O(1)
    """
    def trap(self, height):
        if not height:
            return 0
        n = len(height)
        left, right = 0, n-1
        max_height_left, max_height_right = 0, 0
        water = 0
        while left <= right:  # left = right的情况是那个格子仍然可能存水
            if height[left] < height[right]:
                if height[left] > max_height_left:
                    max_height_left = height[left]
                else:
                    water += max_height_left - height[left]
                left += 1
            else:
                if height[right] > max_height_right:
                    max_height_right = height[right]
                else:
                    water += max_height_right - height[right]
                right -= 1
        return water
