package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class WordBreakII {
	/***
	 * DP做法。
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public List<String> wordBreakDiscussion2(String s, Set<String> wordDict) {
        @SuppressWarnings("unchecked")
		LinkedList<String>[] dp = (LinkedList<String>[]) new LinkedList<?>[s.length()+1];
        for(int i = s.length()-1; i >= 0; i--){
            if(wordDict.contains(s.substring(i,s.length()))) break; //我觉得这一步检查毫无用处。
            if(i == 0) return new LinkedList<String>();
        }

        for(int i = 0; i < dp.length; i++) dp[i] = new LinkedList<String>();

        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if((j == 0 || dp[j].size() > 0) && wordDict.contains(s.substring(j,i))) {
                    if(j == 0) dp[i].add(s.substring(j,i));
                    else {
                        for(String c : dp[j]) {
                            dp[i].add(c + " " + s.substring(j,i));
                        }
                    }
                } 
            }
        }
        return dp[s.length()];
    }
	
	public List<String> wordBreakDiscussion1(String s, Set<String> dict) {
	    List<String> result = new ArrayList<String>();
	    for(int j = s.length() - 1; j >= 0; j--){
	        if(dict.contains(s.substring(j)))
	            break;
	        else{
	            if(j == 0)
	                return result;
	        }
	    }
	    for(int i = 0; i < s.length()-1; i++)
	    {
	        if(dict.contains(s.substring(0,i+1)))
	        {
	            List<String> strs = wordBreak(s.substring(i+1,s.length()),dict);
	            if(strs.size() != 0)
	                for(Iterator<String> it = strs.iterator();it.hasNext();)
	                {
	                    result.add(s.substring(0,i+1)+" "+it.next());
	                }
	        }
	    }
	    if(dict.contains(s)) result.add(s);
	    return result;
	}

	/**
	 * 我的思路: backtracking.
	 * 在这之前先检查一遍每一小段是否是一个word，是否是一个sentence.
	 * 用这一步来简化后面backtracking时的比较代价。
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static List<String> wordBreak(String s, Set<String> wordDict) {
		List<String> rst = new ArrayList<String>();
		if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) { return rst; }
		int len = s.length(), i, j, k;
		
		// record the breakability.
        boolean[][] f = new boolean[len+1][len+1]; // f[i][j] = wordBreak(s.substring(i, j), wordDict);
        boolean[][] y = new boolean[len+1][len+1]; // y[i][j] = wordDict.contains(s.substring(i,j));
        f[len][len] = y[len][len] = true;
        for (i = 0; i < len; i++) { // start        	
        	for (j = i; j <= len; j++) { //end
        		y[i][j] = wordDict.contains(s.substring(i,j));
        		f[i][j] = y[i][j];
        	}
        }
        for (i = 0; i < len; i++) { // start
        	for (j = i+1; j <= len; j++) { //end
        		for (k = i+1; k < j; k++) { //divide
        			f[i][j] = f[i][j] || (f[i][k] && f[k][j]);
        		}
        	}
        }
        
        // backtracking
        backtrack(rst, s, f, y, new StringBuilder(), 0, len);
        return rst;
    }
	
	private static List<String> backtrack(List<String> rst, String s, boolean[][] f, boolean[][] y, StringBuilder sb, int start, int end) {
		if (!f[start][end]) { return rst; }
		if (start == end) {
			rst.add(sb.toString().trim());
			return rst;
		}
		if (start + 1 == end) {
			if (y[start][end]) {
				sb.append(s.substring(start, end));
				rst.add(sb.toString());
				return rst;
			}
		}
		for (int i = start+1; i <= end; ++i) {
        	if (i < end && !(y[start][i] && f[i][end])) { continue; }
        	if (i == end && !y[start][i]) { continue; }
        	sb.append(s.substring(start, i) + " ");
        	backtrack(rst, s, f, y, sb, i, end);
        	int lastBlank = sb.lastIndexOf(" "), secLastBlank = sb.substring(0, lastBlank).lastIndexOf(" ");
        	sb.delete(secLastBlank+1, sb.length());
        }
		return rst;
	}
	
	public static void main(String[] args) {
//		String s = "catsanddog";
//		String[] arr =	{"cat","cats","and","sand","dog"};
		String s = "aaaaaaa";
		String[] arr = {"aaaa","aa","a"};
		Set<String> wordDict = new HashSet<String>();
		for (String e:arr) {
			wordDict.add(e);
		}
		List<String> rst = wordBreak(s, wordDict);
		for (String sen: rst) {
			System.out.println(sen);
		}
	}
}
