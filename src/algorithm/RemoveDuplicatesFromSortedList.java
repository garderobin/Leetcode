package algorithm;

import java.util.HashSet;

import dataStructure.ListNode;

public class RemoveDuplicatesFromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
		HashSet<Integer> hs = new HashSet<Integer>();
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		
        while (head != null) {
        	if (hs.add(head.val)) {
        		pre = head;
        		head = head.next;
        	} else {
        		head = head.next;
        		pre.next = head;
        	}
        }
		return dummy.next;
    }
}
