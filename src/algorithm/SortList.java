package algorithm;

import java.util.Arrays;

import dataStructure.ListNode;

public class SortList {
	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null) { return head; }
		if (head.next.next == null) {
			if (head.val <= head.next.val) { return head; }
			ListNode root = head.next;
			head.next = null;
			root.next = head;
			return root;
		}
		// Get half of list.
		ListNode slow = head, fast = head, left = null, right = null;
		for (; fast.next != null && fast.next.next != null; slow = slow.next, fast = fast.next.next) {}
		// Merge Sort.
		right = sortList(slow.next);
		slow.next = null;
		left = sortList(head);
		return mergeTwoLists(left, right);
    }

	private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {		
		if (l1 == null) { return l2; }
		if (l2 == null) { return l1; }
        ListNode dummy = new ListNode(0), cur = dummy;
        for (;l1 != null && l2 != null; cur = cur.next) {
        	if (l1.val < l2.val) {
        		cur.next = l1;
        		l1 = l1.next;
        	} else {
        		cur.next = l2;
        		l2 = l2.next;
        	}
        }
        
        if (l1 == null && l2 != null) {
        	cur.next = l2;
        } else if (l1 != null && l2 == null) {
        	cur.next = l1;
        }
        
        return dummy.next;
    }
	


	
	public static void main(String[] args) {
		int[] arr = {3,2,4};
		ListNode head = new ListNode(arr);
		head = sortList(head);
		System.out.println(head.getListString());
	}
}
