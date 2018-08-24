# coding=utf-8
from abc import ABCMeta, abstractmethod


class Sqrt:
    __metaclass__ = ABCMeta

    @abstractmethod
    def my_sqrt(self, x):
        """
        :type x: int
        :rtype: int
        """


class SqrtImplNewton(Sqrt):

    def my_sqrt(self, x):
        if not x:
            return 0
        prev, cur = 0.0, 1.0        # cur从一开始！ TODO：复习'导数'derivative 等微积分英文术语及公式
        while prev != cur:
            prev = cur
            cur = (cur + x / cur) / 2
        return int(cur)


class SqrtImplBinarySearch(Sqrt):
    def my_sqrt(self, x):
        start, end = 0, (x // 2) + 1        # 这里要问清楚x的范围，默认情况下千万不要忽略0！
        while start + 1 < end:
            mid = (start + end) // 2
            square = mid * mid
            if square < x:
                start = mid
            else:
                end = mid

        return start if end * end > x else end
