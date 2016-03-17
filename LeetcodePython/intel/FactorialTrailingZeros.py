class Solution(object):

    def trailingZeroes(self, n):
        """
        :type n: int
        :rtype: int
        """
        total = 0
        while n > 4:
            n /= 5
            total += n
        return total
