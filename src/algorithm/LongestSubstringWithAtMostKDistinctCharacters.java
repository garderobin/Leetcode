package algorithm;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
	
	public static void main(String[] args) {
//		System.out.println(lengthOfLongestSubstringKDistinct("abaccc", 2));
		System.out.println(lengthOfLongestSubstringKDistinctV2("aba", 1));
	}
	
	/*
	 * Sliding Window, can support unicode.
	 */
	public static int lengthOfLongestSubstringKDistinctV2(String s, int k) {
		if (s == null || s.length() == 0 || k == 0) return 0;
		if (s.length() <= k) return s.length();
		Map<Character, Integer> map = new HashMap<>();
        int num = 0, i = 0, maxLen = 0, count = 0;
        for (int j = 0; j < s.length(); j++) {
            if ((count = (map.getOrDefault(s.charAt(j), 0))) == 0) { ++num; }
            map.put(s.charAt(j), 1 + count);
            if (num > k) {
            	do { map.put(s.charAt(i), map.get(s.charAt(i)) - 1); }
            	while (map.get(s.charAt(i++)) > 0);
                --num;
            }
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }
	
	
	/*
	 * Sliding Window
	 */
	public int lengthOfLongestSubstringKDistinctDiscussion(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) ++num;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0);
                --num;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
	
	/*
	 * 能通过但是没有必要，因为并不需要次跳过一整个trailing,
	 * 只要count[char]大于零就可以证明这种char不能彻底从左边清楚它的所有出现，继续把左边框往右缩就行了
	 */
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0 || k == 0) return 0;
		if (s.length() <= k) return s.length();
		
		int n = s.length(), start = 0, charCount = 0, maxLen = 0; //start是当前正在维持的合法子串的开头位置
		Map<Character, ArrayDeque<Integer>> endMap = new HashMap<>(k); //Key: character, Value: the last indexes of the trailing key.
		
		for (int i = 0; i < n; ++i) {
			char cur = s.charAt(i);
			if (!endMap.containsKey(cur)) {
				if (charCount == k) {
					while (true) {
						char charStart = s.charAt(start);
						ArrayDeque<Integer> idxs = endMap.get(charStart);
						start = idxs.pollFirst() + 1;
						if (idxs.isEmpty()) {
							endMap.remove(charStart);
							break;
						} else endMap.put(charStart, idxs);
					}
				} else ++charCount;
				
				ArrayDeque<Integer> value = new ArrayDeque<>();
				value.addLast(i);
				endMap.put(cur, value);
			} else {
				ArrayDeque<Integer> idxs = endMap.get(cur);
				if (idxs.peekLast() == i-1) idxs.pollLast();
				idxs.addLast(i);
				endMap.put(cur, idxs);
			}
			maxLen = Math.max(maxLen, i - start + 1);
		}
		
        return maxLen;
    }
}
