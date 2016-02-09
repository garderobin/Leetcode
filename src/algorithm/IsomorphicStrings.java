package algorithm;

import java.util.HashMap;
import java.util.HashSet;

public class IsomorphicStrings {
	
	/**
	 * 三个要点：一是利用ASCII码只有256容量来构建还不算大的数组, extra space还可以。
	 * 二是贪心：存储的是该字符最近一次出现时的位置，只要每一步的最近一次都匹配，最后一定匹配。
	 * 三是边界条件：因为int数组默认初始值就是0， 所以如果对第一位存0， 会歧义。因此存自然次序。
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isIsomorphicDiscussion(String s, String t) {
		if (s == null || t == null || s.length() == 0) {
			return true;
		}
		int[] m1 = new int[256], m2 = new int[256];
		int v1, v2, n = s.length();
		for (int i = 0; i < n; i++) {
			v1 = (int)s.charAt(i);
			v2 = (int)t.charAt(i);
			if (m1[v1] != m2[v1]) {
				return false;
			}
			m1[v1] = i + 1; //这样做是为了放置上一次出现在第零位（数组开头）被误认为是系统默认的初始值0
			m2[v2] = i + 1;
		}
		return true;
		
	}
	
	public boolean isIsomorphic(String s, String t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null || t == null || s.length() != t.length()) {
			return false;
		}
		int len = s.length();
		HashMap<Character, Character> map = new HashMap<Character, Character>(26);
		HashSet<Character> set = new HashSet<Character>(26);
		char cs, ct;
		for (int i = 0; i < len; i++) {
			cs = s.charAt(i);
			ct = t.charAt(i);
			if (map.containsKey(cs)) {
				if (ct != map.get(cs)) {
					return false;
				}
			} else {
				if (!set.add(ct)) {
					return false;
				} 
				map.put(cs, ct);
			}
		}
		
		return false;		
    }
}
