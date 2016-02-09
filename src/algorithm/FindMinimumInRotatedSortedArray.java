package algorithm;

public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
		return findMinSub(nums, 0, nums.length - 1);
    }
	
	private int findMinSub(int[] nums, int s, int e) {
		if (s == e || nums[s] < nums[e]) {
			return nums[s];
		}
		int k = (e - s) / 2 + s, r, l;
		if ( (r = findMinSub(nums, k + 1, e)) != nums[k + 1] ) {
			return r;
		}
		if ( (l = findMinSub(nums, s, k)) != nums[s]) {
			return l;
		}
		return nums[k + 1];
	}
}
