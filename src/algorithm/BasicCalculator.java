package algorithm;

import java.util.Stack;

public class BasicCalculator {
	
	/**
	 * 答案思路：虽然使用栈，但是保证绝对没有两步以上的没有进行的运算。
	 * 碰到加减号时不运算，而是标当前符号，符号默认是1，遇加号还是1，遇减号变成-1.
	 * 栈里只存两个值，遇到当前括号以前的运算结果，和针对当前括号的运算符。
	 * 遇到左括号把此前的结果和运算符压进栈, 其它情况从左到右想加即可。
	 * 遇到右括号，先结束
	 * 
	 * @param s
	 * @return
	 */
	public int calculateDiscussion(String s) {
	    Stack<Integer> stack = new Stack<Integer>();
	    int result = 0;
	    int number = 0;
	    int sign = 1;
	    for(int i = 0; i < s.length(); i++){
	        char c = s.charAt(i);
	        if(Character.isDigit(c)){
	            number = 10 * number + (int)(c - '0');
	        }else if(c == '+'){
	            result += sign * number;
	            number = 0;
	            sign = 1;
	        }else if(c == '-'){
	            result += sign * number;
	            number = 0;
	            sign = -1;
	        }else if(c == '('){
	            //we push the result first, then sign;
	            stack.push(result);
	            stack.push(sign);
	            //reset the sign and result for the value in the parenthesis
	            sign = 1;   
	            result = 0;
	        }else if(c == ')'){
	            result += sign * number;  
	            number = 0;
	            result *= stack.pop();    //stack.pop() is the sign before the parenthesis
	            result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

	        }
	    }
	    if(number != 0) result += sign * number;
	    return result;
	}
	
	/**
	 * 我的思路：全部入栈。遇到右括号或者遍历结束出栈。
	 * 出栈时把所有的加减号都当做数字的正负号，负负得正，所有的运算都是加法。
	 * @param s
	 * @return
	 */
	public static int calculate(String s) {
		if (s == null || s.length() == 0) { return 0; }
        Stack<String> stack = new Stack<String>();
        int i, len = s.length(), n1, n2 = 0;
        char c;
        String sop1 = "", sop2 = "", s1 = "", s2 = "";
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < len; i++) {
        	c = s.charAt(i);
        	if (c == ' ') {
        		continue;
        	} else if (!Character.isDigit(c)) {
        		if (sb.length() > 0) {
        			stack.push(sb.toString());
            		sb = new StringBuilder();
        		}        		
        		if (c == ')') {
        			// 这里可能有 (1)这种情况出现，不见得所有的括号里面至少有三个入栈字符串，为两个数字中间一个操作符
        			s2 = stack.pop();
        			sop2 = stack.pop();
        			if (sop2.equals("(")) {
        				stack.push(s2);
        				continue;
        			}
        			n2 = signedStrToInt(sop2, s2);
        			s1 = stack.pop();
        			while (!stack.empty() && (!(sop1 = stack.pop()).equals("("))) {          				
        				n1 = signedStrToInt(sop1, s1);
        				n2 = n1 + n2;
        				if (stack.empty()) { break; }
        				s1 = stack.pop();
        			}
        			n2 += Integer.parseInt(s1);
        			stack.push(n2 + "");
        		} else { //'+', '-', '('
        			stack.push("" + c);
        		}
        	} else {
        		sb.append(c);
        	}
        }        
        
        // If the string ends with a digit, append the digit to number, push the number to stack and start pop calculation.
        if (sb.length() == 0 && stack.isEmpty()) { return n2; }
        if (sb.length() > 0) {
        	s2 = sb.toString();        	
        } else if (!stack.isEmpty()) {
        	s2 = stack.pop();
        } 
        if (stack.isEmpty()) { return Integer.parseInt(s2); } 
		n2 = signedStrToInt(stack.pop(), s2);
		if (stack.isEmpty()) { return n2; }
		s1 = stack.pop();
		while (!stack.empty() && (!(sop1 = stack.pop()).equals("("))) {          				
			n1 = signedStrToInt(sop1, s1);
			n2 = n1 + n2;
			if (stack.empty()) { break; }
			s1 = stack.pop();
		}
		return n2 + Integer.parseInt(s1);
    }
	
	private static int signedStrToInt(String op, String num) {
		if (op.equals("-")) {
			return (-1) * Integer.parseInt(num);
		} 
		return Integer.parseInt(num);
	}
	
	public static void main(String[] args) {
//		String s = "(1+(4+5+2)-3)+(6+8)";
//		String s1 = "(1)+2";
		String s2 = " 2-(5-6) ";
		System.out.println(calculate(s2));
	}
}
