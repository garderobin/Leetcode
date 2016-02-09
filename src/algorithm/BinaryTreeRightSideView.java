package algorithm;

import java.util.ArrayList;
import java.util.List;

import dataStructure.TreeNode;

public class BinaryTreeRightSideView {
	public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> rst = new ArrayList<Integer>();
        if (root == null) { return rst; }
        return helper(root, 0, rst);
    }
	
	private static List<Integer> helper(TreeNode node, int height, List<Integer> rst) {
		if (rst.size() == height) {
			rst.add(node.val);
		}
		if (node.right != null) {
			helper(node.right, height+1, rst);
		} 
		if (node.left != null) { //这里与上面一个if是递进的，绝不是并列关系！
			helper(node.left, height+1, rst);
		}
		return rst;
	}
	
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		List<Integer> rst = rightSideView(n1);
		for (int e: rst) {
			System.out.println(e);
		}
	}
	
	private TreeNode getRightMostChild(TreeNode node) {
		if (node == null) {
			return null;
		}
		if (node.right != null) {
			return node.right;
		} 
		return node.left;		
	}
}

