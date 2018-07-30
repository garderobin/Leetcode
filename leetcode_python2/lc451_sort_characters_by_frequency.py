# coding=utf-8
import heapq
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class SortCharactersByFrequency:
    __metaclass__ = ABCMeta

    @abstractmethod
    def frequency_sort(self, s):
        """
        :type s: str
        :rtype: str
        """


class SortCharactersByFrequencyImplBucketSorting(SortCharactersByFrequency):
    """
    切记dict遍历的时候不能忘记.items()!!!!!
    切记freq_bucket遍历的时候需要排序, 因为它的key不是数字，而是字符串化的数字，所以不一定是按数字大小自然排序的！！！
    """
    def frequency_sort(self, s):
        counts, freq_buckets = defaultdict(int), defaultdict(list)
        for element in s:
            counts[element] += 1
        for key, count in counts.items():
            freq_buckets[count].append(key)
        res, j = [''] * len(s), 0
        for freq in sorted(freq_buckets.keys(), reverse=True):
            for c in freq_buckets[freq]:
                for i in xrange(freq):
                    res[j] = c
                    j += 1
        return ''.join(res)
