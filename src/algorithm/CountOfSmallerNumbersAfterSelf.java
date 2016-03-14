package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CountOfSmallerNumbersAfterSelf {
	public static List<Integer> countSmaller(int[] nums) {
		if (nums == null || nums.length == 0) { return new ArrayList<Integer>(); }
        int n = nums.length;
        Stack<Integer> stack = new Stack<Integer>();
        int[] dp = new int[n+1], arr = new int[n+1];
        for (int i = 0; i < n; ++i) { arr[i] = nums[i]; }
        arr[n] = Integer.MAX_VALUE;
        
        for (int i = n-1; i >= 0; --i) {
        	stack.push(dp[i]);
        	for (int j = 0; j < i; ++j) {
        		dp[j] = dp[j] + (nums[i] < nums[j] ? 1 : 0);
        	}
        }
        
        List<Integer> rst = new ArrayList<Integer>();
        while (!stack.isEmpty()) { rst.add(stack.pop()); }
        return rst;
    }
	
	public static void main(String[] args) {
		int[] nums = {5, 2, 6, 1};
		Stack<Integer> stack = new Stack<Integer>();
		for (int e: nums) { stack.push(e); }
		List<Integer> rst = countSmaller(nums);
		System.out.println(rst);
//		System.out.println(countSmaller(nums));
	}
}
