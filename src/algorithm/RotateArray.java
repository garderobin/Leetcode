package algorithm;

public class RotateArray {
	public static int[] rotate1(int[] nums, int k) {
		int[] rst = new int[nums.length];
		for(int i = 0; i <= k; i++) {
			rst[i + k] = nums[i];
		}
		for (int j = 0; j < nums.length - k - 1; j++) {
			rst[j] = nums[j + k];
		}
		return rst;
		
	}
	
	public static int[] rotate2(int[] nums, int k) {
		return nums;
		
	}
	
	public static int[] rotate3(int[] nums, int k) {
		return nums;
		
	}
}
