class Trie(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.__root = TrieNode.init_root()

    def insert(self, word):
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: void
        """
        node = self.__root
        for index, char in enumerate(word):
            if char not in node.sons:
                node.sons[char] = TrieNode.init_with_val(char)
            node = node.sons[char]
        node.has_word_finished = True

    def search(self, word):
        """
        Returns if the word is in the trie.
        :type word: str
        :rtype: bool
        """
        node = self.__root
        for char in word:
            if char not in node.sons:
                return False
            node = node.sons[char]
        return node.has_word_finished

    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        node = self.__root
        for char in prefix:
            if char not in node.sons:
                return False
            node = node.sons[char]
        return True

# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)


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
        return '%s, %s, %d' % (self.val, self.sons, self.has_word_finished)
