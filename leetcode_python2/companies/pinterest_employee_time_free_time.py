#
# Your previous Plain Text content is preserved below:
#
# input: list of people's schedules when they are busy
# output: list of time intervals when everyone is free
#
#
# e.g (1, 2), (2, 3) -> [2,3) [3,4)
#
# (123, 135), (400, MAX_INT)
#
# [ [schedule1 :[(,)... ], [schedule 2 ] ... ]
#
# (s1, e1),(s2, e2), (s3, e3)
# (s1, s2, e1, e2, s3, e3), s1 > 0, e3 < MAX_INT
# (s2, s1, e1, e2)
# (0, s1), (e2, s3), (e3, MAX_INT)
#
# (start, 1), (start, 2), (end, 1), (end, 2)
# Time complexity: O(nlogn), n = len(input)
# Space: O(n)
#
import sys


def get_available_intervals(employee_schedules):
    """
    @param employee_schedules: List[List[Tuple]]
    @return: List[Tuple]
    """
    if not any(employee_schedules):
        return [(0, sys.maxint)]

    busy_timeline = []  # [(timestamp, is_start)]
    meeting_set = set([])
    for schedules in employee_schedules:
        for (start, end) in schedules:
            if (start, end) not in meeting_set:
                meeting_set.add((start, end))
                busy_timeline.append((start, True))
                busy_timeline.append((end, False))
    busy_timeline.sort()

    available_intervals = []
    in_progress_meeting_count = 0
    last_end_timestamp = 0
    for timestamp, is_start in busy_timeline:
        if is_start:
            if in_progress_meeting_count == 0 and timestamp > last_end_timestamp:
                available_intervals.append((last_end_timestamp, timestamp))
            in_progress_meeting_count += 1
        else:
            last_end_timestamp = timestamp
            in_progress_meeting_count -= 1

    if last_end_timestamp < sys.maxint:
        available_intervals.append((last_end_timestamp, sys.maxint))

    return available_intervals


if __name__ == "__main__":
    print get_available_intervals([[]])

    employee_intervals = [
        [(1, 3), (5, 7)],
        [(2, 4)]
    ]
    print get_available_intervals(employee_intervals)

    employee_intervals = [
        [(1, 4), (5, 7)],
        [(2, 3)]
    ]
    print get_available_intervals(employee_intervals)

    employee_intervals = [
        [(1, 2), (5, 7)],
        [(3, 4)]
    ]
    print get_available_intervals(employee_intervals)

    employee_intervals = [
        [(1, 2), (5, 7)],
        [(3, 4)],
        [(2, 3)]
    ]
    print get_available_intervals(employee_intervals)