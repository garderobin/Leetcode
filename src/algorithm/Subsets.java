package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	
	/**
	 * Elements in a subset must be in non-descending order.
	 * The solution set must not contain duplicate subsets.
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        rst.add(new ArrayList<Integer>()); // empty set
        if (nums == null || nums.length == 0) {
        	return rst;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        int i, j, len;
        
        for (i = nums.length - 1; i >= 0; i--) {
        	list = new ArrayList<Integer>();
        	list.add(nums[i]);        	          	
        	rst.add(new ArrayList<Integer>(list));
        	len = rst.size() - 1;
        	for (j = 1; j < len; j++) {
        		list = new ArrayList<Integer>(rst.get(j));
        		list.add(nums[i]);
        		rst.add(new ArrayList<Integer>(list));
        	}
        	
        }
        
        return rst;
    }
	
	public static List<List<Integer>> subsetsBacktrace(int[] nums) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		
		
		return rst;
	}
	
	public static void main(String[] args) {
		int[] test = {1,2,3,4};
		List<List<Integer>> rst = subsets(test);
		for (int i = 0; i < rst.size(); i++) {
			ArrayList<Integer> cur = (ArrayList<Integer>) rst.get(i);
			System.out.print("[");
			for (int j = 0; j < cur.size(); j++) {
				System.out.print(cur.get(j) + ",\t");
			}
			System.out.println("]");
		}
	}
}
