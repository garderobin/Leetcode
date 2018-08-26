from abc import ABCMeta, abstractmethod


class GuessNumberHigherOrLower:
    __metaclass__ = ABCMeta

    @staticmethod
    def guess(n):
        return n % 2

    @abstractmethod
    def guess_number(self, n):
        """
        https://leetcode.com/problems/guess-number-higher-or-lower/description/
        :type n: int
        :rtype: int
        """


class GuessNumberHigherOrLowerImplRecursion(GuessNumberHigherOrLower):
    def guess_number(self, n):
        return self.guess_number_in_range(1, n)

    def guess_number_in_range(self, start, end):
        if start == end:
            return start
        target = start + ((end - start) / 2)
        guess_result = GuessNumberHigherOrLower.guess(target)
        if guess_result == 0:
            return target
        elif guess_result == 1:
            return self.guess_number_in_range(target + 1, end)
        else:
            return self.guess_number_in_range(start, target)
