# coding=utf-8
from abc import ABCMeta, abstractmethod


class ValidNumber:
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_number(self, s):
        """
        :type s: str
        :rtype: bool
        """


class ValidNumberImplClassic(ValidNumber):

    def is_number(self, s):
        point_seen = False
        number_seen = False
        e_seen = False
        number_after_e = False
        s = s.strip()
        for i, char in enumerate(s):
            ascii_value = ord(char) - ord('0')
            if 0 <= ascii_value <= 9:
                number_seen = True
                if e_seen:
                    number_after_e = True

            elif char == 'e':
                if e_seen or not number_seen:
                    return False
                e_seen = True

            elif char == '.':
                if e_seen or point_seen:
                    return False
                point_seen = True

            elif char == '+' or char == '-':
                if i != 0 and s[i - 1] != 'e':
                    return False
            else:
                return False
        if e_seen:
            return number_seen and number_after_e
        else:
            return number_seen


class ValidNumberImplJiuzhang(ValidNumber):
    """
    讨巧的地方太多，各种边界条件容易出错
    """
    def is_number(self, s):
        if not s or not s.strip():
            return False
        t = s.strip()
        n, i = len(t), 1 if self.is_sign(t[0]) else 0
        dot_seen, digit_seen = False, False

        while i < n:
            if t[i] == '.':
                if dot_seen:
                    return False
                else:
                    dot_seen = True
            elif t[i].isdigit():
                digit_seen = True
            else:
                break
            i += 1
        if (not digit_seen) or (i < n and t[i] != 'e'):
            print 'a'
            return False

        if i == n:
            return True

        i += 1
        if i == n:
            return False
        if self.is_sign(t[i]):
            i += 1
            if i == n:
                return False
        while i < n:
            if not t[i].isdigit():
                return False
            i += 1

        return i == n

    @staticmethod
    def is_sign(ch):
        return ch in ['+', '-']
