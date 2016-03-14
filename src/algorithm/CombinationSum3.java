package algorithm;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
	public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        int minSum = (1 + k) * k / 2;
        int maxSum = (9 - k + 1 + 9) * k / 2;
        if (n < minSum || n > maxSum) {
        	return rst;
        }
        
        return helper(k, n, new ArrayList<Integer>(), rst, 0, 0, 1); 
        
    }

	private static List<List<Integer>> helper(int k, int n,
			List<Integer> list, List<List<Integer>> rst, int sum, int count, int index) {
		if (sum == n && count == k) {
			rst.add(new ArrayList<Integer>(list));
			return rst;
		}
		if (count == k) {
			return rst;
		}
		int i;
		for (i = index; i < 10 && sum + i <= n; i++) {
			sum += i;
			list.add(i);
			count++;
			helper(k, n, list, rst, sum, count, i + 1);			
			sum -= i;
			count--;
			list.remove(list.size() - 1);
		}
		return rst;
	}
	
	public static void main(String[] args) {
//		int[] candidates = {2,3,6,7};
//		int target = 8;
		List<List<Integer>> rst = combinationSum3(3, 9);
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
