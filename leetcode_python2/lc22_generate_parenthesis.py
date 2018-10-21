from abc import ABCMeta, abstractmethod


class GenerateParenthesis1:
    __metaclass__ = ABCMeta

    @abstractmethod
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """


class GenerateParenthesis1ImplBacktrack(GenerateParenthesis1):
    """
    https://leetcode.com/problems/generate-parentheses/description/
    Time: O(N^2)
    Space: O(N)
    """
    def generateParenthesis(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = []

        def generate(p, left, right):
            if right:
                if left:
                    generate(p + '(', left - 1, right)
                if right > left:
                    generate(p + ')', left, right - 1)
            else:
                result.append(p)

        generate('', n, n)
        return result
