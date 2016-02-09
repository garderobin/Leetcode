package algorithm;

public class FirstMissingPositive {
	
	public static int firstMissingPositiveDiscuss2(int[] nums) {
		for(int i = 0; i < nums.length; ++ i)
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i])
                swap(nums, i, nums[i] - 1);

        for(int i = 0; i < nums.length; ++ i)
            if(nums[i] != i + 1)
                return i + 1;

        return nums.length + 1;
	}
	
	public static int firstMissingPositiveDiscuss2Test(int[] nums) {
		for(int i = 0; i < nums.length; ++ i) {
			printNums(nums, "");
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                printNums(nums, "To swap: nums[" + i + "], nums[" + (nums[i] - 1) + "]");
            	swap(nums, i, nums[i] - 1);                
            }
		}
        for(int i = 0; i < nums.length; ++ i) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
	}
	
	public static void main(String[] args) {
		int[] nums = {-2, -3, 3, 4, -1, 1};
		System.out.println(firstMissingPositiveDiscuss2Test(nums));
	}
	
	private static void printNums(int[] nums, String prompt) {
		System.out.println(prompt);
		System.out.print("[");
		for (int e : nums) {
			System.out.print(e + "\t");
		}
		System.out.println("]");
	}
	
	public static int firstMissingPositiveDiscuss(int[] A) {
        int i = 0;
        while (i < A.length) {
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }

    private static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
	
//	public int firstMissingPositive(int[] nums) {
//        
//    }
}
