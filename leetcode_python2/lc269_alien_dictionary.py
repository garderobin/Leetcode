# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict, deque
from itertools import izip


class AlienDictionary:
    __metaclass__ = ABCMeta

    @abstractmethod
    def alien_order(self, words):
        """
        :type words: List[str]
        :rtype: str
        """


class AlienDictionaryImplBFS2(AlienDictionary):
    """
    Time: O(len(words) * avg_word_length))
    Space: O(1) (因为只有26个字母）
    """
    def __init__(self):
        self.words = []
        self.succ = defaultdict(set)
        self.pred = defaultdict(set)
        self.charset = set([])

    def alien_order(self, words):
        if not words:
            return ''
        self.initialize(words)
        self.build_relationship()
        order_list = self.generate_order_list()
        return ''.join(order_list) if self.is_valid_order_list(order_list) else ''

    def initialize(self, words):
        """
        Time: O(len(words) * avg_word_length))
        """
        self.succ = defaultdict(set)
        self.pred = defaultdict(set)
        self.words = words

        for w in words:
            for c in w:
                self.charset.add(c)

    def build_relationship(self):
        """
        Time: O(len(words) * avg_word_length))
        """
        n = len(self.words)
        for i in xrange(n - 1):
            w1, w2 = self.words[i], self.words[i + 1]
            for c1, c2 in izip(w1, w2):
                if c1 != c2:
                    self.succ[c1].add(c2)
                    self.pred[c2].add(c1)
                    break

    def generate_order_list(self):
        """
        Time: O(len(words) * avg_word_length))
        """
        free_chars = deque(self.charset - set(self.pred.keys()))
        order_list = []
        while free_chars:
            c1 = free_chars.popleft()
            order_list.append(c1)
            for c2 in self.succ[c1]:
                self.pred[c2].discard(c1)
                if not self.pred[c2]:
                    free_chars.append(c2)

        return order_list

    def is_valid_order_list(self, order_list):
        return order_list and len(order_list) == len(self.charset)
