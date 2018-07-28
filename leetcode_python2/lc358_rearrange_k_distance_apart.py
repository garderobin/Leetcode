# coding=utf-8
import heapq
from abc import ABCMeta, abstractmethod
from collections import Counter, defaultdict


class RearrangeKDistanceApart:
    __metaclass__ = ABCMeta

    @abstractmethod
    def rearrange_string(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """


class RearrangeKDistanceApartImplHeap(RearrangeKDistanceApart):

    def rearrange_string(self, s, k):
        if k == 0:
            return s
        counts = Counter(s)
        heap = [[counts[key], key] for key in counts]
        res, j = ['']*len(s), 0
        while j < len(s):
            heap.sort(reverse=True)
            for i in range(k):
                if i >= len(heap) or heap[i][0] == 0:
                    return ''.join(res) if j == len(s) else ''
                res[j] = heap[i][1]
                heap[i][0] -= 1
                j += 1
        return ''.join(res)


class RearrangeKDistanceApartGreedyTwoAuxiliaryArray(RearrangeKDistanceApart):
    """
    Time limit exceeded.
    https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83193/Java-15ms-Solution-with-Two-auxiliary-array.-O(N)-time.
    Greedy: Every time we want to find the best candidate: which is the character with the largest remaining count.
    Time: O(N)???
    Space: O(N)
    """
    def rearrange_string(self, s, k):
        if k <= 1:
            return s

        counts, valid = defaultdict(int), defaultdict(int)
        for c in s:
            counts[c] += 1
            valid[c] = 0

        res = ''
        for index in xrange(len(s)):
            candidate_char = self.find_valid_max(counts, valid, index)
            if not candidate_char:
                return ''
            else:
                counts[candidate_char] -= 1
                valid[candidate_char] = index + k
                res += candidate_char
        return res

    def find_valid_max(self, counts, valid, index):
        candidate_char, max_count = None, -1
        for char, count in counts.items():
            if count > 0 and count > max_count and index >= valid[char]:
                max_count = count
                candidate_char = char
        return candidate_char


class RearrangeKDistanceApartImplList(RearrangeKDistanceApart):
    """
    还没看懂: https://leetcode.com/submissions/detail/166221494/
    """
    def rearrange_string(self, s, k):
        stats = Counter(list(s))
        mostfrq = stats.most_common()[0][1]
        count = stats.values().count(mostfrq)

        if len(s) < (mostfrq - 1) * k + count:
            return ""

        temp = []
        end = []
        for char, n in stats.most_common():
            if n < mostfrq:
                temp.extend(n * char)
            else:
                temp.extend((n - 1) * char)
                end.append(char)

        res = []
        for i in range(mostfrq - 1):
            p = 0
            while i + p * (mostfrq - 1) < len(temp):
                res.append(temp[i + p * (mostfrq - 1)])
                p += 1

        return "".join(res + end)


class RearrangeKDistanceApartImplSorting(RearrangeKDistanceApart):
    """
    没通过，不正确
    """
    def rearrange_string(self, s, k):
        if k <= 1:
            return s

        counters = Counter(s).items()
        counters.sort(key=lambda x: x[1], reverse=True)

        res = ''
        while counters[0][1] > 0:
            if (counters[0][1] - 1) * k > len(s) + 1:
                return ''
            for i in xrange(k):
                if len(res) == len(s):
                    return res
                char, count = counters[i]
                if count > 0:
                    res += char
                    counters[i] = (char, count - 1)
            counters.sort(key=lambda x: x[1], reverse=True)
        return res


class RearrangeKDistanceApartImplPriorityQueue(RearrangeKDistanceApart):
    """
    Time Limit Exceeded on the 57th test case.
    """
    def rearrange_string(self, s, k):
        if k <= 1:
            return s

        pq = []
        counter = Counter(s)
        for char, count in counter.items():
            if (count - 1) * k > len(s) + 1:
                return ''
            heapq.heappush(pq, (-count, char))

        res = ""
        while pq:
            k_slice = []
            for i in xrange(k):
                if not pq:
                    if len(res) == len(s):
                        return res
                    else:
                        return ''
                else:
                    nega_count, char = heapq.heappop(pq)
                    res += char
                    if nega_count + 1 < 0:
                        k_slice.append((nega_count + 1, char))
            for nega_count, char in k_slice:
                heapq.heappush(pq, (nega_count, char))

        return res
