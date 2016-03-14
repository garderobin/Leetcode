package algorithm;

import java.util.Stack;

public class BasicCalculatorII {
	/**
	 * 我的思路：虽然使用栈，但是保证绝对没有两步以上的没有进行的运算。
	 * 碰到加减号时不运算，而是标当前符号，符号默认是1，遇加号还是1，遇减号变成-1.
	 * 栈里只存两个值，遇到当前括号以前的运算结果，和针对当前括号的运算符。
	 * 遇到乘除号把此前的结果和运算符压进栈, 其它情况从左到右想加即可。
	 * 遇到右括号，先结束
	 * 
	 * @param s
	 * @return
	 */
	public static int calculate(String s) {
		if (s == null || s.length() == 0) { return 0; }
		Stack<Integer> stack = new Stack<Integer>();		
        int i, len = s.length(), signPlus = 1, signMul = 0, rst = 0, num = 0;
        char c;
        for (i = 0; i < len; i++) {
        	c = s.charAt(i);
        	if (c == ' ') {
        		continue;
        	} else if (Character.isDigit(c)) {
        		num = num * 10 + (int)(c - '0');
        	} else {
        		if (signMul != 0) { // is dividing or multiplying
        			rst *= Math.pow(num, signMul);
        			if (c == '+' || c == '-') {     
            			rst *= stack.pop();
            			rst += stack.pop();                		
                		signMul = 0;
                		num = 0;
                		signPlus = (c == '+') ? 1 : -1;
                	} else if (c == '*' || c == '/') {
                		num = 0;
                		signMul = (c == '*') ? 1 : -1;
                	} 
        		
            	} else {
            		if (c == '+' || c == '-') {        		
                		rst += num * signPlus;
                		num = 0;
                		signPlus = (c == '+') ? 1 : -1;
                		signMul = 0;
                	} else if (c == '*' || c == '/') {
                		stack.push(rst);
                		stack.push(signPlus);
                		rst = num;
                		num = 0;
                		signMul = (c == '*') ? 1 : -1;
                	} 
            	}
        	}
        		
        }
        
        if (num != 0) {
        	if (signMul != 0) {
        		rst *= Math.pow(num, signMul);
        		rst *= stack.pop();
        		rst += stack.pop();
        	} else {
        		rst += num * signPlus;
        	}
        	
        }
        
        return rst;
    }
	
	public static void main(String[] args) {
//		String s = "3+2*2";
//		String s1 = "3 + 5 / 2 ";
		String s2 = "46";
		System.out.println(calculate(s2));
	}
}
