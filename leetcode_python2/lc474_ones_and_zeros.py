# coding=utf-8
from abc import ABCMeta, abstractmethod


class OnesAndZeros:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_max_form(self, strs, m, n):
        """
        :type strs: List[str]
        :type m: int
        :type n: int
        :rtype: int
        """


class OnesAndZerosImplBackpackStandard(OnesAndZeros):
    """
    TODO: 复习背包问题的标准应用
    Time: O(m * n * len(strs))
    Space: O(m + n)
    """
    def find_max_form(self, strs, m, n):
        if not strs:
            return 0

        f = [[0 for _ in xrange(n + 1)] for _ in xrange(m + 1)]
        # f[j0][j1] = maximum backpack weight with j0 zeros and j1 ones.
        for s in strs:
            if not s:
                continue
            c0 = s.count('0')
            c1 = len(s) - c0
            for j0 in xrange(m, c0 - 1, -1):
                for j1 in xrange(n, c1 - 1, -1):
                    f[j0][j1] = max(f[j0][j1], f[j0 - c0][j1 - c1] + 1)
        return f[m][n]


class OnesAndZerosImplBackpackRotateArrayWithPruning(OnesAndZeros):

    def find_max_form(self, strs, m, n):
        result, resource, total_counts, counters = 0, [m, n], [0, 0], []
        for s in strs:
            if not s:
                continue
            if len(s) == 1:  # 这一整段的剪枝不能帮助复杂度，面试的时候说一说就可以了，不要一开始就写
                num = int(s[0])
                if resource[num] > 0:
                    result += 1
                    resource[num] -= 1
                    if sum(resource) == 0:
                        return result
            else:
                c0 = s.count('0')
                c1 = len(s) - c0
                counters.append((c0, c1))
                total_counts[0] += c0
                total_counts[1] += c1

        if resource[0] >= total_counts[0] and resource[1] >= total_counts[1]:
            return result + len(counters)  # 一些剪枝，容易错，面试的时候最好第一遍不写
        resource = [min(resource[0], total_counts[0]), min(resource[1], total_counts[1])]

        # 二维数组初始化写完的当场就要先检查再往下走，内外顺序很容易写错
        f = [[[0 for r1 in xrange(resource[1] + 1)] for r0 in xrange(resource[0] + 1)],
             [[0 for r1 in xrange(resource[1] + 1)] for r0 in xrange(resource[0] + 1)]]

        for str_index in xrange(len(counters)):
            c0, c1 = counters[str_index]
            i = str_index % 2
            f[i][0][0] = 0

            for j0 in xrange(0, resource[0] + 1):
                for j1 in xrange(0, resource[1] + 1):
                    if c0 <= j0 and c1 <= j1:
                        f[i][j0][j1] = max(f[i ^ 1][j0][j1], f[i ^ 1][j0 - c0][j1 - c1] + 1)
                    else:
                        f[i][j0][j1] = f[i ^ 1][j0][j1]

        return result + f[(len(counters) - 1) % 2][resource[0]][resource[1]]
