# coding=utf-8
import itertools
from abc import ABCMeta, abstractmethod


class BackspaceStringCompare:
    __metaclass__ = ABCMeta

    @abstractmethod
    def backspace_compare(self, S, T):
        """
        :type S: str
        :type T: str
        :rtype: bool
        """


class BackspaceStringCompareImplTwoPointers(BackspaceStringCompare):

    def backspace_compare(self, S, T):
        i, j = len(S) - 1, len(T) - 1
        bs, bt = 0, 0  # current unused backspace count of s and t respectively
        while i >= 0 or j >= 0:
            while i >= 0 and (S[i] == '#' or bs > 0):
                bs += 1 if S[i] == '#' else -1
                i -= 1
            cs = '' if i < 0 else S[i]
            while j >= 0 and (T[j] == '#' or bt > 0):
                bt += 1 if T[j] == '#' else -1
                j -= 1
            ct = '' if j < 0 else T[j]
            if cs != ct:
                return False
            i -= 1
            j -= 1
        return True


class BackspaceStringCompareImplReverseIterator(BackspaceStringCompare):
    """
    Iterator优点是速度快
    学习yield写法, izip_longest写法
    """
    def backspace_compare(self, S, T):
        def reverse_generator(s):
            backspace_num = 0
            for ch in reversed(s):
                if ch == '#':
                    backspace_num += 1
                elif backspace_num > 0:
                    backspace_num -= 1
                else:
                    yield ch

        return not any(x != y for x, y in itertools.izip_longest(reverse_generator(S), reverse_generator(T)))
