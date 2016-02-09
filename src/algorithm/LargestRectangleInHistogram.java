package algorithm;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {
	
	/**
	 * For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle.
	 * Then the largest rectangle is the max of them.
	 * We need to know index of the first smaller (smaller than ‘x’) bar on left of ‘x’ -- 'left index'
	 * and index of first smaller bar on right of ‘x’ -- 'right index'.
	 * We traverse all bars from left to right, maintain a stack of bars. 
	 * Every bar is pushed to stack once. A bar is popped from stack when a bar of smaller height is seen. 
	 * When a bar is popped, we calculate the area with the popped bar as smallest bar. 
	 * The current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’.
	 * @param height
	 * @return
	 */
	public static int largestRectangleAreaDiscussion2(int[] height) {
        height = Arrays.copyOf(height, height.length + 1);

        int maxRect = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < height.length; ++i) {
            while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                int rect = height[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                maxRect = Math.max(maxRect, rect);
            }
            stack.push(i);
        }

        return maxRect;
    }
	
	public static int largestRectangleAreaDiscussion2Test(int[] height) {
        height = Arrays.copyOf(height, height.length + 1);

        int maxRect = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < height.length; ++i) {        	
            while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
            	printStack(stack, i, "while: ");
                int rect = height[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                System.out.println("maxRect = " + maxRect + ", rect = " + rect);
                maxRect = Math.max(maxRect, rect);               
            }
            stack.push(i);
            printStack(stack, i, "after: ");
        }

        return maxRect;
    }
	
	private static void printStack(Stack<Integer> stack, int i, String prompt) {
		Integer[] vector = new Integer[stack.size()];
		stack.copyInto(vector);
		System.out.print("i = " + i + ", " + prompt);
		System.out.print("[");
		for (Integer e : vector) {
			System.out.print(e + "\t");
		}
		System.out.println("]");
	}
	
	/**
	 * 还没有完全理解。
	 * @param height
	 * @return
	 */
	public int largestRectangleAreaDiscussion(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
	
	    Stack<Integer> index = new Stack<Integer>();
	    index.push(-1);
	    int max = 0;
	
	    for  (int i = 0; i < height.length; i++) {
	        //Start calculate the max value
	        while (index.peek() > -1) {
	            if (height[index.peek()] > height[i]){
	                int top = index.pop();                    
	                max = Math.max(max, height[top] * (i - 1 - index.peek()));  
	            }else break;
	        }
	        index.push(i);
	    }
	    while(index.peek() != -1) {
	        int top = index.pop();
	        max = Math.max(max, height[top] * (height.length - 1 - index.peek()));
	    }        
	    return max;
    }
	
	public static int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
	    int maxA = 0, l = height.length, cur_left = 0, cur_right = l, i, j;
	    int[] right = new int[l], left = new int[l], h = new int[l];
	    for (j = 0; j < l; j++) {
	    	right[j] = Integer.MAX_VALUE;
	    	left[j] = 0;
	    	h[j] = Integer.MAX_VALUE;
	    }
	    // compute left (from left to right)
	    for(j = 0; j < l; j++) { 	    	
            if(height[j] > 0)  {
            	left[j] = Math.max(left[j], cur_left);
            }
            else {
            	left[j] = 0; 
            	cur_left = j + 1;
            }
        }
	    
        // compute right (from right to left)
        for(j = l - 1; j >= 0; j--) {
            if(height[j] > 0)  {
            	right[j] = Math.min(right[j], cur_right);
            }
            else {
            	right[j] = l; 
            	cur_right = j;
            }    
        }
        
        // compute height (can do this from either side)
        for(j = 0; j < l; j++) { 
            for (i = left[j]; i < right[j]; i++) {
            	h[j] = Math.min(h[j], height[i]);
            }
        }
        // compute the area of rectangle (can do this from either side)
        for(j = 0; j < l; j++)
            maxA = Math.max(maxA, (right[j] - left[j]) * h[j]);
	    return maxA;
	}
	
	public static void main(String[] args) {
		int[] height = {6,2,5,4,5,1,6};
		System.out.println(largestRectangleAreaDiscussion2Test(height));
	}
}
