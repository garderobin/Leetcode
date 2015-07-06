package algorithm;

import org.javatuples.Pair;

public class MajorityElement {
	public static int majorityElement(int[] nums) {
		int k = nums.length / 2;
		//int left = maforityElement(0)
        return -1;
    }
	
//	public static Pair<Integer, Integer> findMajorityElement(int start, int end, int[] nums) {
//		if (start == end) {
//			return Pair.with(nums[start], 1);
//		}
//		int k = (end - start)/2 + start;
//		Pair<Integer, Integer> left = findMajorityElement(start, k, nums);
//		Pair<Integer, Integer> right = findMajorityElement(k+1, end, nums);
//		if (left.getValue0() == right.getValue0()) {
//			return Pair.with(left.getValue0(), left.getValue1() + right.getValue1());
//		} else if (left.getValue0() >= right.getValue0()) {
//			return left;
//		} 
//		
//		return right;
//	}
	
	
}
