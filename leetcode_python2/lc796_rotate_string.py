# coding=utf-8
from abc import ABCMeta, abstractmethod


class RotateString:
    __metaclass__ = ABCMeta

    @abstractmethod
    def rotate_string(self, A, B):
        """
        :type A: str
        :type B: str
        :rtype: bool
        """


class RotateStringImpl(RotateString):
    """
    复杂度还有没有可能再降低？
    似乎O(N^2）是极限了？
    """
    def rotate_string(self, A, B):
        if (not A) and (not B):
            return True
        else:
            return True if A and B and len(A) == len(B) and (B in A + A) else False
