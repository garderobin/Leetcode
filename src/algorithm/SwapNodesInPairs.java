package algorithm;

import dataStructure.ListNode;

public class SwapNodesInPairs {
	public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) { return head; }
        ListNode dummy = new ListNode(0), prev, left, right, after;
        dummy.next = head;
        for (prev = dummy, left = head; left != null && left.next != null; prev = left, left = after) { 
        	right = left.next;
        	after = right.next;
        	prev.next = right;
        	right.next = left;
        	left.next = after;
        }
        return dummy.next;
    }
}
