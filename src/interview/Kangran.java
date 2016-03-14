package interview;

public class Kangran {
	public static int removeDuplicates(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int posStart = 1, posEnd = 0;
		int prev = nums[0];
		while (posEnd <= nums.length - 1 && posStart <= nums.length - 1) {
			while (posStart <= nums.length - 1 && nums[posStart] < prev) {
				prev = nums[posStart];
				posStart++;
			}
			while (posEnd <= nums.length - 1 && nums[posEnd] <= nums[posStart - 1]) {
				posEnd++;
			}
			if (posEnd <= nums.length - 1 && nums[posEnd] >= nums[posStart]) { // use >= instead of >
				int temp = nums[posEnd];
				nums[posEnd++] = nums[posStart]; // use posEnd++ instead of posEnd
				nums[posStart++] = temp;
			}
		}
		return posStart;
	}
	
	public static int penghui(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int i, j;
		for (i = 0, j = 1; j < nums.length; j++) {
			if (nums[j] > nums[i]) {
				nums[++i] = nums[j];
			}			
		}
		return i;
	}
	
//	private static void swap(int[] nums, int i, int j) {
//		int temp = nums[i];
//		nums[i] = nums[j];
//		nums[j] = temp;
//	}
	
	public static void main(String[] args) {
//		int[] nums0 = {1, 2};
//		int[] nums1 = {0,0,0,3,3,5,5,7,8};
		int[] nums2 = {0,0,2,2,4,4,6,6,8,8,9,10};
		System.out.println(removeDuplicates(nums2));
	}
}
