package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> c = new ArrayList<List<Integer>>();
		if (n < k || k < 0 || n < 0) {
			return c;
		}				
		List<Integer> row = new ArrayList<Integer>();		
		
		return combineSub(1, n, k, c, row);
        
    }
	
	public static  List<List<Integer>> combineSub(int start, int n, int k, List<List<Integer>> c, List<Integer> row) {		
		int i, j;
		if (k == 1) {
			for (i = start; i <= n; i++) {
				List<Integer> cur = new ArrayList<Integer>(row);
				cur.add(i);
				c.add(cur);
			}
			return c;
		}

		for (j = start; j <= n; j++) {
			List<Integer> cur = new ArrayList<Integer>(row);
			cur.add(j);
			combineSub(j+1, n, k-1, c, cur);
		}
		return c;				
				
	}
	
	public static void main(String[] args) {
		List<List<Integer>> triangle = combine(5,5);
		for (int i = 0; i < triangle.size(); i++) {
			ArrayList<Integer> cur = (ArrayList<Integer>) triangle.get(i);
			System.out.print("[");
			for (int j = 0; j < cur.size(); j++) {
				System.out.print(cur.get(j) + ",\t");
			}
			System.out.println("]");
		}
	}
}
