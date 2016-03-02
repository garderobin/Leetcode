package algorithm;

public class BurstBalloon {
	
	/*
	 * 我有一点没有想明白，这个公式的得出真的有数学保障吗？
	 */
	public static int maxCoins(int[] nums) {
	    int[] A = new int[nums.length + 2];
	    int n = 1;
	    for (int x : nums) if (x > 0) A[n++] = x;
	    A[0] = A[n++] = 1;


	    int[][] dp = new int[n][n];
	    for (int k = 2; k < n; ++k) {
	        for (int left = 0; left < n - k; ++left) {
	            int right = left + k;
	            for (int i = left + 1; i < right; ++i) { 
	                dp[left][right] = Math.max(dp[left][right], 
	                A[left] * A[i] * A[right] + dp[left][i] + dp[i][right]);
	            }
	        }
	        System.out.println();
	    }
	    return dp[0][n - 1];
	}
	
	
	/*
	 * 我有一点没有想明白，为什么在
	 */
	public static int maxCoinsDiscussionTest(int[] iNums) {
	    int[] nums = new int[iNums.length + 2];
	    int n = 1;
	    for (int x : iNums) if (x > 0) nums[n++] = x;
	    nums[0] = nums[n++] = 1;


	    int[][] dp = new int[n][n];
	    for (int k = 2; k < n; ++k) {
	        for (int left = 0; left < n - k; ++left) {
	            int right = left + k;
	            for (int i = left + 1; i < right; ++i) {
	            	System.out.println("max(dp[" + left + "][" + right + "]=" + dp[left][right] +
	            			", \tA[" + left + "]*A[" + i + "]*A[" + right + "] + " +
	            			"dp[" + left + "][" + i + "] + dp[" + i + "][" + right + "] = " + 
	            			(nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]) + ")");
	                dp[left][right] = Math.max(dp[left][right], 
	                nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
//	                System.out.println("dp[" + left + "][" + right + "]=" + dp[left][right]);
	            }
	        }
	        System.out.println();
	    }
	    return dp[0][n - 1];
	}
	
	
	public static void main(String[] args) {
		int[] iNums = {3, 1, 5, 8, 7, 2, 4, 6};
		System.out.println(maxCoins(iNums)); 
	}
	
//	public int maxCoins(int[] nums) {
//        if (nums == null || nums.length == 0) { return 0; }
//        int lnb = 1, rnb = 1; // left neighbor, right neighbor
//        return 0;
//    }
	
}
