# coding=utf-8
from abc import ABCMeta, abstractmethod


class WordBreak2(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def word_break(self, s, word_dictionary):
        """
        :type s: str
        :type word_dictionary: List[str]
        :rtype: List[str]
        """


class WordBreak2ImplMemoSearchMoreParameters(WordBreak2):
    """
    Time limit exeeded
    """
    def word_break(self, s, word_dictionary):
        word_set = set(word_dictionary)     # 这一步很重要，能大大节约时间
        memo = dict()              # 记忆化搜索不要用defaultdict，这样没有办法判断到底有没有记忆过
        return self.dfs(s, word_set, memo, 0, len(s))

    def dfs(self, s, word_set, memo, start, end):
        cur_str = s[start:end]
        if cur_str in memo:
            return memo[cur_str]

        break_cur = []
        if cur_str in word_set:
            break_cur.append(cur_str)

        for i in xrange(start + 1, end):
            word_left = s[start:i]
            if word_left in word_set:         # 千万不要两边都break, 没必要，会超时
                for right in self.dfs(s, word_set, memo, i, end):
                    if right:
                        break_cur.append('%s %s' % (word_left, right))

        memo[cur_str] = break_cur
        return break_cur


class WordBreak2ImplMemorizedSearch(WordBreak2):
    """
    Time limit exeeded
    """
    def __init__(self):
        self.s = ''
        self.word_dict = ['']
        self.memo = dict()

    def word_break(self, s, word_dict):
        self.s = s
        self.word_dict = word_dict
        self.memo = dict()              # 记忆化搜索不要用defaultdict，这样没有办法判断到底有没有记忆过
        return self.dfs(0, len(s))

    def dfs(self, start, end):
        if start == end:
            return None

        cur_str = self.s[start:end]
        if cur_str in self.memo:
            return self.memo[cur_str]

        break_cur = []
        if cur_str in self.word_dict:
            break_cur.append(cur_str)

        for i in xrange(start + 1, end):
            word_left = self.s[start:i]
            if word_left in self.word_dict:         # 千万不要两边都break, 没必要，会超时
                for right in self.dfs(i, end):
                    if right:
                        break_cur.append('%s %s' % (word_left, right))

        self.memo[cur_str] = break_cur
        return break_cur


if __name__ == "__main__":
    s = "catsanddog"
    word_dict = ["cat","cats","and","sand","dog"]
    sol = WordBreak2ImplMemorizedSearch()
    print sol.word_break(s, word_dict)
