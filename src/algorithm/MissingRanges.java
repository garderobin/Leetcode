package algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingRanges {
	
	/**
	 * 暂时不考虑duplicates.
	 * @param nums
	 * @param lower
	 * @param upper
	 * @return
	 */
	public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> rst = new ArrayList<String>();
		if (lower > upper) { return rst; }
		else if (lower == upper) {
			if (Arrays.binarySearch(nums, lower) < 0) { rst.add(lower  + ""); }
		} else if (nums == null || nums.length == 0) {
			rst.add(lower + "->" + upper);
		} else {
			int miss = lower;
	        for (int i = 0; i < nums.length && miss <= upper; i++) {
	        	if (miss > nums[i]) { //如果nums有duplicates也一定会被这一句检查出来
	        		continue; 
	        	} else if (miss == nums[i]) {
	        		miss++;
	        	} else {
	        		rst.add(miss + ((miss + 1 == nums[i]) ? "": "->" + (nums[i] - 1)));
	        		miss = nums[i] + 1;
	        	}
	        }
	        if (miss <= upper) { rst.add(miss + ((miss == upper) ? "": "->" + (upper))); }
		}
		
		return rst;
    }
}
