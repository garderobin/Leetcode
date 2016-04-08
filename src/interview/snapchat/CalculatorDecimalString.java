package interview.snapchat;

import java.util.Arrays;
import java.util.List;

public class CalculatorDecimalString {
	public static void main(String[] args) {
		List<String[]> test = Arrays.asList(
//				new String[]{"5000", "1234"},
//				new String[]{"1234.1", "5000.2"},
				new String[]{"9234.0234", "5000.1"},
				new String[]{"9834.48345", "5000.5000"}
				); 
				
		for (String[] ss: test) {
			System.out.println(substract(ss[0], ss[1]));
		}
	}
	
	
	public static String add(String a, String b) {
		int aIdx = a.indexOf('.'), bIdx = b.indexOf('.');
		if (aIdx < 0) aIdx = a.length();
		if (bIdx < 0) bIdx = b.length();
		char[] deci = addDecimal(a, b, aIdx, bIdx), 
				inti = addInteger(a, b, aIdx, bIdx, deci[0]=='1' ? 1 : 0);
		StringBuilder sb = new StringBuilder();
		int i = 0, j = deci.length - 1;
		while (i < inti.length && inti[i] == '0') ++i;
		while (j > 3 && deci[j] == '0') --j;
		sb.append(inti, i, inti.length - i);
		sb.append(deci, 1, deci.length - 1);
		return sb.toString();
	}
	
	
	public static String substract(String a, String b) {
		//TODO 区分大小
		
		StringBuilder sb = new StringBuilder();
		int aIdx = a.indexOf('.'), bIdx = b.indexOf('.');
		if (aIdx < 0) aIdx = a.length();
		if (bIdx < 0) bIdx = b.length();
		char[] deci = substractDecimal(a, b, aIdx, bIdx, 0);
		char[] inti = substractInteger(a, b, aIdx, bIdx, deci[0] - '0');
		int i = 0, j = deci.length - 1;
		while (i < inti.length && inti[i] == '0') ++i;
		while (j > 3 && deci[j] == '0') --j;
		sb.append(inti, i, inti.length - i);
		sb.append(deci, 1, deci.length - 1);
		return sb.toString();
	}
	
	/**
	 * Unsigned
	 */
	private static char[] addInteger(String a, String b, int aEnd, int bEnd, int carry) {
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
		
		for (int i = len1, j = len2, k = inti.length - 1; i > 0 || j > 0; c = c > 9 ? 1 : 0) {
			c += s1.charAt(--i) - '0' + s2.charAt(--j) - '0';
            inti[k--] = (char)((c%10) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
		}
		inti[0] = (char)(c + '0');
		
		return inti;
	}
	
	/**
	 * Unsigned
	 */
	private static char[] addDecimal(String a, String b, int aIdx, int bIdx) {
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
		
		for (int i = n1-1, j = idx2 + len1; i > idx1; --i, --j, c = c > 9 ? 1 : 0) {
			c += (s1.charAt(i) - '0') + (s2.charAt(j) - '0');
            deci[i - idx1 + 1] = (char)((c%10) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
		}
		deci[1] = '.';
		deci[0] = (char)(c + '0');
		
		return deci;
	}
	
	/**
	 * Unsigned, 左一定大于等于右
	 */
	private static char[] substractInteger(String a, String b, int aEnd, int bEnd, int carry) {
		if (a == null || b == null || a.isEmpty() || b.isEmpty() || (aEnd < 0 && bEnd < 0) ) return new char[]{'0'};
		int len1 = aEnd, len2 = bEnd, c = carry, i = len1 - 1;
		char[] inti = new char[len1]; //len1 >= len2
		
		for (int j = len2 - 1; j >= 0 || c == 1; --i, --j, c = c < 10 ? 1 : 0) {
			c = 10 + (a.charAt(i) - '0') - (j < 0 ? 0 : b.charAt(j) - '0') - c;
            inti[i] = (char)((c%10) + '0'); //位操作符优先级低于加减，使用的时候必须加上括号。
		}
		
		for (; i >= 0; --i) inti[i] = a.charAt(i);
		return inti;
	}
	
	private static char[] substractDecimal(String a, String b, int aIdx, int bIdx, int carry) {
		int n1 = a.length(), n2 = b.length(), c = carry;
		int len1 = n1 - aIdx - 1, len2 = n2 - aIdx - 1, maxLen = (len1 > len2) ? len1 : len2;
		char[] deci = new char[maxLen + 2];
		for (int m = maxLen - 1, i = m+aIdx+1, j = m+bIdx+1; m >= 0; --m, --i, --j, c = c < 10 ? 1 : 0) {
			c += 10 + (i < n1 ? a.charAt(i) - '0' : 0) - (j < n2 ? b.charAt(j) - '0' : 0) - c;
            deci[m + 2] = (char)((c%10) + '0'); //位操作符优先级低于加减，使用的时候必须加上括号。
		}
		deci[1] = '.';
		deci[0] = (c < 0) ? '-' : (char)(c + '0');
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
