# coding=utf-8
from abc import ABCMeta, abstractmethod


class FizzBuzz:
    __metaclass__ = ABCMeta

    @abstractmethod
    def fizz_buzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """


class FizzBuzzImplOptimal(FizzBuzz):
    """
    要点：
    1. 减少函数调用能省时间
    2. 除数先大后小能省时间
    3. 一遍过无bug能力
    """
    def fizz_buzz(self, n):
        res = []
        for i in xrange(1, n + 1):
            if i % 15 == 0:
                res.append('FizzBuzz')
            elif i % 5 == 0:
                res.append('Buzz')
            elif i % 3 == 0:
                res.append('Fizz')
            else:
                res.append(str(i))
        return res


class FizzBuzzImpl(FizzBuzz):
    def fizz_buzz(self, n):
        def fizz_buzz_single_number(num):
            if num % 15 == 0:
                return 'FizzBuzz'
            elif num % 3 == 0:
                return 'Fizz'
            elif num % 5 == 0:
                return 'Buzz'
            else:
                return str(num)

        return [fizz_buzz_single_number(v) for v in xrange(1, n + 1)]
