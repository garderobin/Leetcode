package algorithm;

public class ProductOfArrayExceptSelf {
	/**
	 * 它的做法的好处是extra space O(1).
	 * 用的办法是先算左，然后累进算进来右边，
	 * 用1来抵消最左最右的元素，使得两边推进的时候都会隔离当下元素。实现except.
	 * @param nums
	 * @return
	 */
	public int[] productExceptSelfDiscussion(int[] nums) {
	    int n = nums.length;
	    int[] res = new int[n];
	    res[0] = 1;
	    for (int i = 1; i < n; i++) {
	        res[i] = res[i - 1] * nums[i - 1];
	    }
	    int right = 1;
	    for (int i = n - 1; i >= 0; i--) {
	        res[i] *= right;
	        right *= nums[i];
	    }
	    return res;
	}
	
	/**
	 * 我这种思路还是用到extra space.
	 * @param nums
	 * @return
	 */
	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length < 2) {
			return nums;
		}
		int[] product = new int[nums.length];
        int[] pLeft = new int[nums.length];  // pLeft[i] is product of elements from index 0 to i;
        int[] pRight = new int[nums.length]; // pRight[i] is product of elements from index i to len - 1;
        pLeft[0] = nums[0];
        pRight[nums.length - 1] = nums[nums.length - 1];       
        //学会两头并进，双指针比较不容易出错，能提高效率。
        for (int i = 1, j = nums.length - 2; i < nums.length && j > 0; i++, j--) { //待修改
        	pLeft[i] = pLeft[i - 1] * nums[i];
        	pRight[j] = pRight[j + 1] * nums[j];
        }
        
        product[0] = pRight[1];
        product[nums.length - 1] = pLeft[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
        	product[i] = pLeft[i - 1] * pRight[i + 1];
        }
        
        return product;
    }
}
