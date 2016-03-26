package interview.snapchat;

public class WordPatternII {
	public static void main(String[] args) {
		WordPatternII sol = new WordPatternII();
		String[] patterns = {"abab", "aaaa", "aabb", "ab"};
		String[] strs = {"redblueredblue", "asdasdasdasd", "xyzabcxzyabc", "aa"};
		for (int i = 0; i < strs.length; ++i) {
			System.out.println(patterns[i] + ",\t" + strs[i] + ":\t" + sol.wordPatternMatch(patterns[i], strs[i]));
		}
	}
	
	/*
	 * Backtracking with pruning by step length.
	 * Space: (N)
	 */
	public boolean wordPatternMatch(String pattern, String str) {
        if ((pattern == null || pattern.length() == 0) && (str == null || str.length() == 0)) { return true; } // both are empty
        else if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) { return false; } // one is empty
        else if (pattern.length() > str.length()) { return false; }	
        else { return backtrack(pattern, str, new String[256], 0, 0);  }
    }
	
	/*
	 * 记住：发现错误以后直接返回false, 让第一次处理这个pattern mapping的thread来handle，而不是发现错误的thread来handle
	 */
	private boolean backtrack(String pattern, String str, String[] match, int ip, int is) {
		int np = pattern.length(), ns = str.length(), i, j, limit;
		if (ip == np) { return is == ns; }
		
		char pc = pattern.charAt(ip);
		String s = match[pc];
		if (match[pc] != null) { // already mapped
			limit = s.length();
			if (limit + is > ns) { return false; }
			for (i = 0, j = is; i < limit && s.charAt(i) == str.charAt(j); ++i, ++j) {} // Time: O(N)
			return (i == limit) ? backtrack(pattern, str, match, ip+1, j) : false;
		} else { // first met this pattern
			int maxLen = maxLength(pattern, match, ip, ns - is); // Time: O(M)
			if (maxLen < 1) { return false; }
			
			for (j = is + 1, limit = is + maxLen + 1; j < limit; ++j) {
				s = str.substring(is, j);
				if (matchExists(pattern, match, ip, s)) { continue; } // Time: O(M)
				match[pc] = s;
				if (backtrack(pattern, str, match, ip+1, j)) { return true; }				
			}
			
			match[pc] = null; // all mapping solutions are unavailable. 这一句很重要！最后一次错误必须还原，否则会影响到兄弟thread的match.
			return false; 
		}
	}
	
	/*
	 * Time: O(M)
	 */
	private boolean matchExists(String pattern, String[] match, int ip, String s) {
		for (int i = 0; i < ip; ++i) {
			if (match[pattern.charAt(i)].equals(s)) { return true; }
		}
		return false;
	}
	
	/**
	 * Calculate the maximum available length of mapping sequence for a first-met pattern character.
	 * It may return 0 or negative numbers. If so, the function's caller should return false immediately.
	 * Time: O(M)
	 */
	private int maxLength(String pattern, String[] match, int ip, int max) {
		char pc = pattern.charAt(ip);
		int np = pattern.length(), sameCount = 1;
		for (int i = ip + 1; i < np; ++i) {
			if (pc == pattern.charAt(i)) ++sameCount;
			else {
				String mapping = match[pattern.charAt(i)];
				max -= (mapping == null) ? 1 : mapping.length();
			}			 
		}
		return (int) (max / sameCount);
	}
}	
