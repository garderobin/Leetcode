# coding=utf-8
from abc import ABCMeta, abstractmethod


class ValidPerfectSquare:
    """
    Sol #1. a square number is 1+3+5+7+... Time Complexity O(sqrt(N)) (Credit to lizhibupt, thanks for correcting this).
    Sol #2. binary search (Bisection). Time Complexity O(logN)
    Sol #3. Newton Method. See https://en.wikipedia.org/wiki/Newton%27s_method.
     Time Complexity is close to constant, given a positive integer.
    """

    __metaclass__ = ABCMeta

    @abstractmethod
    def is_perfect_square(self, num):
        """
        :type num: int
        :rtype: bool
        """


class ValidPerfectSquareImplBisection(ValidPerfectSquare):
    """
    Bisection法: 异向双指针的binary search，判断指标是两个指针共同运算出的，根据答案来调试到底移动哪边指针。
    Time: O(logN)
    Space: O(1)
    """
    def is_perfect_square(self, num):
        left, right = 0, num
        while left <= right:
            mid = (left + right) >> 1
            mid_square = mid * mid
            if mid_square < num:
                left = mid + 1
            elif mid_square > num:
                right = mid - 1
            else:
                return True
        return False
