class Solution(object):
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        if not num1 or not num2 or num1 == '0' or num2 == '0':
            return "0"

        n1, n2 = len(num1), len(num2)
        products = [0] * (n1 + n2)

        for i in xrange(n1 - 1, -1, -1):
            for j in xrange(n2 - 1, -1, -1):
                products[i + j + 1] += int(num1[i]) * int(num2[j])

        for i in xrange(n1 + n2 - 1, 0, -1):
            carry, products[i] = products[i] / 10, products[i] % 10
            products[i - 1] += carry

        return str(int(''.join(map(str, products))))
