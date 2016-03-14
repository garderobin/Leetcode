package interview;

public class KangranLinkedIn {
	
	public static void test() {
		String[] str = {"abacdab", "1234567", "abacdaba"};
		for (String s: str) {
			System.out.println(shortPalin(s));
		}
		
	}
	
	/**
	 * 思路极好！要再理解
	 * O(N^2) time, O(N) space
	 * @param s
	 * @return
	 */
	static int shortPalinOptimization(String s) {
    	int now = 0, length = s.length();    
    	int[][] matrix = new int [2][length];      
        for (int i = length - 1; i >= 0; --i) {    
        	matrix[now][i] = 1;    
            for (int j = i + 1; j < length; ++j)    
                if (s.charAt(i) == s.charAt(j))    
                	matrix[now][j] = matrix[1 - now][j - 1]+2;    
                else    
                	matrix[now][j] = Math.max(matrix[now][j - 1], matrix[1 - now][j]);    
            now = 1 - now;    
        }
        return length - matrix[length % 2 == 0 ? 1 : 0][length - 1];    
    }
	
	/**
	 * Calculate the minimum number of characters needed to build a palindrome string based on a given string.
	 * 
	 * Basic idea: 	the way to build a shortest palindrome string is to pair every character 
	 * 				which is not included in the string's longest palindrom subsequence (not substring!).
	 * Approach: 	dynamic programming. 
	 * Performance: O(N^2) time, O(N^2) space.
	 * 
	 * @param s
	 * @return  the number of characters we need to insert into s to build a shortest palindrome string.
	 */
	static int shortPalin(String s) {
		if (s == null || s.length() == 0) { return 1; }
		int n = s.length();
		int [][]lp = new int[n][n]; //lp[i][j]: length of longest palindrome subsequence from ith index to jth index
		 
		for(int i = 0; i < n; ++i) { 
			lp[i][i] = 1;	// all the characters in the string are palindrome by itself of length 1.
		}
		for(int sublen = 2; sublen <= n; ++sublen) { // sliding window, each time augment the window size by one
			for(int i = 0; i <= lp.length-sublen; ++i) {
				int j = i + sublen - 1;
				if(s.charAt(i) == s.charAt(j)) {
					lp[i][j] = (sublen == 2) ? 2 : lp[i+1][j-1] + 2;
				} else {
					lp[i][j] = Math.max(lp[i+1][j], lp[i][j-1]);
				}
			}
		}
		return s.length() - lp[0][lp.length-1];
	}
	
	/**
	 * Calculate the length of the longest palindrom subsequence (not substring!) of a given input.
	 * Dynamic programming. O(N^2) time, O(N^2) space.
	 */
	static int longestPalindromSubsequence(String s){
		char [] chars = s.toCharArray();  //Convert string to character array
		int [][]lp = new int[chars.length][chars.length]; //lp[i][j]: length of longest palindrome subsequence from ith index to jth index
		 
		for(int i=0; i<chars.length; i++) { 
			lp[i][i] = 1;	// all the characters in the string are palindrome by itself of length 1.
		}
		for(int sublen = 2; sublen <= chars.length; ++sublen) { // sliding window, each time augment the window size by one
			for(int i=0; i <= lp.length-sublen; ++i) {
				int j = i + sublen - 1;
				if(chars[i] == chars[j] && sublen == 2) {
					lp[i][j] = 2;
				} else if(chars[i] == chars[j]){
					lp[i][j] = lp[i+1][j-1] + 2;
				} else{
					lp[i][j] = Math.max(lp[i+1][j], lp[i][j-1]);
				}
			}
		}
		return lp[0][lp.length-1];
	}
	
	
	// Returns the length of the longest palindromic subsequence in seq
		static int lps(String s, int i, int j) {
			   // Base Case 1: If there is only 1 character
			   if (i == j) { return 1; }
			 
			   // Base Case 2: If there are only 2 characters and both are same
			   if (s.charAt(i) == s.charAt(j) && i + 1 == j) { return 2; }
			 
			   // If the first and last characters match
			   else if (s.charAt(i) == s.charAt(j) ) { return lps (s, i+1, j-1) + 2; }
			 
			   // If the first and last characters do not match
			   return Math.max( lps(s, i, j-1), lps(s, i+1, j) );
		}
		
	
	public static int minCut(String s) {
		int len = s.length();
			if (len == 0) return 0;
	        int[] c = new int[s.length()+1];
	        for(int i = 0; i < len; i++) { c[i] = len; }
	        c[len] = -1;
	        for(int i = len-1; i >= 0; i--){
	            for(int a=i,b=i; a>=0 && b<len && s.charAt(a)==s.charAt(b); a--,b++) c[a] = Math.min(c[a], 1 + c[b+1]);
	            for(int a=i,b=i+1;a>=0 && b<len && s.charAt(a)==s.charAt(b);a--,b++) c[a] = Math.min(c[a], 1 + c[b+1]);
	        }
	        return c[0];
	    }
	
	
    /**
     * Find the first non-repeated character.
     *
     * Basic idea: 	record the last occurrence position for each possible character.
     * 				Set it to the current position when first met, and -1 when met twice.
     * Performance: O(N) time, O(N) space.
     * 
     * @param s - the given input string
     * @return the single character string containing the first non-repeated character.
     */
	static String nonRepeated(String s) {
		int[] pos = new int[256]; // the last occurrence position (one-based, not zero based) for each character in ASCII.
		int i = 0, n = s.length();
		for (; i < n; ++i) {
			char c = s.charAt(i);
			if 		(pos[c] > 0) 	{ pos[c] = -1;  }	// c occurred once before
			else if (pos[c] == 0) 	{ pos[c] = i+1; }	// c never occurred before
			else 					{				}	// c occurred more than once before, ignore it
		}
		
		for (i = 0; i < n && i+1 != pos[s.charAt(i)]; ++i) {} // check out the first non-repeated character
		return s.charAt(i) + "";
		
	}
	
	
	public static String[] fractionSumV2(String[] str) {
		String[] rst = new String[str.length];
		for (int k = 0; k < str.length; ++k) {
			int[] vs = parseArgs(str[k]);
			rst[k] = sumFraction(vs[0], vs[1], vs[2], vs[3]);
		}
	
		return rst;
	}
	
	/**
	 * Parse a single line into four integers
	 * @param s: "X/Y+U/V"
	 * @return [x, y, u, v]
	 */
	static int[] parseArgs(String s) {
		int[] nums = new int[4];
		int cur = 0;
		for (int i = s.length(), nc = 4, trailing = 1; i > 0; i--) {
			char c = s.charAt(i-1);
			switch (c) {
			case '/':
			case '+': 
				nums[--nc] = cur;
				cur = 0;
				trailing = 1;
				break;
			default: 
				cur += trailing * (c - '0');
				trailing *= 10;
				break;
			}
		}
		nums[0] = cur;
		return nums;
	}
	
	/**
	 * Get summed fraction output: (X/Y + U/V) => A/B
	 * input: [x, y, u, v] 
	 * @return: "A/B"
	 */
	static String sumFraction(int u, int v, int x, int y) {
		int up = x*v + u*y, down = v*y, gcd = gcd(up, down);
		int left = (int)up, right = (int)down;
		if (gcd != 0) { 
			left = (int)(up/gcd);
			right = (int)(down/gcd);
		}
		return left + "/" + right;
	}
	
	/**
	 * Calculate the greatest common divisor for input a and b
	 */
	static int gcd(int a, int b){
        if (b==0) return a;
        else return gcd(b,a%b);
    }
	
	
	
	
	
	public static void main(String[] args) {
		test();
	}
	

	
	
	public static String[] fractionSumV1(String[] str) {
		String[] rst = new String[str.length];
		for (int k = 0; k < str.length; ++k) {
			String s = str[k];
			int plusIdx = s.indexOf('+'), d1 = s.indexOf('/'), d2 = s.lastIndexOf('/');
			String[] args = new String[4];
			args[0] = s.substring(0, d1);
			args[1] = s.substring(d1+1, plusIdx);
			args[2] = s.substring(plusIdx+1, d2);
			args[3] = s.substring(d2+1);
			int[] vs = new int[4];
			for (int i = 0; i < 4; ++i) { vs[i] = Integer.parseInt(args[i]); }
			rst[k] = sumFraction(vs[0], vs[1], vs[2], vs[3]);
		}
	
		return rst;
	}
	
}
