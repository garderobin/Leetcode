package algorithm;

import java.util.Stack;

public class TrappingRainWater {
	
	/**
	 * 还没看懂！！！
	 * @param height
	 * @return
	 */
	public int trapDiscussion(int[] height){
	    int left = 0;
	    int right = height.length-1;
	    int trap = 0;
	    int leftMax = 0;
	    int rightMax = 0;
	    while(left <= right){
	        leftMax = Math.max(leftMax, height[left]);
	        rightMax = Math.max(rightMax, height[right]);
	        if(leftMax < rightMax){
	            trap += (leftMax - height[left]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
	            left++;
	        }
	        else{
	            trap += (rightMax - height[right]);
	            right--;
	        }
	    }
	    return trap;
	}
	
	/**
	 * Two pointers
	 * @param height
	 * @return
	 */
	public static int trap(int[] height) {
		if (height == null || height.length < 3) { return 0; }
        //Stack<Integer> stack = new Stack<Integer>(); // Stack里面存的是height的index而不是值
		int left = 0, right = height.length - 1, sub = 0, trap = 0, 
				peek = -1;
		//stack.push((left < right) ? left : right);
		while (left < right) {
			if (peek == -1) {
				if (height[left] < height[right]) {
					peek = left;
					left++; 
				} else {
					peek = right;
					right--;
				}
				continue;
			} else if (peek < left) {
				if (height[left] < height[peek]) {
					sub += height[left];
					left++;
					continue;
				} else {
					trap += (left - peek) * height[peek] - sub;
					sub = 0;
					//peek = (height[left] < height[right]) ? left:right;
					// to be done
					//continue;
					peek = -1;					
				}
			} else if (peek > right) {
				if (height[right] < height[peek]) {
					sub += height[right];
					right--;
					continue;
				} else {
					trap += (peek - right) * height[peek] - sub;
					sub = 0;
					peek = -1;
				}
			} 
		}
		
		if (sub > 0) {
			trap += Math.abs(peek - right - 1) * Math.min(height[right], height[peek]) - sub;
		}
		
		return trap;
	}
	
	
	/**
	 * 不推荐
	 * @param height
	 * @return
	 */
	public static int trapSinglePointer(int[] height) {
		if (height == null || height.length < 3) { return 0; }
        Stack<Integer> stack = new Stack<Integer>(); // Stack里面存的是height的index而不是值
        int trap = 0, i = 1, prevIndex = 0, bottomIndex = -1; 
        
        // Find the start
        for (i = 1; i < height.length && height[i] >= height[i-1]; i++) {}
        if (i > height.length - 2) { return 0; }
        stack.push(i - 1);
        
        // Stack in for smaller steps, out for trap computation.
        for (; i < height.length; i++) {
        	if (height[i] > height[stack.peek()]) {        		
        		bottomIndex = stack.pop();
        		while (!stack.isEmpty() && height[bottomIndex] < height[i]) {
        			prevIndex = stack.pop();
            		trap += (i - prevIndex - 1) * (Math.min(height[prevIndex], height[i]) - height[bottomIndex]);
            		bottomIndex = prevIndex;
        		}       		
        	}
        	stack.push(i);
        }
        
        return trap;      
    }
	
	public static void main(String[] args) {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(trap(height));
	}
}
