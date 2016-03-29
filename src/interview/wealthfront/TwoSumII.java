package interview.wealthfront;

import java.util.HashMap;
import java.util.Map;

public class TwoSumII {
	
	public int[] twoSum(int[] nums, int target) {
		int[] rst = new int[2];
        Map<Integer, Integer> expect = new HashMap<>(); // key: expect_value, small_index
        for (int i = 0; i < nums.length; i++) {
    		if (expect.containsKey(nums[i])) {
    			rst[0] = expect.get(nums[i]);
    			rst[1] = i + 1;
    			return rst;
    		} else {
    			expect.put(target - nums[i], i+1);
    		}
    	}
        return rst;
    }
}
