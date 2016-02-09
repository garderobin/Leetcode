package algorithm;

public class MaximumSubarray {
	
	
	
	/**
	 * O(N)并不难，但分治法是一种更优化的处理。
	 * @param nums
	 * @return
	 */
	public static int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) { return 0; }
		if (nums.length == 1) { return nums[0]; }
		return helper(nums, 0, nums.length - 1);
    }
	
	private static int helper(int[] nums, int start, int end) {
		if (start == end) { return (nums[start] > 0) ? nums[start] : 0; }
		int k = (end - start) / 2 + start, left, right, maxSingle, sum;		
		left = helper(nums, start, k);
		right = helper(nums, k+1, end);
		if ((sum = left + right) == 0 && (maxSingle = Math.max(maxSingle(nums, start, k), maxSingle(nums, k+1, end))) < 0) {
			return maxSingle;
		}
		return sum;
	}
	
	private static int maxSingle(int[] nums, int start, int end) {
		if (start == end) { return nums[start]; }
		if (start + 1 == end) { return Math.max(nums[start],nums[end]); }
		int k = (end - start) / 2 + start, max;
		max = Math.max(maxSingle(nums, start, k), maxSingle(nums, k+1, end));
		return max;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,-1,1};
		System.out.println(maxSubArray(nums));
 	}
}
