package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;


public class BinaryTreeLevelOrderTraversal {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public List<List<Integer>> levelOrder(TreeNode root) {		
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		if (root == null) {
			return rst;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>(); // Current Layer
		ArrayList<Integer> row = new ArrayList<Integer>();
		q.add(root);
		row.add(root.val);		
		int size, i;
		while (!q.isEmpty()) {
			row = new ArrayList<Integer>();
			size = q.size();
			for (i = 0; i < size; i++) {
				root = q.poll();
				row.add(root.val);
				if (root.left != null) {
					q.add(root.left);
				} 
				if (root.right != null) {
					q.add(root.right);
				}
			}
			rst.add(row);
		}
		return rst;
		
    }
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> rst = new Stack<List<Integer>>();
		if (root == null) {
			return rst;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>(); //Current layer
		ArrayList<Integer> row = new ArrayList<Integer>();
		q.add(root);
		row.add(root.val);
		int size, i;
		while (!q.isEmpty()) {
			row = new ArrayList<Integer>();
			size = q.size();
			for (i = 0; i < size; i++) {
				root = q.poll();
				row.add(root.val);
				if (root.left != null) {
					q.add(root.left);
				}
				if (root.right != null) {
					q.add(root.right);
				}
			}
			rst.add(0, row);
		}
		
		return rst;
    }
}
