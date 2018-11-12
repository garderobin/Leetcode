# coding=utf-8
import itertools
from abc import ABCMeta, abstractmethod
from collections import Counter


class ThreeSumWithMultiplicity:
    __metaclass__ = ABCMeta

    @abstractmethod
    def three_sum_multi(self, A, target):
        """
        :type A: List[int]
        :type target: int
        :rtype: int
        """


class ThreeSumWithMultiplicityImplCountingSort(ThreeSumWithMultiplicity):

    def three_sum_multi(self, A, target):
        c = Counter(A)
        res = 0
        for i, j in itertools.combinations_with_replacement(c, 2):
            # 不用担心c[i] == 1的情况，因为接下来乘出来会是0
            k = target - i - j
            if i == j == k:
                res += c[i] * (c[i] - 1) * (c[i] - 2) / 6
            elif i == j != k:
                res += c[i] * (c[i] - 1) / 2 * c[k]
            elif k > i and k > j:
                res += c[i] * c[j] * c[k]
        return res % (10 ** 9 + 7)


class ThreeSumWithMultiplicityImplCounter(ThreeSumWithMultiplicity):
    """
    Time: O(n ^ 2)
    Space: O(n)
    """
    def three_sum_multi(self, A, target):
        orig_res = 0

        counter = Counter(A)
        counts = sorted(counter.items())    # sort这一步不能少
        m = len(counts)

        for i, (key, count) in enumerate(counts):
            if key + key + key > target:
                break

            # use of this element only once
            for j in xrange(i + 1, m):
                j_key, j_count = counts[j]

                new_target = target - key - j_key
                if new_target < j_key:
                    break

                # use j_key for twice
                if j_count > 1 and new_target == j_key:
                    orig_res += count * j_count * (j_count - 1) / 2

                # use j_key for once
                elif new_target > j_key and new_target in counter:
                    orig_res += count * j_count * counter[new_target]

            # use this element twice
            new_target = target - key
            if count > 1 and new_target - key in counter:
                if new_target - key > key:
                    left = count * (count - 1) / 2
                    orig_res += counter[new_target - key] * left

                # use this element 3 times
                if count > 2 and key * 3 == target:
                    orig_res += count * (count - 1) * (count - 2) / 6

        return orig_res % 1000000007
