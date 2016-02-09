package algorithm;

import java.util.Stack;

import dataStructure.TreeNode;

/**
 * 易错点：
 * 不能直接flatten(root.right);必须再声明一遍, 是为了备份，相当于swap的temp.
 * @author jasmineliu
 *
 */
public class FlattenBinaryTreeToLinkedList {
	private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        
//        //Only For Test
//        String s0 = (root == null) ? "null" : root.val + "";
//        String s1 = (right == null) ? "null" : right.val + "";
//        String s2 = (root == null || root.right == null) ? "null" : root.right.val + "";
//        System.out.println("root = " + s0  + ",\tright = " + s1 + ",\troot.right = " + s2);
        
        flatten(right);
    }
    
    /**
     * 
     * @param root
     */
    public void flattenBacktrack(TreeNode root) {
    	
    }
    
    /**
     *  Space: O(n)
     * @param root
     */
    public void flattenUsingStack(TreeNode root) {
    	if(root==null)return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root; 
        if (root.right!=null) {
        	stack.push(root.right);
        }
        if (root.left!=null) {
        	stack.push(root.left);
        }
        while (!stack.isEmpty()) {
        	TreeNode node = stack.pop();
        	if (node.right!=null){
        		stack.push(node.right);
        	}
        	if (node.left!=null) {
        		stack.push(node.left);
        	}
        	cur.right = node;
        	cur.left = null;
        	cur = node;
        }
    }
    
    /**
     * Time: O(n). Space: O(1).
     * @param root
     */
    public void flattenIterative(TreeNode root) {
    	if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        while (root != null) {
            if (root.left == null) {
                root = root.right;
                continue;
            }
            TreeNode left = root.left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = root.right;
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
    }
    
    private TreeNode prev = null;    
    /**
     * 通过
     * @param root
     */
    public void flattenPostTraversal(TreeNode root) {
        if (root == null) {
        	return;
        }            
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
    
    /**
     * 通过
     * @param root
     */
    public void flattenPreTraversal(TreeNode root) {
    	if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }
}
