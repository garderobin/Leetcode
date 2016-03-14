package WayFair;

public class SingleNumber {
	public static int singleNumber(int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
			
		int xor = nums[0];
		for (int i = 1; i < nums.length; i++) {
			xor ^= nums[i];
		}
        return xor;
    }
}
