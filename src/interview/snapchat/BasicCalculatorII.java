package interview.snapchat;

import java.util.Stack;
/*
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces. 
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 */
public class BasicCalculatorII {
	
	public static int calculate(String s) {
		int len, num = 0, rst = 0;
	    if (s == null || (len = s.length()) == 0) return 0;
	    Stack<Integer> stack = new Stack<Integer>();
	    char sign = '+', cur;
	    for (int i = 0; i < len; ++i){
	        if (Character.isDigit(cur = s.charAt(i))){
	            num = num * 10 + s.charAt(i) - '0';
	        } 
	        if (!(Character.isDigit(cur) || cur == ' ') || i==len-1){
	        	switch (sign) {
	        	case '+': stack.push(num); break;
	        	case '-': stack.push(-num); break;
	        	case '*': stack.push(stack.pop() * num); break;
	        	case '/': stack.push(stack.pop() / num); break;
	        	default: ;
	        	}	      
	            sign = cur;
	            num = 0;
	        }
	    }

	    for(int e: stack) rst += e;
	    return rst;
	}
	
	public static int calculateSingleDigitNoBlank(String s) {
		int len, num = 0, rst = 0;
	    if (s == null || (len = s.length()) == 0) return 0;
	    Stack<Integer> stack = new Stack<Integer>();
	    char sign = '+', cur;
	    for (int i = 0; i < len; ++i){
	        if (Character.isDigit(cur = s.charAt(i))){
	            num = s.charAt(i)-'0';
	        } 
	        if (!Character.isDigit(cur) || i==len-1){
	        	switch (sign) {
	        	case '+': stack.push(num); break;
	        	case '-': stack.push(-num); break;
	        	case '*': stack.push(stack.pop() * num); break;
	        	case '/': stack.push(stack.pop() / num); break;
	        	default: ;
	        	}	      
	            sign = cur;
	            num = 0;
	        }
	    }

	    for(int i: stack) rst += i;
	    return rst;
	}
	

	
	public static void main(String[] args) {
//		String s = "3+2*2";
//		String s1 = "3 + 5 / 2 ";
		String s2 = "46";
		System.out.println(calculate(s2));
	}
}
