package interview.snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import dataStructure.TreeNode;

/*
 * Follow-up: 时间优化
 * Follow up: detect invalid input. invalid包括，node数和他们的值不一样，更重要的是两个order的顺序可能不对等所有invalid。如果发现invalid input 返回整棵树为null
 */
public class ConstructBSTByInorderAndPreorderTraversal {
	/*
	 * No valid check
	 * One improvement: remember to use HashMap to cache the inorder[] position. This can reduce your solution from 20ms to 5ms.
	 */
	public static TreeNode buildTree(int[] preorder, int[] inorder) {
	    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
	    for(int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
	    
	    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
	}

	private static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
	    if(preStart > preEnd || inStart > inEnd) return null;

	    TreeNode root = new TreeNode(preorder[preStart]);
	    int inRoot = inMap.get(root.val), numsLeft = inRoot - inStart;

	    root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
	    root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

	    return root;
	}
	/*
	 * With valid check
	 * One improvement: remember to use HashMap to cache the inorder[] position. This can reduce your solution from 20ms to 5ms.
	 */
	public static TreeNode buildTreeWithValidCheck(int[] preorder, int[] inorder) {
	    Map<Integer, Integer> inMap = generateInMapWithValidCheck(preorder, inorder);
	    return (inMap == null) ? null : buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
	}
	
	
	private static Map<Integer, Integer> generateInMapWithValidCheck(int[] preorder, int[] inorder) {
		if (inorder == null || preorder == null || inorder.length != preorder.length) return null;
		
		int n = inorder.length, preVal = Integer.MIN_VALUE, inVal = Integer.MIN_VALUE, xor = 0;
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> inMap = new HashMap<>();
		
		for (int i = 0; i < n; inVal = inorder[i++]) {
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
		return (xor == 0) ? inMap : null;
	}
	
	
	@SuppressWarnings("unused")
	private static boolean isValidPreorderAndInorder(int[] preorder, int[] inorder) {
		if (inorder == null || preorder == null || inorder.length != preorder.length) return false;
		int n = inorder.length, preVal = Integer.MIN_VALUE, inVal = Integer.MIN_VALUE, xor = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < n; inVal = inorder[i++]) {
			if (inorder[i] <= inVal) return false;  // check inorder validness
			if (preorder[i] < preVal) return false; // check preorder validness
			for (; !stack.isEmpty() && stack.peek() < preorder[i]; stack.pop()) preVal = stack.peek(); 
			stack.push(preorder[i]);
			xor ^= (inorder[i] ^ preorder[i]);
		}
		return xor == 0;
	}
	
	boolean canRepresentBST(int pre[]) {
        // Create an empty stack
        Stack<Integer> s = new Stack<Integer>();
 
        // Initialize current root as minimum possible
        // value
        int root = Integer.MIN_VALUE;
 
        // Traverse given array
        for (int i = 0; i < pre.length; i++) {
            // If we find a node who is on right side
            // and smaller than root, return false
            if (pre[i] < root) {
                return false;
            }
 
            // If pre[i] is in right subtree of stack top,
            // Keep removing items smaller than pre[i]
            // and make the last removed item as new
            // root.
            while (!s.empty() && s.peek() < pre[i]) {
                root = s.peek();
                s.pop();
            }
 
            // At this point either stack is empty or
            // pre[i] is smaller than root, push pre[i]
            s.push(pre[i]);
        }
        return true;
    }
	
	public static List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
       // null or leaf
       if (root == null) {
           return result;
       }
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
	
	
	public static void main(String[] args) {
		int[][] in = { {1,2,3},{1,2,3,4,5,6},{1,2,3,4} };
		int[][][] pre ={ {{1,3,2}, {1,2,3}, {2,1,3},{2,3,1}, {3,1,2}, {3,2,1}}, //invalid, valid, valid, invalid, valid, valid.
		{{3,2,1,5,4,6}, {3,2,1,5,6,4}}, // valid, invalid
		{{1,3,4,2}, {3,2,1}, {4,3,2,0}} }; // all invalid
		
		for (int i = 0; i < pre.length; ++i) {
			int[] inorder = in[i];
			for (int j = 0; j < pre[i].length; ++j) {
				int[] preorder = pre[i][j];
				printInputArrays(preorder, inorder);
				System.out.println(preorderTraversal(buildTreeWithValidCheck(preorder, inorder)));
			}
		}
	}
}
