# coding=utf-8
class TrieNode(object):
    def __init__(self, val='', word='', sons=None):
        self.val = val
        self.word = word
        self.sons = sons if sons else dict()


class Trie(object):
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):    # todo 几乎Trie的题都会用到的方法，要背下来，经常复习
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: void
        """
        node = self.root
        for c in word:
            if c not in node.sons:
                node.sons[c] = TrieNode(c)
            node = node.sons[c]
        node.word = word

    def search(self, word):     # 不是每道题都用得到
        """
        Returns if the word is in the trie.
        :type word: str
        :rtype: bool
        """
        if not word:
            return False
        node = self.root
        for c in word:
            if c not in node.sons:
                return False
            node = node.sons[c]
        return bool(node.word)

    def starts_with(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        node = self.root
        for c in prefix:
            if c not in node.sons:
                return False
            node = node.sons[c]
        return True
