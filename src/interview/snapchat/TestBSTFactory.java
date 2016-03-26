package interview.snapchat;

//This is the text editor interface. 
//Anything you type or change here will be seen by the other person in real time.
//1. 根据preorder， inorder construct binary tree.要求 explain + coding + compile + test case.
//2. Follow up. detect invalid input. invalid包括，node数和他们的值不一样，更重要的是两个order的顺序可能不对等所有invalid。如果发现invalid input 返回整棵树为null

import java.util.*;

public class TestBSTFactory {
	public static void main(String[] args) {
	    int[][] in = {{1,2,3},{1,2,3,4,5,6},{1,2,3,4}};
	    int[][][] pre ={{{1,3,2}, {1,2,3}, {2,1,3},{2,3,1}, {3,1,2}, {3,2,1}}, //invalid, valid, valid, invalid, valid, valid.
		                {{3,2,1,5,4,6}, {3,2,1,5,6,4}}, // valid, invalid
		                {{1,3,4,2}, {3,2,1}, {4,3,2,0}}}; // all invalid			
		for (int i = 0; i < pre.length; ++i) {
			int[] inorder = in[i];
			for (int j = 0; j < pre[i].length; ++j) {					
				int[] preorder = pre[i][j];
				printInputArrays(preorder, inorder);
				System.out.println(preorderTraversal(BSTFactory.buildTreeWithValidCheck(preorder, inorder)));
			}
		}
	}
 
 
	private static List<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		// null or leaf
		if (root == null) return result;
		result.add(root.val);
		result.addAll(preorderTraversal(root.left));
		result.addAll(preorderTraversal(root.right));
		return result;
	}
	
	
	private static void printInputArrays(int[] pre, int[] in) {
		System.out.print("inorder = [");
		for (int e: in) System.out.print(e + ",");
		System.out.print("],\tpreorder = [");
		for (int e: pre) System.out.print(e + ",");
		System.out.println("]");
	}
 
}

class BSTFactory {
 /**
  * Construct a binary search tree by preorder and inorder traversal.
  * Currently it assumes no duplicate values in this BST.
  */ 
 public static TreeNode buildTreeWithValidCheck(int[] preorder, int[] inorder) {
	    Map<Integer, Integer> inMap = generateInMapWithValidCheck(preorder, inorder);
	    return (inMap == null) ? null : buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
	}
	
	
	private static Map<Integer, Integer> generateInMapWithValidCheck(int[] preorder, int[] inorder) {
		if (inorder == null || preorder == null || inorder.length != preorder.length) return null;
		
		int n = inorder.length, preVal = Integer.MIN_VALUE, inVal = Integer.MIN_VALUE, xor = 0;
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> inMap = new HashMap<>();
		
		for (int i = 0; i < n; inVal = inorder[i], preVal = preorder[i++]) {
			// Check inorder validness
			if (inorder[i] <= inVal) return null;  
			
			// Check preorder validness
			if (preorder[i] < preVal) return null; 
			for (; !stack.isEmpty() && stack.peek() < preorder[i]; stack.pop()) preVal = stack.peek(); 
			stack.push(preorder[i]);
			
			// Prepare for checking bijection between two order's content
			xor ^= (inorder[i] ^ preorder[i]);
			
			// Cache inorder data sequence
			inMap.put(inorder[i], i);
		}
		return (xor == 0) ? null : inMap;
	}
 
 private static TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
     if (preStart > preEnd || inStart > inEnd) return null;
     
     TreeNode root = new TreeNode(preorder[preStart]);
     int inRootIdx = inMap.get(root.val), prePivot = inRootIdx - inStart + preStart;
     
     root.left = buildTreeHelper(preorder, preStart + 1, prePivot, inorder, inStart, inRootIdx - 1, inMap);
     root.left = buildTreeHelper(preorder, prePivot, preEnd, inorder, inRootIdx + 1, inEnd, inMap);
     
     return root;
 }
 
}

class TreeNode {
 int val;
 TreeNode left, right;
 
 TreeNode(int v) { val = v; }
}
