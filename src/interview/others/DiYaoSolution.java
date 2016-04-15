package interview.others;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
public class DiYaoSolution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    	BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		
		String line = bi.readLine();
		int n = Integer.parseInt(line), count = 0;
		
		MyStack stack = new MyStack();
		while ((line = bi.readLine()) != null) {
			String[] split = line.split("[ ]+");
			if (split[0].equals("pop")) {
				stack.pop();
			} else if (split[0].equals("push")) {
				stack.push(Integer.parseInt(split[1]));
			} else {
				stack.inc(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
			}
			if (stack.isEmpty()) writer.println("EMPTY");
			else writer.println(stack.peek());
			if (++count == n) break;
		} 
		writer.flush();
		bi.close();
		writer.close();
		if (count < n) throw new Exception();
    } 
    
  static class MyStack {
    	Deque<Integer> stack;
    	
    	public MyStack() {
    		stack = new ArrayDeque<Integer>();
    	}
    	
    	public boolean isEmpty() {
    		return stack.isEmpty();
    	}
    	
    	public int peek() {
    		return stack.isEmpty() ? Integer.MIN_VALUE : stack.peekLast();
    	}
    	
    	public void push(int a) {
    		stack.offerLast(a);
    	}
    	
    	public void pop() {
    		stack.pollLast();
    	}
    	
    	//如果不到x个element,那就新添值为d的值进去
    	public void inc(int x, int d) {
    		if (x <= 0) return;
    		int[] oldEle = new int[x];
    		for (int i = 0; i < x; ++i) {
    			oldEle[i] = stack.isEmpty() ? 0 : stack.pollFirst();
    		}
    		for (int i = x - 1; i >= 0; --i) {
    			stack.offerFirst(oldEle[i] + d);
    		}
    	}
	}
}
