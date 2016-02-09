package algorithm;

public class StringToInteger {
	public static int myAtoi(String str) {
		if (str == null || (str = str.trim()).length() == 0) {
            return 0;
        }
        char c = str.charAt(0);
        int pos = 0, num = 0, digit, len = str.length();
        boolean isNegative = (c == '-');
        if ((isNegative || c == '+')) { 
           	pos++;
        } 
        while (pos < len && Character.isDigit((c = str.charAt(pos)))) {
        	digit = (int)(c - '0');
            if ((Integer.MAX_VALUE - digit) / 10 < num) { //论判断int越界的技巧
            	return (isNegative) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            num = num * 10 + (int)(c - '0');
            pos++;
        }
        return (isNegative) ? (-1) * num : num;
	}
	
	public static void main(String args[]) {
		String s = "    10522545459";
		System.out.println(myAtoi(s));
	}
}
