package algorithm;

public class WildCardMatching {
	
	public boolean isMatchDiscussion(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1, pLen = pattern.length();            
        while (s < str.length()){
            // advancing both pointers
            if (p < pLen  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pLen && pattern.charAt(p) == '*'){
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
        while (p < pLen && pattern.charAt(p) == '*') { p++; }

        return p == pattern.length();
	}
//	public static boolean isMatch(String s, String p) {		
//        if (s == null || s.length() == 0) {
//        	return (p == null || p.length() == 0 || p.equals("*"));
//        }
//        if (p == null || p.length() == 0) {
//        	return (s.equals("*"));
//        }
//        if (s.equals("*") || p.equals("*")) {
//			return true;
//		}
//        
//        char cs = s.charAt(0);
//        char cp = p.charAt(0);
//                
//        if (cs == '*') { //不是最后一位
//        	s = s.substring(1);
//        	while (p.length() > 0) {
//        		if (isMatch(s, p)) {
//        			return true;
//        		}
//        		p = p.substring(1);
//    		}
//        	return false;
//        }
//        if (cp == '*') { //不是最后一位
//        	p = p.substring(1);
//        	while (s.length() > 0) {
//        		if (isMatch(s, p)) {
//        			return true;
//        		}
//        		s = s.substring(1);
//    		}
//        	return false;
//        }
//        if ((cs == '?' && cp != '*') || (cp == '?' && cs != '*')) {
//        	return isMatch(s.substring(1), p.substring(1));
//        } 
//        if (cs != cp) {
//        	return false;
//        }
//        
//        return isMatch(s.substring(1), p.substring(1));
//        
////        int i = 0;
////        int j = 0;
////        int lc = s.length();
////        int lp = p.length();
////        char cs, cp;
////        while (i < lc && j < lp) {
////        	cs = s.charAt(i);
////        	cp = s.charAt(j);
////        }
////        
//		
//	}
//	
//	public static boolean isMatch2(String s, String p) {
//		int sLen, pLen;
//	    if (p == null || (pLen = p.length()) == 0) {
//	       	return (s == null || (s.length() == 0));
//	    }
//	    p = simplyTrailingStars(p);
//	    
//	    if (s == null || (sLen = s.length()) == 0) {
//	       	return (p.equals("*"));
//	    }
//	    if (p.equals("*") || s.equals(p)) {
//			return true;
//		}
//	   
//	    boolean startWithStar = (p.charAt(0) == '*'), endWithStar = (p.charAt(pLen - 1) == '*');
//		String[] split = p.split("\\*");
//		int pi = 0, pj = split.length, ss = 0, se = sLen;
//		if (!startWithStar) {
//			if (!s.startsWith(split[1])) { //split[0]什么也没有是空格
//				return false;
//			}
//			pi = 2;
//			ss = split[1].length();
//		}
//		if (!endWithStar) {
//			if (s.endsWith(split[--pj])) { //split[0]什么也没有是空格
//				return false;
//			}
//			se = sLen - split[pj--].length(); //pj = pLen - 2;
//		}
//		//TODO
//	    return isMatchHelper(s, split, ss, se, pi, pj);
//	}	
//	
//	
//	private static boolean isMatchHelper(String s, String[] split, int ss, int se, int pi, int pj) {
////		int iFirstMark = split[pi].indexOf('?');
////		String temp;
////		if (iFirstMark < 0) {
////			temp = split[pi];
////		} else {
////			temp = split[pi].substring(0, iFirstMark);
////		}
//////		int i1 = s.indexOf(), i2;
////		if (i1 < 0) { return false; }
////		for (int i = pi; i < pj; i++) {
////			if (isMatchNoStar(ss.substring(i1, ss+len), split[i], ss, se)) {
////				ss += split[i].length();
////			} else {
////				return false;
////			}
////		}
////		return false;
//	}
//	
//	//注意处理连续问号的情况
//	private static boolean isMatchNoStar(String s, String pair, int ss, int se) {
//		
//		int len = pair.length() - 1, i;
//		if (se - ss <= len) { 
//			return false; 
//		}
//		
//		//Ending '?'s
//		for (i = len; len >= 0 && pair.charAt(i) == '?'; i--) {}
//		String snew = s.substring(ss, ss+i+1), pnew = pair.substring(0, i+1);
//		String[] noMark = pair.split("\\?");
//		if (noMark.length == 1) {
//			return snew.equals(pnew);
//		}
//		i = 0;
//		boolean marking = true;
//		for (String e: noMark) {
//			len = e.length();
//			if (!marking) {
//				++i; 
//			}
//			if (len == 0) { // it is a '?' before spliting
//				++i;
//				marking = true;
//			} else {
//				marking = false;
//				if (!snew.substring(i, i+len).equals(e)) { 
//					return false; 
//				}
//				i += len;
//			}
//		}
//		return true;
//	}
//	
//	public static boolean isMatchSub(String s, String p, int bs, int bp, int ls, int lp) {
//		char cs, cp;
//		if (bs == ls) {
//			return s.charAt(bs - 1) == '*' || bp == lp || p.charAt(bp) == '*';
//		}
//		if (bp == lp) {
//			return p.charAt(bp - 1) == '*' || s.charAt(bp) == '*';
//		}
//		cs = s.charAt(bs);
//		cp = p.charAt(bp);
//		if (cs == '*') { //不是最后一位
//        	bs++;
//        	while (bp < lp) {
//        		if (isMatchSub(s, p, bs, bp, ls, lp)) {
//        			return true;
//        		}
//        		bp++;
//    		}
//        	return false;
//        }
//        if (cp == '*') { //不是最后一位
//        	bp++;
//        	while (bs < ls) {
//        		if (isMatchSub(s, p, bs, bp, ls, lp)) {
//        			return true;
//        		}
//        		bs++;
//    		}
//        	return false;
//        }
//         
//        if (cs != cp || (!((cs == '?' && cp != '*') || (cp == '?' && cs != '*')))) {
//        	return false;
//        }
//		
//		return isMatchSub(s, p, bs + 1, bp + 1, ls, lp);
//	}
//	
//	private static String simplyTrailingStars(String p) {
//		// Simply trailing * in p
//	    StringBuilder sb = new StringBuilder();
//	    boolean staring = false;
//	    int pl = p.length();
//	    for (int i = 0; i < pl; i++) {
//	    	char c = p.charAt(i);	    	
//	    	if (!(c == '*' && staring)) { sb.append(c);}
//	    	staring = (c == '*');
//	    }
//	    return sb.toString();
//	}
//	
//	public static void main(String[] args) {
//		String s = "abbaabbbbaaaaababbbaabbaabaababbaababaabbabbaabbbab";
//		String p = "b***b**";
//		String test = "?a???b??c?d???";
////		p = simplyTrailingStars(p);
////		String[] split = p.split("\\*");
////		for (String e: split) {
////			System.out.println(e + ",");
////		}
////		String[] split = test.split("\\?");
////		for (String e: split) {
////			System.out.println(e + ",");
////		}
//		String m = "bacdebcdcedfff";
//		System.out.println(isMatchNoStar(m, test, 0, m.length()));
//	}
//	
//	
//}
}