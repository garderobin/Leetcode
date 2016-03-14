package interview.google;

import dataStructure.TreeNode;

public class BinaryTreeLongestConsecutiveSequence {
	
	/**
	 * 遇到这种路径类型的问题，必须找一个参数来记录，以与所求的最大/最小值区别开。
	 * @param root
	 * @return
	 */
	public static int longestConsecutive(TreeNode root) {
		if (root == null) { return 0; }
		return Math.max(helper(root.left, root.val+1, 0, 1), helper(root.right, root.val+1, 1, 1)); 
    }
	
	public static int helper(TreeNode root, int expect, int consecLen, int curMax) {
		if (consecLen > curMax) { curMax = consecLen; }
		if (root == null) { return curMax; }
		consecLen = (expect == root.val) ? consecLen+1 : 1; 
		return Math.max(helper(root.left, ++expect, ++consecLen, curMax), helper(root.right, expect, consecLen, curMax));
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		root.left = n2;
		System.out.println(longestConsecutive(root));
	}
}
