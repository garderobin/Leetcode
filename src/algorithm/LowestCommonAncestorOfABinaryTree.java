package algorithm;

import dataStructure.TreeNode;

public class LowestCommonAncestorOfABinaryTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == null || q == null) { return null; }
        if (isAncestorOf(p, q)) { return p; }
        if (isAncestorOf(q, p)) { return q; }
        if (!isAncestorOf(root, p) || !isAncestorOf(root, q)) { return null; }
        return helper(root, p, q);
    }
	
	private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
		if (isAncestorOf(root.left, p) && isAncestorOf(root.left, q)) { return helper(root.left, p, q); }
		if (isAncestorOf(root.right, p) && isAncestorOf(root.right, q)) { return helper(root.right, p, q); }
//		if ((isAncestorOf(root.left, p) && isAncestorOf(root.right, q)) || (isAncestorOf(root.right, p) && isAncestorOf(root.left, q))) {
//			return root;
//		}
		return root;
	}
	
	private boolean isAncestorOf(TreeNode root, TreeNode target) {
		if (root == null) { return false; }
		if (root.val == target.val) { return true; }
		return isAncestorOf(root.left, target) || isAncestorOf(root.right, target);
	}
}
