# coding=utf-8
from abc import ABCMeta, abstractmethod
from bisect import bisect_left


class RussianDollEnvelope:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_envelopes(self, envelopes):
        """
        :type envelopes: List[List[int]]
        :rtype: int
        """


class RussianDollEnvelopeImplLIS2(RussianDollEnvelope):
    """
    在按(w, -h)排过序以后就完全不用考虑width, 只用height来做lis就可以了。
    用排序来降维, 并且在dp的时候只是用降维过了的结果，思路非常威武
    Time: O(NlogN)
    Space: O(N)
    """
    def max_envelopes(self, envelopes):
        lis = []
        for (w, h) in sorted(envelopes, key=lambda (a, b): (a, -b)):
            insert = bisect_left(lis, h)
            if insert == len(lis):
                lis.append(h)
            else:
                lis[insert] = h
        return len(lis)


class RussianDollEnvelopeImplLIS(RussianDollEnvelope):

    @staticmethod
    def can_russian_doll(envelope1, envelope2):
        return envelope1[0] < envelope2[0] and envelope1[1] < envelope2[1]

    def max_envelopes(self, envelopes):
        if not envelopes:
            return 0

        sorted_envelopes = sorted(envelopes, key=lambda (a, b): (a, -b))
        lis = [sorted_envelopes[0]]
        for envelope in sorted_envelopes[1:]:
            if self.can_russian_doll(lis[-1], envelope):
                lis.append(envelope)
            else:
                lis[self.get_first_index_not_able_to_russian_doll(lis, envelope)] = envelope
        return len(lis)

    def get_first_index_not_able_to_russian_doll(self, lis, envelope):
        start, end = 0, len(lis) - 1
        while start + 1 < end:
            mid = (start + end) // 2
            if self.can_russian_doll(lis[mid], envelope):
                start = mid     # 因为是找first larger or equal 所以往右走的条件更苛刻，不大不小的时候都是往左
            else:
                end = mid
        return end if self.can_russian_doll(lis[start], envelope) else start


class RussianDollEnvelopeImplBruteForce(RussianDollEnvelope):
    """
    虽然这种办法TLE了，但是作为last resort必须掌握
    用working set的思路，至少面试的时候要能做出来，不要被什么memo search陷进去，不好count的时候就老老实实正着推
    Time: O(N * (2 ^ N)): 1*(2^1) + 2*(2^2) + ... + (n-1)*(2^(n-1)) < n*(2 + 2^2 + ... + 2^(n-1)) = n*(2^n - 2)
    Space: O(2 ^ N): the total possible permutations of N nums
    参见lc491: Increasing Subsequences
    """
    @staticmethod
    def can_russian_doll(envelopes, eid1, eid2):
        e1, e2 = envelopes[eid1], envelopes[eid2]
        return e1[0] < e2[0] and e1[1] < e2[1]

    def max_envelopes(self, envelopes):
        if not envelopes:
            return 0

        sorted_envelopes = sorted(envelopes)

        max_envelopes = 1
        dolls = []  # element type: tuple (envelope_count_in_the_doll, largest_envelop_id_of_the_doll)
        for eid, (w, h) in enumerate(sorted_envelopes):
            print eid, w, h, dolls
            can_wrap_prev_doll = False
            for did in xrange(len(dolls)):
                envelope_count, largest_envelope_id = dolls[did]
                if self.can_russian_doll(envelopes, largest_envelope_id, eid):
                    can_wrap_prev_doll = True
                    max_envelopes = max(max_envelopes, envelope_count + 1)
                    dolls.append((envelope_count + 1, eid))
            if not can_wrap_prev_doll:
                dolls.append((1, eid))
        return max_envelopes
