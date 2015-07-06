package algorithm;

public class BinaryTreeMaximumPathSum {
	class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	public int maxPathSum(TreeNode root) {
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
