package GoogleOA;

import java.util.ArrayList;
import java.util.List;

/**
 * 这一题需要好好看。三种算法都还没完全明白。
 * 尤其是我自己的backtrack为什么会超时TLE还没有想明白。
 * @author jasmineliu
 *
 */
public class GeneralizedAbbreviation {
	
	/**
	 * Backtracking
	 * @param word
	 * @return
	 */
	public List<String> generateAbbreviationsDiscussion3(String word){
        List<String> ret = new ArrayList<String>();
        backtrack(ret, word, 0, "", 0);
        return ret;
    }

    private void backtrack(List<String> ret, String word, int pos, String cur, int count){
        if(pos==word.length()){
            if(count > 0) cur += count;
            ret.add(cur);
        }
        else{
            backtrack(ret, word, pos + 1, cur, count + 1);
            backtrack(ret, word, pos+1, cur + (count>0 ? count : "") + word.charAt(pos), 0);
        }
    }
	
	/**
	 * General Recursion
	 * @param word
	 * @return
	 */
	public List<String> generateAbbreviationsDiscussion2(String word) {
		List<String> res = new ArrayList<String>();
        int len = word.length();
        res.add(len==0 ? "" : String.valueOf(len));
        for(int i = 0 ; i < len ; i++)
            for(String right : generateAbbreviationsDiscussion2(word.substring(i+1))){
                String leftNum = i > 0 ? String.valueOf(i) : "";
                res.add( leftNum + word.substring(i,i + 1) + right );
            }
        return res;
	}
	
	/**
	 * Bit Manipulation
	 * @param word
	 * @return
	 */
	public List<String> generateAbbreviationsDiscussion1(String word) {
	    List<String> ret = new ArrayList<>();
	    int n = word.length();
	    for(int mask = 0;mask < (1 << n);mask++) {
	        int count = 0;
	        StringBuffer sb = new StringBuffer();
	        for(int i = 0;i <= n;i++) {
	            if(((1 << i) & mask) > 0) {
	                count++;
	            } else {
	                if(count != 0) {
	                    sb.append(count);
	                    count = 0;
	                }
	                if(i < n) sb.append(word.charAt(i));
	            }
	        }
	        ret.add(sb.toString());
	    }
	    return ret;
	}
	
	
//	public static List<String> generateAbbreviations(String word) {
//		List<String> rst = new ArrayList<String>();
//        if (word == null || word.length() == 0) { return rst; }
//        return helper(word, 0, rst, "");
//    }
//	
//	private static List<String> helper(String word, int index, List<String> rst, String prefix) {
//		if (index == word.length()) {
//			return rst;
//		}
//		int limit = word.length() - index;
//		rst.add(prefix + limit);
//		rst.add(prefix + word.substring(index, word.length()));
//		for (int i = 1; i < limit; i++) {
//			helper(word, index+i, rst, (prefix += i));
//			helper(word, index+i, rst, (prefix += word.substring(index, index+i+1)));
//		}
//		return rst;
//	}
}
