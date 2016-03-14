package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SubsetII {
	/**
	 * 两个内容相同的list在hashtable里面判断不出来重复。
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
		ArrayList<List<Integer>> rst = new ArrayList<List<Integer>>();
        rst.add(new ArrayList<Integer>()); // empty set
        set.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0) {
        	return rst;
        }
        Arrays.sort(nums);
        
        List<Integer> list = new ArrayList<Integer>();
        int i, j, len, dup = 0;
        
        for (i = nums.length - 1; i >= 0; i--) {
        	list = new ArrayList<Integer>();
        	list.add(nums[i]); 
        	if (set.add(new ArrayList<Integer>(list))) {
        		dup = 0;
        		rst.add(new ArrayList<Integer>(list));
        	} else {
        		dup = 1;
        	}
        	len = rst.size() - 1 + dup; //关键在这里
        	for (j = 1; j < len; j++) {
        		list = new ArrayList<Integer>(rst.get(j));
        		list.add(0, nums[i]);
        		if (set.add(new ArrayList<Integer>(list))) {
        			rst.add(new ArrayList<Integer>(list));
        		} 
        	}
        	
        }
        
        return rst;
    }
	
//	private static List<List<Integer>> mapToList(Hashtable<Integer, ArrayList<Integer>> map) {
//		List<List<Integer>> rst = new ArrayList<List<Integer>>();
//		Iterator<Entry<Integer, ArrayList<Integer>>> iter = map.entrySet().iterator(); 
//		while (iter.hasNext()) { 
//		    Entry<Integer, ArrayList<Integer>> entry = iter.next(); 
//		    ArrayList<Integer> val = entry.getValue(); 
//		    rst.add(val);
//		} 
//		return rst;
//	}
	
	public static void main(String[] args) {
		int[] test = {2,2,2,2,2};
//		int[] t2 = {1,1}, t3 = {1,2,2};
//		ArrayList<Integer> l1 = new ArrayList<Integer>();
//		ArrayList<Integer> l2 = new ArrayList<Integer>();
//		ArrayList<Integer> l3 = new ArrayList<Integer>();
//		HashSet<ArrayList<Integer>> set = new HashSet<>();
//		l1.add(1);
//		l1.add(2);
//		l2.add(1);
//		l2.add(2);
//		l3.add(1);
//		l3.add(2);
//		set.add(l1);
//		System.out.println(set.add(l2));
//		set.add(l3);
//		
		
		List<List<Integer>> rst = subsetsWithDup(test);
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
