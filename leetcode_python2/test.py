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


if __name__ == "__main__":
    strs = ['a:bc', 'def']
    encoded_char_list = []
    escape_char, connector_suffix, escaped_original_char = ':', ':;', '::'
    # for s in strs:
    #     for c in s:
    #         encoded_char_list.append(c)
    #         if c == escape_char:
    #             encoded_char_list.append(escape_char)

    # def transform_single_string(s):
    #     for ch in s:
    #         encoded_char_list.append(escaped_original_char if ch == escape_char else ch)
    # map(lambda x: transform_single_string(x), strs)
    sol = EncodeAndDecodeStringsImpl()
    print sol.encode(strs)
    # print encoded_char_list
