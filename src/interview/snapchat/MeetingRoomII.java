package interview.snapchat;

import java.util.Arrays;
import java.util.PriorityQueue;

import dataStructure.Interval;

public class MeetingRoomII {
	/**
	 * PriorityQueue
	 * Time: O(NlogN), Space: O(N)
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) return 0; 
		if (intervals.length == 1)  return 1; 
        PriorityQueue<Interval> lastMeetings = new PriorityQueue<>(intervals.length, (Interval o1, Interval o2) -> o1.end - o2.end);
        Arrays.sort(intervals, (Interval o1, Interval o2) -> o1.start - o2.start);
        lastMeetings.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
        	if (intervals[i].start >= lastMeetings.peek().end) {
        		lastMeetings.poll();
        	} 
        	lastMeetings.offer(intervals[i]);
        }
        
        return lastMeetings.size();
    }

}

//class Interval {
//    int start;
//    int end;
//    int weight;
//    Interval() { start = 0; end = 0; }
//    Interval(int s, int e, int w) { start = s; end = e; weight = w;}
//}
