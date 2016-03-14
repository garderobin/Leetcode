package interview.google;

import java.util.LinkedList;

public class PatchingArray {
	
	public static int minPatches(int[] nums, int n) {
		long miss = 1, added = 0, i = 0;
	    while (miss <= n) {
	        if (i < nums.length && nums[(int)i] <= miss) {
	            miss += nums[(int)i++];
	        } else {
	            miss += miss; //补上的这个数正是miss，而一旦补上，miss * 2的范围都可以覆盖了。而miss * 2极有可能超过int最大值。
	            added++;
	        }
	    }
	    return (int)added;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 5, 10};
		int n = 20;
		minPatches(nums, n);
	}
	
	/**
	 * 未完成的错误答案
	 * @param nums
	 * @param n
	 * @return
	 */
	public static int minPatchesMy(int[] nums, int n) {
        if (n == 1) { return nums[0]==1 ? 0 : 1; }
//        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        LinkedList<Integer> list = new LinkedList<Integer>();
        int sum = 0, rst = 0;
        for (int i = nums.length; i > 0; i--) {
//        	queue.offer(nums[i-1]);
        	list.addFirst(nums[i-1]);
        	sum += nums[i-1];
        }
        
        if (nums[0] != 1) {
        	sum += (rst = 1);
//        	queue.offer(1);
        	list.addFirst(1);
        }
        if (n > sum) {
        	return rst + patchHelper(list, Math.max(sum, n-sum));
        } else if (n < sum) {
        	
        } else {
        	
        }
        
		return -1;
    }
	
	public static int patchHelper(LinkedList<Integer>queue,  int n) {
		if (n == 1) { return 0; }
//		queue.
		return -1;
	}
}
