package algorithm;

public class MoveZeros {
	public static void moveZeroes(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
        int i = 0, j = 1, temp;
        while (nums[i] != 0 && i < nums.length) {
    		i++;
    	}        
        for (j = i + 1; j < nums.length; j++) {        	
        	if (nums[j] != 0) {
        		temp = nums[i];
        		nums[i++] = nums[j];
        		nums[j] = temp;
        	}
        }
    }
	
	public static void main(String[] args) {
		int[] nums = {0,1,0,3,12};
		moveZeroes(nums);
		for(int e: nums) {
			System.out.print(e + "\t");
		}
	}
}
