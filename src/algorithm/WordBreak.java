package algorithm;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	public static boolean wordBreak(String s, Set<String> wordDict) {		
        int len = s.length(), i, j, k;
        boolean[][] f = new boolean[len][len+1]; // f[i][j] = wordBreak(s.substring(i, j), wordDict);
        for (i = 0; i < len; i++) { // start        	
        	for (j = i; j <= len; j++) { //end
        		f[i][j] = wordDict.contains(s.substring(i,j));
        	}
        }
        
        for (i = 0; i < len; i++) { // start
        	for (j = i+1; j <= len; j++) { //end
        		for (k = i+1; k < j; k++) { //divide
        			f[i][j] = f[i][j] || (f[i][k] && f[k][j]);
        		}
        	}
        }
        return f[0][len];
    }
	
	public static void main(String[] args) {
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("leet");
		wordDict.add("code");
		String s = "leetcode";
		System.out.println(wordBreak(s, wordDict));
	}
	
	private boolean wordBreakSub(String s, Set<String> wordDict, boolean[][] f, int start, int end) {
//		if (star)
//		for (int i = 1; i < len && !rst; i++) {
//        	rst = rst || (wordBreak(s.substring(0, i), wordDict) && wordBreak(s.substring(i, len), wordDict));
//        }	
		return false;
	}
}
