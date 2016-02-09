package algorithm;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
	
	/**
	 * 这里的DP存储的就是这个LIS本身。
	 * 每次遇到一个新数，如果比原来的LIS都大，长度加一；
	 * 反之，它应该取代的那个数更新为现在的大数。
	 * 被改掉的如果是小的，并不会影响大的。
	 * @param nums
	 * @return
	 */
	public static int lengthOfLISDiscussion(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if (i < 0) { i = -(i + 1); }
            dp[i] = x;
            if(i == len) { len++; }
        }

        return len;
    }
	
	public static int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) { return 0; }
		if (nums.length == 1) { return 1; }
        int[] f = new int[nums.length];
		int i, j, max = 1;
		Arrays.fill(f, 1);
        for (i = 1; i < nums.length; i++) {            
        	for (j = 0; j < i; j++) {
        		if (nums[i] > nums[j]) {
        			f[i] = Math.max(f[j] + 1, f[i]);
        		}
        	}
        }
        
        for (i = 0; i < f.length; i++) {
        	max = Math.max(max, f[i]);
        }
        
        return max;
    }
	
	
	public static void main(String[] args) {
		int[] nums = {10,9,2,5,3,4,0,11};
		System.out.println(lengthOfLISDiscussion(nums));
	}
}
