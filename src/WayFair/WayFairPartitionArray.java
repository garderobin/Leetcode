package WayFair;
/*
 * 一道coding题，一个array，里面有正负数，要求负的放左边，正的放右边，要保持正负数的相对顺序，要求用两种方法做。
 * 注意看leetcode 86题 Partition(LinkedList)
 */
public class WayFairPartitionArray {
	/*
	 * In-place version. O(N^2) time, O(1) space.
	 * Bubble sort. 
	 */
	public static void partitionArray1(int[] nums) {
		if (nums == null || nums.length < 2) { return; }
		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] < 0) { 
				for (int j = i; j > 0 && nums[j-1] > 0; --j) {
					swap(nums, j-1, j);
				}
			} 
		}
	
	}
	
	/*
	 * O(N) time, O(N) space.
	 * Two-pass.
	 */
	public static void partitionArray2(int[] nums) {
		if (nums == null || nums.length < 2) { return; }
		// First pass: count negative numbers and positive numbers.
		int left = 0, right = 0;
		int[] backup = new int[nums.length];
		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] < 0) { ++right; }
			backup[i] = nums[i];
		}
		
		// Second pass: build sorted array.
		for (int e: backup) {
			if (e < 0) 	{ nums[left++] = e;  }
			else 		{ nums[right++] = e; }
		}
	}
	
	
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, -2, 3, -4, 5, 6, 7, -8, -9, 10};
		partitionArray2(nums);
		for (int e: nums) { System.out.print(e + ", "); }
	}
}
