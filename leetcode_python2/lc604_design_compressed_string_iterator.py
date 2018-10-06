# coding=utf-8
from abc import ABCMeta, abstractmethod


class AbstractCompressedStringIterator:
    __metaclass__ = ABCMeta

    @abstractmethod
    def next(self):
        """
        :rtype: str
        """

    @abstractmethod
    def has_next(self):
        """
        :rtype: bool
        """


class CompressedStringIteratorImplStack(AbstractCompressedStringIterator):
    """
    果然是遇到iterator无脑用stack
    TODO： 用这道题复习了一下iterator的几个需要考虑的方面:
    1. 原始数据可能无限大
    2. 操作数可能无限多
    3. init的辅助数组要不要操作，是只动指针还是选择直接操作数组
    从我的时间来看复杂度相同的情况下只动指针比较快，但是容易写出bug;
    直接修改辅助数组，坏处是rollback更难，所以面试的时候要问transaction的需求，transaction, ACID等等
    4. 没有数据了干烧怎么办，如果面试官没有提到要设计hasNext, 自己要提出来hasNext

    Time: O(N) for init, O(1) for next() and hasNext()
    Space: O(N)
    """
    def __init__(self, compressedString):
        self.counter_arrays = []    # 用counter而不构造original string的原因是count可能非常非常大，会超时
        if compressedString:
            repeat, factor = 0, 1
            for c in reversed(compressedString):
                if c.isdigit():     # 记住isdigit()中间没有下划线！而且char也能用
                    repeat += int(c) * factor
                    factor *= 10
                else:
                    if repeat > 0:
                        self.counter_arrays.append([c, repeat])     # 这里不能写成tuple, tuple是immutable
                    repeat, factor = 0, 1

    def next(self):
        if self.counter_arrays:
            self.counter_arrays[-1][1] -= 1
            c = self.counter_arrays[-1][0]
            if self.counter_arrays[-1][1] == 0:  # 不要把这一步放到has_next()里面去做，这样会在前面有很多次next的时候造成has_next时间长
                self.counter_arrays.pop()
            return c
        else:
            return ' '

    def has_next(self):
        return bool(self.counter_arrays)
