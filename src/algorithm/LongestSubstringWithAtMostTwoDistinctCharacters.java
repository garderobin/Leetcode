package algorithm;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
	
	/**
	 * 记录两个指针: i是当前正在维持的合法子串的开头位置，j是此前一直连续着的字符的前一个位置
	 * 当第三个字符出现的时候，i应当被更新为j后面一位，而j被更新为前一位。
	 * @param s
	 * @return
	 */
	int lengthOfLongestSubstringTwoDistinctDiscussion(String s) {
        int i = 0, j = -1;
        int maxLen = 0, n = s.length();
        for (int k = 1; k < n; k++) {
            if (s.charAt(k) == s.charAt(k-1)) continue;
            if (j > -1 && s.charAt(k) != s.charAt(j)) {
                maxLen = (maxLen > k-i) ? maxLen : k-i;
                i = j + 1;
            }
            j = k - 1;
        }
        return maxLen > (n - i) ? maxLen : n - i;
    }
	
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
		if (s == null) { return 0; }
		else if (s.length() < 3) { return s.length(); }
        int len = s.length(), m1 = 0, m2 = 1, max = 2;
        
        // find the first pair
        for (char c1 = s.charAt(0); m2 < len && s.charAt(m2) == c1; ++m2) {}
        if (m2 > len-2) { return len; }
        
        char c1 = s.charAt(0), c2 = s.charAt(m2);
        for (int i = m2 + 1, s1 = -1, s2 = m2; i < len; ++i) {
        	char c = s.charAt(i);
        	if (c == c1) {
        		if (s1 < 0) { s1 = i; }
        		s2 = -1;
        	} else if (c == c2) {
        		if (s2 < 0) { s2 = i; }
        		s1 = -1;
        	} else {
        		max = Math.max(max, i-m1);
        		m1 = (s1 < 0) ? s2 : s1; 	c1 = s.charAt(m1); 	s1 = -1;
        		m2 = i; 					c2 = c; 			s2 = i;
        	}
        }
        max = Math.max(max, len-m1); 
		return max;
    }
	
	public static void main(String[] args) {
		String[] test = {
				"eceba", 
				"aba", 
				"abaccc",
				"ccaabbb",
				"cdaba"
		};
		for (String s: test) {
			System.out.println(lengthOfLongestSubstringTwoDistinct(s));
		}
	}
}
