class Solution(object):
    def ip2number(self, ip):
        numbers = list(map(int, ip.split(".")))
        n = (numbers[0] << 24) + (numbers[1] << 16) + (numbers[2] << 8) + numbers[3]
        return n

    def number2ip(self, n):
        return ".".join([str(n >> 24 & 255), str(n >> 16 & 255), str(n >> 8 & 255), str(n & 255)])

    def ilowbit(self, x):
        for i in range(32):
            if x & (1 << i):
                return i

    def lowbit(self, x):
        return 1 << self.ilowbit(x)

    def ipToCIDR(self, ip, n):
        number = self.ip2number(ip)
        result = []
        while n > 0:
            lb = self.lowbit(number)
            while lb > n:
                lb = lb // 2

            n = n - lb

            result.append(self.number2ip(number) + "/" + str(32 - self.ilowbit(lb)))
            number = number + lb
        return result


if __name__ == '__main__':
    sol = Solution()
    print sol.ipToCIDR("255.0.0.7", 10)
