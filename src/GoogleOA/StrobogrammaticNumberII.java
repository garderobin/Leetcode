package GoogleOA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrobogrammaticNumberII {
	
	
	public static List<String> findStrobogrammatic(int n) { 
		return helper(n, true);
	}
	
	private static List<String> helper(int n, boolean isInitial) {
		if (n < 1)  { return new ArrayList<String>(Arrays.asList("")); }
	    if (n == 1)  { return new ArrayList<String>(Arrays.asList("0", "1", "8")); }
	    
	    List<String> lp = helper(n - 2, false), rst = new ArrayList<String>();
	    for (String middle: lp) {
	    	if (!isInitial) { rst.add("0" + middle + "0"); }
	    	rst.add("1" + middle + "1");
	    	rst.add("6" + middle + "9");
	    	rst.add("8" + middle + "8");
	    	rst.add("9" + middle + "6");
	    }
	    return rst;
	}
	/**
	 * 这种思路比较蠢，往中间插。正确的做法是往两边插入
	 * @param n
	 * @return
	 */
	public static List<String> findStrobogrammaticSolution1(int n) {
		List<String> rst = new ArrayList<String>();
        if (n < 1) { return rst; }
        
        String[] arr1 = {"0", "1", "8"}, arr2 = {"11","69","88","96"}; 
        ArrayList<String> l1  = new ArrayList<String>(3), l2 = new ArrayList<String>(4);
        for (String s1: arr1) { l1.add(s1); }
        for (String s2: arr2) { l2.add(s2); }
        List<ArrayList<String>> baseLists = new ArrayList<ArrayList<String>>(2); 
        baseLists.add(l2);
        baseLists.add(l1);
        
        if (n < 3) { return baseLists.get(n % 2); }
        
        baseLists.get(0).add("00");
        int m = n % 2, k = (n + 1) / 2 - 1;
    	List<String> lp = findStrobogrammatic(n - 2 + m), lm = baseLists.get(m);
		for (String prev: lp) {
			for (String middle: lm) {
				rst.add(prev.substring(0, k) + middle + prev.substring(k));
			}
		}
		return rst;
    }
	
	public static void main(String[] args) {
		for (int i = 1; i < 30; i++) {
			System.out.println(i + ": \t" + findStrobogrammatic(i).size());
		}
	}
	
	
}
