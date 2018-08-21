from abc import ABCMeta, abstractmethod


class MeetingRooms2(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def min_meeting_rooms(self, intervals):
        """
        :type intervals: List[Interval]
        :rtype: int
        """


class MeetingRooms2ImplSweepLine(MeetingRooms2):
    def min_meeting_rooms(self, intervals):
        sweep_line = []
        for interval in intervals:
            sweep_line.append((interval.start, 1))
            sweep_line.append((interval.end, -1))

        max_conflicting_events, cur_meeting_rooms = 0, 0
        for point in sorted(sweep_line):
            cur_meeting_rooms += point[1]
            max_conflicting_events = max(max_conflicting_events, cur_meeting_rooms)

        return max_conflicting_events


class Interval(object):
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e
