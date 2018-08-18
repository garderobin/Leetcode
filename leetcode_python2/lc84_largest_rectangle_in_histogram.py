# coding=utf-8
from abc import ABCMeta, abstractmethod


class LargestRectangleInHistogram(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def largest_rectangle_area(self, heights):
        """
        :type heights: List[int]
        :rtype: int
        """


class LargestRectangleInHistogramImplMonotonousStack(LargestRectangleInHistogram):
    @staticmethod
    def _get_first_lower_bar_indexes(heights, start, end, increment):
        monotonous_stack = [start]
        first_lower_indexes = [start - increment for _ in xrange(len(heights))]
        for i in xrange(start + increment, end, increment):
            # 下面这里必须是>=不是>, 因为只有严格小雨才能破坏长方形
            while monotonous_stack and heights[monotonous_stack[-1]] >= heights[i]:
                monotonous_stack.pop()
            if monotonous_stack:
                first_lower_indexes[i] = monotonous_stack[-1]
            monotonous_stack.append(i)  # 这里之所以最后才append, 是因为不想在计算结果的时候考虑当前值
        return first_lower_indexes

    def largest_rectangle_area(self, heights):
        if not heights:
            return 0
        n = len(heights)
        left_first_lower_pos = self._get_first_lower_bar_indexes(heights, 0, n, 1)
        right_first_lower_pos = self._get_first_lower_bar_indexes(heights, n-1, -1, -1)
        return max([h * (right_first_lower_pos[i] - left_first_lower_pos[i] - 1) for i, h in enumerate(heights)])


class LargestRectangleInHistogramImplOnePassMoreMultiplication(LargestRectangleInHistogram):
    """
    For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle.
    Then the largest rectangle is the max of them.
    We need to know index of the first smaller (smaller than ‘x’) bar on left of ‘x’ -- 'left index'
    and index of first smaller bar on right of ‘x’ -- 'right index'.
    We traverse all bars from left to right, maintain a stack of bars.
    Every bar is pushed to stack once. A bar is popped from stack when a bar of smaller height is seen.
    When a bar is popped, we calculate the area with the popped bar as smallest bar.
    The current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’.
    """
    def largest_rectangle_area(self, heights):
        if not heights:
            return 0
        index_stack = []
        max_rect = 0
        for i, h in enumerate(heights + [0]):
            while index_stack and heights[index_stack[-1]] > h:  # 一定不要忘了stack存的只是index! TODO:这里为什么不是>=?
                # 因为是单调升序序列所以保证了使用heights[index_stack.pop()]的正确性
                max_rect = max(max_rect, heights[index_stack.pop()] * (i - index_stack[-1] - 1 if index_stack else i))
            index_stack.append(i)
        return max_rect
