package algorithm;

public class SearchInRotatedSortedArrayII {
	public static boolean search(int[] nums, int target) {
		if (nums == null || nums.length == 0) { return false; }
        return searchSub(nums, target, 0, nums.length - 1);
    }
	
	private static boolean searchSub(int[] nums, int target, int s, int e) {
		if (s == e) { 
			return (nums[s] == target);
		}		
		if ((nums[s] < nums[e]) && (target < nums[s] || nums[e] < target)) { return false; }		
		int k = (e - s) / 2 + s;
		return (searchSub(nums, target, k + 1, e) 
				|| searchSub(nums, target, s, k));
	}
	
	public static void main(String args[]) {
		int[] nums = {1,3};
		int target = 1;
		System.out.println(search(nums, target));
	}
}
