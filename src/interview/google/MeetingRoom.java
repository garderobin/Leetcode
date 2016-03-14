package interview.google;

import java.util.Arrays;
import java.util.Comparator;

import dataStructure.Interval;

public class MeetingRoom {
	
	public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) { return true; }
		Arrays.sort(intervals, comparatorByStartTimeAscending);
		for (int i = 1, prevEnd = intervals[0].end; i < intervals.length; prevEnd = intervals[i++].end) {
			if (prevEnd > intervals[i].start) { return false; }
		}
		return true;
	}
	
	private static Comparator<Interval> comparatorByStartTimeAscending = new Comparator<Interval>() {
		@Override
		public int compare(Interval m1, Interval m2) {
			return m1.start - m2.start;
		}
	};
}
