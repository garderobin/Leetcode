package algorithm;

import java.util.HashMap;

public class ContainsDuplicate {
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		if (nums == null || k <= 0) {
			return false;
		}
		
		HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if ( hs.containsKey(nums[i]) ) {
				if (i - hs.get(nums[i]) <= k ) {
					return true;
				} 				
			}
			hs.put(nums[i], i);
		}
		
        return false;
    }
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || k <= 0) {
			return false;
		}
		
		HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if ( hs.containsKey(nums[i]) ) {
				if (i - hs.get(nums[i]) <= k ) {
					return true;
				} 				
			}
			hs.put(nums[i], i);
		}
		
        return false;
    }
}
