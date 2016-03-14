package interview.others;

public class Drchrono {
	
//	public static int solutionV4(String string, String pattern, int N, int M) {
//		int occr = 0, limit = N - M + 1;
//		for (int s = 0, p = 0; s < limit; ++s) {
//            if (isMatch(string, pattern, s, N, M)) { occr++; s +=}           
//        }
//        
//		return occr;
//	}
	
	public static int solutionV3(String string, String pattern, int N, int M) {
		int occr = 0, limit = N - M + 1;
		for (int s = 0; s < limit; ++s) {
            if (isMatch(string, pattern, s, N, M)) { occr++; }           
        }
        
		return occr;
	}
	
	private static boolean isMatch(String string, String pattern, int s, int N, int M) {
        int p = 0, match = 0, starIdx = -1;            
        while (p < M){
            // advancing both pointers
            if (p < M  && (pattern.charAt(p) == '?' || string.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < M && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < M && pattern.charAt(p) == '*') { p++; }

        return p == pattern.length();
	}
	
	public static int solutionV2(String string, String pattern, int N, int M) {
		int occr = 0, s = 0, p = 0;
		for (int match = 0, wcIdx = -1, bs = 0; s < N;) {
			System.out.println("bs = " + bs + ", s = " + s + ", p = " + p);
//			for (p = 0; p < M && string.charAt(s)==pattern.charAt(p); ++s, ++p) {} 
//			  
            if (p < M && (string.charAt(s)==pattern.charAt(p))) {
            	++s;
            	++p;
            } 
         
            // * found, only advancing pattern pointer
            else if (p < M && pattern.charAt(p) == '*'){
            	wcIdx = p;
                match = s;
                p++;
//                s++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (p < M && wcIdx != -1){
                p = wcIdx + 1;
                match++;
                s = match;
            }  
            // not match
            
            else if (p < M){
            	System.out.println("bs = " + bs + ", s = " + s + ", p = " + p + ", not match");
            	p = 0;
            	s = ++bs;
            } 
            else if (p == M) {
//            	if (p == M) { 
            	++occr; 
            	System.out.println(string.substring(bs, s));
            	p = 0;
            	s = ++bs;
//            }
        }
                     
        }
		//check for remaining characters in pattern
        while (p < M && pattern.charAt(p) == '*') { p++; }

        if (p == M) { ++occr; }
        
		return occr;
	}
	
	public static int solutionV1(String string, String pattern, int N, int M) {
		int occr = 0;
		for (int s = 0, p = 0; s < N; ++s) {
            for (p = 0; p < M  && s < N && (pattern.charAt(p)=='*' || string.charAt(s)==pattern.charAt(p)); ++s, ++p) {} 
            if (p == M) { 
            	++occr; 
            	s = (s >= M) ? s - M: -1;
            }           
        }
        
		return occr ;
	}
	
	public static void test() {
//		String string = "ABCDCDABCDABCDDDDCDA", pattern = "D*D";
		String string = "ABCDDCDABCDABCDDDCDA", pattern = "AB*D";
//		String string = "AAAABCDADABA", pattern = "AB***D";
//		String string = "BABB", pattern = "BA*";
//		String string = "BBBB", pattern = "***";
//		String string = "BBBB", pattern = "A*";
		System.out.println(solutionV3(string, pattern, string.length(), pattern.length()));
	}
	
	public static void main(String[] args) {
		test();
	}
}
