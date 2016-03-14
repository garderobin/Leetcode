package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WhitePagesOA {
	static int count = 0;
	
	/**
	 * Final version
	 * @throws InputMismatchException
	 */
	@SuppressWarnings("resource")
	public static void shortestPathStream() {
		Scanner sc = new Scanner(System.in);
		
		try {
			int n0 = sc.nextInt();
			StringBuilder sb = new StringBuilder("0 ");
			for (int start = 0, range = n0, pos = 0, nextRange = range; ; start = range, range = nextRange) {
				for (int i = start + 1; i <= range; ++i) {
					try {
						int step = sc.nextInt();	
						if (i + step > nextRange) {
							pos = i;
							nextRange = i + step;
						}
					} catch (InputMismatchException e) {
						sc.close();
						throw e;
					} catch (NoSuchElementException e) {
						System.out.println(sb.toString() + "out");
						System.exit(0);
					}
					
				}
				if (nextRange <= range) { throw new Exception();}
				sb.append(pos + " ");
			}	
			
		} catch (Exception e1) {
			sc.close();
			System.out.print("failure"); System.exit(0);
		}
		
	}
	
	public static List<Integer> shortestPathGreedyV2(int[] nums) {
		List<Integer> rst = new ArrayList<Integer>();
		if (nums == null || nums.length == 0 || nums[0] <= 0) { return rst; }
		rst.add(0);
		for (int start = 0, range = nums[0], pos = start, nextRange = range; range < nums.length; start = range, range = nextRange) {
			for (int i = start + 1; i <= range; ++i) {
				if (i + nums[i] > nextRange) {
					pos = i;
					nextRange = i + nums[i]; 
				}
			}
			if (nextRange <= range) { return new ArrayList<Integer>(); }
			rst.add(pos);
		}
		
		return rst;
		
	}
	
	
	public static List<Integer> shortestPathGreedy(int[] nums) {
		List<Integer> rst = new ArrayList<Integer>();
		if (nums == null || nums.length == 0 || nums[0] <= 0) { return rst; }
		
		int start0 = 0, range0 = start0 + nums[0], range = range0;
		rst.add(start0);
		while (range < nums.length) {
			for (int i = start0 + 1; i <= range0; ++i) {
				if (i + nums[i] > range) {
					range = i + nums[i]; 
				}
			}
			if (range <= range0) { return new ArrayList<Integer>(); }
			start0 = range0;
			range0 = range;
			rst.add(start0);
		}
		
		return rst;
		
	}
	
	/**
	 * DFS Pruning.
	 */
	public static List<Integer> shortestPath2(int[] nums) {
		if (nums == null || nums.length == 0 || nums[0] <= 0) { return Collections.emptyList(); }
		return dfs(nums, 0, new ArrayList<Integer>());
	}
	
	private static List<Integer> dfs(int[] nums, int start, List<Integer> path) {
		System.out.println(++count + ",\tstart=" + start);
		if (nums[start] <= 0) { return null; }
		
		int minSteps = nums.length, minStart = nums.length + 1; 
		path.add(start);
		if (nums[start] + start >= nums.length) { return path; }
		
		List<Integer> npath = null;
		for (int i = start+1, limit = Math.min(nums.length, start + nums[start] + 1); i < limit; ++i) {
//			System.out.println(++count + ",\tstart=" + start + ",\ti=" + i);
			List<Integer> mpath = dfs(nums, i, new ArrayList<Integer>(path));
			if (mpath != null && mpath.size() < minSteps) {
				if(minStart < nums.length) { nums[minStart] = 0; } // prune the last not-shortest path.
				minSteps = mpath.size();
				npath = mpath;
				minStart = i;
			} else if (i < nums.length){ // as int as no path exists or the path is not the current shortest one, prune it.
				nums[i] = 0;
			}
		}
		return npath;
	}
	
//	private static List<Integer> dfs(int[] nums, int start, List<Integer> path) {
//		System.out.println(++count + ",\tstart=" + start);
//		if (start == nums.length) {  return path;  }
//		if (nums[start] <= 0) { return null; }
//		
//		int minSteps = nums.length, minStart = nums.length + 1; 
//		path.add(start);
//		if (nums[start] + start >= nums.length) { return path; }
//		
//		List<Integer> npath = null;
//		for (int i = start+1, limit = Math.min(nums.length, start + nums[start]) + 1; i < limit; ++i) {
////			System.out.println(++count + ",\tstart=" + start + ",\ti=" + i);
//			List<Integer> mpath = dfs(nums, i, new ArrayList<Integer>(path));
//			if (mpath != null && mpath.size() < minSteps) {
//				if(minStart < nums.length) { nums[minStart] = 0; } // prune the last not-shortest path.
//				minSteps = mpath.size();
//				npath = mpath;
//				minStart = i;
//			} else if (i < nums.length){ // as int as no path exists or the path is not the current shortest one, prune it.
//				nums[i] = 0;
//			}
//		}
//		return npath;
//	}
	
//	public static List<Integer> shortestPath4(int[] nums) {
//		LinkedList<Integer> rst = new LinkedList<Integer>();
//		if (nums == null || nums.length == 0 || nums[0] <= 0) { return (List<Integer>)rst; }
//		int n = nums.length, unreachIndex = 1, j;
//		int[] steps = new int[n+1], lastPos = new int[n+1];
//		for (int i = Mat) {
//			if (i < n && nums[i] <= 0) { continue; }
//			for (j = i+1; j <= n && j <= i+nums[i]; ++j) {
//				if (j < n && nums[j] <= 0) { continue; }
//				if (steps[j] == 0 || steps[j] > steps[i] + 1) {
//					steps[j] = steps[i] + 1;
//					lastPos[j] = i;
//				}
//				
//			}
//			unreachIndex = Math.max(unreachIndex, j);
//		}
//		if (steps[n] == 0) { return (List<Integer>) rst; }
//		for (int i = lastPos[n]; i > 0; i = lastPos[i]) { rst.addFirst(i); }
//		rst.addFirst(0);
//		return (List<Integer>) rst;
//	}
	
	public static List<Integer> shortestPath3(int[] nums) {
		LinkedList<Integer> rst = new LinkedList<Integer>();
		if (nums == null || nums.length == 0 || nums[0] <= 0) { return (List<Integer>)rst; }
		int n = nums.length, unreachIndex = 1, j;
		int[] steps = new int[n+1], lastPos = new int[n+1];
		for (int i = 0; i < unreachIndex; ++i) {
			if (i < n && nums[i] <= 0) { continue; }
			for (j = i+1; j <= n && j <= i+nums[i]; ++j) {
				if (j < n && nums[j] <= 0) { continue; }
				if (steps[j] == 0 || steps[j] > steps[i] + 1) {
					steps[j] = steps[i] + 1;
					lastPos[j] = i;
				}
				
			}
			unreachIndex = Math.max(unreachIndex, j);
		}
		if (steps[n] == 0) { return (List<Integer>) rst; }
		for (int i = lastPos[n]; i > 0; i = lastPos[i]) { rst.addFirst(i); }
		rst.addFirst(0);
		return (List<Integer>) rst;
	}
	
	@SuppressWarnings("unused")
	private static int[] readNumsArray() {
		List<Integer> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) { list.add(sc.nextInt()); }
		int[] nums = new int[list.size()];
		
		for (int i = 0; i < list.size(); ++i) { nums[i] = list.get(i); }
		
		sc.close();
		return nums;
	}
	
	
	/**
	 * DP solution. O(N^2) time, O(N) space.
	 * If nums[i] = k, and ith index can be reached in steps[i] steps, 
	 * then index [i+1]...[i+k] can be reached in no more than (steps[i]+k) steps.
	 * Record the last visited position for the current shortest path of each index for printing path purpose.
	 */
	public static List<Integer> shortestPath1(int[] nums) {
		if (nums == null || nums.length == 0 || nums[0] <= 0) { return new ArrayList<Integer>(); }
		LinkedList<Integer> rst = new LinkedList<>();
		
		// Filter immovable indexes. O(N) time, O(N) space.
		List<Integer> movableIndexes = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; ++i) { if (nums[i] > 0) movableIndexes.add(i); }
		if (movableIndexes.size() == 0) { return (List<Integer>) rst; }
		
		// Dynamic programming. O(N^2) time, O(N) space.
		int n = nums.length, m = movableIndexes.size(), j = 1, unreachIndex = 1, ix = 0, jx = 0;
		int[] steps = new int[n+1], lastPos = new int[n+1];
//		for (int i = 0; i < nearestUnreached && i < n; ++i) {
		for (int i = 0; i < m && i < unreachIndex; ++i) {
			ix = movableIndexes.get(i);
			if (nums[ix] <= 0) { continue; }
			if (ix + nums[ix] >= n) {
				lastPos[n] = ix;
				break;
			}
			for (j = i+1; j < m && (jx = movableIndexes.get(j)) <= ix + nums[ix]; ++j) {
				if (steps[jx] == 0 || steps[jx] > steps[ix] + 1) {
					steps[jx] = steps[ix] + 1;
					lastPos[jx] = ix;
				}
			}
//			unreachIndex = Math.min(n, Math.max(movableIndexes.get(j-1)+1, unreachIndex));
			unreachIndex = Math.max(unreachIndex, j);
			
		}
		if (steps[n] == 0) { return Collections.emptyList(); }
		for (int i = lastPos[n]; i > 0; i = lastPos[i]) { rst.addFirst(i); }
		rst.addFirst(0);
		return (List<Integer>) rst;
	}
	
	public static void main(String[] args) {
//		int[] nums = {5,6,-1,4,2,4,1,0,0,4};
//		int[] nums = {5,0,-1,0,0,4,1,0,0,5,0,0,0,0, 5,0,-1,0,0,4,1,0,0,5,0,0,0,0, 5,0,-1,0,0,4,1,0,0,5,0,0,0,0, 5,0,-1,0,0,4,1,0,0,5, 5,0,-1,0,0,4,1,0,0,5,0,0,0,0, 5,0,-1,0,0,4,1,0,0,5,0,0,0,0};
//		int[] nums = {1,2,3};
//		int[] nums = {5,6,0,4,2,4,1,0,0,4};
//		int[] nums = {1,0,0};
//		System.out.println(shortestPathGreedyV2(nums));
		shortestPathStream();
	}
	
}

/*
 * 给一个数组， 每一个数表示下一次能走的最大步长，求一条走出这个数组的最短路径。
 * 例如：[5,6,0,4,2,4,1,0,0,4] 返回[0,5,9] 
 */
