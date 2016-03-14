package algorithm;

import dataStructure.TreeNode;

public class RecoverBST {
	
	/**
	 * Morris Traversal: O(N) time, O(1) space.
	 */
	public void recoverTree(TreeNode root) {
        TreeNode pre = null, cur = null, first = null, second = null;

        while (root!=null) {
            if (root.left != null) {
                // connect threading for root
                cur = root.left;
                while (cur.right != null && cur.right != root)
                    cur = cur.right;
                // the threading already exists
                if (cur.right != null) {
                    if (pre != null && pre.val > root.val) {
                    	if (first == null) first = pre;
                    	second = root; 
                    }
                    pre = root;
                    root = root.right;
                    
                    cur.right = null;
                } else {
                    // construct the threading
                    cur.right = root;
                    root = root.left;
                }
            } else {
                if (pre != null && pre.val > root.val){
                	if (first == null) first = pre;
                	second = root; 
                }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values;
        if(first != null && second != null){
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }
}
