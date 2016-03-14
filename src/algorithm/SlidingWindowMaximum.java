package algorithm;

public class SlidingWindowMaximum {
	/**
	 * Wrong answer. See C++ version.
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0) { return new int[0]; }
		else if (k == 1) { return nums; }
        int n = nums.length, m = Math.max(1, n - k + 1), max = Integer.MIN_VALUE;
        int[] rst = new int[m];
        for (int i = 0; i < k && i < n; ++i) { max = (max > nums[i]) ? max : nums[i]; }
        rst[0] = max;
//        for (int i = k, j = 0; i < n; ++i) {
//        	rst[++j] = (max = (nums[i] > max) ? max : nums[i]);
        	
//        }
        return rst;
    }
}
