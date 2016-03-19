package interview.factset;

import java.util.ArrayDeque;
import java.util.Deque;

import dataStructure.TreeNode;

public class BinaryTreeToDoublyLinkedList {
	
	/*
	 * Post-order
	 */
	DoublyLinkedNode bst2dlistIterative(TreeNode root) {
		if (root == null) { return null; }
		Deque<TreeNode> stack = new ArrayDeque<>();
		DoublyLinkedNode dRoot = null;
		TreeNode p = root;
		
		while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            if (dRoot == null) { dRoot = new DoublyLinkedNode(root.val); }
	            else { // add new node to the front of doubly linked list
	            	DoublyLinkedNode dCur = new DoublyLinkedNode(root.val);
	            	dCur.post = dRoot;
	            	dRoot.prev = dCur;
	            }
	            p = p.right;             // Reverse the process of preorder
	        } else {
	            TreeNode node = stack.pop();
	            p = node.left;           // Reverse the process of preorder
	        }
	    }
		return dRoot;
	}
	
	/*
	 * Post-order
	 */
	DoublyLinkedNode bst2dlistRecursive(TreeNode root) {
		if (root == null) { return null; }
		DoublyLinkedNode dRoot = new DoublyLinkedNode(root.val), dLeft = null, dRight = null;
		if (root.left != null) {
			dLeft = bst2dlistRecursive(root.left);
			dLeft.post = dRoot;
			dRoot.prev = dLeft;
		}
		if (root.right != null) {
			dRight = bst2dlistRecursive(root.left);
			dRoot.post = dRight;
			dRight.prev = dRoot;
					
		}
		
		return (dLeft == null) ? dRoot : dLeft;
	}

}

class DoublyLinkedNode {
	int val;
	DoublyLinkedNode prev;
	DoublyLinkedNode post;
	
	public DoublyLinkedNode() {}
	
	public DoublyLinkedNode(int value) {
		this.val = value;
	}
	public DoublyLinkedNode(int value, DoublyLinkedNode prev) {
		this.val = value;
		this.prev = prev; 
	}
}
