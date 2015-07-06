package algorithm;

//Not correct!
public class BalancedBinaryTree {
	class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}	
	
//	public boolean isBalanced(TreeNode root) {
//		if (root == null) {
//            return true;
//        }
//        if (root.left == null && root.right == null) {
//			return true;
//		} else if (root.left != null && root.right == null) {
//			return (root.left.left == null && root.left.right == null);
//		} else if (root.left == null && root.right != null) {
//			return (root.right.right == null && root.right.left == null);
//		} 
//		return (isBalanced(root.left) && isBalanced(root.right));
//        
//    }
	
	public boolean isBalanced(TreeNode root) {
		return maxDepth(root) != -1;
	}

	private int maxDepth(TreeNode root) {
		if (root == null) {
			return 0; // not -1, still balanced
		}
		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}
	
}
