package interview.snapchat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given List<word>, String para, 返回包涵所有word最短的一段话,返回String 
 * 目前，我对para用non-alphebetic (unicode)分割开作为单词之间的区分，匹配的时候case-sensitive
 * 
 * Ask：可以有overlapping吗？， 例如abcd 同时包含abc和bcd?
 * Ask: para单词以空格间隔开吗？
 * Ask: 考虑大小写吗？
 * Ask: 需要考虑标点符号吗？
 * (先把word简化成character来做的，就是LC的minimum window substring)
 */
public class MinimumWindowSubstringII {
	
	public static void main(String[] args) {
		List<String> words = Arrays.asList("I", "will", "be", "back");
		String para = "I will be back!!! will I will  I be back?";
		System.out.println(shortestSubstr(words, para));
	}
	
	// 如果Para是隔开的， 用LRU实现，遍历一遍即可。
	   // for(i-->dict.size()){
	   // word = para[i];
	   // if(dict.contains(word));
	   // LRU.put(word,i);//插入到tail前，如果LRU已含有word,修改index,移到tail前
	   // if(LRU.size()==dict.size()){
	   //      min = Math.min(min, LRU.tail.prev.idx-LRU.head.next.idx);
	   //      LRU.remove(head.next);//删除第一个dict含有的单词
	   //   }
	   // }
	public static String shortestSubstrLRU(List<String> words, String para) {
		return null;
	}
	
	/*
	 * Sliding Window Version
	 */
	public static String shortestSubstr(List<String> words, String para) {
		if (para == null || para.isEmpty() || words == null || words.isEmpty()) return "";
		int start = 0, minLen = Integer.MAX_VALUE, totalLen = 0;
		
		// Count dictionary word frequency and build dictionary
		Map<String, Integer> dict = new HashMap<>(); // Key: work, Value: counter for this word in the given list.
		for (String s: words) {
			if (s == null || s.isEmpty()) continue;
			totalLen += s.length();
			dict.put(s, 1 + dict.getOrDefault(s, 0));
		}
		if (totalLen > para.length()) return ""; // paragraph to short
		
		// Split paragraph into words, can break by handle blanks and special characters, can handle Unicode characters.
		List<Integer> occrStarts = new ArrayList<>();
		List<String> occrWords = new ArrayList<>();
		for (int i = 0, curStart = 0, pLen = para.length(); i < pLen; ++i) {
			if (!Character.isAlphabetic(para.charAt(i))) {
				if (curStart < i) { // a new word is done
					occrStarts.add(curStart);
					occrWords.add(para.substring(curStart, i));
				}
				curStart = i+1;
			}
		}
		if (occrWords.size() < dict.size()) return "";
		
		// Actual window moving, right pointer increase word counter by 1 and left pointer decrease world counter
		for (int head = 0, tail = 0, pLen = occrWords.size(), unuse = words.size(); tail < pLen; ++tail) {
			String tailKey = occrWords.get(tail);
			dict.put(tailKey, dict.getOrDefault(tailKey, 0) - 1); 
			if (dict.get(tailKey) >= 0) --unuse; // right pointer decrease counters
			
			// Window's left border
			for (; unuse == 0; ++head) { // while the current substring is a valid one
				int curLen = occrStarts.get(tail) + tailKey.length() - occrStarts.get(head);
				if (curLen < minLen) {
					minLen = curLen;
					start = occrStarts.get(head);
				}
				
				String headKey = occrWords.get(head);
				dict.put(headKey, dict.getOrDefault(headKey, 0) + 1); 
				if (dict.get(headKey) > 0) ++unuse; // left pointer increase counters
			}
		}
		
		
		return minLen == Integer.MAX_VALUE ? "" : para.substring(start, start + minLen); 
	}
}
