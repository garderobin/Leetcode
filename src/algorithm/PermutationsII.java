package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PermutationsII {
	/**
	 * 用最最简单的思维方式就可以完全避开duplicate带来的误区。
	 * 事实上遍历元素的时候把新元素往每一个旧list的每一个位置插，形成的新list只要用HashSet检查一遍就能把任何重复的去掉。
	 * @param num
	 * @return
	 */
	public List<List<Integer>> permuteUniqueDiscussion(int[] num) {
		LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < num.length; i++) {
            Set<String> cache = new HashSet<>();
            while (res.peekFirst().size() == i) {
                List<Integer> l = res.removeFirst();
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> newL = new ArrayList<>(l.subList(0,j));
                    newL.add(num[i]);
                    newL.addAll(l.subList(j,l.size()));
                    if (cache.add(newL.toString())) res.add(newL);
                }
            }
        }
        return res;

    }
	
	/**
	 * 我的思路： list只存index，用这种办法来绕开已经在list中的元素
	 * 在加入rst时才把index转换成值
	 * TLE没有解决。
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst; 
        }
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        return helper(rst, list, nums, set);

    }
	
	public static List<List<Integer>> helper(List<List<Integer>> rst, List<Integer> list, int[] num, 
			HashSet<ArrayList<Integer>> set){
        if(list.size() == num.length) { 
        	return indexListToNumList(rst, list, num, set);
        }
        
        for(int i = 0; i < num.length; i++){
//            if(list.contains(num[i])){
        	if (list.contains(i)) {
                continue;
            }
//            list.add(num[i]);
        	list.add(i);
                   
            helper(rst, list, num, set);
            
            list.remove(list.size() - 1);
            
        }
        
        return rst;
    }
	
	private static List<List<Integer>> indexListToNumList (List<List<Integer>> rst, List<Integer> list, int[] num, HashSet<ArrayList<Integer>> set) {
		ArrayList<Integer> completeList = new ArrayList<Integer>(list.size());
		for (int index : list) {
			completeList.add(num[index]);
		}
		if (set.add(completeList)) {
			rst.add(completeList);
		}
		return rst;
	}
	
	public static void main(String[] args) {
		int[] nums = {3,3,0,0,2,3,2};
		List<List<Integer>> triangle = permuteUnique(nums);
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
