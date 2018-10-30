from abc import ABCMeta, abstractmethod


class GenerateParenthesis2:
    """
       Follow up 1: given K types of parenthesis, each has n pairs.
       Generate all combinations of well-formed parentheses.
       E.g, n = 2, k = 3
       one possible combination is '(([{}]{})[])'
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def generateParenthesis(self, n, paren_list):
        """
        :type n: int
        :type paren_list: List[str]. e.g. ['()', '[]']
        :rtype: List[str]
        """


class GenerateParenthesis2ImplBacktrack(GenerateParenthesis2):
    """
    Follow up 1: given K types of parenthesis, each has n pairs.
    Generate all combinations of well-formed parentheses.
    E.g, n = 2, k = 3
    one possible combination is '(([{}]{})[])'

    Follow up 2: given K types of parenthesis, each has n pairs.
    output the number of all combinations of well-formed parentheses.
    """
    def generateParenthesis(self, n, paren_list):
        """
        :type paren_list: List[str]. e.g. ['()', '[]', '{}']
        :type n: int
        :rtype: List[str]
        """
        result = []
        k = len(paren_list)

        def generate(p, open_left, left, right, total_right):
            for i in xrange(k):
                if not total_right:
                    result.append(p)
                    return

                if not right[i]:
                    continue

                cl, cr = paren_list[i][0], paren_list[i][1]
                if left[i]:
                    next_left = left[:]
                    next_left[i] -= 1

                    next_open = open_left[:]
                    next_open.append(cl)

                    generate(p + cl, next_open, next_left, right, total_right)

                if right[i] > left[i] and open_left[-1] == cl:
                    next_right = right[:]
                    next_right[i] -= 1

                    next_open = open_left[:]
                    next_open.pop()

                    generate(p + cr, next_open, left, next_right, total_right - 1)

        generate('', [], [n] * k, [n] * k, n * k)
        return result


if __name__ == '__main__':
    sol = GenerateParenthesis2ImplBacktrack()
    paren = sol.generateParenthesis(2, ['()', '[]', '{}'])
    unique = set(paren)
    print len(paren), len(unique)
