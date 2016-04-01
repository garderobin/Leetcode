package algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaxDepthOfBinaryTree {
	class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>();
	
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}		
        return findMaxDepth(root, 1);
    }
	
	public int findMaxDepth(TreeNode root, int h) {
		if (root.left == null && root.right == null) {
			return h;
		} else if (root.left != null && root.right == null) {
			return findMaxDepth(root.left, h+1);
		} else if (root.left == null && root.right != null) {
			return findMaxDepth(root.right, h+1);
		} else  {
			return Math.max(findMaxDepth(root.left, h+1), findMaxDepth(root.right, h+1));
		}
        
	}
	
	public int maxDepthBST(TreeNode root) {
		if (root == null) {
			return 0;
		}	
		LinkedList<TreeNode> q = new LinkedList<TreeNode>(); // Current Layer
		HashMap<TreeNode, Integer> s = new HashMap<TreeNode, Integer>(); // Visited Nodes
		int max = 1;
		int temp;
		q.add(root);
		s.put(root, 1);

		while (!q.isEmpty()) {
			root = q.poll();
			temp = s.get(root) + 1;
			if (!(root.left == null || s.containsKey(root.left))) {
				q.add(root.left);				
				s.put(root.left, temp);
				if (temp > max) {
					max = temp;
				}
			}
			if (!(root.right == null || s.containsKey(root.right))) {
				q.add(root.right);
				s.put(root.right, temp);
				if (temp > max) {
					max = temp;
				}
			}
		}
				
		return max;
	}
	
	
	
	public int findMaxDepthBFS(TreeNode root, int h) {
		if (root.left == null && root.right == null) {
			return h;
		} else if (root.right == null) {
			return findMaxDepth(root.left, h+1);
		} else if (root.left == null) {
			return findMaxDepth(root.right, h+1);
		} else  {
			return Math.max(findMaxDepth(root.left, h+1), findMaxDepth(root.right, h+1));
		}
        
	}
	
}
