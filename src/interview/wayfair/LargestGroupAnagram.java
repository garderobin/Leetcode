package interview.wayfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestGroupAnagram {
	
	public static void main(String[] args) {
		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(largestAnagramGroupNoOrder(strs));
	}
	
	/**
	 * Use a HashMap to store anagram groups, using sorted ascending anagram as key.
	 * At first, we have a integer recording the current maximum group length and we initialize it to 1.
	 * We also record the current largest group's anagram key, which is a String initialized as empty.
	 * 
	 * Then we start our traversing:
	 * For every given input word, we generate its anagram key by counting sort which takes a linear time and a constant space.
	 * (Generate Key Step 1:)That is, we keep an integer array of size 26 which are our buckets and then use one pass to record each letter's occurrence size.
	 * (Generate Key Step 2:)Then we traverse our letter buckets and use a StringBuilder to generate the final anagram key. The process takes also linear time.
	 * We put the key and update its value list as well as the max length and largest group's key.
	 * 
	 * When finishing traversing. If we found the max length is still 1 it means no anagram in given input. Return the map's first entry's value list.
	 * Otherwise, return the value list of the max key in the map.
	 * 
	 * (If we need a sorted largest group, just sort it for the last step.)
	 * 
	 * Based on: All input will be lower case.
	 * Based on: No empty input.
	 * Based on: Does not care word order inside a group.
	 * 
	 */
	public static List<String> largestAnagramGroupNoOrder(String[] strs) {
		if (strs == null || strs.length == 0) { return new ArrayList<String>(); }
		Map<String, List<String>> map = new HashMap<>();
		int max = 1;
		String maxKey = null;
		for (String s : strs) {
			String key = getAnagramKey(s);
			if (map.containsKey(key)) { map.get(key).add(s); } 
			else { 
				List<String> value = new ArrayList<String>();
				value.add(s);
				map.put(key, value);
				int gsize = map.get(key).size();
				if (gsize > max) {
					max = gsize;
					maxKey = key;
				}
			}
		}
		
		return (max == 1) ? (map.entrySet().iterator().next().getValue()) : map.get(maxKey);
	}
	
	/*
	 * Generate a all-lower-case-letter word's sorted anagram.
	 * O(N) time, O(1) space using counting sort (bucket sort).
	 * N is the total length of all words in strs.
	 */
	private static String getAnagramKey(String s) {
		if (s == null || s.length() == 0) { return ""; }
		
		final int LETTER_SIZE = 26;	    
		int[] charCount = new int[LETTER_SIZE];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0, n = s.length(); i < n; ++i) { ++charCount[s.charAt(i) - 'a']; }
		
		for (int i = 0; i < LETTER_SIZE; ++i) {
			for (int j = 0; j < charCount[i]; ++j) {
				sb.append((char)(i + 'a'));
			}
		}
		return sb.toString();
	}

}
