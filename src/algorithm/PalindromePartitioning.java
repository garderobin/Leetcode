package algorithm;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
	/**
	 * DP
	 * @param s
	 * @return
	 */
	public static List<List<String>> partitionDiscussion(String s) {
        int len = s.length();
        List<List<String>>[] result = new List[len + 1];
        result[0] = new ArrayList<List<String>>();
        result[0].add(new ArrayList<String>());

        boolean[][] pair = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            result[i + 1] = new ArrayList<List<String>>();
            for (int left = 0; left <= i; left++) {
                if (s.charAt(left) == s.charAt(i) && (i-left <= 1 || pair[left + 1][i - 1])) {
                    pair[left][i] = true;
                    String str = s.substring(left, i + 1);
                    for (List<String> r : result[left]) {
                        List<String> ri = new ArrayList<String>(r);
                        ri.add(str);
                        result[i + 1].add(ri);
                    }
                }
            }
        }
        return result[len];
    }
	
	/**
	 * My backtracking.
	 * @param s
	 * @return
	 */
	public static List<List<String>> partition(String s) {
		List<List<String>> rst = new ArrayList<List<String>>();
		return backtrack(rst, s, new ArrayList<String>(), 0, 0);
    }
	
	private static List<List<String>> backtrack(List<List<String>> rst, String s, ArrayList<String> list, int start, int count) {
		int len = s.length();
		if (start == len && count == len) { //这里的count很关键，因为下面的跳转判断不能完全将非法的遍历完成的序列剔除
			rst.add(new ArrayList<String>(list));
			return rst;
		}
		for (int i = start; i < len; i++) {
			inner:
			for (int j = i; j < len; j++) {
				if (!isPalindrome(s, i, j)) {
//					if (j == len - 1) { return rst; }
					continue inner;
				} 
				list.add(s.substring(i, j+1));
				backtrack(rst, s, list, j+1, count+j-i+1); 
				list.remove(list.size()-1); //这一步并没有错，错在跳转。
			}
		}
		return rst;
	}
	
	private static boolean isPalindrome(String s, int begin, int end){
        if (begin < 0) return false;
        while (begin < end){
            if (s.charAt(begin++) != s.charAt(end--)) return false;
        }
        return true;
    }
	
	public static void main(String[] args)  {
		String s = "amanaplanacanalpanama";
//		String s = "bbb";
		List<List<String>> triangle = partition(s);
		for (int i = 0; i < triangle.size(); i++) {
			ArrayList<String> cur = (ArrayList<String>) triangle.get(i);
			System.out.print("[");
			for (int j = 0; j < cur.size(); j++) {
				System.out.print(cur.get(j) + ",\t");
			}
			System.out.println("]");
		}
	}
}
