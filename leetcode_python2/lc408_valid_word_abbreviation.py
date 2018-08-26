# coding=utf-8
from abc import ABCMeta, abstractmethod


class ValidWordAbbreviation:
    __metaclass__ = ABCMeta

    @abstractmethod
    def valid_word_abbreviation(self, word, abbr):
        """
        :type word: str
        :type abbr: str
        :rtype: bool
        """


class ValidWordAbbreviationImplTwoPointers(ValidWordAbbreviation):
    """

    一定要问面试官，0开头是否具有合理性？
    """
    def valid_word_abbreviation(self, word, abbr):
        word_index, cur_abbr_start_index = 0, -1
        for abbr_index, c in enumerate(abbr):  # 注意enumerate使用
            if c.isdigit():
                if cur_abbr_start_index < 0:
                    if c == '0':
                        return False
                    cur_abbr_start_index = abbr_index
            else:
                word_index += int(abbr[cur_abbr_start_index:abbr_index]) if cur_abbr_start_index >= 0 else 0
                # 一定要在加完word_index值以后立刻进行边界检查！
                if word_index >= len(word) or c != word[word_index]:
                    return False
                cur_abbr_start_index = -1
                word_index += 1

        return cur_abbr_start_index < 0 or len(word) - word_index == int(abbr[cur_abbr_start_index:])

    def valid_word_abbreviation_digit_recorder(self, word, abbr):
        word_index, cur_abbr_start_index = 0, -1
        for abbr_index, c in enumerate(abbr):  # 注意enumerate使用
            if c.isdigit():
                if cur_abbr_start_index < 0:
                    cur_abbr_start_index = abbr_index
                    if c == '0':
                        return False
            else:
                word_index += int(abbr[cur_abbr_start_index:abbr_index]) if cur_abbr_start_index >= 0 else 0
                # 一定要在加完word_index值以后立刻进行边界检查！
                if word_index >= len(word) or c != word[word_index]:
                    return False
                cur_abbr_start_index = -1
                word_index += 1

        return cur_abbr_start_index < 0 or len(word) - word_index == int(abbr[cur_abbr_start_index:])