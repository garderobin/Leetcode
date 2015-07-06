package algorithm;

import java.util.Stack;

public class ValidParentheses {
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		int len = s.length();
		char c = ' ';
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			switch(c) {
				case '{':
				case '[':
				case '(': stack.push(c); break;
				case '}': if (stack.empty() || stack.peek() != '{') {
								return false;
							} else {
								stack.pop();
								break;
							}
				case ']': if (stack.empty() || stack.peek() != '[') {
							return false;
						} else {
							stack.pop();
							break;
						}
				case ')': if (stack.empty() || stack.peek() != '(') {
							return false;
						} else {
							stack.pop();
							break;
						}
				default: break;
			}
			
		}
		
		return stack.empty();
    }
	
}
