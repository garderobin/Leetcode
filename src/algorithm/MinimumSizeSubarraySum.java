package algorithm;

public class MinimumSizeSubarraySum {
	/**
	 * two pointers
	 * @param s
	 * @param nums
	 * @return
	 */
	public static int minSubArrayLenV2(int s, int[] nums) {
		if (nums == null || nums.length == 0) { return 0; }
        int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE, curLen;
        for (; end < nums.length; end++) {
        	if (nums[end] >= s) { return 1; }
        	if ((sum += nums[end]) < s) { 
        		continue; 
        	}
        	for (; sum >= s && start < end; sum -= nums[start++]) { }        	
        	if ((curLen = end - start + 2) < minLen) { 
        		minLen = curLen; 
        	}
        }
        return (minLen <= nums.length) ? minLen: 0;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,1};
		int s = 3;
		System.out.println(minSubArrayLenV2(s, nums));
	}
	
	
	/**
	 * 原始DP: O(n*n)
	 * @param s
	 * @param nums
	 * @return
	 */
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0) { return 0; }
        int[][] f = new int[nums.length+1][nums.length+1]; // f[i][j] means the sum of sub-array from i to j (both inclusive).
        int i, j, minLen = Integer.MAX_VALUE, curLen;
        for (i = 1; i < f.length; i++) {
        	for (j = i, curLen = j - i + 1; j < f[0].length && curLen < minLen; j++) {
        		f[i][j] = f[i][j-1] + nums[j-1];
        		for (; f[i][j] >= s; ++i, curLen = j - i + 1) {
        			minLen = curLen;
        			f[i][j] = s - nums[i-1];
        		}
        		
        	}
        }
		return (minLen <= nums.length) ? minLen : 0;
    }
}
