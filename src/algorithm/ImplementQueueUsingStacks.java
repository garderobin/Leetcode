package algorithm;

import java.util.Stack;


public class ImplementQueueUsingStacks{
	Stack<Integer> stack = new Stack<Integer>();
	
	// Push element x to the back of queue.
    public void push(int x) {
        stack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        stack.pop();
    }

    // Get the front element.
    public int peek() {
        return -1;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack == null || stack.isEmpty() || stack.size() == 0;
    }
	
}
