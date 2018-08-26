from abc import ABCMeta, abstractmethod
from collections import Counter, defaultdict


class ValidWordAbbreviation:
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_unique(self, word):
        """
        :type word: str
        :rtype: bool
        """


class ValidWordAbbreviationImpl2(ValidWordAbbreviation):
    def __init__(self, dictionary):
        """
        :type dictionary: List[str]
        """
        self.word_set = set(dictionary)
        self.abbr_count_map = defaultdict(int)
        for word in self.word_set:
            self.abbr_count_map[self.word_to_abbreviation(word)] += 1

    @staticmethod
    def word_to_abbreviation(word):
        if not word:
            return ''
        if len(word) < 3:
            return word
        return '%s%d%s' % (word[0], len(word) - 2, word[-1])

    def is_unique(self, word):
        """
        :type word: str
        :rtype: bool
        """
        abbr = self.word_to_abbreviation(word)
        if word in self.word_set:
            return self.abbr_count_map[abbr] == 1
        else:
            return abbr not in self.abbr_count_map


class ValidWordAbbreviationImpl(ValidWordAbbreviation):
    def __init__(self, dictionary):
        """
        :type dictionary: List[str]
        """
        self.word_set = set(dictionary)
        self.abbr_count_map = dict(Counter([self.word_to_abbreviation(word) for word in self.word_set]))

    @staticmethod
    def word_to_abbreviation(word):
        if not word:
            return ''
        if len(word) < 3:
            return word
        return '%s%d%s' % (word[0], len(word) - 2, word[-1])

    def is_unique(self, word):
        """
        :type word: str
        :rtype: bool
        """
        abbr = self.word_to_abbreviation(word)
        if word in self.word_set:
            return self.abbr_count_map[abbr] == 1
        else:
            return abbr not in self.abbr_count_map

