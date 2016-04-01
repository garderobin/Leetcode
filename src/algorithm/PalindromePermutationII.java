package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PalindromePermutationII {
	public static List<String> generatePalindromes(String s) {
		List<String> rst = new LinkedList<String>();
		if (s == null || s.length() == 0) { return rst; }
		else if (s.length() == 1) { return Collections.singletonList(s); }
		int n = s.length();
		
		// Count occurrence for each character. O(N) time, O(N) space.
		int[] occr = new int[256]; // not work for non-ASCII codes!!!
		for (int i = 0; i < n; ++i) { ++occr[s.charAt(i)]; }
		
		// Check validness. O(1) time, O(1) space. (always less then 256)
		List<Integer> evenIndexes = new LinkedList<>();
		int oddIndex = -1;
		for (int i = 0; i < occr.length; ++i) {
			if (occr[i] > 0) { evenIndexes.add(i); }
			if ((occr[i] & 1) == 1) { 
				if (oddIndex >= 0) { return new ArrayList<String>(); }
				else { oddIndex = i; }
			} 
		}		
		if (evenIndexes.size() == 0) { return Collections.singletonList(s); }
		String middle = (oddIndex >= 0) ? (char)(oddIndex) + "" : "";
		
		// Generate left-half permutations using backtracking. O(N^2) time, O(N) space.
		LinkedList<StringBuilder> bs = new LinkedList<StringBuilder>();
		bs.add(new StringBuilder()); 
		int charCount = 0;
		for (int i: evenIndexes) {
			char c = (char)i;
			for (int j = 0, limit = occr[i]/2; j < limit; ++j, ++charCount) {
				while (!bs.isEmpty() && bs.peekFirst().length() == charCount) {
					StringBuilder sb = bs.pollFirst();
					if (sb.length() == 0) { 
						sb.append(c);
						bs.add(sb);
					} else {
						for (int k = 0; k < charCount; ++k) {
							if (sb.charAt(k) != c) { 
								sb.insert(k, c); 
								bs.add(new StringBuilder(sb)); // this step is O(N).
								sb.deleteCharAt(k);
							} 
						}
						sb.append(c);
						bs.add(sb);
					}
				}
			}
		}
		
		// Generate full palindrome strings based on half-strings. O(N!) time, O(1) space.
		for (StringBuilder sb: bs) {
			StringBuilder right = new StringBuilder(sb);
			sb.append(right.reverse());
			if (middle.length() > 0) { sb.insert(sb.length()/2, middle); }
			rst.add(sb.toString());
		}
		
		return rst;
    }
	
	public static void main(String[] args) {
//		int[] test = new int[256];
//		test['a'] = 1;
////		for (int e: test) { System.out.print(e + ", "); }
//		System.out.println((int)'0');
//		for (int i = 0; i < 256; ++i) {
//			System.out.print((char)i + ", ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < 256; ++i) {
//			test[(char)i] = i;
////			System.out.print((char)i + ", ");
//		}
//		
//		for (int i = 0; i < 256; ++i) {
//			System.out.print(test[i] + ", ");
//		}
//		
		System.out.println(generatePalindromes("abcccab"));
	}
}
