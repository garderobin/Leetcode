package algorithm;

public class RotateArray {
	
	/**
	 * 原理是先全部逆转，使得两部分各在需要的区域，
	 * 然后再对每个区域进行元素顺序反正。
	 * @param nums
	 * @param k
	 * @return
	 */
	public static void rotateDiscussion(int[] nums, int k) {
		if(nums.length <= 1){
            return;
        }
        //step each time to move
        int step = k % nums.length; //除余是为了处理 k >= nums.length 的情况。
        reverse(nums,0,nums.length - 1);
        reverse(nums,0,step - 1);
        reverse(nums,step,nums.length - 1);
	}
	
	
	/***
	 * reverse int array from n to m
	 * 使用多次异或实现无extra space的swap.
	 * @param nums
	 * @param n
	 * @param m
	 */
    public static void reverse(int[] nums, int n, int m){
        while(n < m){
            nums[n] ^= nums[m];
            nums[m] ^= nums[n];
            nums[n] ^= nums[m];
            n++;
            m--;
        }
    }
	
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
	
	/**
	 * 从右开始，每个元素往左移动(n-k)位。
	 * 左边的元素则每个向右移动k位。
	 * @param nums
	 * @param k
	 * @return
	 */
	public static int[] rotate2(int[] nums, int k) {
		return nums;
		
	}
	
	public static int[] rotate3(int[] nums, int k) {
		return nums;
		
	}
}
