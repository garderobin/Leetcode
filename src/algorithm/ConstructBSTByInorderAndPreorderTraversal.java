package algorithm;

import datastructure.TreeNode;

public class ConstructBSTByInorderAndPreorderTraversal {
	
	private static int findPosition(int[] arr, int start, int end, int key) {
		int i;
		for (i = start; i <= end; i++) {
			if (arr[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	//Wrong answer
		public static TreeNode buildTreeJiuzhang(int[] preorder, int[] inorder) {
			if (preorder == null || inorder == null || inorder.length != preorder.length || inorder.length == 0) {
				return null;
			}
			
			return myBuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
		}
		
	
	private static TreeNode myBuildTree(int[] preorder, int[] inorder, int pstart, int pend, int istart, int iend) {
		if (istart > iend || pstart > pend) {
			return null;
		} // Why not consider pstart and pend?
		
		TreeNode root = new TreeNode(preorder[pstart]);
		int pos = findPosition(inorder, istart, iend, preorder[pstart]);
		root.left = myBuildTree(preorder, inorder, istart, pos - 1, pstart + 1, pstart + pos - istart);
		root.right = myBuildTree(preorder, inorder, pos + 1, iend, pstart - istart + pos + 1, pend);
		
		return root;
	}

    private TreeNode buildTree(int[] preorder, int[] inorder, int preorderIndex, int start, int end) {
        if (start > end) return null;
        TreeNode node = new TreeNode(preorder[preorderIndex]);
        int inorderIndex = findInorderIndex(inorder, start, end, preorder[preorderIndex]);
        node.left = buildTree(preorder, inorder, preorderIndex + 1, start, inorderIndex - 1);
        node.right = buildTree(preorder, inorder, preorderIndex + inorderIndex - start + 1, inorderIndex + 1, end);
        return node;
    }    
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return buildTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private int findInorderIndex(int[] inorder, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == key) return i;
        }
        return -1;
    }
	

}
