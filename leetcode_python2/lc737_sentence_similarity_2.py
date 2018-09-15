from abc import ABCMeta, abstractmethod


class SentenceSimilarity2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def are_sentences_similar_two(self, words1, words2, pairs):
        """
        :type words1: List[str]
        :type words2: List[str]
        :type pairs: List[List[str]]
        :rtype: bool
        """


class SentenceSimilarity2ImplUnionFine(SentenceSimilarity2):
    def __init__(self):
        self.fathers_dict = {}

    def are_sentences_similar_two(self, words1, words2, pairs):
        if ((not words1) ^ (not words2)) or len(words1) != len(words2):
            return False
        self.build_fathers_dict(pairs)
        for i in xrange(len(words1)):
            if not self.are_words_similar(words1[i], words2[i]):
                return False
        return True

    def build_fathers_dict(self, pairs):
        self.fathers_dict = {}

        for word1, word2 in pairs:
            self.union(word1, word2)

        return self.fathers_dict

    def are_words_similar(self, word1, word2):
        if word1 == word2:
            return True
        elif not self.fathers_dict:
            return False
        else:
            return self.find(word1) == self.find(word2)

    def union(self, a, b):
        self.fathers_dict[self.find(a)] = self.find(b)

    def find(self, word):
        node = word
        if node not in self.fathers_dict:
            self.fathers_dict[node] = node
        else:
            path = []
            while self.fathers_dict[node] != node:  # todo: possible fix me
                path.append(node)
                node = self.fathers_dict[node]
            for compress in path:
                self.fathers_dict[compress] = node
        return node
