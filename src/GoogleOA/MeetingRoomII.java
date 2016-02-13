package GoogleOA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import dataStructure.Interval;

public class MeetingRoomII {
	/**
	 * Time: O(NlogN), Space: O(N)
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) { return 0; }
		else if (intervals.length == 1) { return 1; }
        PriorityQueue<Interval> lastMeetings = new PriorityQueue<Interval>(intervals.length, comparatorByEndTimeAscending);
        Arrays.sort(intervals, comparatorByStartTimeAscending);
        lastMeetings.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
        	if (intervals[i].start >= lastMeetings.peek().end) {
        		lastMeetings.poll();
        	} 
        	lastMeetings.offer(intervals[i]);
        }
        
        return lastMeetings.size();
    }
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
	
	private static Comparator<Interval> comparatorByEndTimeAscending = new Comparator<Interval>() {
		@Override
		public int compare(Interval m1, Interval m2) {
			return m1.end - m2.end;
		}
	};
}
