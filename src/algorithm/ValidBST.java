package algorithm;

/**
 * 一定要注意相等的边界条件
 * @author jasmineliu
 *
 */
public class ValidBST {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	class StatNode {
		TreeNode node;
		int min;
		int max;
		StatNode(TreeNode node, int min, int max) {
			this.node = node;
			this.min = min;
			this.max = max;
		}
	}
	
	public boolean isValidBST(TreeNode root) {
		//return false;
        //return myIsValidBST(new StatNode(root, root.val, root.val));
		return myIsValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
//		if (root == null) {
//			return true;
//		}
//		
//		if (root.left == null && root.right == null) {
//			return true;
//		}
//		if (root.left == null) {
//			return root.right.val >= root.val && isValidBST(root.right);
//		}
//		if (root.right == null) {
//			return root.left.val <= root.val && isValidBST(root.left);
//		}		
//		return root.right.val >= root.val && root.left.val <= root.val && isValidBST(root.left) && isValidBST(root.right);
    }

	private boolean myIsValidBST(TreeNode root, int allowMin, int allowMax) {
		if (root == null) {
			return true;
		}
		if (root.val <= allowMin || root.val >= allowMax ) {
			return false;
		}
		
		if (root.left == null && root.right == null) {
			return true;
		}
		if (root.left == null) {
			return root.right.val > root.val && myIsValidBST(root.right, root.val, allowMax);
		}
		if (root.right == null) {
			return root.left.val < root.val && myIsValidBST(root.left, allowMin, root.val);
		}		
		return root.right.val > root.val && root.left.val < root.val && myIsValidBST(root.right, root.val, allowMax) && myIsValidBST(root.left, allowMin, root.val);
	}

//	private StatNode getStatNode(TreeNode root) {
//		int min, max;
//		if (root == null) {
//			return null;
//		}
//		if (root.left == null) {
//			min = Math.min(root.left.val, root.val);
//		}
//		StatNode sl = getStatNode(root.left);
//		StatNode sr = getStatNode(root.right);
//		return root;
//	}
}
