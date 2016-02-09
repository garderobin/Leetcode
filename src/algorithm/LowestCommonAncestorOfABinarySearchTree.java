package algorithm;

import dataStructure.TreeNode;

public class LowestCommonAncestorOfABinarySearchTree {
	/**
	 * 从根开始直到找到一个需要对两个数进行分叉的地方，
	 * 如果始终没有出现分差，取较小的一个作为祖先即可。
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) { return null; }
        if (root.val > Math.max(p.val, q.val)) { //这里没有考虑相等
        	return lowestCommonAncestor(root.right, p, q);
        } 
        if (root.val < Math.min(p.val, q.val)) {
        	return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
}
