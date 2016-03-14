package algorithm;

public class NextPermutation {
	public static void nextPermutation(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
        int i = nums.length - 1;
        int j = nums.length - 1;
        int temp;        
        while (i > 0) {        	
        	if (nums[i] > nums[i - 1]) {
        		while (nums[i - 1] >= nums[j]) {
        			j--;
        		}
        		temp = nums[j];
        		nums[j] = nums[i - 1];
        		nums[i - 1] = temp;
//        		len = nums.length - i;
        		for (j = nums.length - 1; j > i; j--) {
        			temp = nums[j];
        			nums[j] = nums[i];
        			nums[i] = temp;
        			i++;
        		}
        		return;
        	}
        	i--;
        }
//        len = nums.length - 1;
        for (j = nums.length - 1; j > i; j--) {
        	temp = nums[j];
        	nums[j] = nums[i];
        	nums[i] = temp;
        	i++;
        }
    }
	
	public static void main(String[] args) {
//		int[] t1 = {2,5,4,1}; //
		int[] t2 = {5,4,3,2,1};
//		int[] t3 = {1,2,3};
		nextPermutation(t2);
		for (int e : t2) {
			System.out.print(e + ", ");
		}
		
	}
}
