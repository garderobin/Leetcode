package algorithm;

public class MinDepthOfBinaryTree {
	class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}		
        return findMinDepth(root, 1);
    }
	
	public int findMinDepth(TreeNode root, int h) {
		if (root.left == null && root.right == null) {
			return h;
		} else if (root.left != null && root.right == null) {
			return findMinDepth(root.left, h+1);
		} else if (root.left == null && root.right != null) {
			return findMinDepth(root.right, h+1);
		} else  {
			return Math.min(findMinDepth(root.left, h+1), findMinDepth(root.right, h+1));
		}
        
	}
}
