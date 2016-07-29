package interview.snapchat;

import java.util.Arrays;

import dataStructure.Interval;

public class MeetingRoom {
	
	public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) { return true; }
        
		Arrays.sort(intervals, (Interval a, Interval b) -> a.start - b.start);
		
		for (int i = 1, prevEnd = intervals[0].end; i < intervals.length; prevEnd = intervals[i++].end) {
			if (prevEnd > intervals[i].start) { return false; }
		}
		return true;
	}
	
}
