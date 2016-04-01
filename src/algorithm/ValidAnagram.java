package algorithm;

public class ValidAnagram {

	public static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        if (s == t || s.equals(t)) {
            return true;
        } 
        int len = s.length();
        int[] cs = new int[26], ct = new int[26];        
        for (int i = 0; i < len; i++) {
        	cs[s.charAt(i) - 'a']++;
        	ct[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len; i++) {
        	if (cs[i] != ct[i]) {
        		return false;
        	}
        }
        return true;
    }
	
	public static void main(String[] args) {
		String s = "ab";
		String t = "ba";
		System.out.println(isAnagram(s, t));
	}
}
