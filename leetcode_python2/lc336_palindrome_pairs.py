from abc import ABCMeta, abstractmethod


class PalindromePairs:
    __metaclass__ = ABCMeta

    @abstractmethod
    def palindrome_pairs(self, words):
        """
        :type words: List[str]
        :rtype: List[List[int]]
        """


class PalindromePairsImplCheckSuffix(PalindromePairs):
    @staticmethod
    def is_palindrome(w1):
        return w1 == w1[::-1]

    def palindrome_pairs(self, words):
        word_index_dict = {word: i for i, word in enumerate(words)}
        result = []
        for word, idx in word_index_dict.iteritems():
            for j in xrange(len(word) + 1):
                prefix, suffix = word[:j], word[j:]

                if j > 0 and self.is_palindrome(prefix):    # check only once for the case that word[::-1] exists
                    left = suffix[::-1]
                    if left != word and left in word_index_dict:  # we have the unique guarantee
                        result.append([word_index_dict[left], idx])
                if self.is_palindrome(suffix):
                    right = prefix[::-1]
                    if right != word and right in word_index_dict:
                        result.append([idx, word_index_dict[right]])
        return result
