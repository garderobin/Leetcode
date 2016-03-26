package interview.snapchat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmicableNumbers {
	public static void main(String[] args) {
		List<List<Integer>> res = Amicable(66992);
	    System.out.println(res);
	}
	// 0 1 2 3 4 5 6 7 8 9 
	// 1 1 1 1 1 1 1 1 1 1
	// 2       2   2   2
	// 3           3     3
	// 4         
	/*
	 * O(NlogN) time, O(N) space.
	 */
	public static List<List<Integer>> Amicable(int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int[] sum = new int[n+1]; // sum[i] stores the factor sum of number i.
		
		for (int i = 0; i <= n; ++i) sum[i] = 1;
	    for (int i = 2; i <= n/2; i++) //O(nlogN) time.  n*(1/2+1/3+1/4....1/n)
	    	for (int j = 2*i; j <= n; j += i) sum[j] += i;
	     
	    Map<Integer,Integer> map = new HashMap<>(); // Key: factor sums; Value: numbers
	    for (int i = 0; i <= n; ++i) {
	   	 	if (map.containsKey(i)) {
	    		if (map.get(i) == sum[i]) { // i equals to the factor sum of j, and j equals to the factor sum of i.
	    			res.add(Arrays.asList(sum[i], i));
	            }
	        }
	        else map.put(sum[i],i);
	    }

	    return res;
	 }
	 
	 
}
