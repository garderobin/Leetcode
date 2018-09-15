from abc import ABCMeta, abstractmethod


class SentenceSimilarity:
    __metaclass__ = ABCMeta

    @abstractmethod
    def are_sentences_similar(self, words1, words2, pairs):
        """
        :type words1: List[str]
        :type words2: List[str]
        :type pairs: List[List[str]]
        :rtype: bool
        """


class SentenceSimilarityImpl(SentenceSimilarity):
    """
    Time: O(N+P)
    Space: O(P)
    """

    def are_sentences_similar(self, words1, words2, pairs):
        """
        :type words1: List[str]
        :type words2: List[str]
        :type pairs: List[List[str]]
        :rtype: bool
        """
        if ((not words1) ^ (not words2)) or len(words1) != len(words2):
            return False

        pairs_dict = self.build_pairs_dict(pairs)
        for i in xrange(len(words1)):
            if not self.are_words_similar(words1[i], words2[i], pairs_dict):
                return False
        return True

    def are_words_similar(self, word1, word2, pairs_dict):
        if word1 == word2:
            return True
        elif not pairs_dict:
            return False
        else:
            return word1 in pairs_dict and word2 in pairs_dict[word1]

    def build_pairs_dict(self, pairs):
        pairs_dict = {}

        def add_similarity(a, b):
            if a in pairs_dict:
                pairs_dict[a].add(b)
            else:
                pairs_dict[a] = {b}

        for word1, word2 in pairs:
            add_similarity(word1, word2)
            add_similarity(word2, word1)

        return pairs_dict
