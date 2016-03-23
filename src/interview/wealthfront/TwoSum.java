package interview.wealthfront;

import java.util.HashMap;

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int[] a = new int[2];
        int i1, i2;
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            hs.put(nums[i], i+1);
        }
        for (int j = 0; j < nums.length; j++) {
            if (hs.containsKey(target - nums[j])) {
                i1 = j + 1;
                i2 = hs.get(target - nums[j]);
                if (i1 == i2) {
                    continue;
                } else {
                    a[0] = i1;
                    a[1] = i2;
                    return a;
                }
               
            }
        }
        return a;
    }
}
