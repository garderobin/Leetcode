# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict
from string import ascii_lowercase


class WordLadder2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def findLadders(self, beginWord, endWord, wordList):
        """
        :type beginWord: str
        :type endWord: str
        :type wordList: List[str]
        :rtype: List[List[str]]
        """


class WordLadder2ImplBacktrackWithIteration(WordLadder2):
    """
    TODO: 需要结合word ladder 1 复习， 自己重写
    https://leetcode.com/problems/word-ladder-ii/discuss/40458/Use-defaultdict-for-traceback-and-easy-writing-20-lines-python-code
    """
    def findLadders(self, beginWord, endWord, wordList):
        parents = self.find_parents(beginWord, endWord, set(wordList))
        return self.generate_transformations(beginWord, endWord, parents)

    @staticmethod
    def find_parents(beginWord, endWord, word_set):
        level = {beginWord}
        parents = defaultdict(set)

        while level and endWord not in parents:
            next_level = defaultdict(set)
            for node in level:
                for char in ascii_lowercase:
                    for i in range(len(beginWord)):
                        child = node[:i] + char + node[i + 1:]
                        if child in word_set and child not in parents:
                            next_level[child].add(node)
            level = next_level
            parents.update(next_level)

        return parents

    @staticmethod
    def generate_transformations(beginWord, endWord, parents):
        res = [[endWord]]
        while res and res[0][0] != beginWord:
            res = [[p] + r for r in res for p in parents[r[0]]]
        return res


if __name__ == '__main__':
    sol = WordLadder2ImplBacktrackWithIteration()
    sol.findLadders("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"])
