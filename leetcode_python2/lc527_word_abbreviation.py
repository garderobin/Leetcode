# coding=utf-8
from abc import ABCMeta, abstractmethod


class WordAbbreviation:
    __metaclass__ = ABCMeta

    @abstractmethod
    def words_abbreviation(self, words):
        """
        :type words: List[str]
        :rtype: List[str]
        """


class WordAbbreviationImplHash(WordAbbreviation):

    @staticmethod
    def get_abbreviation(word, prefix_length):
        if not word or len(word) - prefix_length < 3:
            return word

        return '%s%d%s' % (word[:prefix_length], len(word) - prefix_length - 1, word[-1])

    def __init__(self):
        self.prefix_lengths = []
        self.abbr_word_indexes = dict()
        self.shared_abbr_word_indexes = dict()
        self.result = []

    def words_abbreviation(self, words):
        self.init_abbr(words)
        self.fix_dup(words)
        return self.result

    def init_abbr(self, words):
        """
        Init abbreviation related parameters without considering duplications.
        :param words: List[str]
        :return: void
        """
        self.prefix_lengths = []
        self.abbr_word_indexes, self.shared_abbr_word_indexes = dict(), dict()
        self.result = []

        for word_index, word in enumerate(words):
            self.prefix_lengths.append(1)
            abbr = self.get_abbreviation(word, 1)
            self.result.append(abbr)
            self.add_abbr_and_word_index(abbr, word_index)

    def fix_dup(self, words):
        unique = False
        while not unique:
            unique = True
            prev_shared_abbr_word_indexes = [word_indexes
                                             for word_indexes in self.shared_abbr_word_indexes.values()
                                             if len(word_indexes) > 1]

            self.abbr_word_indexes, self.shared_abbr_word_indexes = dict(), dict()

            for word_indexes in prev_shared_abbr_word_indexes:  # 这里绝对不能边读边写同一个数组！
                unique = False
                for index in word_indexes:
                    self.prefix_lengths[index] += 1
                    abbr = self.get_abbreviation(words[index], self.prefix_lengths[index])
                    self.result[index] = abbr
                    self.add_abbr_and_word_index(abbr, index)

    def add_abbr_and_word_index(self, abbr, word_index):
        if abbr in self.abbr_word_indexes:
            self.abbr_word_indexes[abbr].append(word_index)
            self.shared_abbr_word_indexes[abbr] = self.abbr_word_indexes[abbr]
        else:
            self.abbr_word_indexes[abbr] = [word_index]
