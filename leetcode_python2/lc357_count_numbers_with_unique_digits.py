from abc import ABCMeta, abstractmethod


class CountNumbersWithUniqueDigits:
    __metaclass__ = ABCMeta

    @abstractmethod
    def count_numbers_with_unique_digits(self, n):
        """
        :type n: int
        :rtype: int
        """


class CountNumbersWithUniqueDigitsImpl(CountNumbersWithUniqueDigits):
    """
    Time: O(1)
    Space: O(1)
    """
    def count_numbers_with_unique_digits(self, n):
        if n == 0:
            return 1
        elif n > 10:
            return self.count_numbers_with_unique_digits(10)
        else:
            delta, result = 1, 1
            for x in xrange(1, n+1):
                result += 9 * delta
                delta *= (10 - x)
            return result


