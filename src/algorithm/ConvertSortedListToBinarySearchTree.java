package algorithm;

import dataStructure.ListNode;
import dataStructure.TreeNode;

public class ConvertSortedListToBinarySearchTree {
	
	
	public TreeNode sortedListToBST(ListNode head) {
	    if(head==null)
	        return null;
	    ListNode slow = head;
	    ListNode fast = head;
	    ListNode temp=null;

	    //find the mid node
	    while(fast.next!=null && fast.next.next!=null){
	        fast = fast.next.next;
	        temp = slow;
	        slow = slow.next;
	    }

	    if(temp!=null)
	        temp.next = null; //break the link
	    else
	        head = null;

	    TreeNode root = new TreeNode(slow.val);
	    root.left = sortedListToBST(head);
	    root.right = sortedListToBST(slow.next);
	    return root;
	}
	
//	private static TreeNode unsortedListToBST(ListNode head) {
//		if (head == null) { return null; }
//        TreeNode root = new TreeNode(head.val);
//        for (head = head.next; head != null; root = insert(root, new TreeNode(head.val)), head = head.next);
//        return root;
//    }
	
//	private static TreeNode insert(TreeNode root, TreeNode node) {
//		if (root == null) { return node; }
//		if (node.val > root.val) {
//			root.right = insert(root.right, node);			
//		} else {
//			root.left = insert(root.left, node);
//		}
//		root = balance(root);
//		return root;
//	}
	
//	private static TreeNode balance(TreeNode root) {
//		// Left-left
//		
//		// Right-right
//		
//		// Left-right
//		
//		// Right-left
//		return null;
//	}
//
//	private static TreeNode rotateLeft(TreeNode root, TreeNode pivot) {
//		return pivot;
//	}
//	
//	private static TreeNode rotateRight(TreeNode root, TreeNode pivot) {
//		return pivot;
//	}
//	
}
