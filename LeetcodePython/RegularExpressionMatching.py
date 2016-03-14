class RegularExpressionMatching(object):

    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """

        m, n = len(s), len(p)
        f = [True] + ([False] * n)

        for i in range(2, len(f)):
            f[i] = f[i-2] and p[i-1] == '*'

        for i in range(m):
            pre = f[0]
            f[0] = False
            for j in range(1, n+1):
                cur = f[j]
                pc = p[j-1]
                if pc == '*':
                    f[j] = f[j-2] or (f[j] and (s[i] == p[j-2] or p[j-2] == '.'))
                else:
                    f[j] = pre and (s[i] == p[j-1] or p[j-1] == '.')

                pre = cur

        return f[n]

