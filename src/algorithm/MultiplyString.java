package algorithm;

public class MultiplyString {
	
	/**
	 * 利用的技巧是：The product of two numbers cannot exceed the sum of the two lengths. (e.g. 99 * 99 cannot be five digit)
	 * 中间存储未必要遵循最终输出的规则，可以怎么舒服，怎么简便怎么来。
	 * 1. compute products from each pair of digits from num1 and num2. 
	 * 2. carry each element over. 
	 * 3. output the solution.
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiplyDiscussion(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }
	
	public static String multiply(String num1, String num2) {
		if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) { return null; }
		if (num2.equals("0")) { return num2; }
		int l1 = num1.length(), l2 = num2.length();
		String temp = num1;		
		int[] x, y; 
		if (l1 < l2) {
			y = new int[l1];
			x = new int[l2];
			num1 = num2;
			num2 = temp;
		} else {
			x = new int[l1];
			y = new int[l2];
		}
		
		String[] m = new String[y.length];
		
		for (int i = 0; i < x.length; i++) {
			x[i] = num1.charAt(i) - '0';
		}
		
		for (int j = y.length - 1, k = 0; k < y.length; j--, k++) { //反过来了！
			y[j] = num2.charAt(j) - '0';
			m[k] = singleDigitMultiply(y[j], x);
		}
		
        return sumAllDigitMultiply(m);        
    }
    
	private static String singleDigitMultiply(int v, int[] x) {
		StringBuilder sb = new StringBuilder();
		int plus = 0, cur = 0;
		for (int j = x.length - 1; j >= 0; j--) {
			cur = v * x[j] + plus;
			plus = cur / 10;
			sb.insert(0, cur % 10);
		}
		if (plus > 0) {
			sb.insert(0, plus);
		}
		return sb.toString();
	}
	
	private static String sumAllDigitMultiply(String[] m) {
		StringBuilder sb = new StringBuilder();
		int i, j, lastLen = m[m.length - 1].length(), sumLen = lastLen + m.length - 1, 
				index = 0, plus = 0, cur = 0, jMax = m.length - 1, curLen = 0;
		for (i = 0; i < sumLen; i++) {
			cur = plus;
			for (j = Math.min(i, jMax); j >= 0 && (curLen = m[j].length()) > i - j; j--) {
				index = curLen + j - i - 1;				
				if (index >= 0) {
					cur += m[j].charAt(index) - '0';
				}				
			}
			plus = cur / 10;
			sb.insert(0, cur % 10);
		}
		if (plus > 0) {
			sb.insert(0, plus);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(multiply("132", "456"));
	}
		
}
