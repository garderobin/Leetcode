package interview;

import java.util.HashMap;

public class ICIMS {
	
	public static String solution(String S) {
		if (S == null) { return ""; }
		HashMap<String, Character> rules = new HashMap<>();
		rules.put("AB", 'A');
		rules.put("BA", 'A');
		rules.put("CB", 'C');
		rules.put("BC", 'C');
		rules.put("AA", 'A');
		rules.put("CC", 'C');
		
		char[] ch = S.toCharArray();
		int len = ch.length, i, j;
		for (i = 0; i < len - 1; i++) {
			if (ch[i] == '\0') { continue; }
			for (j = i+1; j < len && ch[j] == '\0'; j++) {}
			if (j == len) { break; }
			String key = ch[i] + "" + ch[j];
			if (rules.containsKey(key)) {
				ch[i] = rules.get(key);
				ch[j] = '\0';
				i = Math.max(i-2, -1);
			}
		}
		
		// Delete all blank spaces
		StringBuilder sb = new StringBuilder(len);
		for (i = 0; i < len; i++) {
			if (ch[i] != '\0') { sb.append(ch[i]); }
		}
		return sb.toString();
		
	}
	
	public static String solutionInitial(String S) {
		if (S == null) { return ""; }
		HashMap<String, String> rules = new HashMap<>();
		rules.put("AB", "AA");
		rules.put("BA", "AA");
		rules.put("CB", "CC");
		rules.put("BC", "CC");
		rules.put("AA", "A");
		rules.put("CC", "C");
		int len = S.length();
		for (int i = 0; i < len - 1; i++) {
			String key = S.substring(i, i+2);
			if (rules.containsKey(key)) {
				S = S.substring(0, i) + rules.get(key) + ((i+2 >= len) ? "" : S.substring(i+2, len));
				i = Math.max(i-2, -1);
				len = S.length();
			}
		}
		return S;
		
	}
	
	public static void main(String[] args) {
//		StringBuilder sb = new StringBuilder();
//		sb.append('a');
//		sb.append('\0');
//		sb.append('c');
//		System.out.println(sb.toString());
//		int[] A = {2,2,3,4,3,3,2,2,1,1,2,5};
//		System.out.println(solution(A));
//		String S = "BBBBBBBBBBABBCC";
////		String S = "ACACACACACACACACACACACACACA";
//		String s1 = "ACACACACACACACACACACACACACAC";
//		String s = "BACABACABACABACABACBCBCBCACACABABAABCCCCBABAAABBCB";
		System.out.println(solution("ABBCC"));
//		System.out.println(solution(s1));
	}
	
	public static int simplify (int[] A) {
		if (A == null || A.length == 0) { return 0; }
		if (A.length == 1) { return 1; }
		int leftPos = -1, cur = A[0], count = 1, i;
		for (i = 1; i < A.length && A[i] == cur; i++) { }  // left most of the array
		if (i == A.length) { return 1; } // all elements are same
		
		// middle part of the array
		for (leftPos = i-1,	cur = A[i]; i < A.length; i++) {
			if ((A[i] > cur && (cur < A[leftPos])) || ((A[i] < cur) && (cur > A[leftPos])) ) { // extrema found
				++count;
				leftPos = i-1;
				cur = A[i];
			} else if ((A[i] > cur && (cur > A[leftPos])) || (A[i] < cur && (cur < A[leftPos]))) {
				leftPos = i-1;
				cur = A[i];
			}
		}
		
		// right most of the array
		if (leftPos > -1) { ++count; } // the tail extrema
		return count;
	}
	
	public static int arraySolution (int[] A) {
		if (A == null || A.length == 0) { return 0; }
		if (A.length == 1) { return 1; }
		int leftPos = -1, cur = A[0], count = 1, i;
		for (i = 1; i < A.length; i++) {  // left most of the array
			if (A[i] != cur) {
				leftPos = i-1;		
				cur = A[i];
				break;
			}
		}
		if (i == A.length) { return 1; } // all elements are same
		
		// middle part of the array
		for (; i < A.length; i++) {
				if ((A[i] > cur && (cur < A[leftPos])) || ((A[i] < cur) && (cur > A[leftPos])) ) { // extrema found
					++count;
					leftPos = i-1;
					cur = A[i];
				} else if ((A[i] > cur && (cur > A[leftPos])) || (A[i] < cur && (cur < A[leftPos]))) {
					leftPos = i-1;
					cur = A[i];
				}
		}
		
		// right most of the array
		if (leftPos > -1) { ++count; } // the tail extrema
		return count;
	}
	
	
}
