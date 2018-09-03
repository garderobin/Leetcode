# coding=utf-8
from abc import ABCMeta, abstractmethod
from bisect import bisect_left


class IsSubSequence:
    """
    这个题值得重点看，G/F双高频，由浅入深涉及greedy, dp, binary_search, Trie, Inverted index (系统设计层面了）
    自带一堆follow up, 还有一个更高阶的follow up: lc792
    """
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_subsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """


class IsSubSequenceImplFollowUpTrie(IsSubSequence):
    """
    Follow up question:
    If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B,
    and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

    这题用inverted index + binary search 应对母本固定的情况，同时引进Trie作为多次查询的缓存。
    """

    """
    For follow-up: inverted indexing + binary search + Trie
    """

    @staticmethod
    def get_inverted_indexing(t):
        inverted_indexes = {}       # 不用defaultdict是为了方便查keyError来做后续的剪枝
        for index, element in enumerate(t):
            if element in inverted_indexes:
                inverted_indexes[element].append(index)
            else:
                inverted_indexes[element] = [index]
        return inverted_indexes

    def __init__(self):
        self.inverted_indexing_maps = {}

    def is_subsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        if t not in self.inverted_indexing_maps:
            self.inverted_indexing_maps[t] = self.get_inverted_indexing(t)

        idx = self.inverted_indexing_maps[t]
        # TODO: implementation using trie
        # TODO: ask Yiwei/Shiqi to verify the trie answer.
        return True


class IsSubSequenceImplFollowUp(IsSubSequence):
    """
    Follow up question:
    If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B,
    and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?

    这题用inverted index + binary search 应对母本固定的情况，算法和系统设计的有机结合。
    """

    """
    For follow-up: inverted indexing + binary search
    Time: O(N) for pre-processing (building inverted index of t), O(MlogN) for query per s.
    """

    @staticmethod
    def get_inverted_indexing(t):
        inverted_indexes = {}       # 不用defaultdict是为了方便查keyError来做后续的剪枝
        for index, element in enumerate(t):
            if element in inverted_indexes:
                inverted_indexes[element].append(index)
            else:
                inverted_indexes[element] = [index]
        return inverted_indexes

    def __init__(self):
        self.inverted_indexing_maps = {}

    def is_subsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        if t not in self.inverted_indexing_maps:
            self.inverted_indexing_maps[t] = self.get_inverted_indexing(t)

        idx = self.inverted_indexing_maps[t]
        prev = 0  # search index in t
        for i, c in enumerate(s):
            if c not in idx:            # 这句非常重要！不仅仅是优化，而且是防止c不在idx中产生的keyError!
                return False
            j = bisect_left(idx[c], prev)
            if j == len(idx[c]):
                return False
            prev = idx[c][j] + 1
        return True


class IsSubSequenceImplGreedyFastestUsingFind(IsSubSequence):
    """
    find函数要知道会用，当然面试的时候要问面试官是不是我不能用这种库函数来做
    """
    def is_subsequence(self, s, t):
        idx = 0
        for c in s:
            idx = t.find(c, idx)
            if idx == -1:
                return False
            idx += 1
        return True


class IsSubSequenceImplGreedy(IsSubSequence):

    def is_subsequence(self, s, t):
        if not s:
            return True
        if (not t) or len(s) > len(t):
            return False
        i, j = 0, 0
        unique_chars_t = set(t)
        while i < len(s) and j < len(t):
            if s[i] not in unique_chars_t:
                return False
            if s[i] == t[j]:
                i += 1
            j += 1
        return i == len(s)


class IsSubSequenceImplDP(IsSubSequence):
    """
    匹配型动态规划，Time Limit Exceeded!
    虽然对于这题是个蠢笨解法，但是用来复习匹配型动态规划模板和常见错误是很好的热身
    """

    def is_subsequence(self, s, t):
        if not s:
            return True
        if not t:
            return False

        unique_chars_t = set(t)
        for cs in list(set(s)):
            if cs not in unique_chars_t:
                return False

        n, m = len(s), len(t)
        f = [[True] * (m + 1), [False] * (m + 1)]  # f[i][j] = is_subsequence(s[:i], t[:j])
        for i in xrange(1, n + 1):
            f[i % 2][0] = False     # 这句少了就悲剧了
            for j in xrange(1, m + 1):
                f[i % 2][j] = f[i % 2][j - 1] or (f[(i - 1) % 2][j - 1] and s[i - 1] == t[j - 1])
        return f[n % 2][m]

    def is_subsequence_draft(self, s, t):
        if not s:
            return True
        elif not t:
            return False
        else:
            n, m = len(s), len(t)
            f = [[True] * (m + 1), [False] * (m + 1)]  # f[i][j] = is_subsequence(s[:i], t[:j])
            for i in xrange(1, n + 1):
                f[i % 2][0] = False     # 这一句万万不能少！在列举初始化条件的时候就要用变量表示！
                for j in xrange(1, m + 1):
                    f[i % 2][j] = f[(i - 1) % 2][j] or f[i % 2][j - 1] or (f[(i - 1) % 2][j - 1] and s[i-1] == t[j-1])
            return f[n % 2][m]
