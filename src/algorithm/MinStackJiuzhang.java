package algorithm;

import java.util.Stack;

public class MinStackJiuzhang {
	private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public MinStackJiuzhang() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.empty() == true)
            minStack.push(number);
        else if (Integer.parseInt(minStack.peek().toString()) >= number)
            minStack.push(number);
    }

    public int pop() {
        if (stack.peek().equals(minStack.peek()) ) 
            minStack.pop();
        return stack.pop();
    }
    
    public int top() {
    	return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
