package algorithm;

import dataStructure.ListNode;

public class PalindromeLinkedList {
	public boolean isPalindrome(ListNode head) {
		if (head == null) {
			return true;
		}
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy, prev, cur, next = dummy, last; //存疑问
        while (true) {
        	if (fast.next == null) { //偶回文
        		prev = slow.next;
        		last = fast;
        		break;
        	} else if (fast.next.next == null) { //奇回文
        		prev = slow.next.next;
        		last = fast.next;
        		break;
        	} else {
        		slow = slow.next;
            	fast = fast.next.next;            	
        	}          	

        }
        
     // Revert the half-right part        		
		for (cur = prev.next; cur != null; prev = cur, cur = next) {
			next = cur.next;
			cur.next = prev;
		} 
		
		// Compare the left and reverted right part
		slow.next = null;
		cur = head;
		for (cur = head; cur != null; cur = cur.next, last = last.next) {
			if (cur.val != last.val) {
				return false;
			}
		}
        
        return true;
    }	
	
}

