package interview.snapchat;

import java.util.Arrays;

public class ThreeSumClosest {
	
	/**
	 * 我能明白它为什么对，但不明白自己为什么错。
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosestDiscussion(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
	
	public static int threeSumClosest(int[] nums, int target) {   
		Arrays.sort(nums);
        int i, left, right, sum, prev = 0, closest;
        closest = (target > 0) ? Integer.MAX_VALUE - target : Integer.MIN_VALUE - target;
        for (i = nums.length - 1; i > 1; i--) {
        	if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
        		continue;
        	}
        	right = i - 1;
        	left = 0;        	
        	sum = nums[left] + nums[right] + nums[i];
        	prev = sum;
        	while (left < right && (sum = nums[left] + nums[right] + nums[i]) < target) {
        		while (nums[left] == nums[left + 1] && left < right - 1) {
            		left++;
            	}
        		left++;
        		prev = sum;
        	}
        	if (sum == target || prev == target) {
        		return target;
        	}
        	
        	if (Math.abs(target - sum) < Math.abs(target - closest)) {
        		closest = sum;
        	}
        	if (Math.abs(target - prev) < Math.abs(target - closest)) {
        		closest = prev;
        	}
        }
        
        return closest;
    }
	
	public static void main(String args[]) {
		int[] nums = {1, 1, -1, -1, 3};
		int target = -1;
		System.out.println(threeSumClosest(nums, target));
	}
}
