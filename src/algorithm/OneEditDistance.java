package algorithm;

public class OneEditDistance {
	/**
     * 12345
     *  2345
     * 1345
     * 02345
     * 12045
     */
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || Math.abs(s.length() - t.length()) > 1) { return false; }
        int ls = s.length(), lt = t.length(), i;
        s = s.toLowerCase();
        t = t.toLowerCase();
        if (ls == lt) {
        	for (i = 0; i < ls && s.charAt(i) == t.charAt(i); i++) {}
        	return i == ls || s.substring(i).equals(t.substring(i));
        } else {
        	String x, y;
        	int lx;
        	if (ls < lt) 	{ x = s; lx = ls; y = t; } 
        	else 			{ x = t; lx = lt; y = s; }
        	for (i = 0; i < lx && x.charAt(i) == y.charAt(i); i++) {}
        	return i == lx || x.substring(i+1).equals(y.substring(i));
        }
    }
}
