# coding=utf-8
from abc import ABCMeta, abstractmethod

import heapq


class FindKPairsWithSmallestSums:
    __metaclass__ = ABCMeta

    @abstractmethod
    def k_smallest_pairs(self, nums1, nums2, k):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :type k: int
        :rtype: List[List[int]]
        """


class FindKPairsWithSmallestSumsImpl(FindKPairsWithSmallestSums):
    """
    Time: O(KlogK)
    Space: O(KlogK)
    这题与lc378除了返回形式不同，几乎完全等价
    这种二个数组强迫双指针就是二维数组， matrix
    一定要先问清楚面试官需不需要考虑null的情况
    边界条件容易忽略：k = len(nums1) * len(nums2)
    一定要注意扁平化visited的时候是index1 * len2 + index2, 不是index1 * len1 + index2
    """
    def k_smallest_pairs(self, nums1, nums2, k):
        if not nums1 or not nums2:
            return []

        res = []

        len1, len2 = len(nums1), len(nums2)
        limit = min(k, len1 * len2)

        visited = {0}
        candidates = [(nums1[0] + nums2[0], 0, 0)]
        for _ in xrange(limit):
            pair_sum, index1, index2 = heapq.heappop(candidates)
            res.append([nums1[index1], nums2[index2]])
            flat_index = (index1 * len2) + index2
            if index1 + 1 < len1 and flat_index + len2 not in visited:
                heapq.heappush(candidates, (nums1[index1 + 1] + nums2[index2], index1 + 1, index2))
                visited.add(flat_index + len2)
            if index2 + 1 < len2 and flat_index + 1 not in visited:
                heapq.heappush(candidates, (nums1[index1] + nums2[index2 + 1], index1, index2 + 1))
                visited.add(flat_index + 1)
        return res
