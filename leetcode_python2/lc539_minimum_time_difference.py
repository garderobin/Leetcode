# coding=utf-8
from abc import ABCMeta, abstractmethod


class MinimumTimeDifference:
    __metaclass__ = ABCMeta

    @abstractmethod
    def find_min_difference(self, timePoints):
        """
        :type timePoints: List[str]
        :rtype: int
        """
        if not timePoints or len(timePoints) < 2:
            return 0


class MinimumTimeDifferenceImplBucketSortingSet(MinimumTimeDifference):
    """
    Time: O(n) 而且是one pass
    Space: O(1)
    TODO 复习bucket sort & 循环数组
    1. 虽然是循环数组，但是因为只求相邻差的极值，没有必要用extended array, 让蛇头咬一下蛇尾就可以了
    2. bucket sort不一定非要事死的bucket，对这题来说因为只要有重复就返回零，
    所以在保证去重的情况下完全可以用set. 遍历bucket只在最后iterate buckets的时候做就可以
    """
    def find_min_difference(self, timePoints):
        if not timePoints or len(timePoints) < 2 or len(timePoints) > 1440:
            return 0

        hour_buckets, minute_buckets = set([]), set([])  # hour_buckets不是必须的，但是用它可以剪枝，加快速度
        for timePoints in timePoints:
            hour, minute = [int(s) for s in timePoints.split(':')]
            if hour in hour_buckets and (hour, minute) in minute_buckets:
                return 0
            else:
                hour_buckets.add(hour)
                minute_buckets.add((hour, minute))

        first_time, last_time, min_diff = -1, -1441, 1440
        for h in xrange(24):    # 为了简单，面试的时候可以先写常数，但是提一笔这里应该refactor成 HOURS_IN_A_DAY
            if h in hour_buckets:
                for m in xrange(60):
                    if (h, m) in minute_buckets:
                        this_time = h * 60 + m
                        min_diff = min(min_diff, this_time - last_time)
                        first_time, last_time = this_time if first_time < 0 else first_time, this_time

        return min(min_diff, first_time + 1440 - last_time)


HOURS_IN_A_DAY, MINUTES_IN_AN_HOUR = 24, 60


def get_diff_in_minutes(time1, time2):
    return abs((time2[0] - time1[0]) * MINUTES_IN_AN_HOUR + (time2[1] - time1[1]))


class MinimumTimeDifferenceImplBucketSortingArray(MinimumTimeDifference):
    def find_min_difference(self, timePoints):
        if not timePoints or len(timePoints) < 2 or len(timePoints) > 1440:
            return 0

        minute_buckets = [[False for m in xrange(MINUTES_IN_AN_HOUR)] for h in xrange(HOURS_IN_A_DAY + HOURS_IN_A_DAY)]
        hour_buckets = [False for h in xrange(HOURS_IN_A_DAY + HOURS_IN_A_DAY)]
        for timePoints in timePoints:
            hour, minute = [int(s) for s in timePoints.split(':')]
            if minute_buckets[hour][minute]:
                return 0
            else:
                minute_buckets[hour][minute] = True
                hour_buckets[hour] = True

                extend_hour = hour + HOURS_IN_A_DAY
                minute_buckets[extend_hour][minute] = True
                hour_buckets[extend_hour] = True

        last_time, min_diff = (-25, 0), HOURS_IN_A_DAY * MINUTES_IN_AN_HOUR
        for h in xrange(HOURS_IN_A_DAY + HOURS_IN_A_DAY):
            if hour_buckets[h]:
                for m in xrange(MINUTES_IN_AN_HOUR):
                    if minute_buckets[h][m]:
                        if h - last_time[0] < HOURS_IN_A_DAY:
                            min_diff = min(min_diff, get_diff_in_minutes(last_time, (h, m)))
                        last_time = (h, m)

        return min_diff
