package interview.google;

public class StrobogrammaticNumberIII {
	public static int strobogrammaticInRange(String low, String high) {
		
		if (high == null || low == null || high.length() == 0 || high.charAt(0) == '-' || high.charAt(0) == '0') { return 0; } //这里默认开头为零其后不为零不是合法
		int lowLen = low.length(), highLen = high.length();
		if (lowLen == 0 || low.charAt(0) == '0') { return strobogrammaticInRange("0", high); }
		char cl = low.charAt(0), ch = high.charAt(0);
		if (cl == '+' || cl == '0') { return strobogrammaticInRange(low.substring(1), high); }
		if (ch == '+' || ch == '0') { return strobogrammaticInRange(low, high.substring(1)); }
		
		boolean startFromZero = low.startsWith("0");
		int[] count = new int[highLen], sum = new int[highLen];
		return -1;
    }
	
	private static String getEffectiveString(String s) {
		return (s.charAt(0) == '+' || s.charAt(0) == '0') ? getEffectiveString(s.substring(1)) : s; 
	}
	
	/**
	 * 只要开头为零，整个就是零
	 * 这里存在互相矛盾，待会在改。
	 * @param high
	 * @param count
	 * @param sum
	 * @return
	 */
	private  static int strobogrammaticFromZero(String high, int[] count, int[] sum) {
		return sum[high.length()-1] + effectCountInSingleLevel(high, count, sum);
    }
	
	private static int effectCountInSingleLevel(String s, int[] count, int[] sum) {
		int n = s.length(); 
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			switch (s.charAt(0)) {
			case '0': return 1;
			case '1': 
			case '2': 
			case '3':
			case '4':
			case '5': 
			case '6': 
			case '7': return 2;
			case '8': 
			case '9': return 3;
			}
		}
		int rst = 0, shrink = strobogrammaticFromZero(getEffectiveString(s.substring(1, n-1)), count, sum);
		int is = (int)s.charAt(0) - '0', ie = (int)s.charAt(n-1) - '0';
		switch (is) {
		case 0: return countSpecialStart(0, ie, shrink, n, sum);
		case 1: return countSpecialStart(1, ie, shrink, n, sum);
		case 2: 
		case 3:
		case 4:
		case 5: return sum[n-1] + sum[n-2] + shrink;
		case 6: //
		case 7: return sum[n-2] + sum[n-2] + shrink;
		case 8: //
		case 9: //
		}
		
		return -1;
	}
	
	/**
	 * 
	 * @param is
	 * @param shrink
	 * @param n 进来的时候一定大于等于2
	 * @param sum
	 * @return
	 */
	private static int countSpecialStart(int is, int ie, int shrink, int n, int[] sum) {
		int[] mapping = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
		int[] multi = 	{0, 0, -1, -1, -1, -1, 1, -1, 2, 3};
		int rst = sum[n-1] + (sum[n-2] * multi[is]);
		if (ie < mapping[is]) {
			return rst;
		} else if (ie == mapping[is]) {
			return rst + shrink;
		} else {
			return rst + sum[n-2];
		}
	}
	
	private static void countAndSumStrobogrammaticInLength(int n, int[] count, int[] sum) {
		int[] arr1 = {0, 3}, arr2 = {0, 3, 4}, arr3 = {0, 3, 4, 12},
			sum1 = {0, 3}, sum2 = {0, 3, 7}, sum3 = {0, 3, 7, 19};
		switch (n) {
		case 1: count = arr1; sum = sum1; return;
		case 2: count = arr2; sum = sum2; return;
		case 3: count = arr3; sum = sum3; return;
		default:
			count[0] = 0; count[1] = 3; count[2] = 4; count[3] = 12;
			sum[0] = 0; sum[1] = 3; sum[2] = 7; sum[3] = 19;
			for (int i = 4; i < count.length; i++) {
				count[i] = count[(i % 2) + 2];
				for (int j = 1, k = i / 2; j < k; j++) {
					count[i] *= 5;
				}
				sum[i] = sum[i-1] + count[i];
			}
		}
	}
	
	public static boolean isStrobogrammatic(String num) {
		for (int i=0, j=num.length()-1; i <= j; i++, j--)
	        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
	            return false;
	    return true;
	}
	
	public static void main(String[] args) {
		int[] count = new int[21], sum = new int[21];
		countAndSumStrobogrammaticInLength(20, count, sum);
		for (int i = 0; i < count.length; i++) {
			System.out.println(i + ": \t" + count[i] + ", \t" + sum[i]);
		}
	}
}
