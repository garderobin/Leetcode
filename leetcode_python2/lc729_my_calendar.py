# coding=utf-8
from _bisect import bisect_right
from abc import ABCMeta, abstractmethod


class MyCalendar:
    __metaclass__ = ABCMeta

    @abstractmethod
    def book(self, start, end):
        """
        :type start: int
        :type end: int
        :rtype: bool
        """


class MyCalendarImplArray(MyCalendar):
    """
    Each book: time: O(N)
    Space: O(N)
    这里用merge interval来剪枝了
    而且binary search也加块了搜索
    """
    def __init__(self):
        self.calendar = []

    def book(self, start, end):
        """
        :type start: int
        :type end: int
        :rtype: bool
        """
        if not self.calendar:
            self.calendar = [[start, end]]  # 这里不能写append!
        else:
            insert_idx = bisect_right(self.calendar, [start, end])
            if insert_idx > 0 and start < self.calendar[insert_idx - 1][1]:
                return False
            if insert_idx < len(self.calendar) and end > self.calendar[insert_idx][0]:
                return False

            if not self.merge_interval(start, end, insert_idx):
                self.calendar.insert(insert_idx, [start, end])
        return True     # always check if the indent is correct

    def merge_interval(self, start, end, insert_idx):
        is_merged = False
        if insert_idx > 0 and start == self.calendar[insert_idx - 1][1]:
            self.calendar[insert_idx - 1][1] = end
            is_merged = True
        if insert_idx < len(self.calendar) and end == self.calendar[insert_idx][0]:
            if is_merged:
                self.calendar[insert_idx - 1][1] = self.calendar[insert_idx][1]
                del self.calendar[insert_idx]  # python list has no delete(idx) method!
            else:
                self.calendar[insert_idx][0] = start
                is_merged = True
        return is_merged


class MyCalendarImplBST(MyCalendar):
    """
    在没有balance的情况下worst case也不好, 时间上并不优越, 胜在简洁，写的时候不容易出错。
    理想上用红黑数AVL这种可以self balancing的结构最好，但python没有
    """
    def __init__(self):
        self.root = None

    def book(self, start, end):
        if not self.root:
            self.root = Node(start, end)
            return True
        return self.book_helper(start, end, self.root)

    def book_helper(self, s, e, node):
        if s >= node.e:
            if node.right:
                return self.book_helper(s, e, node.right)
            else:
                node.right = Node(s, e)
                return True
        elif e <= node.s:
            if node.left:
                return self.book_helper(s, e, node.left)
            else:
                node.left = Node(s, e)
                return True
        else:
            return False


class Node:
    def __init__(self, s, e, left=None, right=None):
        self.s = s
        self.e = e
        self.left = left
        self.right = right


# Your MyCalendar object will be instantiated and called as such:
# obj = MyCalendar()
# param_1 = obj.book(start,end)
