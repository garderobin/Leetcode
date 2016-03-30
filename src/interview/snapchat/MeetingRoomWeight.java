package interview.snapchat;

import java.util.*;
/*
 * 在会议之间有时间冲突的情况下参加哪些会议使得最终收益最大
 */
public class MeetingRoomWeight {
	public static void main(String[] args) {
		int[][] test = {{0,2,3}, {1,4,7}, {2,3,5}, {2,5,4}, {4,6,9}};
		Interval[] interval = new Interval[test.length];
		for (int i = 0; i < test.length; ++i) interval[i] = new Interval(test[i][0], test[i][1], test[i][2]);
		int res = meetingroomPQ(interval);
		System.out.println(res);
	}
	
	static class Interval {
		int start;
		int end;
		int weight;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e, int w) { start = s; end = e; weight = w;}
	}
	
	/*
	 * Use PriorityQueue to reduce average Utils time. 
	 * O(N^2) time, O(N) space.
	 */
	public static int meetingroomPQ(Interval[] meetings) {
		Arrays.sort(meetings,(Interval a, Interval b) -> (a.start==b.start) ? (a.end-b.end) : (a.start-b.start));
		int n = meetings.length, max = 0;
		Queue<Integer> q = new PriorityQueue<Integer>(n, (Integer a, Integer b) -> meetings[a].end - meetings[b].end);
		int[] dp = new int[n];
		for(int i = 0; i < n; ++i) {
			dp[i] = meetings[i].weight;
			while (!q.isEmpty() && meetings[q.peek()].end <= meetings[i].start) { //降低了avg time,但是worst仍然是O(n)
				dp[i] = Math.max(dp[i], dp[q.poll()] + meetings[i].weight);
			}
			q.offer(i); // O(logN) time
			if (max < dp[i]) max = dp[i];
		}
		return max;
	}
	

	/*
	 * DP: O(N^2) time, O(N) space.
	 */
	public static int meetingroomDP(Interval[] meetings) {
		Arrays.sort(meetings,(Interval a, Interval b) -> (a.start==b.start) ? (a.end-b.end) : (a.start-b.start));
		int[] dp = new int[meetings.length];
		int max = 0;
		for(int i = 0; i < meetings.length; ++i) {
			dp[i] = meetings[i].weight;
			for (int j = 0; j < i; ++j) {
				if (meetings[j].end <= meetings[i].start && dp[i] < dp[j] + meetings[i].weight) {
					dp[i] = dp[j] + meetings[i].weight;
				}
			}
			max = Math.max(dp[i], max);
		}
		return max;
	}
	
	
}
