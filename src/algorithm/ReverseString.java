package algorithm;

public class ReverseString {
	
	public String reverseString(String s) {
		if (s == null || s.isEmpty()) return "";
		char[] chs = s.toCharArray();
		for (int i = 0, j = chs.length - 1; i < j; ++i, --j) {
			chs[i] ^= chs[j];
			chs[j] ^= chs[i];
			chs[i] ^= chs[j];
		}
		return new String(chs);
    }
}
