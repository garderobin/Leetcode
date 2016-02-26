package algorithm;

import dataStructure.TreeNode;

public class LargestBSTSubtree {
	
	class Result {
        int res;
        int min;
        int max;
        public Result(int res, int min, int max) {
            this.res = res;
            this.min = min;
            this.max = max;
        }
    }

    public int largestBSTSubtreeDiscussion(TreeNode root) {
        Result res = BSTSubstree(root);
        return Math.abs(res.res);
    }

    private Result BSTSubstree(TreeNode root) {
        if (root == null) return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        Result left = BSTSubstree(root.left);
        Result right = BSTSubstree(root.right);
        if (left.res < 0 || right.res < 0 || root.val < left.max || root.val > right.min) {
            return new Result(Math.max(Math.abs(left.res), Math.abs(right.res)) * -1, 0, 0);
        } else {
            return new Result(left.res + right.res + 1, Math.min(root.val, left.min), Math.max(root.val, right.max));
        }
    }
	
	
	public static int largestBSTSubtree(TreeNode root) {
        return Math.abs(helper(root));
    }
    /* return the node number if the subtree is BST or its largestBSTSubtree muplied by (-1) */
    private static int helper(TreeNode root) {
        if (root == null) { return 0; }
        else if (root.left == null && root.right == null) { return 1; }
        else if (root.left == null) { 
            int r = helper(root.right);
            return (root.val < root.right.val && r > 0) ? r+1 : 0-Math.abs(r);
        } else if (root.right == null) { 
            int l = helper(root.left);
            return (root.val > root.left.val && l > 0) ? l+1 : 0-Math.abs(l);
        } else {
            int r = helper(root.right), l = helper(root.left), v = root.val, vl = root.left.val, vr = root.right.val;
            return (v > vl && l > 0 && v < vr && r > 0) ? r+l+1 : 0-Math.max(Math.abs(r), Math.abs(l));
        }
    }
    
    public static void main(String[] args) {
    	TreeNode n3 = new TreeNode(3);
    	TreeNode n2 = new TreeNode(2);
    	TreeNode n4 = new TreeNode(4);
    	TreeNode n1 = new TreeNode(1);
    	n3.left = n2;
    	n3.right = n4;
    	n4.left = n1;
    	System.out.println(largestBSTSubtree(n3));
    }
}
