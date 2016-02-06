package algorithm;

import dataStructure.TreeNode;

public class ConstructBSTByInorderAndPostorderTraversal {
	public static TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder == null || postorder == null || postorder.length == 0) {
			return null;
		}
        TreeNode root = myBuildTree(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
        
        return root;
    }
	
	private static TreeNode myBuildTree(int[] inorder, int[] postorder, int postIndex, int start, int end) {
		if (start > end) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[postIndex]);
		int inorderIndex = findInorderIndex(inorder, start, end, postorder[postIndex]);
		root.right = myBuildTree(inorder, postorder, postIndex - 1, inorderIndex + 1, end);
		// int rightGap = end - inorderIndex - 1
		// int rightLen = end - inorderIndex
		root.left = myBuildTree(inorder, postorder, postIndex - end + inorderIndex - 1, start, inorderIndex - 1);
				
		return root;		
	}
	
	private static int findInorderIndex(int[] inorder, int start, int end, int key) {
		int i;
		for (i = start; i <= end; i++) {
			if (inorder[i] == key) {
				return i;
			}
		}
		return -1;	
	}
}
