# coding=utf-8
import heapq
from abc import ABCMeta, abstractmethod
from collections import defaultdict, Counter


class TopKFrequentWords:
    __metaclass__ = ABCMeta

    @abstractmethod
    def top_k_frequent(self, words, k):
        """
        :type words: List[str]
        :type k: int
        :rtype: List[str]
        """


class TopKFrequentWordsImplHeapify(TopKFrequentWords):
    """
    Heapify的好处是count和key全都排序. Building a heap is O(N).
    beats 100%
    Time: O(nlogk)
    Space: O(n)
    """
    def top_k_frequent(self, words, k):
        counts = defaultdict(int)
        for w in words:
            counts[w] += 1
        h = [(-count, key) for key, count in counts.items()]
        heapq.heapify(h)
        return [heapq.heappop(h)[1] for _ in range(k)]


class TopKFrequentWordsImplCounter(TopKFrequentWords):

    def top_k_frequent(self, words, k):
        counter = Counter(words)
        reverse_counter = sorted(map(lambda (word, count): (-count, word), counter.iteritems()))
        return [reverse_counter[i][1] for i in xrange(k)]


class TopKFrequentWordsImplBucketSorting(TopKFrequentWords):
    def top_k_frequent(self, words, k):
        counts, freq_buckets = defaultdict(int), defaultdict(list)
        for w in words:
            counts[w] += 1
        for word, count in counts.items():
            freq_buckets[count].append(word)

        res, j = [''] * k, 0
        for freq, words in reversed(freq_buckets.items()):
            words.sort()
            for w in words:
                res[j] = w
                j += 1
                if j >= k:
                    return res
        return res
