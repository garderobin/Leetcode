package interview.snapchat;
/*
 * 说是有个array，找到一个index使它左边的和与右边的和相等。比如｛1，2，3，4，3，2，1｝->3
 * 问：一定存在吗？只存在一个？
 * 
 */
public class ArrayLeftSumEqualsRightSum {
	
	/*
	 * 找到一个就返回
	 */
	public static int sol(int[] nums) {
		if (nums == null || nums.length == 0) return -1;
		int[] sums = new int[nums.length];
		int total = 0;
		for (int i = 0; i < nums.length; ++i) {
			total += nums[i];
			sums[i] = total;
		}
		
		for (int i = 1; i < nums.length; ++i) {
			if (sums[i-1] == total - sums[i]) return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
	}
}
