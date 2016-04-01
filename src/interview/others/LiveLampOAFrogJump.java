package interview.others;

import java.util.Arrays;
import java.util.HashMap;
import algorithm.Utils;

public class LiveLampOAFrogJump {
	
	public static void main(String[] args) {
		testFrogJump();
		testMonkeyJump();
	}

	private static void testFrogJump() {
		System.out.println("\ntest frogJumpGreedy(A, X, D): ");
		int[][] As = {{2,0,1}, {4,0,1,2,3}, {3,1,3,5,2,7,6}};
		int[] Xs = {3, 5, 7};
		int[] Ds = {1, 1, 2};
		int[] expects = {2, 4, 3};
		for (int i = 0; i < Ds.length; ++i) {
			System.out.print("A=" + Utils.intArrayToString(As[i]) + ",\tX=" + Xs[i] + ",\tD=" + Ds[i]);
			System.out.println(",\texpect=" + expects[i] + ",\toutput=" + frogJump(As[i], Xs[i], Ds[i]));
		}
	}
	
	private static void testMonkeyJump() {
		System.out.println("\ntest monkeyJump(A, X, D): ");
		int[][] As = {{1, -1, 0, 2, 3, 5}, {1,1}, {-1,-1,0}, {1,2,3,5,0}};
		int[] Ds = {3, 0, 1, 1};
		int[] expects = {2, -1, -1, 5};
		for (int i = 0; i < Ds.length; ++i) {
			System.out.print("A=" + Utils.intArrayToString(As[i]) + ",\tD=" + Ds[i]);
			System.out.println(",\texpect=" + expects[i] + ",\toutput=" + monkeyJump(As[i], Ds[i]));
		}
	}
	
	/* Greedy, O(X*D) time, O(1) space */ 
	/**
	 * Find the minimum time a monkey need to jump across the river.
	 * @param A - A[i] means the minimum time that position[i]'s stone is above water.
	 * @param D - maximum step length for each jump.
	 * @return the minimum time a monkey need to jump across the river.
	 */
	public static int monkeyJump(int[] A, int D) {
		if (A == null || A.length <= D) return 0;
		if (D == 0) return -1;
		int maxTimeUsed = -1;
		for (int start = -1, range = D-1, nextJump = D-1; range < A.length; start = range, range = (nextJump += D)) {
			while (range > start && A[range] < 0)  --range;
			if (range == start) return -1; // all -1s and cannot add jump any more
			for (int i = start + 1; i <= range; ++i) {
				if (A[i] >= 0 && A[i] < A[nextJump]) {
					if ((nextJump = i) + D >= A.length) 
						return A[i] > maxTimeUsed ? A[i] : maxTimeUsed;
				}
			}
			if (maxTimeUsed < A[nextJump]) maxTimeUsed = A[nextJump]; 
		}
		return maxTimeUsed;
	}
	
	public static int monkeyJumpKangran(int[] A, int D) {
		if (A == null || A.length <= D) return 0;
		int X = A.length, max = Integer.MIN_VALUE;
        for(int i = 0; i < X; i++) { max = max > A[i] ? max : A[i]; }
        int B[] = new int[max + 1];
        for(int i = 0; i < X; i++) { B[i] = -1; }
        for(int i = 0; i < X; i++) { if(A[i] >= 0) { B[A[i]] = i; } }
		boolean[] positions = new boolean[X + 1];
		positions[0] = true;
		positions[X] = true;
		int pos = 0, time;
		for (time = 0; time < X; time++) {
			if (B[time] > pos && B[time] < X) {			
				positions[B[time]] = true; 
				if (B[time] < pos + D + 1) {
					for (pos = B[time] + D; pos > B[time] && !positions[pos]; pos--) {}
					if (pos >= X) { return time; }
				}
			}
		}		
		return -1;
	}
	
	/* 贪心算法，按照时间从前向后松弛, O(X*D) time, O(X) space. */
	/**
	 * Find the minimum time a frog need to jump across the river.
	 * @param A - A[i] means at the time i, a leaf falls at position A[i].
	 * @param X - river width
	 * @param D - maximum distance of each jump
	 * @return
	 */
	public static int frogJump(int[] A, int X, int D) {
		if (X <= D) { return 0; }		
		if (A == null || A.length == 0) { return -1; }		
		boolean[] posAvailable = new boolean[X + 1];
		posAvailable[0] = true;
		posAvailable[X] = true;
		int curPos = -1;
		for (int time = 0; time < A.length && curPos < X; time++) {
			posAvailable[A[time]] = true;
			if (A[time] > curPos && A[time] < X) {			
				if (A[time] < curPos + D + 1) {
					for (curPos = Math.min(A[time] + D, X); curPos > A[time] && !posAvailable[curPos]; --curPos) {}
					for (int reach = curPos; posAvailable[reach]; reach += D) {
						if (reach + D >= X) return time;
					}
				}
			}
		}		
		return -1;
	}

	/**
	 * Wrong Answer
	 * 贪心算法，按照时间从前向后松弛
	 * 复杂度 N*D
	 * @param A - A[i] means at the time i, a leaf falls at position A[i].
	 * @param X - river width
	 * @param D - step length
	 * @return
	 */
	public static int frogJumpGreedyInitial(int[] A, int X, int D) {
		if (X <= D) { return 0; }		
		if (A == null || A.length == 0) { return -1; }		
		boolean[] posAvailable = new boolean[X + 1];
		posAvailable[0] = true;
		posAvailable[X] = true;
		int curPos = 0, time;
		for (time = 0; time < A.length; time++) {
			if (A[time] > curPos && A[time] < X) {			
				posAvailable[A[time]] = true; 
				if (A[time] < curPos + D + 1) {
					for (curPos = A[time] + D; curPos > A[time] && !posAvailable[curPos]; --curPos) {}
					if (curPos >= X) { return time; }
				}
			}
		}		
		return -1;
	}

	
	/**
	 * 动态规划，按照位置从前向后初始化，后面的按照前面的子问题紧缩
	 * 复杂度 X*D
	 * @param A
	 * @param X
	 * @param D
	 * @return
	 */
	public static int frogJumpDP(int[] A, int X, int D) {
		if (X <= D) { return 0; }		
		if (A == null || A.length == 0) { return -1; }
		
		// y[i] means the minimum seconds the frog needs to reach position i.
		// t[i] means the earliest time that position i is on the bank or has at least one leave on it.
		int[] y = new int[X + 1], t = new int[X + 1];
		for (int i = 1; i < X; i++) {
			y[i] = Integer.MAX_VALUE;
			t[i] = Integer.MAX_VALUE;
		}
        y[X] = Integer.MAX_VALUE; //y[0], t[0] and t[X] are all 0.
        
        for (int i = 0; i < A.length; i++) {
        	if (A[i] < X) {
        		t[A[i]] = Math.min(t[A[i]], i);
        	}        	
        }
        
        int pos, step;
        for (pos = 1; pos <= D; pos++) {
        	if (t[pos] < A.length) {
        		y[pos] = t[pos];
        	}
        }
        for (pos = D + 1; pos <= X; pos++) {
        	if (t[pos] < A.length) { 
        		for (step = 1; step <= D; step++) {
        			y[pos] = Math.min(Math.max(t[pos], y[pos - step]), y[pos]);
        		}
        	}
        }
        
        return y[X] == Integer.MAX_VALUE ? -1 : y[X];
	}
	
	
	
	
    public static int frogJumpDPV1(int[] arr, int X, int D) {
        if (arr == null || arr.length == 0 || X <= D) {
        	return 0;
        }
        int[] rst = new int[X + 1];// rst[i] means the minimum seconds the frog needs to reach position i.
        Arrays.fill(rst, Integer.MAX_VALUE);
        rst[0] = 0;
        /** leaf: Key: position from 0 to X; 
         * Value: the earliest time that this position is on the land or has at least one leaf on it.
         */
        HashMap<Integer, Integer> leaf = new HashMap<>();
        leaf.put(0, 0); 
        leaf.put(X, 0);
//        int[] t = new int[X + 1];  // t[j] means the earliest time that position j is on the land or has at least one leaf on it.
        
        // Initialize leaf map
        for (int i = 0; i < arr.length; i++) {
        	if (!leaf.containsKey(arr[i]))
        		leaf.put(arr[i], i); 
        }
        
        // rst
        for (int pos = 1; pos <= X; pos++) {
        	if (leaf.containsKey(pos)) {
        		if (pos <= D) {
        			rst[pos] = leaf.get(pos);
                } else {
                	for (int step = 1; step <= D; step++) {
                		if (leaf.get(pos) < rst[pos - step]) {
                			rst[pos] = Math.min(rst[pos], rst[pos - step]);
                        } else {
                        	rst[pos] = Math.min(rst[pos], leaf.get(pos));
                        }
                	}
                }
            }
        }
        
        return rst[X] == Integer.MAX_VALUE ? -1 : rst[X];
    }
}
