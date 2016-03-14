package interview.google;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import dataStructure.TreeNode;

public class ClosestBinarySearchTreeValueII {
	/**
	 * 我认为自己的这种做法复杂度是O(logN)
	 * @param root
	 * @param target
	 * @param k
	 * @return
	 */
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		final double t = target;
	    List<Integer> rst = new ArrayList<Integer>(k);
	    rst.addAll(dfs(root, target, k, new PriorityQueue<Integer>(k, 
	            new Comparator<Integer>(){ // offset descending order
	                @Override
	                public int compare(Integer o1, Integer o2) {
	                    if (o1.intValue() == o2.intValue()) return 0;
	                    return (Math.abs(o1 - t) < Math.abs(o2 - t)) ? 1 : -1; 
	                }
	            })));        
	    return rst;
	}

	private PriorityQueue<Integer> dfs(TreeNode root, double target, int k, PriorityQueue<Integer> queue) {
	    int v = root.val;
	    if (queue.size() < k) { 
	        queue.offer(v); 
	    } else if ((Math.abs(v - target) < Math.abs(queue.peek() - target))) { 
	        queue.poll(); //head is the furthest element in current K nearest elements
	        queue.offer(v);
	    } 

	    if (root.left != null) { queue = dfs(root.left, target, k, queue); }
	    if (root.right != null) { queue = dfs(root.right, target, k, queue); }

	    return queue;
	}
	
}
