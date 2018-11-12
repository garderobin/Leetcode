# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class MaxPointsOnALine:
    __metaclass__ = ABCMeta

    @abstractmethod
    def max_points(self, points):
        """
        :type points: List[Point]
        :rtype: int
        """


class MaxPointsOnALineImplGCD(MaxPointsOnALine):
    """
    改编自java答案： https://leetcode.com/problems/max-points-on-a-line/discuss/47113/A-java-solution-with-notes
    利用分数简化来储存除法结果，避免了用小数造成的精度不足

    要熟悉数组中任选两个元素到组合：
    for i in xrange(n):
        for j in xrange(i):
            // do something
    """
    def max_points(self, points):
        if not points:
            return 0

        n, result = len(points), 0

        for i in xrange(n):
            point_counts = defaultdict(int)
            overlap, cur_max_points = 0, 0

            for j in xrange(i):
                dx, dy = points[j].x - points[i].x, points[j].y - points[i].y

                if dx == dy == 0:
                    overlap += 1
                    continue

                (x, y) = self.simplify_fraction(dx, dy)
                point_counts[(x, y)] += 1
                cur_max_points = max(cur_max_points, point_counts[(x, y)])

            result = max(result, cur_max_points + overlap + 1)

        return result

    def simplify_fraction(self, numerator, denominator):
        gcd = self.gcd(numerator, denominator)  # gcd could be negative!
        return (numerator, denominator) if gcd == 0 else (numerator / gcd, denominator / gcd)

    @staticmethod
    def gcd(a, b):
        """
        Calculate the Greatest Common Divisor of a and b.
        Unless b==0, the result will have the same sign as b
        (so that when b is divided by it, the result comes out positive).
        """
        while b:
            a, b = b, a % b
        return a


class MaxPointsOnALineImplBruteForce(MaxPointsOnALine):
    """
    Time: O(N ^ 2)
    Space: O(N ^ 2)
    问题： 有除法的存在，精度永远有极限
    不要忘了重复点的情况
    由于我这种算法是记录直线与x轴，y轴的交点，所一定要想办法处理交点在(0,0)这种还必须区分斜率的情况
    """
    def max_points(self, points):
        if not points:
            return 0

        points_on_lines = defaultdict(set)
        max_points_on_a_line = self.max_points_on_vertical_or_horizontal_lines(points)
        n = len(points)
        for i in xrange(n):
            x1, y1 = points[i].x, points[i].y
            for j in xrange(i):
                x2, y2 = points[j].x, points[j].y
                if x2 == x1 or y2 == y1:
                    continue

                delta = x1 * y2 - x2 * y1
                if delta == 0:
                    line_key = (0.0, 0.0, y2 * 100.0 / x2 if x1 == 0.0 else y1 * 100.0 / x1)
                else:
                    line_x = delta * 1.0 / (y2 - y1)
                    line_y = delta * 1.0 / (x2 - x1)
                    line_key = (line_x, line_y, 0.0)

                points_on_lines[line_key].add(i)
                points_on_lines[line_key].add(j)

                max_points_on_a_line = max(max_points_on_a_line, len(points_on_lines[line_key]))
        return max_points_on_a_line

    @staticmethod
    def max_points_on_vertical_or_horizontal_lines(points):
        point_counts_on_vertical_lines = defaultdict(int)  # key: int; val: point counts on the line
        point_counts_on_horizontal_lines = defaultdict(int)  # key: int; val: point counts on the line
        max_points_on_a_line = 0
        for p in points:
            point_counts_on_vertical_lines[p.x] += 1
            point_counts_on_horizontal_lines[p.y] += 1
            max_points_on_a_line = max(max_points_on_a_line,
                                       point_counts_on_vertical_lines[p.x],
                                       point_counts_on_horizontal_lines[p.y])
        return max_points_on_a_line
