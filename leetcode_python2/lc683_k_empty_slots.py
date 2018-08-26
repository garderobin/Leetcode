# coding=utf-8
from abc import ABCMeta, abstractmethod


class KEmptySlots:
    __metaclass__ = ABCMeta

    @abstractmethod
    def k_empty_slots(self, flowers, k):
        """
        :type flowers: List[int]
        :type k: int
        :rtype: int
        """


class KEmptySlotImpl3(KEmptySlots):
    # Time: O(N)
    # Space: O(N/(K+1))
    # Idea: if we split the array into fix-length buckets with size (k+1),
    # Then the final window left border and right border must be in two adjacent buckets.
    # If the new bloom position is the lower bloom position of its bucket,
    # It might create a k empty window with the upper bloom position of its left adjacent bucket.
    # If the new bloom position is the upper bloom position of its bucket,
    # It might create a k empty window with the lower bloom position of its right adjacent bucket.
    def k_empty_slots(self, flowers, k):
        if not flowers or k + 2 > len(flowers) or k < 0:
            return -1

        n = len(flowers)
        bucket_count = n // (k + 1) + 1
        upper, lower = [-1 for _ in xrange(bucket_count)], [20001 for _ in xrange(bucket_count)]

        for day_index, bloom_pos in enumerate(flowers):
            bucket = (bloom_pos - 1) // (k + 1)
            if bloom_pos < lower[bucket]:
                lower[bucket] = bloom_pos
                if bucket > 0 and bloom_pos - upper[bucket - 1] == k + 1:
                    return day_index + 1
            if bloom_pos > upper[bucket]:
                upper[bucket] = bloom_pos
                if bucket + 1 < bucket_count and lower[bucket + 1] - bloom_pos == k + 1:
                    return day_index + 1
        return -1


class KEmptySlotImpl2(KEmptySlots):

    # Time complexity: maybe worst case O(N^2)
    # 但是general case真的很快
    # 在不得不每次都遍历列表的情况下，多花空间维护temp表也不失为一个好方法
    def k_empty_slots(self, flowers, k):
        n = len(flowers)
        if k + 2 > n or k < 0:
            return -1

        empty_windows = [(0, n+1)]  # extend one more digit in both left and right.
        for day_index, bloom_position in enumerate(flowers):
            empty_windows_after_bloom = []
            for window in empty_windows:
                if (window[0] > 0 and bloom_position == window[0] + k + 1) or \
                        (window[1] <= n and bloom_position == window[1] - k - 1):
                    return day_index + 1

                if bloom_position < window[0] or bloom_position > window[1]:
                    empty_windows_after_bloom.append(window)
                else:
                    if bloom_position - window[0] > k:
                        empty_windows_after_bloom.append((window[0], bloom_position))
                    if window[1] - bloom_position > k:
                        empty_windows_after_bloom.append((bloom_position, window[1]))
                    # ignore all the size < k splits

                empty_windows = empty_windows_after_bloom
        return -1


class KEmptySlotsImpl(KEmptySlots):
    # https://leetcode.com/problems/k-empty-slots/discuss/107931/JavaC++-Simple-O(n)-solution
    # Fixed-size sliding window jump with greedy idea:
    # when moving from left to right, if current position blooms earlier than left border,
    # all left then current can be ignored, move the window left border to the current position.
    # Time: O(N)
    # Space: O(N)
    # Cons: this method must do exactly 2n time to get the earliest date with exactly k consecutive empty slots.
    # We cannot return early.
    # So it has good time complexity but may run longer time then some O(N^2) solution in practice.
    def k_empty_slots(self, flowers, k):
        n = len(flowers)
        if k + 2 > n or k < 0:
            return -1

        position_bloom_day, left, right, res = self.get_position_bloom_day_list(flowers), 1, k + 2, n + 1
        for pos in xrange(1, n + 1):
            if right > n:
                break
            elif position_bloom_day[pos] < position_bloom_day[left] or \
                    position_bloom_day[pos] <= position_bloom_day[right]:
                if pos == right:
                    res = min(res, max(position_bloom_day[left], position_bloom_day[right]))
                left = pos
                right = left + k + 1
        return res if res <= n else -1

    @staticmethod
    def get_position_bloom_day_list(flowers):
        position_bloom_day, day = [0 for _ in xrange(len(flowers) + 1)], 1
        for bloom_position in flowers:
            position_bloom_day[bloom_position] = day
            day += 1

        return position_bloom_day

