class SelfCrossing(object):
    def isSelfCrossingDiscussion(self, x):
        return any(d >= b > 0 and (a >= c or (a >= c - e >= 0 and f >= d - b))
                   for a, b, c, d, e, f in ((x[i:i + 6] + [0] * 6)[:6]
                                            for i in range(len(x))))

    def isSelfCrossing(self, x):
        """
        :type x: List[int]
        :rtype: bool
        """
        if len(x) < 4:
            return True

        # direction is either always inner or always outer
        n = len(x)
        for i in range(3, n):
            if x[i - 1] <= x[i - 3] and x[i] >= x[i - 2]:
                return True
            elif i > 3 and x[i] + x[i - 4] == x[i - 1]:
                return True
            else:
                pass

        return False


if __name__ == '__main__':
    xs = [[2, 1, 1, 2], [1, 2, 3, 4], [3, 3, 4, 2, 2]]
    sol = SelfCrossing()
    for x in xs:
        print(sol.isSelfCrossingDiscussion(x))
