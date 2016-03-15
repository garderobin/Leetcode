package interview.wayfair;

public class MaximumSubArray {
	public int[] maxSubArray(int[] nums) {
		if (nums == null) return new int[0]; 
		if (nums.length < 2) return nums;
		
		long maxSum = nums[0] , curSum = nums[0] + 0; // max total sub-sum and max consecutive sub-sum.s
		int start = 0, end = 0, curStart = 0, n = nums.length;
		for (int i = 0; i < n; ++i) {
			int e = nums[i];
			curSum += e;
			if (curSum < e) {
				curSum = e;
				curStart = i;
			}
			if (curSum > maxSum) {
				start = curStart;
				end = i;
			}
		}
		
		int[] sub = new int[end - start + 1];
		for (int i = start; i <= end; ++i) { sub[i-start] = nums[start]; }
		return sub;
	}
}
