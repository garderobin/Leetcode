package algorithm;

import java.util.Stack;

public class ExcelSheetColumnNumberTitle {
	public static String convertToTitleByIteration(int n) {
		int m = (int)((double)(Math.log(n)) / (double)(Math.log(26)));
		for (int i = m; i >= 0; i--) {
			
		}
		int p = n / 26;
		int q = n % 26;
		if (n > 0 && q == 0) {
			q = 26;
			p--;
		}
		
		char c = (char)(q + 64);
		char d = (char)(p + 64);
				
		if (p == 0) {
			return c + "";
		}
						
		return (d + "") +  (c + "");          
    }
	
	public static void main(String[] args) {
		//System.out.println(convertToTitle(677));
		//System.out.println((int)('C') - 64);
		System.out.println(titleToNumber("AB"));
	}
	
	public static String convertToTitle(int n) {
		StringBuilder sb  = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();
		int q;
		char c;
		while (n > 26) {
			q = n % 26;						
			n = n / 26;
			if (q == 0) {
				q = 26;
				n--;
			}
			c = (char)(q + 64);
			stack.push(c);
		}
		c = (char)(n + 64);
		stack.push(c);
		int size = stack.size();		
		for (int i = 0; i < size; i++) {
			sb.append(stack.pop());
		}		
		return sb.toString();
	}
	
	public static int titleToNumber(String s) {
        int len = s.length();
        int exp = 0;
        int n = 0;
        int v, i, j;
        char c = ' ';
        for (i = len - 1; i >= 0; i--) {
        	c = s.charAt(i);
        	v = (int)(c) - 64;
        	for (j = 0; j < exp; j++) {
        		v  = v * 26;
        	}
        	n += v;
        	exp++;
        }
		return n;
    }
}
