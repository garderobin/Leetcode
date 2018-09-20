# coding=utf-8
from abc import ABCMeta, abstractmethod
from bisect import bisect_left
from collections import defaultdict


class NumberOfMatchingSubsequences:
    """
    其实这题非常适合map-reduce
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def num_matching_subsequences(self, S, words):
        """
        :type S: str
        :type words: List[str]
        :rtype: int
        """


class NumberOfMatchingSubsequenceImplParallelCharIterator(NumberOfMatchingSubsequences):
    """
    一个更高端的并行计算解法：
    https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation
    make all words waiting for a space character and prepend a space character to S:
    This avoids some code duplication and now the solution would also handle empty words if they were allowed
    (the problem says all words are non-empty).
    妙用iterator! # TODO 需要反复对python这种iterator的写法更熟悉
    """
    def num_matching_subsequences(self, S, words):
        waiting = defaultdict(list, {' ': map(iter, words)})
        for c in ' ' + S:
            for it in waiting.pop(c, ()):
                waiting[next(it, None)].append(it)
        return len(waiting[None])


class NumberOfMatchingSubsequencesImpl(NumberOfMatchingSubsequences):
    @staticmethod
    def get_inverted_indexing(t):
        inverted_indexes = {}  # 不用defaultdict是为了方便查keyError来做后续的剪枝
        for index, element in enumerate(t):
            if element in inverted_indexes:
                inverted_indexes[element].append(index)
            else:
                inverted_indexes[element] = [index]
        return inverted_indexes

    @staticmethod
    def is_subsequence(s, inverted_indexing):
        prev = 0
        for i, c in enumerate(s):
            if c not in inverted_indexing:  # 这句非常重要！不仅仅是优化，而且是防止c不在idx中产生的keyError!
                return False
            j = bisect_left(inverted_indexing[c], prev)
            if j == len(inverted_indexing[c]):
                return False
            prev = inverted_indexing[c][j] + 1
        return True

    def num_matching_subsequences(self, S, words):
        idx = self.get_inverted_indexing(S)
        return sum(int(self.is_subsequence(s, idx)) for s in words)
