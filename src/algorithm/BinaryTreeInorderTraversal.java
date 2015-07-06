package algorithm;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

public class BinaryTreeInorderTraversal {
	
	public static List<Integer> preorderTraversal(TreeNode root) {
        return myPreorderTraversal(root, new ArrayList<Integer>());
    }
	
	private static List<Integer> myPreorderTraversal(TreeNode root, List<Integer> rst) {		
        if (root == null) {
        	return rst;
        }
        rst.add(root.val);
        if (root.left == null && root.right == null) {
        	return rst;
        } 
        if (root.left != null) {
        	myPreorderTraversal(root.left, rst);
        }
        if (root.right != null) {
        	myPreorderTraversal(root.right, rst);
        }
        
        return rst;
	}
	
	public static List<Integer> inorderTraversal(TreeNode root) {
		return myInorderTraversal(root, new ArrayList<Integer>());
    }

	private static List<Integer> myInorderTraversal(TreeNode root, List<Integer> rst) {
		if (root == null) {
        	return rst;
        }
		
		if (root.left != null) {
			myInorderTraversal(root.left, rst);
		}
		rst.add(root.val);
		if (root.right != null) {
			myInorderTraversal(root.right, rst);
		}
		
		return rst;
	}
	
	public static List<Integer> postorderTraversal(TreeNode root) {
		return myPostorderTraversal(root, new ArrayList<Integer>());
    }

	private static List<Integer> myPostorderTraversal(TreeNode root, List<Integer> rst) {
		if (root == null) {
        	return rst;
        }
		
		if (root.left != null) {
			myPostorderTraversal(root.left, rst);
		}
		
		if (root.right != null) {
			myPostorderTraversal(root.right, rst);
		}
		
		rst.add(root.val);
		
		return rst;
	}
}
