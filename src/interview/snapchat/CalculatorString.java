package interview.snapchat;

public class CalculatorString {
	public static void main(String[] args) {
		String a = Integer.toBinaryString(-1), b = "10";
		String c = addBinaryUnsigned(a, b);
		System.out.println(a + " + " + b + " = " + c);
	}
	
	public static String addDigitalUnsigned(String a, String b) {
		return null;
	}
	
	public static String addDigitalSigned(String a, String b) {
		return null;
	}
	
	public static String substractDigitalSigned(String a, String b) {
		return null;
	}
	
	public static String substractDigitalUnsigned(String a, String b) {
		return null;
	}
	
	public static String addBinaryUnsigned(String a, String b) { // leetcode 4ms
        if (a == null || a.isEmpty()) return b;
        if (b == null || b.isEmpty()) return a;
        
        int n = a.length(), m = b.length();
        char[] s = new char[(n > m) ? n+1 : m+1]; 
        for (int c = 0, i = n-1, j = m-1, k = s.length-1; i >= 0 || j >= 0 || c == 1; c /= 2) {
            c += i >= 0 ? a.charAt(i--) - '0' : 0;
            c += j >= 0 ? b.charAt(j--) - '0' : 0;
            s[k--] = (char)((c&1) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
        }
        return (new String(s)).trim(); //第一位可能没有赋值, 默认值是\x，所以必须trim()
    }
}
