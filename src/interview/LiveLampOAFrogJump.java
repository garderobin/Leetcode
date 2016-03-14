package interview;

import java.util.Arrays;
import java.util.HashMap;

public class LiveLampOAFrogJump {
	
	/**
	 * 贪心算法，按照时间从前向后松弛
	 * 复杂度 N*D
	 * @param A
	 * @param X
	 * @param D
	 * @return
	 */
	public static int frogJumpGreedy(int[] A, int X, int D) {
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
					for (curPos = A[time] + D; curPos > A[time] && !posAvailable[curPos]; curPos--) {}
					if (curPos >= X) { return time; }
				}
			}
		}		
		return -1;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 3, 1, 4, 2, 5};
//		int[] a2 = {};
		System.out.println(frogJumpGreedy(arr, 7, 3));
		//System.out.println(frogJumpMine(arr, 7, 3));
		//System.out.println(frogJumpMine(arr, 5, 1));
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
	
	
	
	
    public static int frogJump(int[] arr, int X, int D) {
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
