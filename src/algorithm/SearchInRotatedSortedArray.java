package algorithm;

public class SearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
		if (nums == null) { return -1; }
        return searchSub(nums, target, 0, nums.length - 1);
    }
	
	private int searchSub(int[] nums, int target, int s, int e) {
		if (s == e) {
			return (nums[s] == target) ? s : -1;
		}
		
		if ((nums[s] < nums[e]) && (nums[s] > target || nums[e] < target)) {			
			return -1;			
		}
		
		int k = (e - s) / 2 + s, r, l;
		if ((r = searchSub(nums, target, k + 1, e)) >= 0) {
			return r;
		}
		if ((l = searchSub(nums, target, s, k)) >= 0) {
			return l;
		}
		return -1;
	}
}
