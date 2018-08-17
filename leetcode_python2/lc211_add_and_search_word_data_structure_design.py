# coding=utf-8
class WordDictionary(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.__root = TrieNode.init_root()

    def addWord(self, word):
        """
        Adds a word into the data structure.
        :type word: str
        :rtype: void
        """
        if not word:
            return
        node = self.__root
        for char in word:
            if char not in node.sons:
                node.sons[char] = TrieNode.init_with_val(char)
            node = node.sons[char]
        node.has_word_finished = True

    def search(self, word):
        """
        Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
        :type word: str
        :rtype: bool
        """
        return word and self.match(word, self.__root, 0)

    def match(self, word, father, start_index):
        """
        Backtrack
        """
        node = father
        for index in xrange(start_index, len(word)):
            char = word[index]
            if char == '.':
                for son in node.sons.itervalues():  # 这里很容易把itervalues忘记了！
                    if self.match(word, son, index + 1):
                        return True
                return False  # 这里非常容易自作聪明地多加判断条件！把逻辑关系想清楚了很重要！
            else:
                if char not in node.sons:
                    return False
                else:
                    node = node.sons[char]
        return node.has_word_finished


# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)


class TrieNode(object):
    @classmethod
    def init_root(cls):
        return cls(None, dict(), False)

    @classmethod
    def init_with_val(cls, val):
        return cls(val, dict(), False)

    def __init__(self, val, sons, has_word_finished):
        self.val = val
        self.sons = sons
        self.has_word_finished = has_word_finished

    def __str__(self):
        return '%s %s %s' % (self.val, self.sons, self.has_word_finished)
