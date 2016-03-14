package algorithm;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaximumGap {
	
	/**
	 * Radix sort by binary lengths and digits
	 * @param nums
	 * @return
	 */
	public static int maximumGapV3(int[] nums) {
		if (nums == null) { return 0; }
		else if (nums.length < 2) { return 0; }
		else if (nums.length == 2) { return Math.abs(nums[1] - nums[0]); }
		
		// Radix sort by binary presentation. O(N) time. O(N) space.
		int n = nums.length, maxGap = 0;
		int[] sorted = new int[n]; // sort does not consider duplicates
		Deque<Integer> idxs = new LinkedList<Integer>(), vals = new LinkedList<Integer>(), //同步消长方可
				doneIdxs = new LinkedList<Integer>(); //用ListNode可能会更好
		for (int i = 0; i < n; ++i) { idxs.add(i); vals.add(nums[i]); }
		for (int i = 0, count = 0; i < 32 && idxs.size() > 0; ++i) {
			Deque<Integer> iLeft = new LinkedList<Integer>(), vLeft = new LinkedList<Integer>(), //同步消长方可
					iRight = new LinkedList<Integer>(), vRight = new LinkedList<Integer>();
			for (Iterator<Integer> it = idxs.iterator(), vt = vals.iterator(); it.hasNext(); ) {
				int index = it.next(), value = vt.next();
				switch (value) {
				case 0: doneIdxs.addLast(index); break;
				case 1: doneIdxs.addFirst(index); break;
				default:  
					if ((value & 1) == 0) { iLeft.add(index); vLeft.add(value >> 1); }
					else 				  { iRight.add(index); vRight.add(value >> 1); 	 }
				}
			}
			while (!doneIdxs.isEmpty()) { sorted[count++] = nums[doneIdxs.pollLast()]; }
			iLeft.addAll(iRight); vLeft.addAll(vRight); //这一步是有O(N)复杂度的！再改就好了
			idxs = iLeft; vals = vLeft;
		}
		
		for (int i = 1; i < n; ++i) { maxGap = Math.max(maxGap, sorted[i] - sorted[i-1]); }
		
		return maxGap;
	}
	
	public static void main(String[] args) {
		int[] nums = {15252,16764,27963,7817,26155,20757,3478,22602,20404,6739,16790,10588,16521,6644,20880,15632,27078,25463,20124,15728,30042,16604,17223,4388,
				23646,32683,23688,12439,30630,3895,7926,22101,32406,21540,31799,3768,26679,21799,23740};
		System.out.println(maximumGapV3(nums));
	}
	
	/**
	 * Bucket sort by range
	 * @param nums
	 * @return
	 */
	public int maximumGapV2(int[] nums) {
		if (nums == null || nums.length < 2) { return 0; }
		else if (nums.length == 2) { return Math.abs(nums[1] - nums[0]); }
		
        int n = nums.length, maxNum = 0, minNum = Integer.MAX_VALUE, maxGap = 0;
        @SuppressWarnings("unchecked")
		PriorityQueue<Integer>[] buckets = new PriorityQueue[n];
        
        // 1st pass: find the range of the elements.
        for (int i = 0; i < n; ++i) {
        	int e = nums[i];
        	maxNum = (e > maxNum) ? e : maxNum;
        	minNum = (e < minNum) ? e : minNum;
        	buckets[i] = new PriorityQueue<Integer>();
        }
        
        // 2nd pass: bucket sort.
        for (int i = 0, k = 1+(maxNum-minNum)/n; i < n; ++i) {  //k值也就是每支桶的大小非常关键，很容易出边界错误
            buckets[(nums[i]-minNum)/k].offer(nums[i]); 
        }
        
        // 3rd pass: calculate maximum gap.
        int prev = minNum, cur = minNum;
        for (PriorityQueue<Integer> q: buckets) { 
        	while (!q.isEmpty()) {
        		cur = q.poll();
        		maxGap = Math.max(maxGap, cur - prev); 
        		prev = cur;
        	}
        }
        
        return maxGap;
    }
	
	/**
	 * 这种算法内存消耗是O(2^31-1), 不推荐
	 * @param nums
	 * @return
	 */
	public int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) { return 0; }
		else if (nums.length == 2) { return Math.abs(nums[1] - nums[0]); }
        int maxNum = 0, minNum = Integer.MAX_VALUE, maxGap = 0;
        
        // 1st pass: find the range of the elements.
        for (int e: nums) {
        	maxNum = (e > maxNum) ? e : maxNum;
        	minNum = (e < minNum) ? e : minNum;
        }
        
        // 2nd & 3rd pass: radix sort.
        boolean[] radix = new boolean[maxNum - minNum + 1];
        for (int e: nums) { radix[e - minNum] = true; }
        for (int i = 0, j = 0; i < radix.length; ++i) { if (radix[i]) { nums[j++] = i;} }
        
        // 4th pass: calculate maximum gap.
        for (int i = 1; i < nums.length; ++i) { maxGap = Math.max(maxGap, nums[i] - nums[i-1]); }
        
        return maxGap;
    }
}
