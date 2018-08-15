# coding=utf-8
from abc import ABCMeta, abstractmethod


def knows(a, b):
    return a == b


class FindTheCelebrity:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_celebrity(self, n):
        """
        :type n: int
        :rtype: int
        """


class FineTheCelebrityImplSingleCandidateWithCache(FindTheCelebrity):
    """
    https://www.jiuzhang.com/solution/find-the-celebrity/
    利用传递在第一遍筛选的时候就找出唯一可能的candidate
    Time: O(N)
    Space: O(1)
    """
    def find_celebrity(self, n):
        candidate = 0
        visited_relations = {}
        for i in xrange(1, n):
            candidate_knows_guest = knows(candidate, i)
            visited_relations[(candidate, i)] = candidate_knows_guest
            if candidate_knows_guest:
                candidate = i

        for i in xrange(n):
            if i != candidate and \
                    (self.knows_after_cache(candidate, i, visited_relations) or
                     not self.knows_after_cache(i, candidate, visited_relations)):
                return -1
        return candidate

    @staticmethod
    def knows_after_cache(a, b, cache):
        return ((a, b) in cache and cache[(a, b)]) or knows(a, b)


class FineTheCelebrityImplJiuzhang(FindTheCelebrity):
    """
    https://www.jiuzhang.com/solution/find-the-celebrity/
    利用传递在第一遍筛选的时候就找出唯一可能的candidate
    Time: O(N)
    Space: O(1)
    """
    def find_celebrity(self, n):
        candidate = 0
        for i in xrange(1, n):
            if knows(candidate, i):
                candidate = i

        for i in xrange(n):
            if i != candidate and (knows(candidate, i) or not knows(i, candidate)):
                return -1
        return candidate


class FineTheCelebrityImplHash(FindTheCelebrity):
    """
    Time: O(N)
    Space: O(N)
    """
    def find_celebrity(self, n):
        celebrity_candidates = set([x for x in xrange(n)])
        left, right = 0, 1
        visited_relations = {}
        while right < n:
            left_knows_right, right_knows_left = knows(left, right), knows(right, left)
            visited_relations[(left, right)] = left_knows_right
            visited_relations[(right, left)] = right_knows_left
            if left_knows_right and not right_knows_left:
                celebrity_candidates.remove(left)
                left = right
                right += 1
            elif (not left_knows_right) and right_knows_left:
                celebrity_candidates.remove(right)
                right += 1
            else:
                celebrity_candidates.remove(left)
                celebrity_candidates.remove(right)
                left = right + 1
                right += 2
        if len(celebrity_candidates) != 1:
            return -1
        else:
            last_candidate = list(celebrity_candidates)[0]
            for person in xrange(n):
                if person != last_candidate and (not self.knows_after_cache(person, last_candidate, visited_relations)
                                                 or self.knows_after_cache(last_candidate, person, visited_relations)):
                    return -1
            return last_candidate

    @staticmethod
    def knows_after_cache(a, b, cache):
        return ((a, b) in cache and cache[(a, b)]) or knows(a, b)
