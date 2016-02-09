package algorithm;


public class PalindromePartitioningII {
	
	/**
	 * 还没有完全理解
	 * @param s
	 * @return
	 */
	public static int minCutDiscussion(String s) {
		int len = s.length();
		if (len == 0) return 0;
        int[] c = new int[s.length()+1];
        for(int i = 0; i < len; i++) { c[i] = Integer.MAX_VALUE; }
        c[len] = -1;
        for(int i = len-1; i >= 0; i--){
            for(int a=i,b=i; a>=0 && b<len && s.charAt(a)==s.charAt(b); a--,b++) c[a] = Math.min(c[a], 1 + c[b+1]);
            for(int a=i,b=i+1;a>=0 && b<len && s.charAt(a)==s.charAt(b);a--,b++) c[a] = Math.min(c[a], 1 + c[b+1]);
        }
        return c[0];
    }
	
	public static int minCutDiscussionTest(String s) {
		int len = s.length();
		if (len == 0) return 0;
        int[] c = new int[s.length()+1];
        for(int i = 0; i < len; i++) { c[i] = len; }
        c[len] = -1;
        for(int i = len-1; i >= 0; i--){
            for(int a=i,b=i; a>=0 && b<len && s.charAt(a)==s.charAt(b); a--,b++)  {
            	System.out.print("u: c["+a+"] = " + c[a] + ",\t1 + c["+b+"+1] = " + (1+c[b+1]));
            	c[a] = Math.min(c[a], 1 + c[b+1]);
            	System.out.println(",\tc["+a+"] = " + c[a]);
            }
            for(int a=i,b=i+1; a>=0 && b<len && s.charAt(a)==s.charAt(b);a--,b++)  {
            	System.out.print("d: c["+a+"] = " + c[a] + ",\t1 + c["+b+"+1] = " + (1+c[b+1]));
            	c[a] = Math.min(c[a], 1 + c[b+1]);
            	System.out.println(",\tc["+a+"] = " + c[a]);
            }
        }
        return c[0];
    }
	
	/**
	 * DP
	 * @param s
	 * @return
	 */
	public static int minCutV2(String s) {
		if (s == null || s.length() < 2) { return 0; }
		int len = s.length(), i, j, k;
		int[][] f = new int[len][len+1]; // f[s][e]: minCut(s.substring(s, e))
//        int min = Integer.MAX_VALUE, curMin;
        for (i = 0; i < len; i++) {
        	for (j = i+1; j < f[0].length; j++) {
        		if (isSubstringPalindrome(s, i, j)) {
        			f[i][j] = 0;
        		} else {
        			for (k = i+1; k < j; k++) {
        				f[i][j] = Math.min(f[i][k] + f[k][j], f[i][j]);
        			}
        		}
        	}
        }
		return f[0][len];
    }
	
	private static boolean isSubstringPalindrome(String s, int begin, int end){
        if (begin < 0) return false;
        while (begin < end){
            if (s.charAt(begin++) != s.charAt(end--)) return false;
        }
        return true;
    }
	
	public static void main(String[] args) {
		String s = "bb";
		int minCut = minCutDiscussionTest(s);
		System.out.println(minCut);
	}
	
	/**
	 * TLE超时
	 * @param s
	 * @return
	 */
	public static int minCut(String s) {
		if (s == null || s.length() < 2 || isPalindrome(s)) { return 0; }
        int min = Integer.MAX_VALUE, len = s.length(), curMin;
        for (int i = 1; i < len; i++) {
        	curMin = minCut(s.substring(0, i)) + minCut(s.substring(i));
        	if (curMin < min) { min = curMin; }
        }
		return min;
    }
	
	private static boolean isPalindrome(String s){
		int begin = 0, end = s.length() - 1;
        while (begin < end){
            if (s.charAt(begin++) != s.charAt(end--)) return false;
        }
        return true;
    }
	
}
