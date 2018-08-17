# coding=utf-8
import heapq
from collections import deque, defaultdict, Counter

from lc271_encode_and_decode_strings import EncodeAndDecodeStringsImpl


class Point:
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b


class Solution:
    """
    @param l1: top-left coordinate of first rectangle
    @param r1: bottom-right coordinate of first rectangle
    @param l2: top-left coordinate of second rectangle
    @param r2: bottom-right coordinate of second rectangle
    @return: true if they are overlap or false
    """
    def doOverlap(self, l1, r1, l2, r2):
        if l1.x < l2.x:
            rec_left, rec_right = (l1, r1), (l2, r2)
        else:
            rec_left, rec_right = (l2, r2), (l1, r1)
        rec_right_is_on_right_of_rec_left = rec_left[1].x <= rec_right[0].x
        rec_right_is_on_top_of_rec_left = rec_left[0].y <= rec_right[1].y
        rec_right_is_on_bottom_of_rec_left = rec_left[1].y >= rec_right[0].y
        return not (rec_right_is_on_right_of_rec_left
                    or rec_right_is_on_top_of_rec_left
                    or rec_right_is_on_bottom_of_rec_left)


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


class TestObj(object):
    def __init__(self, n):
        self.father = [x for x in xrange(1, n + 1)]
        self.components = set(self.father)


if __name__ == "__main__":
    obj = TestObj(5)
    print 'father', obj.father
    print 'components', obj.components

    obj.father.append(100)
    print 'father', obj.father
    print 'components', obj.components




