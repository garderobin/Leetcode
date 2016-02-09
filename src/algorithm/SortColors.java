package algorithm;

public class SortColors {
	
	/**
	 * 这种做法异常简介
	 * 岂是：当正面思考极难控制边界和跳过的时候就从反面，或者补集开始思考。
	 * @param nums
	 */
	public static void sortColorsDiscussion(int[] nums) {
        int second = nums.length - 1, zero = 0;
        for (int i = 0; i <= second; i++) {
            //while (nums[i]==2 && i<second) swap(A[i], A[second--]);
            while (nums[i] == 2 && i < second) swap(nums, i, second--);
            while (nums[i] == 0 && i > zero) swap(nums, i, zero++);
        }
	}
	
	/**
	 * 我这种做法为了省略所有不必要的交换而代码复杂
	 * 这样的做法在原有序列排列程度很高的时候能够做到其实不用完全遍历
	 * 但是边界条件太多容易出错
	 * @param nums
	 */
	public static void sortColors(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
        int end0 = -1, start2 = nums.length, i = 0;
        while (end0 < i && i < start2) {
        	while (--start2 >= 0 && nums[start2] == 2) {}
        	if (start2 < 0) {
        	    return;
        	}
        	start2++;
        	while (++end0 < start2 && nums[end0] == 0) {} 
        	if (end0 == start2) {
        	    return;
        	}   
        	if (i < end0) { //这里有没有等号是很重要的边界条件。
    			i = end0;
    		}
        	end0--;        	
        	switch(nums[i]) { 
        	case 0: swap(nums, ++end0, i); break;
        	case 2: swap(nums, --start2, i); break;
        	default: i++; break;
        	}
        	
        }        
    }
	
	private static void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
	
	public static void main(String[] args) {
		int[] nums = {2,1,1,0,0,1,1,2,1,0,2,1};
		//int[] nums = {1, 2};
		sortColors(nums);
		for (int i : nums) {
			System.out.print(i + ", ");
		}
	}
	
}
