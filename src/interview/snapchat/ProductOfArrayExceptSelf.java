package interview.snapchat;

public class ProductOfArrayExceptSelf {
	public static void main(String[] args) {
		int[] a = {1,2,3};
		int[] b = a;
		b[0] = 0;
		System.out.println(a[0]);
	}
	
	/*
	 * O(n) time, O(1) space
	 * Pros: constant space
	 * Cons: two passes.
	 * 用的办法是先算左，然后累进算进来右边，
	 * 用1来抵消最左最右的元素，使得两边推进的时候都会隔离当下元素。实现except.
	 */
	public int[] productExceptSelfV2(int[] nums) {
		if (nums == null || nums.length == 0) return new int[0];
		if (nums.length == 1) return new int[]{0};
		
		int n = nums.length, right = 1;
		int[] output = new int[n];
		
		// Multiply left part
		output[0] = 1;
		for (int i = 1; i < n; ++i) {
			output[i] = output[i-1] * nums[i-1];
		}
		
		// Multiply right part
		for (int i = n-1; i > 0; --i) {
			output[i] *= right;
			right *= nums[i];
		}
		
		return output;
	}
	
	/*
	 * O(n) time, O(n) space
	 * 好处One-pass, 坏处是extra space
	 */
	public int[] productExceptSelfV1(int[] nums) {
		if (nums == null || nums.length == 0) return new int[0];
		if (nums.length == 1) return new int[]{0};
		
		int n = nums.length;
		// left[i] is the product from 0th to ith elements (both side inclusive), right[i] from ith to (n-1)th elements
		int[] left = new int[n+1], right = new int[n+1], output = new int[n];
		left[0] = 1;
		right[n] = 1;
		
		for (int i = 1, j = n-2; i < n; ++i, --j) {
			left[i] = left[i-1] * nums[i-1];
			right[j] = right[j+1] * nums[j+1];
			
			if (i >= j) {
				output[i] = left[i-1] * right[i+1];
			}
		}
		
		return output;
	}
}
