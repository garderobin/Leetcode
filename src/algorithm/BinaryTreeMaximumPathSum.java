package algorithm;

import dataStructure.TreeNode;

public class BinaryTreeMaximumPathSum {
	
	public static int maxPathSum(TreeNode root) {
		return sub(root, Integer.MIN_VALUE, 0);
	}
	
	private static int sub(TreeNode root, int maxPath, int path) {
		if (root == null) { return maxPath; }
		int curPath = root.val + single(root.left, 0, 0) + single(root.right, 0, 0);
		int leftPath = sub(root.left, Integer.MIN_VALUE, 0);
		int rightPath = sub(root.right, Integer.MIN_VALUE, 0);
		curPath = Math.max(curPath, Math.max(leftPath, rightPath));
		return Math.max(curPath, maxPath);
		
	}
	
	private static int single(TreeNode root, int maxSingle, int single) {
		if (root == null) { return maxSingle; }
		single += root.val;
		if (root.left == null && root.right == null) {
			return Math.max(maxSingle, single);
		}
		return Math.max(single(root.left, maxSingle, single), single(root.right, maxSingle, single));
	}
	
	public int maxPathSumV0(TreeNode root) {
        return helper(root, root.val);
    }
    
    private int helper(TreeNode root, int sum) {
		if (root == null) {
			return sum;
		}
		int sl = (root.left == null) ? 0 : helper(root.left, root.left.val);
		int sr = (root.right == null) ? 0 : helper(root.right, root.right.val);		
		if (sl + sr + root.val <= 0 && root.val <= 0 && sr + root.val <= 0 && sl + root.val <= 0) {
			return sum - root.val;
		}
//		sum += root.val;
		if (sl <= 0 && sr <= 0) {
			return sum;
		}
		if (sr > 0) {
			sum += sr;
		}
		if (sl > 0) {
			sum += sl;
		}
		 
		return sum;
	}
}
