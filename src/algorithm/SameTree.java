package algorithm;

public class SameTree {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
        	return true;
        } else if (p == null || q == null || p.val != q.val) {
        	return false;
        } else return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
