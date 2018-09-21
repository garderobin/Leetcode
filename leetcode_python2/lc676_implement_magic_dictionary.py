# coding=utf-8
class MagicDictionary(object):

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = TrieNode()

    def buildDict(self, words):
        """
        Build a dictionary through a list of words
        :type words: List[str]
        :rtype: void
        """
        for w in words:
            self.add(w)

    def search(self, word):
        """
        Returns if there is any word in the trie that equals to the given word after modifying exactly one character
        :type word: str
        :rtype: bool
        """
        if not word:
            return False

        node = self.root
        for i, c in enumerate(word):
            if c not in node.sons:
                return self.replace(word, node, i)
            else:
                if self.replace(word, node, i):
                    return True
                node = node.sons[c]
        return False

    def replace(self, word, father, replace_index):
        for candidate in father.sons.itervalues():
            if candidate.val != word[replace_index]:
                if self.match(candidate, word, replace_index + 1):
                    return True
        return False

    def add(self, word):
        node = self.root
        for c in word:
            if c not in node.sons:
                node.sons[c] = TrieNode(c)
            node = node.sons[c]
        node.word = word

    def match(self, father, word, start):
        """
        TODO match 函数要熟练写
        """
        cur_node = father
        for j in xrange(start, len(word)):
            if word[j] not in cur_node.sons:
                return False
            else:
                cur_node = cur_node.sons[word[j]]
        return bool(cur_node.word)


class TrieNode(object):
    def __init__(self, val='', word='', sons=None):
        self.val = val
        self.word = word
        self.sons = sons if sons else dict()

# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dict)
# param_2 = obj.search(word)
