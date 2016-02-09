package algorithm;

public class RemoveDuplicatesFromSortedArrayII {
	
	/**
	 * 还没明白怎么回事，要再看！
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		int i = 0;
	    for (int n : nums)
	        if (i < 2 || n > nums[i-2])
	            nums[i++] = n;
	    return i;
    }
}
