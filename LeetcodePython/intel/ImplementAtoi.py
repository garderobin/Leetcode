import re


class Solution(object):

    def myAtoi(self, str):
        """
        :type str: str
        :rtype: int
        """
        n, sign, base, i, base_limit, max_int, min_int = len(str), 1, 0, 0, 214748364, 2147483647, -2147483648

        if n <= 0:
            return 0

        while i < n and str[i] == ' ':
            i += 1

        if str[i] == '-' or str[i] == '+':
            sign -= 2 * (str[i] == '-')
            i += 1

        while i < n and ord('0') <= ord(str[i]) <= ord('9'):
            int_v = int(str[i])
            if base > base_limit or (base == base_limit and int_v > 7):
                if sign == 1:
                    return max_int
                else:
                    return min_int
            else:
                base = base * 10 + int_v
            i += 1

        return base * sign

    # Use regex to filter out invalid characters. Remember to import re.
    def atoiRegex(self, string):
        string = string.strip()
        string = re.findall('(^[\+\-0]*\d+)\D*', string)

        try:
            result = int(''.join(string))
            MAX_INT = 2147483647
            MIN_INT = -2147483648
            if result > MAX_INT > 0:
                return MAX_INT
            elif result < MIN_INT < 0:
                return MIN_INT
            else:
                return result
        except:
            return 0

