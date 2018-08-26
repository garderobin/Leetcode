# coding=utf-8
from abc import ABCMeta, abstractmethod
import math


class SumOfSquareNumbers:
    __metaclass__ = ABCMeta

    @abstractmethod
    def judge_square_sum(self, c):
        """
        :type c: int
        :rtype: bool
        """


class SumOfSquareNumbersImplSet(SumOfSquareNumbers):
    def judge_square_sum(self, c):
        sqrt = int(math.sqrt(c)) + 1
        square_set = {0, 1}
        for i in xrange(2, sqrt):
            square = i * i
            square_set.add(square)  # 这一步放在前面很重要！c = 1 + 1, 动笔前多思考是否允许元素重复使用
            if c - square in square_set:
                return True
        return False


class SumOfSquareNumbersImplFermatEuler(SumOfSquareNumbers):
    """
    需要考前强化分解质因数等数学常规操作！！！
    需要对平方和等常用数学概念进行公式枚举和强化
    根据费马定理: 对c分解质因数，每个k=4m+3的质因数出现次数为偶数
    (费马平方和定理：奇质数能表示为两个平方数之和的充分必要条件是该素数被4除余1)
    https://zh.wikipedia.org/wiki/%E8%B4%B9%E9%A9%AC%E5%B9%B3%E6%96%B9%E5%92%8C%E5%AE%9A%E7%90%86
    """
    def judge_square_sum(self, c):
        i = 2
        while i * i <= c:
            count = 0
            while c % i == 0:
                c /= i
                count += 1
            if i % 4 == 3 and count % 2 != 0:
                return False
            i += 1
        return c % 4 != 3
