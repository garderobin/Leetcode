package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CountOfSmallerNumbersAfterSelf {
	public List<Integer> countSmaller(int[] nums) {
		if (nums == null || nums.length == 0) { return new ArrayList<Integer>(); }
        int n = nums.length, min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        List<Integer>  stack = new Stack<Integer>();
        int[] dp = new int[n+1], arr = new int[n+1];
        for (int i = 0; i < n; ++i) { arr[i] = nums[i]; }
        arr[n] = Integer.MAX_VALUE;
        
        for (int i = n; i > 0; --i) {
        	for (int j = 0; j < i; ++j) {
        		dp[j] = dp[j] + (nums[i] < nums[j] ? 1 : 0);
        	}
//        	stack.push(dp[])
        }
        
        return null;
    }
}
