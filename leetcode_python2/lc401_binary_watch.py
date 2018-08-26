# coding=utf-8
from abc import ABCMeta, abstractmethod
from collections import defaultdict


class BinaryWatch:
    __metaclass__ = ABCMeta

    @abstractmethod
    def read_binary_watch(self, num):
        """
        :type num: int
        :rtype: List[str]
        """


class BinaryWatchImplBruteForce(BinaryWatch):

    def read_binary_watch(self, num):
        return ['%d:%02d' % (h, m)
                for h in xrange(12) for m in xrange(60)
                if (bin(h) + bin(m)).count('1') == num]


class BinaryWatchImplBacktrack(BinaryWatch):
    """
    需要用Back-tracking的题特点： 不同的方面共享资源池。
    Time: O(1)
    Space: O(1)
    因为12和60都是常数
    """
    @staticmethod
    def generate_time_string(hour, minute):
        return '%d:%02d' % (hour, minute)

    def read_binary_watch(self, num):
        if num < 0 or num > 8:
            return []

        max_allow_hour, max_allow_minute = 11, 59
        hour_lights_values = [[i] if i < 2 else [] for i in xrange(num + 2)]
        minute_lights_values = [[i] if i < 2 else [] for i in xrange(num + 2)]
        results = []

        self.backtrack(1, hour_lights_values, max_allow_hour, 1, num)
        self.backtrack(1, minute_lights_values, max_allow_minute, 1, num)

        for hour_lights_on in xrange(num + 1):
            for hour in hour_lights_values[hour_lights_on]:
                for minute in minute_lights_values[num - hour_lights_on]:
                    results.append(self.generate_time_string(hour, minute))

        return results

    def backtrack(self, cur_value, lights_values, max_allow_number, used_digit_ones, available_digit_ones):
        if available_digit_ones <= 0 or (cur_value << 1) > max_allow_number:
            return

        for last_digit in xrange(2):
            new_value = (cur_value << 1) + last_digit
            if new_value <= max_allow_number:
                new_used_digit_ones = used_digit_ones + last_digit
                lights_values[new_used_digit_ones].append(new_value)
                self.backtrack(new_value, lights_values, max_allow_number,
                               new_used_digit_ones, available_digit_ones - last_digit)


if __name__ == "__main__":
    sol = BinaryWatchImplBacktrack()
    maph = [[x] if x < 2 else [] for x in xrange(10 + 1)]
    sol.backtrack(1, maph, 59, 1, 11)
    print maph
    print sol.read_binary_watch(1)
