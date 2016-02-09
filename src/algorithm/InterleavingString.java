package algorithm;

public class InterleavingString {
	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s3 == null || s3.length() == 0) {
			return ( (s1 == null || s1.length() == 0) && (s2 == null || s2.length() == 0) );
		}
		if (s1 == null || s1.length() == 0) { return s3.equals(s2); }
		if (s2 == null || s2.length() == 0) { return s3.equals(s1); }		
		
		// We want position 0 to denote there is no index used in a string, so in all strings, let new index = initial_index + 1.
        int l1 = s1.length() + 1, l2 = s2.length() + 1, l3 = s3.length() + 1; // Be careful!
		if (l3 != l1 + l2 - 1) { return false; }
        boolean[][] b = new boolean[l3][l1]; //b[k][i] marks whether s1(0, i) and s2(0, k - i) can build s3(0, k)        
        int i = 0, j = 0; 
        char c3;
        b[0][0] = true;                
        for (i = 1; i < l1; i++) { // s1 only
        	b[i][i] = b[i-1][i-1] && s3.charAt(i-1) == s1.charAt(i-1);
        }
        for (j = 1; j < l2; j++) { // s2 only
        	b[j][0] = b[j-1][0] && s3.charAt(j-1) == s2.charAt(j-1);
        }
        for (i = 1; i < l1; i++) { // using s1 and s2 to overlap for interleaving        	
        	for (j = 1; j < l2; j++) {
        		if (i+j < l3) {
        			c3 = s3.charAt(i+j-1);
            		b[i+j][i] = b[i+j][i] || (b[i+j-1][i] && c3 == s2.charAt(j-1)) || (b[i+j-1][i-1] && c3 == s1.charAt(i-1));
        		}        		
        	}
        }
        for (i = 0; i < l1; i++) {
       		if (b[l3-1][i]) { return true; }
       	}
        return false;
    }
	
	public static void main(String[] args) {
		String s1 = "aabcc", s2 = "dbbca",s3 = "aadbbcbcac", s4 = "aadbbbaccc";
		System.out.println(isInterleave(s1, s2, s4));
	}
}
