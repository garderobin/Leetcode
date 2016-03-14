package algorithm;

import java.util.Stack;

public class EvaluateReversePolishNotation {
	public static int evalRPN(String[] tokens) {
        int cur = 0, n1, n2;
        Stack<Integer> stack = new Stack<Integer>();
        for (String s: tokens) {
        	try{
        		cur = Integer.parseInt(s);
        		stack.push(cur);
        	} catch (NumberFormatException e) {
        		n2 = stack.pop();
        		n1 = stack.pop();
        		if (s.equals("+")) {            		
            		stack.push(n1 + n2);
            	} else if (s.equals("-")) {
            		stack.push(n1 - n2);
            	} else if (s.equals("*")) {
            		stack.push(n1 * n2);
            	} else if (s.equals("/")) {
            		stack.push(n1 / n2);
            	} else {
            		System.out.println("Another Expression!");
            		System.exit(0);
            	}
        	}
        	
        }
        
        return stack.pop();
    }
	
	public static void main(String args[]) {
//		String[] tokens = {"2", "1", "+", "3", "*"};
		String[] t2 = {"4", "13", "5", "/", "+"};
		System.out.println(evalRPN(t2));
	}
}
