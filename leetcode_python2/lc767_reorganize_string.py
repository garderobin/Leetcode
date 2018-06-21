# coding=utf-8
import heapq
from abc import ABCMeta, abstractmethod
from collections import defaultdict, Counter


class ReorganizeString:
    __metaclass__ = ABCMeta

    @abstractmethod
    def reorganize_string(self, S):
        """
        :type S: str
        :rtype: str
        """


class ReorganizeStringImplBucketSorting(ReorganizeString):
    """
    Python string operation: https://waymoot.org/home/python_string/
    Pattern: dc(d)b(d)a...d(d)c(d)b(d)a dc(d)b...dc(d)b dc...dc (d)
    Time: O(n) if sorting uses linear sorting, O(nlogn) if sorting uses python default sorting.
    Space: O(n)
    """
    def reorganize_string(self, S):
        def sort(collection, key):
            """
            Can be refactored into a linear sorting.
            """
            return sorted(collection, key=key)

        if not S:
            return ''

        char_count_dict = defaultdict(int)
        for char in S:
            char_count_dict[char] += 1

        sorted_char_counts = sort(char_count_dict.items(), key=lambda (k, v): v)

        if sorted_char_counts[-1][1] * 2 - 1 > len(S):
            return ""

        result_list, char_nums, bottom_used_count = [], len(sorted_char_counts), 0
        top_char, unused_top_count = sorted_char_counts[-1][0], sorted_char_counts[-1][1]

        if len(sorted_char_counts) == 2:
            for i in xrange(sorted_char_counts[0][1]):
                result_list.append(top_char)
                result_list.append(sorted_char_counts[0][0])
            if sorted_char_counts[-1][1] - sorted_char_counts[-2][1] > 0:
                result_list.append(top_char)
        else:
            # Use top 1 char to split unmatchable top 2 chars that cannot be split by the top 3 chars.
            # If we don't do this step first, we need to always leave enough top char to split the second top char
            # at last when the 3rd top char is all consumed.
            match_top2_more_than_top3_count = sorted_char_counts[-2][1] - sorted_char_counts[-3][1]
            for top_to_use in xrange(match_top2_more_than_top3_count):
                result_list.append(top_char)
                result_list.append(sorted_char_counts[-2][0])
            unused_top_count -= match_top2_more_than_top3_count

            # Construct patten: dcdbda...dcdbda dcdb...dcdb (assume char count d > c > b > a)
            for i in xrange(char_nums - 2):
                bottom_to_use_count = sorted_char_counts[i][1] - bottom_used_count
                for bottom_to_use in xrange(bottom_to_use_count):
                    for j in xrange(char_nums - 2, i - 1, -1):
                        if unused_top_count > 0:
                            unused_top_count -= 1
                            result_list.append(top_char)
                        result_list.append(sorted_char_counts[j][0])
                    bottom_used_count += 1

            if len(result_list) < len(S):
                result_list.append(top_char)

        return ''.join(result_list)


class ReorganizeStringImpl2(ReorganizeString):
    def reorganize_string(self, S):
        """
        Given the fact that only when no element has larger than (n+1)/2 count we can construct a non-empty result,
        just sort the original string and plot its first half in odd positions and second half in even positions.
        """
        if not S:
            return ''
        N = len(S)
        A = []
        res = [''] * N
        for n, e in sorted((S.count(x), x) for x in set(S)):
            if n > (N + 1) / 2:
                return ''
            A.extend(e * n)
        # print A
        res[::2], res[1::2] = A[N / 2:], A[:N / 2]
        return ''.join(res)


class ReorganizeStringImpl3(ReorganizeString):
    def reorganize_string(self, S):
        """
        Given the fact that only when no element has larger than (n+1)/2 count we can construct a non-empty result,
        just sort the original string and plot its first half in even positions and second half in old index positions.
        Time: O(NlogN). Can be refactored to O(N) if we use linear sorting instead of python default sorting.
        """
        if not S:
            return ''

        N, char_count_dict = len(S), defaultdict(int)
        for char in S:
            char_count_dict[char] += 1
            if char_count_dict[char] > (N + 1) / 2:
                return ''
        res, sorted_str = ['_'] * N, sorted(S)
        res[::2], res[1::2] = sorted_str[N / 2:], sorted_str[:N / 2]
        return ''.join(res)


class ReorganizeStringImplHeapQ(ReorganizeString):
    """
    Priority Queue (heapq): Greedy: always insert current top 1 char + current top 2 char.
    慎选PriorityQueue, 因为每次操作都是O(logN), 所以很难达成最终线性。而且进出的边界条件容易出错。
    但是优点：易于拓展，至少对此题来说可以不局限于2位相邻的检查要求。这一点其他几个linear算法做不到。

    思路：处理相邻问题，那就每次都两个邻值一批操作
    因为p_a初始值是0， 所以能确保一次出堆后再进堆仍然是有最多count的在堆顶
    假设in counter: d > c > b > a
    d <= c + b + ... + a + 1
    d - 1 <= c - 1 + b + ... + a + 1
    所以a会一直消耗到把count(c) - count(b) 差值完全耗光为之然后去耗c
    总之永远是当前剩下最多的前两名元素互相消耗
    Time: O(NlogN)
    Space: O(N)
    """
    def reorganize_string(self, S):
        res = ""
        pq = []
        c = Counter(S)  # O(N)
        for char, count in c.items():
            if count + count > 1 + len(S):
                return ''
            heapq.heappush(pq, (-count, char))
        p_a, p_b = 0, ''  # 因为p_a初始值是0， 所以能确保一次出堆后再进堆仍然是有最多count的在堆顶
        while pq:
            a, b = heapq.heappop(pq)
            res += b
            if p_a < 0:
                heapq.heappush(pq, (p_a, p_b))
            a += 1
            p_a, p_b = a, b

        return res


if __name__ == "__main__":
    tests = ["aaabbbcc", "aba", "vvvlo", "baaba", "eqmeyggvp", "kkkkzrkatkwpkkkktrq", "abbabbaaab"]
    solutions = [ReorganizeStringImplBucketSorting(),
                 ReorganizeStringImpl2(),
                 ReorganizeStringImpl3(),
                 ReorganizeStringImplHeapQ()
                 ]
    for t in tests[0:]:
        for s in solutions[-1:]:
            print s.reorganize_string(t)
