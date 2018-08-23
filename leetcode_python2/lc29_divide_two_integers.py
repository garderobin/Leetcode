# coding=utf-8
from abc import ABCMeta, abstractmethod


class DivideTwoIntegers:
    __metaclass__ = ABCMeta

    @abstractmethod
    def divide(self, dividend, divisor):
        """
        :type dividend: int
        :type divisor: int
        :rtype: int
        """


class DivideTwoIntegersImplShift(DivideTwoIntegers):
    """
    利用了二进制除法的本质：
    https://www.cnblogs.com/zuoxiaolong/p/computer10.html
    """
    INT_MAX = 2147483647

    def divide(self, dividend, divisor):
        if divisor == 0:
            return self.INT_MAX
        neg = dividend > 0 > divisor or dividend < 0 < divisor
        a, b = abs(dividend), abs(divisor)
        ans, shift = 0, 31
        while shift >= 0:
            if a >= b << shift:
                a -= b << shift
                ans += 1 << shift
            shift -= 1
        if neg:
            ans = - ans
        if ans > self.INT_MAX:
            return self.INT_MAX
        return ans


class DivideTwoIntegersImplBinarySearch(DivideTwoIntegers):
    """
    这次是用九章的模板，把二分法用循环而不是递归来写
    """
    INT_MIN, INT_MAX = -2147483648, 2147483647

    def divide(self, dividend, divisor):
        if dividend == 0:
            return 0
        if dividend == self.INT_MIN and divisor == -1:
            return self.INT_MAX

        dvd, dvs = abs(dividend), abs(divisor)
        if dvd < dvs:
            return 0
        sign = 1 if max(dividend, divisor) < 0 or min(dividend, divisor) > 0 else -1

        start, end = 1, dvd
        while start + 1 < end:
            mid = (start + end) // 2
            product = mid * dvs
            if product == dvd:
                return sign * mid
            elif product > dvd:
                end = mid
            else:
                start = mid
        return sign * (start if end * dvs - self.INT_MAX > dvd - self.INT_MAX else end)
