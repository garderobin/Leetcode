package interview.google;

import java.util.LinkedList;
import java.util.Queue;

public class VerifyPreorderSerializationOfABinaryTree {
	
	/**
	 *  LinkedList性能比Stack要好， 而且这里也更符合语义，不要使用stack.
	 * @param preorder
	 * @return
	 */
	public static boolean isValidSerialization(String preorder) {
		 if (preorder.charAt(0) == '#') { return preorder.length() == 1; } 
	        String[] nodes = preorder.split(",");
	        if (nodes.length < 3 || nodes.length % 2 == 0) { return false; }
	        Queue<String> queue = new LinkedList<String>();
	        queue.add(nodes[0]);
	        boolean even = false;
	        for (int i = 1; i < nodes.length; i++, even = !even) {
	        	if (nodes[i].charAt(0) != '#') { queue.add(nodes[i]); }
	        	if (even) { 
	        		queue.poll(); 
	        		if (queue.isEmpty()) { return i == nodes.length - 1; } 
	        	}
	        }
	        
	        return queue.isEmpty();
    }
	
	public static void main(String[] args) {
		String[] test = {"9,3,4,#,#,1,#,#,2,#,6,#,#", "9,#,#,1"};
		long startTime1 = System.nanoTime();
		for (String s: test) {
			for (int i = 0; i < 10000; i++) {
				isValidSerialization(s);
			}
		}
		long endTime1 = System.nanoTime();
		System.out.println("stack: \t" + (endTime1 - startTime1)/1000000.0 + " ms");
		long startTime2 = System.nanoTime();
		for (String s: test) {
			for (int i = 0; i < 10000; i++) {
				isValidSerialization(s);
			}
		}
		long endTime2 = System.nanoTime();
		System.out.println("LinkedList: \t" + (endTime2 - startTime2)/1000000.0 + " ms");
		
	}
}
