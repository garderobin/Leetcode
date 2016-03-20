class Solution(object):

    def countBits(self, num):
        """
        :type num: int
        :rtype: List[int]
        """
        if num == 0:
            return [0]
        elif num == 1:
            return [0, 1]
        else:
            rst, j = [0, 1] + ([0] * (num-1)), 1
            for i in range(2, num+1):
                if i == (j << 1):
                    rst[i] = 1
                    j <<= 1
                else:
                    rst[i] = 1 + rst[i-j]

            return rst
