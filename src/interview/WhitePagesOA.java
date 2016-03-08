package interview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WhitePagesOA {
	/**
	 * DP solution. O(N^2) time, O(N) space.
	 * If nums[i] = k, and ith index can be reached in steps[i] steps, 
	 * then index [i+1]...[i+k] can be reached in no more than (steps[i]+k) steps.
	 * Record the last visited position for the current shortest path of each index for printing path purpose.
	 */
	public static List<Integer> shortestPath(int[] nums) {
		if (nums == null || nums.length == 0 || nums[0] <= 0) { return Collections.emptyList(); }
		LinkedList<Integer> rst = new LinkedList<>();
		int n = nums.length;
		int[] steps = new int[n+1], lastPos = new int[n+1];
		for (int i = 0; i < n; ++i) {
			if (nums[i] <= 0) { continue; }
			for (int j = i+1, limit = 1 + ((i+nums[i] > n) ? n : i+nums[i]); j < limit; ++j) {
				if (steps[j] == 0 || steps[j] > steps[i] + 1) {
					steps[j] = steps[i] + 1;
					lastPos[j] = i;
				}
			}
		}
		if (steps[n] == 0) { return Collections.emptyList(); }
		for (int i = lastPos[n]; i > 0; i = lastPos[i]) { rst.addFirst(i); }
		rst.addFirst(0);
		return (List<Integer>) rst;
	}
	
	public static void main(String[] args) {
		int[] nums = {5,6,-1,4,2,4,1,0,0,4};
		System.out.println(shortestPath(nums));
	}
	
}

/*
 * 给一个数组， 每一个数表示下一次能走的最大步长，求一条走出这个数组的最短路径。
 * 例如：[5,6,0,4,2,4,1,0,0,4] 返回[0,5,9] 
 */
