package algorithm;

public class SearchRanges {
	
	/**
	 * 还没仔细看，需要再写一遍。
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] searchRangeDiscussion(int[] nums, int target) {
		int start = firstGreaterEqual(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, firstGreaterEqual(nums, target + 1) - 1};
	}
	
	//find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    //actually this is the same as lower_bound in C++ STL.
    private static int firstGreaterEqual(int[] A, int target) {
        int low = 0, high = A.length;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            //low <= mid < high
            if (A[mid] < target) {
                low = mid + 1;
            } else {
                //should not be mid-1 when A[mid]==target.
                //could be mid even if A[mid]>target because mid<high.
                high = mid;
            }
        }
        return low;
    }
	
	public static int[] searchRange(int[] nums, int target) {
		int[] rst = {-1, -1};
        if (nums == null || nums.length == 0 || target > nums[nums.length - 1] || target < nums[0]) {
        	return rst;
        }
        return findTarget(nums, target, 0, nums.length - 1);
    }
	
	private static int[] findTarget(int[] nums, int target, int start, int end) {		
		if (start == end) {
			if (nums[start] != target) {
				int rst[] = {-1, -1};
				return rst;
			}							
		}
		if (target > nums[end] || target < nums[start]) {
			int rst[] = {-1, -1};
			return rst;
		}
		int k = (end - start) + start;
		if (nums[k] > target) {
			return findTarget(nums, target, start, k - 1);
		} else if (nums[k] < target) {
			return findTarget(nums, target, k + 1, end);
		}
		int i = k - 1, j = k + 1;
		while (i > -1 && nums[i] == target) {
			i--;
		}
		while (j < nums.length && nums[j] == target) {
			j++;
		}
		int[] rst = {i + 1, j - 1};
		return rst;
	}
	
	
	
	public static void main(String[] args) {
		int[] nums = {5, 7, 7, 8, 8, 10};
		int[] rst = searchRange(nums, 1);
		System.out.println(rst[0] + ", " + rst[1]);
	}
}
