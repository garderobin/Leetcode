package algorithm;

public class InvertBinaryTree {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
        	return root;
        }
        TreeNode temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
        
    }
}
