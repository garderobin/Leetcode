package algorithm;

import dataStructure.TreeNode;

public class SumRootToLeafNumbers {
	public static int sumNumbers(TreeNode root) {
        return sub(root, 0);
	}
	
	private static int sub(TreeNode root, int sum) {
		if (root == null) { return 0; } 
		sum = 10 * sum + root.val;
		if (root.left == null && root.right == null) {						
			return sum;
		} 
		int left = sub(root.left, sum);
		int right = sub(root.right, sum);
		return left + right;
//		return sub(root.left, sum) + sub(root.right, sum);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(0);
		root.left = left;
		System.out.println(sumNumbers(root));
	}
}
