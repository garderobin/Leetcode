from abc import ABCMeta, abstractmethod


class ReverseOnlyLetters:
    __metaclass__ = ABCMeta

    @abstractmethod
    def reverse_only_letters(self, S):
        """
        :type S: str
        :rtype: str
        """


class ReverseOnlyLettersImpl1(ReverseOnlyLetters):
    """
    Time: O(n)
    Space: O(n)
    """
    def reverse_only_letters(self, S):
        letters, rs = [c for c in S if c.isalpha()], []
        for c in S:
            if c.isalpha():
                rs.append(letters.pop())
            else:
                rs.append(c)

        return ''.join(rs)
