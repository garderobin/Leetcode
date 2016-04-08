package interview.snapchat;

public class BigDouble implements Comparable<BigDouble> {
	boolean negative;
	byte[] digital; //每个byte存一个十进制的digit, 正序从左到右存储, 不存符号位
	byte[] decimal;
	
	public BigDouble(String s) { // 优化：throw exception
		negative = false;
		s = s.trim();
		int len1 = s.indexOf('.'), len2 = s.length() - len1, i = 0;
		if (len1 < 0) {
			decimal = new byte[0];
			len1 = s.length();
			len2 = 0;
		} else {
			while (len2 > 0 && s.charAt(len2 + len1 - 1) == '0') --len2;
		}
		while (i < len1 && s.charAt(i) == 0) ++i;
		if (s.charAt(i) == '-') {
			negative = true;
			++i;
		}
		digital = new byte[len1 - i];
		decimal = new byte[len2];
		for (int j = 0; i < len1; ++i, ++j) digital[j] = (byte)(s.charAt(i) - '0');
		for (int j = 0; i < len2; ++i, ++j) decimal[j] = (byte)(s.charAt(i) - '0');
		
	}
	
	@Override
	public int compareTo(BigDouble o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static String add(String a, String b) {
//		int aIdx = a.indexOf('.'), bIdx = b.indexOf('.');
//		if (aIdx < 0) aIdx = a.length();
//		if (bIdx < 0) bIdx = b.length();
//		char[] deci = addDecimal(a, b, aIdx, bIdx), inti = addInteger(a, b, aIdx, bIdx, in);
//		if (deci[0] == '1') inti[inti.length - 1] = (char)(inti[inti.length - 1] + '1');
//		StringBuilder sb = new StringBuilder();
//		int i = 0, j = deci.length - 1;
//		while (i < inti.length && inti[i] == '0') ++i;
//		while (j > 3 && deci[j] == '0') --j;
//		sb.append(inti, i, inti.length - i);
//		sb.append(deci, 1, deci.length - 1);
//		return sb.toString();
		return null;
	}
	
	public static String substract(String a, String b) {
		return null;
	}
	
	/**
	 * Unsigned
	 */
	@SuppressWarnings("unused")
	private static byte[] addInteger(String a, String b, int aEnd, int bEnd, int carry) {
		if (a == null || b == null || a.isEmpty() || b.isEmpty() || (aEnd < 0 && bEnd < 0) ) return new byte[]{0};
		String s1 = a, s2 = b;
		int len1 = aEnd, len2 = bEnd, c = carry;
		if (len1 > len2) {
			s1 = b;
			s2 = a;
			len1 = bEnd;
			len2 = aEnd;
		}
		byte[] inti = new byte[len2 + 1];
		
		for (int i = len1, j = len2, k = inti.length - 1; i > 0 || j > 0; c = c > 9 ? 1 : 0) {
			c += s1.charAt(--i) - '0' + s2.charAt(--j) - '0';
            inti[k--] = (byte)((c%10) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
		}
		inti[0] = (byte)(c + '0');
		
		return inti;
	}
	
	/**
	 * Unsigned
	 */
	@SuppressWarnings("unused")
	private static byte[] addDecimal(String a, String b, int aIdx, int bIdx) {
		if (a == null || b == null || a.isEmpty() || b.isEmpty() || (aIdx == a.length() && bIdx == b.length()) ) return new byte[]{0, -1, 0};
		if (aIdx == a.length()) {
			byte[] deci = new byte[b.length() - bIdx - 1];
			for (int i = bIdx + 1, m = 0; i < b.length(); ++i, ++m) deci[m] = (byte)(b.charAt(i) - '0');
			return deci;
		}
		if (bIdx == b.length()) {
			byte[] deci = new byte[a.length() - aIdx - 1];
			for (int i = aIdx + 1, m = 0; i < a.length(); ++i, ++m) deci[m] = (byte)(a.charAt(i) - '0');
			return deci;
		}
		String s1 = a, s2 = b;
		int idx1 = aIdx, idx2 = bIdx, len1 = a.length() - aIdx - 1, len2 = b.length() - bIdx - 1;
		if (len1 > len2) {
			s1 = b;
			s2 = a;
			idx1 = bIdx; 
			idx2 = aIdx;
			len1 = len2;
			len2 = a.length() - aIdx - 1;
		}
		int n1 = s1.length(), n2 = s2.length(), c = 0; // n1 <= n2
		
		byte[] deci = new byte[len2 + 2];
		
		for (int m = deci.length - 1, j = n2-1, i = len2; i > len1; --i ) deci[m--] = (byte)(s2.charAt(j--) - '0');
		
		for (int i = n1-1, j = idx2 + len1; i > idx1; --i, --j, c = c > 9 ? 1 : 0) {
			c += (s1.charAt(i) - '0') + (s2.charAt(j) - '0');
            deci[i - idx1 + 1] = (byte)((c%10) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
		}
		deci[1] = -1;
		deci[0] = (byte)(c + '0');
		
		return deci;
	}
	
	/**
	 * Unsigned
	 */
	@SuppressWarnings("unused")
	private static char[] substractInteger(String a, String b, int aEnd, int bEnd, int carry) {
		if (a == null || b == null || a.isEmpty() || b.isEmpty() || (aEnd < 0 && bEnd < 0) ) return new char[]{'0'};
		String s1 = a, s2 = b;
		int len1 = aEnd, len2 = bEnd, c = carry;
		if (len1 > len2) {
			s1 = b;
			s2 = a;
			len1 = bEnd;
			len2 = aEnd;
		}
		char[] inti = new char[len2 + 1];
		
		for (int i = len1, j = len2, k = inti.length - 1; k > 0 || c == 1; c = c > 9 ? 1 : 0) {
			c += i > 0 ? s1.charAt(--i) - '0' : 0;
            c += j > 0 ? s2.charAt(--j) - '0' : 0;
            inti[k--] = (char)((c%10) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
		}
//		inti[0] = (char)(c + '0');
		
		return inti;
	}
	
	/**
	 * Unsigned
	 */
	@SuppressWarnings("unused")
	private static char[] substractDecimal(String a, String b, int aIdx, int bIdx, int carry) {
		if (a == null || b == null || a.isEmpty() || b.isEmpty() || (aIdx == a.length() && bIdx == b.length()) ) return new char[]{'0', '.', '0'};
		if (aIdx == a.length()) return b.substring(bIdx, b.length()).toCharArray();
		if (bIdx == b.length()) return a.substring(aIdx, a.length()).toCharArray();
		String s1 = a, s2 = b;
		int idx1 = aIdx, idx2 = bIdx, len1 = a.length() - aIdx - 1, len2 = b.length() - bIdx - 1;
		if (len1 > len2) {
			s1 = b;
			s2 = a;
			idx1 = bIdx; 
			idx2 = aIdx;
			len1 = len2;
			len2 = a.length() - aIdx - 1;
		}
		int n1 = s1.length(), n2 = s2.length(), c = 0; // n1 <= n2
		
		char[] deci = new char[len2 + 2];
		
		for (int m = deci.length - 1, j = n2-1, i = len2; i > len1; --i ) deci[m--] = s2.charAt(j--);
		
		for (int i = n1-1, j = idx2 + len1; i >= idx1; --i, --j, c = c > 9 ? 1 : 0) {
			c += (s1.charAt(i) - '0') + (s2.charAt(j) - '0');
            deci[i - idx1 + 1] = (char)((c%10) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
		}
		deci[1] = '.';
		deci[0] = (char)(c + '0');
		
		return deci;
	}
	
//	private static int compareIntegerAbs(String a, String b, int aEnd, int bEnd) {
//		return 0;
//	}
//	
//	private static int compareDecimal(String a, String b, int aIdx, int bIdx) {
//		return 0;
//	}

}
