package interview.wayfair;

import dataStructure.ListNode;

public class MergeTwoSortedListAndRemoveDuplicates {
	public ListNode mergeTwoListsAndRemoveDuplicates(ListNode l1, ListNode l2) {	
		ListNode dummy = new ListNode(0), cur = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val == l2.val) { // keep either side is OK. I choose to keep l1.
				cur.next = l2;
				l1 = l1.next;
				l2 = l2.next;
			} else if (l1.val < l2.val) {
        		cur.next = l1;
        		l1 = l1.next;
        	} else {
        		cur.next = l2;
        		l2 = l2.next;
        	}
        }
        
        if 		(l2 != null) { cur.next = l2; }
        else if (l1 != null) { cur.next = l1; }
        
        return dummy.next;
	}
	
}
