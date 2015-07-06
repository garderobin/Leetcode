package algorithm;

public class WildCardMatching {
	public static boolean isMatch(String s, String p) {		
        if (s == null || s.length() == 0) {
        	return (p == null || p.length() == 0 || p.equals("*"));
        }
        if (p == null || p.length() == 0) {
        	return (s.equals("*"));
        }
        if (s.equals("*") || p.equals("*")) {
			return true;
		}
        
        char cs = s.charAt(0);
        char cp = p.charAt(0);
                
        if (cs == '*') { //不是最后一位
        	s = s.substring(1);
        	while (p.length() > 0) {
        		if (isMatch(s, p)) {
        			return true;
        		}
        		p = p.substring(1);
    		}
        	return false;
        }
        if (cp == '*') { //不是最后一位
        	p = p.substring(1);
        	while (s.length() > 0) {
        		if (isMatch(s, p)) {
        			return true;
        		}
        		s = s.substring(1);
    		}
        	return false;
        }
        if ((cs == '?' && cp != '*') || (cp == '?' && cs != '*')) {
        	return isMatch(s.substring(1), p.substring(1));
        } 
        if (cs != cp) {
        	return false;
        }
        
        return isMatch(s.substring(1), p.substring(1));
        
//        int i = 0;
//        int j = 0;
//        int lc = s.length();
//        int lp = p.length();
//        char cs, cp;
//        while (i < lc && j < lp) {
//        	cs = s.charAt(i);
//        	cp = s.charAt(j);
//        }
//        
		
	}
	
	public static boolean isMatch2(String s, String p) {
		if (s == null || s.length() == 0) {
	       	return (p == null || p.length() == 0 || p.equals("*"));
	    }
	    if (p == null || p.length() == 0) {
	       	return (s.equals("*"));
	    }
	    if (s.equals("*") || p.equals("*")) {
			return true;
		}
	    
	    return isMatchSub(s, p, 0, 0);
	}	
	
	
	public static boolean isMatchSub(String s, String p, int bs, int bp) {
		char cs, cp;
		int ls = s.length();
		int lp = p.length();		
		if (bs == ls) {
			return s.charAt(bs - 1) == '*' || bp == p.length() || p.charAt(bp) == '*';
		}
		if (bp == lp) {
			return p.charAt(bp - 1) == '*' || s.charAt(bp) == '*';
		}
		cs = s.charAt(bs);
		cp = p.charAt(bp);
		if (cs == '*') { //不是最后一位
        	bs++;
        	while (bp < lp) {
        		if (isMatchSub(s, p, bs, bp)) {
        			return true;
        		}
        		bp++;
    		}
        	return false;
        }
        if (cp == '*') { //不是最后一位
        	bp++;
        	while (bs < ls) {
        		if (isMatchSub(s, p, bs, bp)) {
        			return true;
        		}
        		bs++;
    		}
        	return false;
        }
         
        if (cs != cp || (!((cs == '?' && cp != '*') || (cp == '?' && cs != '*')))) {
        	return false;
        }
		
		return isMatchSub(s, p, bs + 1, bp + 1);
	}
	
	public static void main(String[] args) {
		System.out.println(isMatch2("ab", "**"));
	}
	
	
}
