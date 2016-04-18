package interview.snapchat;

import java.util.HashMap;
import java.util.Map;

/*
 * 一定上来先问有没有非ASII字符！否则bitmap就行不通
 */
public class MinimumWindowSubstring {
	public static void main(String[] args) {
		String[][] test = {
				{"baaaaa", "aaa"},
				{"aabbaaccddaaa", "abcd"}
		};
		
		for (String[] ss: test) {
			System.out.println(minWindowV1(ss[0], ss[1]));
		}
	}
	
	/**
	 * Two pointers. O(N) time, O(M) space
	 * window的右边界碰到目标元素，这个元素的计数器减减(原先没有出现的元素置成负一)，左边界遇到目标元素，这个元素的计数器加一, 原先没有出现的元素变成0
	 * 如果左边界的计数此刻大于零，证明它原来是目标元素之一, 收缩我们的有效窗口
	 */
    public static String minWindowV1(String s, String t) {
		if (s == null || s.isEmpty() || t == null || t.isEmpty()) return "";
		int start = 0, minLen = Integer.MAX_VALUE, unuse = t.length();
		 
		// Count character apperances to build a dictionary
		Map<Character, Integer> dict = new HashMap<>(); // word count map. Key: character. Value: appearance times in t.
		for (int i = 0, tLen = t.length(); i < tLen; ++i) {
			char c = t.charAt(i);
			dict.put(c, 1 + dict.getOrDefault(c, 0));
		}
		 
		// Minimum window traversing
		for (int head = 0, tail = 0, sLen = s.length(); tail < sLen; ++tail) { //外圈是在右端扩展窗口，一次一个
			char tailKey = s.charAt(tail);
			dict.put(tailKey, dict.getOrDefault(tailKey, 0) - 1);
			
			for (unuse -= dict.get(tailKey) >= 0 ? 1 : 0; unuse == 0; ++head) { //发现valid的子串，在左端收缩窗口
				if (1 + tail - head < minLen) minLen = 1 + tail - (start = head);
				char headKey = s.charAt(head);
				dict.put(headKey, 1 + dict.get(headKey));
				if (dict.get(headKey) > 0) ++unuse; // make it invalid
			}
		}
		return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen); 
	
    }
	
	
}
