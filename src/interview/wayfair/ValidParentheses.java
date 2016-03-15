package interview.wayfair;

import java.util.Stack;

public class ValidParentheses {
	public static void main(String[] args) {
		System.out.println((int)('}'));
		System.out.println((int)(']'));
		System.out.println((int)(')'));
	}
	
	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
		int len = s.length();
		char[] mapping = new char[126]; // the largest ASCII code among close brackets is 125
		mapping['}'] = '{';
		mapping[']'] = '[';
		mapping[')'] = '(';
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == '{' || c == '[' || c =='(')                    { stack.push(c); }
			else if (stack.empty() || stack.peek() != mapping[c])   { return false;  }
			else                                                    { stack.pop();   }
		}
		
		return stack.empty();
    }
	
}
