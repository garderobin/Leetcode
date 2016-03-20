package interview.kevin;

import java.util.ArrayList;
import java.util.List;

public class KangranTwitter {
	public static void main(String[] args) {
		String[][] test = {{"aabb", "ab"}, {"ababababababababababababa", "aba"}};
		for (String[] pair: test) {
			System.out.println(deleteString(pair[0], pair[1]));
		}
		
	}
	
	public static int deleteString(String s, String t) {
		if (s == null || s.length() == 0) { return 0; }
		if (t == null || t.length() == 0) { return s.length(); }
		
		List<Character> haystack = new ArrayList<>();
		int[] next = makeNext(t); // O(M)
		
		for (int i = 0; i < s.length(); ++i) { haystack.add(s.charAt(i)); } // O(N)
		
		return deleteString(haystack, t, next, 0);
	}
	
	private static int[] makeNext(String needle) {
		int i = -1, j = 0, m = needle.length();
        int[] next = new int[m];
        next[0] = -1;
        while (j < m - 1) {
            if (i == -1 || needle.charAt(i) == needle.charAt(j))
                next[++j] = ++i;
            else 
                i = next[i];
        }
        return next;
	}
	
	private static int deleteString(List<Character> haystack, String needle, int[] next, int start) {
		if (start >= haystack.size()) return 0;
		int m = needle.length(), max = 0;
		for (int j = kmp(haystack, needle, next, start); j >= 0; j = kmp(haystack, needle, next, start)) {
			for (int k = 0; k < m; ++k) { haystack.remove(j); }
			int count = 1 + deleteString(haystack, needle, next, start);
			if (count > max) max = count;
			else if (count == 1)  break; 
			for (int k = 0; k < m; ++k) { haystack.add(j, needle.charAt(k)); }
		}
		return max;
	}
	
	/*
     * KMP algorithm. O(N) time, O(M) space. (N > M)
     */
    private static int kmp(List<Character> haystack, String needle, int[] next, int start){
        if (haystack == null || haystack.size() < next.length) return -1;
        
        //generate next array, need O(n) time
        int i = -1, j = 0, n = haystack.size(), m = needle.length();
        //check through the haystack using next, need O(m) time
        for (i = start, j = 0; i < n && j < m; ) {
            if (j == -1 || haystack.get(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            else 
                j = next[j];
        }
        return (j == m) ? i - j : -1;
    }
	
}
