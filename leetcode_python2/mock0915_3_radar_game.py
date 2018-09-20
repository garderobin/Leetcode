# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict

from mock0915_1_sum_of_subarray_sums import TestCase


class RadarGame:
    __metaclass__ = ABCMeta

    @abstractmethod
    def can_move_to_right_up_corner(self, radars, X, Y):
        """
        On the first quadrant, there are a group of radars, each of which is scanning a circle area constantly.
        A bot is moving slowly to the right, up corner [X, Y] and hopes to bypass all radar controlled areas.
        It can only move within the rectangle area formed by [0, 0] and [X, Y].
        A radar is represented by a list of [x, y, r], with (x, y) is the center and r is the radar radius.
        Assume: for each radar, we have 0.0 <= x <= X; 0.0 <= y <= Y; r > 0.
        :param radars: List[List[float]]. [[x, y, r] ...] (x, y) is the center and r is the radar radius.
        :param X: float
        :param Y: float
        :return: can the bot move from (0, 0) to (X, Y) without detected by any radar or moving out of allowed area.
        """


class RadarGameImplUnionFind(RadarGame):
    def __init__(self):
        self.father = {}

    def can_move_to_right_up_corner(self, radars, X, Y):
        if not radars:
            return True

        if not self.initialize(radars, X, Y):
            return False

        self.draw_components(radars)

        component_block_sides = defaultdict(set)
        for rid, (x_pos, y_pos, radius) in enumerate(radars):
            block_sides = {'u': Y - y_pos <= radius,
                           'd': y_pos <= radius,
                           'l': x_pos <= radius,
                           'r': X - x_pos <= radius}
            for side in ['u', 'd', 'l', 'r']:
                if block_sides[side]:
                    component_id = self.father[rid]
                    component_block_sides[component_id].add(side)
                    if self.is_component_blocking_move(component_block_sides[component_id]):
                        return False
        return True

    def initialize(self, radars, X, Y):
        """
        :return: if father array can be initialized without finding any radar directly scans the bot start or end point.
        """
        self.father = {}
        for rid, radar in enumerate(radars):
            if self.radar_can_scan_position(radar, 0, 0) or self.radar_can_scan_position(radar, X, Y):
                return False
            self.father[rid] = rid
        return True

    def radar_can_scan_position(self, radar, x_scan, y_scan):
        x_center, y_center, radius = radar
        return ((x_center - x_scan) ** 2) + ((y_center - y_scan) ** 2) <= radius ** 2

    def draw_components(self, radars):
        """
        注意combination的写法，不同于partition, 可以减少很多不必要操作
        :param radars:
        :return:
        """
        n = len(radars)
        for rid1 in xrange(n - 1):
            x1, y1, radius1 = radars[rid1]
            for rid2 in xrange(rid1 + 1, n):
                x2, y2, radius2 = radars[rid2]
                if ((y1 - y2) ** 2) + ((x1 - x2) ** 2) <= (radius1 + radius2) ** 2:
                    self.union(rid1, rid2)

    def union(self, rid1, rid2):
        self.father[self.find(rid2)] = self.find(rid1)

    def find(self, rid):
        path = []
        node = rid
        while self.father[node] != node:
            path.append(node)
            node = self.father[node]
        for compress in path:
            self.father[compress] = node
        return node

    def is_component_blocking_move(self, block_sides):
        for condition in ['ud', 'ur', 'ld', 'lr']:
            if condition[0] in block_sides and condition[1] in block_sides:
                return True
        return False


if __name__ == '__main__':
    test_cases = [
        TestCase(input_data=[[[1.0, 1.0, 2.0]], 3.0, 4.0], expected_output=False),
        TestCase(input_data=[[[2.0, 3.0, 2.0]], 3.0, 4.0], expected_output=False),
        TestCase(input_data=[[[1.0, 1.0, 1.0]], 3.0, 4.0], expected_output=False),
        TestCase(input_data=[[[2.0, 1.0, 1.0], [3.0, 1.0, 1.0]], 10.0, 10.0], expected_output=True),
        TestCase(input_data=[[[2.0, 1.0, 1.0], [8.0, 1.0, 1.0]], 10.0, 10.0], expected_output=True),
        TestCase(input_data=[[[2.0, 1.0, 1.0], [2.0, 4.0, 2.0], [8.0, 1.0, 1.0]], 10.0, 10.0], expected_output=False),
        TestCase(input_data=[[[2.0, 1.0, 1.0], [2.0, 3.0, 2.0]], 10.0, 10.0], expected_output=False),
        TestCase(input_data=[[[2.0, 1.0, 1.0], [2.0, 4.0, 1.0]], 5.0, 5.0], expected_output=True)
    ]
    solutions = [
        RadarGameImplUnionFind(),
    ]
    for sol in solutions:
        print 'test solution: ', type(sol)
        all_tests_passed = True
        for test in test_cases:
            actual_output = sol.can_move_to_right_up_corner(test.input_data[0], test.input_data[1], test.input_data[2])
            if actual_output != test.expected_output:
                print 'Fail test case: \n%s\n\tActual Output:%s' % (str(test), actual_output)
                all_tests_passed = False
        if all_tests_passed:
            print '^_^ All test passed for this solution.'
