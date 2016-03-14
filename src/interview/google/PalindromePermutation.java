package interview.google;

import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation {
	
	/**
	 * 用set可以方便地避免counter计算这一步，实现one-pass.
	 * @param s
	 * @return
	 */
	public boolean canPermutePalindromeDiscussion(String s) {
        Set<Character> set=new HashSet<Character>();
        for(int i=0; i<s.length(); ++i){
            if (!set.add(s.charAt(i))) { set.remove(s.charAt(i)); }
        }
        return set.size() < 2;
    }
	
	public boolean canPermutePalindrome(String s) {
		boolean[] occrOddTimes = new boolean[256];
        for (int i = 0, len = s.length(); i < len; i++) {
        	int key = s.charAt(i); //这一步千万不能减去'a'，那是只有字母的情况，也不能减去‘0’，那是只有数字的情况
        	occrOddTimes[key] = !occrOddTimes[key];
        }
        
        int countOccrOddTimes = 0;
        for (boolean e: occrOddTimes) {
        	if (e) { 
        		if (++countOccrOddTimes == 2) { return false; } 
        	}
        }
        
        return true;
    }
}
