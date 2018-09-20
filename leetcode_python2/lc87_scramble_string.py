# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import Counter


class ScrambleString:
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_scramble(self, s1, s2):
        """
        :type s1: str
        :type s2: str
        :rtype: bool
        """

# https://www.jiuzhang.com/solution/scramble-string/#tag-highlight-lang-java


class ScrambleStringImplMemoSearchWithPreProcessPruning(ScrambleString):
    """
    提前计算好prune所需的矩阵并不会把速度变快，因为三维度数组初始化本身就需要时间
    """

    def __init__(self):
        self.memo = {}

    def is_scramble(self, s1, s2):
        if not s1:
            return 0

        n = len(s1)
        self.memo = self.init_memo(s1, s2)
        return self.memo_search(0, 0, n)

    def memo_search(self, x, y, k):
        if (x, y, k) in self.memo:
            return self.memo[(x, y, k)]

        scramble = False
        for i in xrange(1, k):
            if ((self.memo_search(x, y, i) and self.memo_search(x + i, y + i, k - i)) or
                    (self.memo_search(x, y + k - i, i) and self.memo_search(x + i, y, k - i))):
                scramble = True
                break
        self.memo[(x, y, k)] = scramble
        return scramble

    @staticmethod
    def init_memo(s1, s2):
        n = len(s1)
        memo = {}
        for x in xrange(n):
            for y in xrange(n):
                memo[(x, y, 1)] = s1[x] == s2[y]
                counts = {}
                for k in xrange(0, n - max(x, y)):
                    counts[s1[x + k]] = counts.get(s1[x + k], 0) + 1
                    counts[s2[y + k]] = counts.get(s2[y + k], 0) - 1
                    if counts[s1[x + k]] != 0 or counts[s2[y + k]] != 0:
                        memo[(x, y, k + 1)] = False
        return memo


class ScrambleStringImplDPWithPruning(ScrambleString):
    @staticmethod
    def get_count_map(s):
        return dict(Counter(s).iteritems())

    """
    从leetcode运行时间来看pruning没有省时间，反而因为diff数组初始化耽误了时间
    但是理论上数据量非常大的时候n^3的pruning的diff数组初始化将会节省下面检索scramble的时间
    Time: O(N^4)
    Space: O(N^3)
    """

    def is_scramble(self, s1, s2):
        # state: f[k][x][y] = True <==> is_scramble(s1[x:x+k], s2[y:y+k]) == True, k: [1, n]
        # function: for i in xrange(1, k):
        #           s11, s12 = s1[x:x+i], s1[x+i:x+k]
        #           s21, s22 = s2[y:y+i], s2[y+i:y+k]
        #           s23, s24 = s2[y:y+k-i], s2[y+k-i:y+k]
        #           is_scramble = (is_scramble(s11, s21) and is_scramble(s12, s22)) or
        #                         (is_scramble(s11, s24) and is_scramble(s12, s23))
        #          f[k][x][y] = f[k][x][y] or ((f[i][x][y] and f[k-i][x+i][y+i]) or (f[i][x][y+k-i] and f[k-i][x+i][y]))
        # initialize: f[0][x][y] = True, f[1][x][y] = s1[x] == s2[y]
        # answer: f[n][0][0]
        if not s1:
            return 0
        n = len(s1)

        # O(n^3) Time for initialization
        f = [[[False for _ in xrange(n)] for _ in xrange(n)] for _ in xrange(n + 1)]  # 多重循环时里层外层很容易搞错！
        diff = [[[False for _ in xrange(n)] for _ in xrange(n)] for _ in xrange(n + 1)]
        # diff[x][y][k] = character distribution (counts) is identical for s1[x:x+k] and s2[y:y+k]
        for x in xrange(n):
            for y in xrange(n):
                f[1][x][y] = s1[x] == s2[y]
                counts = {}
                for k in xrange(0, n - max(x, y)):
                    counts[s1[x + k]] = counts.get(s1[x + k], 0) + 1
                    counts[s2[y + k]] = counts.get(s2[y + k], 0) - 1
                    if counts[s1[x + k]] != 0 or counts[s2[y + k]] != 0:
                        diff[k + 1][x][y] = True

        # O(n^4) Time even with pruning
        for k in xrange(2, n + 1):
            for x in xrange(n - k + 1):
                for y in xrange(n - k + 1):
                    if diff[k][x][y]:
                        f[k][x][y] = False
                        continue
                    for i in xrange(1, k):
                        if (f[i][x][y] and f[k - i][x + i][y + i]) or (f[i][x][y + k - i] and f[k - i][x + i][y]):
                            f[k][x][y] = True
                            break
        return f[n][0][0]


class ScrambleStringImplDPSimplified(ScrambleString):
    """
    和下面一个的解法一样，不过是因为我们用sliding window， 所以把window长度k放在最外层而不是最里层
    另一个要点是背模版。因为我们从sliding window length作为最外层循环， 所以不需要x, y倒序循环，全都可以正序，因为i < k
    Time: O(N^4)
    Space: O(N^3)
    """

    def is_scramble(self, s1, s2):
        # state: f[k][x][y] = True <==> is_scramble(s1[x:x+k], s2[y:y+k]) == True, k: [1, n]
        # function: for i in xrange(1, k):
        #           s11, s12 = s1[x:x+i], s1[x+i:x+k]
        #           s21, s22 = s2[y:y+i], s2[y+i:y+k]
        #           s23, s24 = s2[y:y+k-i], s2[y+k-i:y+k]
        #           is_scramble = (is_scramble(s11, s21) and is_scramble(s12, s22)) or
        #                         (is_scramble(s11, s24) and is_scramble(s12, s23))
        #          f[k][x][y] = f[k][x][y] or ((f[i][x][y] and f[k-i][x+i][y+i]) or (f[i][x][y+k-i] and f[k-i][x+i][y]))
        # initialize: f[0][x][y] = True, f[1][x][y] = s1[x] == s2[y]
        # answer: f[n][0][0]
        if not s1:
            return 0
        n = len(s1)
        f = [[[False for _ in xrange(n)] for _ in xrange(n)] for _ in xrange(n + 1)]  # 多重循环时里层外层很容易搞错！

        for x in xrange(n):
            for y in xrange(n):
                f[1][x][y] = s1[x] == s2[y]

        for k in xrange(2, n + 1):
            for x in xrange(n - k + 1):
                for y in xrange(n - k + 1):
                    for i in xrange(1, k):
                        if (f[i][x][y] and f[k - i][x + i][y + i]) or (f[i][x][y + k - i] and f[k - i][x + i][y]):
                            f[k][x][y] = True
                            break
        return f[n][0][0]


class ScrambleStringImplDP(ScrambleString):
    """
    Time: O(N^4)
    Space: O(N^3)
    """

    def is_scramble(self, s1, s2):
        # state: f[x][y][k] = True <==> is_scramble(s1[x:x+k], s2[y:y+k]) == True, k: [1, n]
        # function: for i in xrange(1, k):
        #           s11, s12 = s1[x:x+i], s1[x+i:x+k]
        #           s21, s22 = s2[y:y+i], s2[y+i:y+k]
        #           s23, s24 = s2[y:y+k-i], s2[y+k-i:y+k]
        #           is_scramble = (is_scramble(s11, s21) and is_scramble(s12, s22)) or
        #                         (is_scramble(s11, s24) and is_scramble(s12, s23))
        #          f[x][y][k] = f[x][y][k] or ((f[x][y][i] and f[x+i][y+i][k-i]) or (f[x][y+k-i][i] and f[x+i][y][k-i]))
        # initialize: f[x][y][0] = True, f[x][y][1] = s1[x] == s2[y]
        # answer: f[0][0][n]
        if not s1:
            return 0
        n = len(s1)
        f = [[[False for _ in xrange(n + 1)] for _ in xrange(n)] for _ in xrange(n)]  # 多重循环时里层外层很容易搞错！

        for x in xrange(n):
            for y in xrange(n):
                f[x][y][1] = s1[x] == s2[y]

        for k in xrange(2, n + 1):                  # k: [2, n]
            for x in xrange(n - k, -1, -1):         # x: [0, n-k]
                for y in xrange(n - k, -1, -1):     # y: [0, n-k]
                    for i in xrange(1, k):          # i: [1, k]
                        if (f[x][y][i] and f[x + i][y + i][k - i]) or (f[x][y + k - i][i] and f[x + i][y][k - i]):
                            f[x][y][k] = True
                            break
        return f[0][0][n]


class ScrambleStringImplMemoSearchWithPruning(ScrambleString):
    """
    也许是本题最佳选项，显著加快
    """
    def __init__(self):
        self.memo = {}
        self.s1 = ''
        self.s2 = ''

    def is_scramble(self, s1, s2):
        if not s1:
            return 0

        n = len(s1)
        self.s1, self.s2, self.memo = s1, s2, {}
        for x in xrange(n):
            for y in xrange(n):
                self.memo[(x, y, 1)] = s1[x] == s2[y]

        return self.memo_search(0, 0, n)

    def memo_search(self, x, y, k):
        if (x, y, k) in self.memo:
            return self.memo[(x, y, k)]

        counts = dict(Counter(self.s1[x:x + k]).iteritems())
        for c in self.s2[y:y + k]:
            if c not in counts:
                self.memo[(x, y, k)] = False
                return False
            elif counts[c] == 1:
                del counts[c]
            else:
                counts[c] -= 1
        if len(counts) > 0:
            self.memo[(x, y, k)] = False
            return False

        scramble = False
        for i in xrange(1, k):
            if ((self.memo_search(x, y, i) and self.memo_search(x + i, y + i, k - i)) or
                    (self.memo_search(x, y + k - i, i) and self.memo_search(x + i, y, k - i))):
                scramble = True
                break
        self.memo[(x, y, k)] = scramble
        return scramble


class ScrambleStringImplMemoSearch(ScrambleString):
    """
    过不了， 是错的
    """
    def __init__(self):
        self.memo = {}
        self.s1 = ''
        self.s2 = ''

    def is_scramble(self, s1, s2):
        if not s1:
            return 0

        n = len(s1)
        self.s1, self.s2, self.memo = s1, s2, {}
        for x in xrange(n):
            for y in xrange(n):
                self.memo[(x, y, 1)] = s1[x] == s2[y]

        return self.memo_search(0, 0, n)

    def memo_search(self, x, y, k):
        if (x, y, k) in self.memo:
            return self.memo[(x, y, k)]

        scramble = False
        for i in xrange(1, k):
            if ((self.memo_search(x, y, i) and self.memo_search(x + i, y + i, k - i)) or
                    (self.memo_search(x, y + k - i, i) and self.memo_search(x + i, y, k - i))):
                scramble = True
                break
        self.memo[(x, y, k)] = scramble
        return scramble


if __name__ == "__main__":
    s1 = "abc"
    s2 = "acb"
    sol = ScrambleStringImplDPWithPruning()
    print sol.is_scramble(s1, s2)
