from abc import ABCMeta, abstractmethod


class NextGreaterElement3:
    __metaclass__ = ABCMeta

    @abstractmethod
    def next_greater_element(self, n):
        """
        :type n: int
        :rtype: int
        """
        s = list(str(n))
        m = len(s)

        j = -1
        for i in xrange(m - 2, -1, -1):
            cur = int(s[i])

            if cur < int(s[i + 1]):
                stack = s[i + 1:]
                j = i + 1

                while cur >= int(stack[-1]):
                    s[j] = stack.pop()
                    j += 1

                s[j] = s[i]
                s[i] = stack.pop()
                j += 1

                while j < m:
                    s[j] = stack.pop()
                    j += 1

                break
        else:
            return -1

        res = int(''.join(s))
        return res if res < 2147483648 else -1
