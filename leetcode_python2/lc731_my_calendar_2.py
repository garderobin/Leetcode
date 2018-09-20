# coding=utf-8
from abc import ABCMeta, abstractmethod
from bisect import bisect_right


class MyCalendar2:
    __metaclass__ = ABCMeta

    @abstractmethod
    def book(self, start, end):
        """
        :type start: int
        :type end: int
        :rtype: bool
        """


class MyCalendarTwoImplBST(MyCalendar2):
    """
    错的
    TODO debug
    """
    def __init__(self):
        self.double_book_root = None
        self.single_book_root = None

    def book(self, start, end):
        if not self.single_book_root:
            self.single_book_root = TreeNode(start, end)
            return True
        elif self.can_trigger_triple_booking(self.double_book_root, start, end):
            return False
        else:
            self.try_trigger_double_booking(self.single_book_root, start, end)

    def can_trigger_triple_booking(self, node, start, end):
        if not node:
            return False
        elif start >= node.end:
            return self.can_trigger_triple_booking(node.right, start, end)
        elif end <= node.start:
            return self.can_trigger_triple_booking(node.left, start, end)
        else:
            return True

    def try_trigger_double_booking(self, node, start, end):
        if start >= node.end:
            if node.right:
                self.try_trigger_double_booking(node.right, start, end)
            else:
                node.right = TreeNode(start, end)
        elif end <= node.start:
            if node.left:
                self.try_trigger_double_booking(node.left, start, end)
            else:
                node.left = TreeNode(start, end)
        else:
            overlap_node = TreeNode(max(start, node.start), min(end, node.end))
            if not self.double_book_root:
                self.double_book_root = overlap_node
            else:
                self.add_booking(self.double_book_root, overlap_node)

    def add_booking(self, root, insert):
        if insert.start >= root.end:
            if root.right:
                self.add_booking(root.right, insert)
            else:
                root.right = insert
        elif insert.end <= root.start:
            if root.left:
                self.add_booking(root.left, insert)
            else:
                root.left = insert
        else:   # this situation will never happen since we checked triple booking in advance of calling this function.
            return


class TreeNode:
    def __init__(self, start, end):
        self.start = start
        self.end = end
        self.left = None
        self.right = None


class MyCalendarTwoImplArray(MyCalendar2):
    """
    时刻提醒自己正在处理到数组是否排过序，没有排序的话能不能直接返回，还是要遍历到底
    Interval overlap的确认方法： start1 < end2 and end1 > start2 这个式子足以囊括各种overlap情形
    """
    def __init__(self):
        self.single_books = []
        self.double_books = []

    def book(self, start, end):
        """
        :type start: int
        :type end: int
        :rtype: bool
        """
        if self.can_trigger_triple_booking(start, end):
            return False

        self.try_trigger_double_booking(start, end)
        self.add_single_booking(start, end)
        return True

    def can_trigger_triple_booking(self, start, end):
        for double_start, double_end in self.double_books:
            if double_start < end and double_end > start:
                return True
        return False

    def try_trigger_double_booking(self, start, end):
        for single_start, single_end in self.single_books:
            if single_start < end and single_end > start:
                self.double_books.append((max(start, single_start), min(end, single_end)))

    def add_single_booking(self, start, end):
        self.single_books.append((start, end))
