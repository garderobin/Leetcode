# coding=utf-8
from abc import ABCMeta, abstractmethod


class WordSearch2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_words(self, board, words):
        """
        :type board: List[List[str]]
        :type words: List[str]
        :rtype: List[str]
        """


class WordSearch2ImplTrie(WordSearch2):
    """
    TODO
    Time:
    Space: O(sum_of_words_lens_in_dict + NM)
    """
    DIRECTIONS = [(1, 0), (0, -1), (-1, 0), (0, 1)]

    @staticmethod
    def build_trie_from_words(words):
        trie = Trie()
        for word in words:
            trie.add(word)
        return trie

    @staticmethod
    def inside(board, x, y):
        return 0 <= x < len(board) and 0 <= y < len(board[0])

    def find_words(self, board, words):
        if board is None or len(board) == 0:
            return []
        trie = self.build_trie_from_words(words)
        result = set([])  # 这一步不能是list! 而且这里不能写成result = {}, 还有, flatten visited非常容易出错，还不如存二维set
        rows, cols = len(board), len(board[0])
        for i in xrange(rows):
            for j in xrange(cols):
                self.search(board, i, j, trie.get_root.sons.get(board[i][j]), {(i, j)}, result)
                # if len(result) == len(words):  # 这一句并不能起到很好的剪枝效果
                #     break
        return list(result)

    def search(self, board, x, y, node, visited, result):
        if not node:
            return

        if node.has_word_finished:
            result.add(node.word)  # Don't return here!

        for delta_x, delta_y in self.DIRECTIONS:
            x_, y_ = x + delta_x, y + delta_y
            if self.inside(board, x_, y_) and (x_, y_) not in visited:
                visited.add((x_, y_))
                self.search(board, x_, y_, node.sons.get(board[x_][y_]), visited, result)
                visited.remove((x_, y_))


class Trie(object):
    def __init__(self):
        self.__root = TrieNode.init_root()

    @property
    def get_root(self):
        return self.__root

    def add(self, word):
        node = self.__root
        for c in word:
            if c not in node.sons:
                node.sons[c] = TrieNode.init_with_val(c)
            node = node.sons[c]
        node.has_word_finished = True
        node.word = word


class TrieNode(object):
    @classmethod
    def init_root(cls):
        return cls(None, dict(), False, '')

    @classmethod
    def init_with_val(cls, val):
        return cls(val, dict(), False, '')

    def __init__(self, val, sons, has_word_finished, word):
        self.val = val
        self.sons = sons
        self.has_word_finished = has_word_finished
        self.word = word


if __name__ == "__main__":
    sol = WordSearch2ImplTrie()
    board = [["a", "a", "a", "a"], ["a", "a", "a", "a"], ["a", "a", "a", "a"]]
    words = ["aaaaaaaaaaaa", "aaaaaaaaaaaaa", "aaaaaaaaaaab"]
    result = sol.find_words(board, words)
    print result
