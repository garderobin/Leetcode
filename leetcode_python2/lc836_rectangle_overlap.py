# coding=utf-8
from abc import ABCMeta, abstractmethod


class RectangleOverlap:
    __metaclass__ = ABCMeta

    @abstractmethod
    def is_rectangle_overlap(self, rec1, rec2):
        """
        :type rec1: List[int]
        :type rec2: List[int]
        :rtype: bool
        """


class RectangleOverlapImpl(RectangleOverlap):
    """
    比较的时候一定要记得加上等号
    要问面试官有一条边挨着算不算overlap，这题不算，所以比较的时候没有等号就挂了。
    """
    def is_rectangle_overlap(self, rec1, rec2):
        rec_left, rec_right = (rec1, rec2) if rec1[0] < rec2[0] else (rec2, rec1)
        rec_right_is_on_right_of_rec_left = rec_left[2] <= rec_right[0]
        rec_right_is_on_top_of_rec_left = rec_left[3] <= rec_right[1]
        rec_right_is_on_bottom_of_rec_left = rec_left[1] >= rec_right[3]
        return \
            not rec_right_is_on_right_of_rec_left \
            and not rec_right_is_on_top_of_rec_left \
            and not rec_right_is_on_bottom_of_rec_left
