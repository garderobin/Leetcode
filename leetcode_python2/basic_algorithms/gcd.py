from abc import ABCMeta, abstractmethod


def simplify_fraction(self, numerator, denominator):
    gcd = self.gcd(numerator, denominator)  # gcd could be negative!
    return (numerator, denominator) if gcd == 0 else (numerator / gcd, denominator / gcd)


class GCDInterface:
    __metaclass__ = ABCMeta

    @abstractmethod
    def gcd(self, a, b):
        """
        Calculate the Greatest Common Divisor of a and b.
        :param a: int
        :param b: int
        :return: int
        """


class GCD(GCDInterface):
    def gcd(self, a, b):
        while b:
            a, b = b, a % b
        return a


class GCDImplRecursion(GCDInterface):
    def gcd(self, a, b):
        if b == 0:
            return a
        else:
            return self.gcd(b, a % b)
