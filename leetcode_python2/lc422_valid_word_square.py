# coding=utf-8
from abc import ABCMeta, abstractmethod


class ValidWordSquare:
    __metaclass__ = ABCMeta

    @abstractmethod
    def valid_word_square(self, words):
        """
        :type words: List[str]
        :rtype: bool
        """


class ValidWordSquareImpl(ValidWordSquare):
    def valid_word_square(self, words):
        if len(words) != len(words[0]):
            return False

        n = len(words)
        for i in xrange(n):
            if len(words[i]) > n:  # 这种情况很容易楼检查！！！
                return False

            for j in xrange(i + 1, n):
                if len(words[i]) <= j:
                    if len(words[j]) > i:
                        return False
                else:
                    if len(words[j]) <= i or words[i][j] != words[j][i]:
                        return False

        return True
