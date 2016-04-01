package algorithm;


public class SymmetricTree {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		if (root.left == null && root.right == null) {
			return true;
		}
		if ((root.left == null && root.right != null) || (root.right == null && root.left != null)) {
			return false;
		}		
		
		return (myIsSymmetric(root.left, root.right));
        
    }
	
	public boolean myIsSymmetric(TreeNode l, TreeNode r) {
		if (l == null && r == null) {
			return true;
		} else if (l == null || r == null || l.val != r.val) {
			return false;
		}
				
		return (myIsSymmetric(l.left, r.right) && myIsSymmetric(l.right, r.left));
	}
}
