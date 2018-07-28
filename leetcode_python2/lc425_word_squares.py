from abc import ABCMeta, abstractmethod


class WordSquares:
    __metaclass__ = ABCMeta

    @abstractmethod
    def word_squares(self, words):
        """
        :type words: List[str]
        :rtype: List[List[str]]
        """


class WordSquareImpl(WordSquares):
    def word_squares(self, words):
        pass