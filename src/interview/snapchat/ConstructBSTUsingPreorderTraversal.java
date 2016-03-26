package interview.snapchat;

import dataStructure.TreeNode;

public class ConstructBSTUsingPreorderTraversal {
	TreeNode preorder2bst(int[] pre) {
		return null;
	}
	
	TreeNode preorder2bstHelper(int[] pre, int index, int max, int min) {
		if (index >= pre.length) { return null; }
		TreeNode root = null;
		int key = pre[index];
		if (key <= max && key >= min) {
			
		} else {}
		return root;
	}
}
