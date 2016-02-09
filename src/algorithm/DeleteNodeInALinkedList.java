package algorithm;

import dataStructure.ListNode;

public class DeleteNodeInALinkedList {
	
	/**
	 * Change value will be OK.
	 * It is so tricky.
	 * @param node
	 */
	public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
