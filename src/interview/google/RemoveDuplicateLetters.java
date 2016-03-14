package interview.google;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateLetters {
	
	/**
	 * Greedy.
	 * 这个方法不对，还没有通过验证。
	 * @param s
	 * @return
	 */
	public String removeDuplicateLettersOptimize(String s) {
        int[] cnt = new int[26];
        Set<Character> singleChars = new HashSet<Character>();
        int pos = 0, i = 0; // the position for the smallest s[i]
        for (i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
            if (c < s.charAt(pos)) pos = i; // pos is the smallest element in the left side of the first one-occurrence element. 
            if (--cnt[c - 'a'] == 0)  {
            	singleChars.add(c);
            	break; // find the first char which occurs only once.
            }
        }
        for (++i; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if (--cnt[c - 'a'] == 0)  {
            	singleChars.add(c);
        	}
        }
        return s.length() == 0 ? "" : s.charAt(pos) + optimizeHelper(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""), singleChars);
    }

	public String optimizeHelper(String s, Set<Character> singleChars) {
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i; // pos is the smallest element in the left side of the first one-occurrence element. 
            if (singleChars.contains(s.charAt(i))) break; // find the first char which occurs only once.
        }
        return s.length() == 0 ? "" : s.charAt(pos) + optimizeHelper(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""), singleChars);
    }
	
	/**
	 * Greedy.
	 * @param s
	 * @return
	 */
	public String removeDuplicateLettersDiscussion(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i; // pos is the smallest element in the left side of the first one-occurrence element. 
            if (--cnt[s.charAt(i) - 'a'] == 0) break; // find the first char which occurs only once.
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
	
	/**
	 * wrong answer.
	 * @param s
	 * @return
	 */
	public static String removeDuplicateLetters(String s) {
		if (s == null || s.length() == 0) { return ""; }
		StringBuilder sb = new StringBuilder();
        int[] pos = new int[26]; // all positions are starting from 1 instead of 0.
        boolean[] remove = new boolean[s.length()];
        char rightNeighbor = s.charAt(s.length()-1);
        pos[rightNeighbor - 'a'] = s.length();
        for (int i = s.length()-1; i > 0; i--) {
        	char c = s.charAt(i-1);
        	int charIndex = c - 'a';
        	if (pos[charIndex] == 0) { // first occurrence.
        		pos[charIndex] = i;
        		rightNeighbor = c;
        	} else {
//        		if (s.charAt(pos[charIndex] - 1) < c) { // remains the right occurrence and removes the left occurrence.
//        		if (s.charAt(i) >= c) {
        		if (rightNeighbor >= c) {
        			remove[pos[charIndex] - 1] = true;
        			pos[charIndex] = i;
        			rightNeighbor = c;
        		} else {
        			remove[i-1] = true;
        		}
        	}
        }
        
        for (int i = 0; i < remove.length; i++) {
        	if (!remove[i]) { sb.append(s.charAt(i)); }
        }
		return sb.toString();
    }
	
//	private static char getFirstRemainNeibor()

	public static void main(String[] args) {
		String[] test = {"bcabc", "cbacdcbc", "abacb"};
		for (String s: test) {
			System.out.println(removeDuplicateLetters(s));
		}
		
//		cbacdcbc
//		cbacd
	}
}
