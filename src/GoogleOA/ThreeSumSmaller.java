package GoogleOA;

import java.util.Arrays;

public class ThreeSumSmaller {
	
	public static int threeSumSmallerImprove(int[] nums, int target) {
		if (nums == null || nums.length < 3) { return 0; }
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 1; i--) {
        	int left = 0, right = i-1;
        	while (left < right) {
        		if (nums[left] + nums[right] + nums[i] < target) {
    				count += right - (left++);
    			} else {
    				right--;
    			}
        	}
        }
        return count;
    }

	/**
	 * 自己做的， O(N^3)复杂度，明显不够优化
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int threeSumSmaller(int[] nums, int target) {
		if (nums == null || nums.length < 3) { return 0; }
        Arrays.sort(nums);
        int rst = 0;
        for (int i = nums.length - 1; i > 1; i--) {
        	for (int j = i-1; j > 0; j--) {
        		for (int k = j-1; k >= 0; k--) {
        			if (nums[i] + nums[j] + nums[k] < target) {
        				rst += k+1;
        				break;
        			}
        		}
        	}
        }
        return rst;
    }
	
//	private static int combination(int total, int select) {
//		if (total < select || select < 0) { return 0; }
//		select = Math.min(select, total - select);
//		long divident = 1, divider = 1;
//		for (int i = 0; i < select; i++) {
//			divident *= (total-i);
//			divider *= (i+1);
//		}
//		return (int) (divident / divider);
//	}
	
	public static void main(String[] args) {
		int[] nums = {-2, 0, 1, 3}; 
		System.out.println(threeSumSmaller(nums, 2));
	}
}
