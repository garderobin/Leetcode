package interview.snapchat;

public class WildCardMatching {
	
	public boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, sLen = str.length(), match = 0, starIdx = -1, pLen = pattern.length(); 
        
        for (char pChar = ' '; s < sLen; ) {
            if (p < pLen  && ((pChar = pattern.charAt(p)) == '?' || pChar == str.charAt(s))){
                ++s; // common match, advance both pointers
                ++p;
            } else if (p < pLen && pChar == '*') { // * found, only advance pattern pointer
                starIdx = p;
                match = s;
                ++p;
            } else if (starIdx != -1) { // last pattern pointer was *, advance string pointer
                p = starIdx + 1;
                s = ++match;
            } else return false; // pattern finished with no previous star rest for use, hence not match.
        }

        while (p < pLen && pattern.charAt(p) == '*') ++p;  // check for remaining characters in pattern

        return p == pattern.length();
	}

}