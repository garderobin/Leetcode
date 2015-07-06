package algorithm;

public class HouseRobber {
	public static int rob(int[] nums) {
        int[] f = new int[nums.length];
        f[0] = nums[0];
        f[1] = (nums[0] > nums[1]) ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++) {
        	f[i] = (f[i-2] + nums[i] > f[i-1]) ? f[i-2] + nums[i] : f[i-1];
        }
        
        return f[nums.length - 1];
    }
	public static int robII(int[] nums) {
		if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        int[][] f = new int[nums.length][2];
        f[0][0] = nums[0];
        f[0][1] = 0;
        f[1][0] = nums[0];
        f[1][1] = nums[1];
        if (nums.length == 2) {
            return Math.max(f[1][0], f[1][1]);
        }
        int i = 2;
        for (i = 2; i < nums.length - 1; i++) {
        	f[i][0] = (f[i-2][0] + nums[i] > f[i-1][0]) ? f[i-2][0] + nums[i] : f[i-1][0];
        	f[i][1] = (f[i-2][1] + nums[i] > f[i-1][1]) ? f[i-2][1] + nums[i] : f[i-1][1];
        }
        f[i][0] = f[i-1][0];
        f[i][1] = Math.max(f[i-2][1] + nums[i], f[i-1][1]);
        return Math.max(f[i][0], f[i][1]);
    }
	
	public static void main(String args[]) {
		int[] nums = {1,3,1};
		System.out.println(robII(nums));
	}
}
