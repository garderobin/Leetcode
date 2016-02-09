package algorithm;

import dataStructure.TreeNode;
public class KthSmallestElementInABST {
	public int kthSmallest(TreeNode root, int k) {
        int countLeft = countNodes(root.left);
        if (k == countLeft + 1) { return root.val; }
        if (k < countLeft + 1) {
        	return kthSmallest(root.left, k);
        } 
        return kthSmallest(root.right, k - 1 - countLeft);
    }
	
	private int countNodes(TreeNode root) {
		if (root == null) { return 0; }
		return countNodes(root.left) + countNodes(root.right) + 1;
	}
}
