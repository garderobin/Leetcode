package algorithm;

public class FindPeakElement {
	public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) { return -1; }
        if (nums.length == 1) { return 0; }
        if (nums[0] > nums[1]) { return 0; }
        if (nums[nums.length - 1] > nums[nums.length - 2]) { return nums.length - 1; }
        return peakSub(nums, 1, nums.length - 2);
    }
	
	private int peakSub(int[] nums, int start, int end) {
		if (start > end) 	{ return -1; }
		if (start == end || nums[start] > nums[start + 1]) 	{ return start; }
        if (nums[end] > nums[end - 1]) { return end; }
        return peakSub(nums, ++start, --end);
	}
}
