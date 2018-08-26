# coding=utf-8
from abc import ABCMeta, abstractmethod


class DecodeString(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def decode_string(self, s):
        """
        :type s: str
        :rtype: str
        """


class DecodeStringImplJiuzhangStack(DecodeString):
    """
    Time: O(N)
    Space: O(N)
    https://www.jiuzhang.com/solution/decode-string/
    把所有字符一个个放到 stack 里， 如果碰到了 ]，就从 stack 找到对应的字符串和重复次数，decode 之后再放回 stack 里
    """
    def decode_string(self, s):
        stack = []
        for c in s:
            if c == ']':
                strs = []
                while stack and stack[-1] != '[':
                    strs.append(stack.pop())

                # skip '['
                stack.pop()

                repeats = 0
                base = 1
                while stack and stack[-1].isdigit():
                    repeats += (ord(stack.pop()) - ord('0')) * base
                    base *= 10
                stack.append(''.join(reversed(strs)) * repeats)
            else:
                stack.append(c)

        return ''.join(stack)


class DecodeStringImplMyStack(DecodeString):
    """
    双栈的想法从根本上就是错的，因为哪个数字和那个字符串匹配谁先谁后根本就不能完全正确判断，逻辑不能自洽：
    Input:
    "3[z]2[2[y]pq4[2[jk]e1[f]]]ef"
    Output:
    "zzzyypqjkjkefefefefefefefefef"
    Expected:
    "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"
    因此下面这个解法是错的，只有单栈在这里才可以用！
    另外面对难题，不要在乎什么start index这种省时间的小伎俩，堆栈本就容易出错，
    先用最少量的代码把正确性保证了再说！
    """
    @staticmethod
    def is_char_digit(c):
        return 47 < ord(c) < 58

    def decode_string(self, s):
        if not s:
            return ''
        number_start, string_start = -1, -1
        number_stack, string_stack = [], []

        is_last_push_digit = False

        for i, c in enumerate(s):
            if self.is_char_digit(c):
                if number_start < 0:
                    number_start = i
                if string_start >= 0:
                    string_stack.append(s[string_start:i])
                    is_last_push_digit = False
                    string_start = -1
            elif c == '[':
                # finish calc number and push the number into stack
                if number_start >= 0:
                    number_stack.append(int(s[number_start:i]))
                    is_last_push_digit = True
                number_start = -1
                string_start = -1
            elif c == ']':
                # POP stack, finish a pattern
                if string_start < 0:  # e.g. 3[b2[e]] => ns: 3, ss: bee => ss: beebeebee
                    repeat = number_stack.pop()
                    string = string_stack.pop()
                    string_stack.append(''.join([string for _ in xrange(repeat)]))
                else:
                    # 3[a4[b]2[cd]ef] => ns: [3,4] ss: [a], cur:b] => ns: [3], ss: [abbbb]
                    # => ns: [3,2], ss: [abbbb], cur: cd] => ns: [3], ss:[abbbbcdcd], false
                    # => ns: [3], ss:[abbbbcdcd], cur: ef] => ns: [3], ss:[abbbbcdcdef], false
                    # a4[b] => ns: [4], ss: [a], cur: b] => ns: [], ss: [abbbb]
                    cur_str = s[string_start:i]
                    repeat = number_stack.pop() if is_last_push_digit else 1
                    prefix = '' if not string_stack else string_stack.pop()
                    string_stack.append(prefix + ''.join([cur_str for _ in xrange(repeat)]))

                is_last_push_digit = False
                number_start = -1
                string_start = -1
            else:
                if string_start < 0:
                    string_start = i

        # handle last unresolved string. 1: 3[abbbbcdcdef], 2: 2[ab]ef => abab ef
        # print number_stack, string_stack, string_start
        while number_stack:
            repeat = number_stack.pop()
            string = string_stack.pop()
            string_stack.append(''.join([string for _ in xrange(repeat)]))

        if string_start >= 0:
            string_stack.append(s[string_start:])

        return ''.join(string_stack)
