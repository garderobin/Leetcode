package interview.snapchat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) {
        	return rst;
        }        
        
        // Pre-process the candidates
        Arrays.sort(candidates);
        int index = Math.abs(Arrays.binarySearch(candidates, target) + 1);
        if (index < 1) {
        	return rst;
        }
        candidates = Arrays.copyOf(candidates, index);

        return helper(candidates, target, new ArrayList<Integer>(), rst, 0, 0);
    }

	private static List<List<Integer>> helper(int[] c, int target,
			List<Integer> list, List<List<Integer>> rst, int sum, int index) {
		if (sum == target) {
			if (!rst.contains(list)) {
				rst.add(new ArrayList<Integer>(list));
			}
			
			return rst;
		}
		int i;
		for (i = index; i < c.length && sum + c[i] <= target; i++) {
			sum += c[i];
			list.add(c[i]);
			helper(c, target, list, rst, sum, i + 1);
			sum -= list.get(list.size() - 1);
			list.remove(list.size() - 1);
		}
		
		return rst;
	}
	
	public static void main(String[] args) {
		int[] candidates = {2, 2, 2}; //{1, 1, 2, 5, 6, 7}
		int target = 2;
		List<List<Integer>> rst = combinationSum2(candidates, target);
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
