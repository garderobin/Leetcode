package algorithm;

import dataStructure.ListNode;

public class ReverseSingleLinkedList {

	ListNode prev = null;
	public ListNode reverseList(ListNode cur) {	        
		if (cur == null) {
			return prev;
		}
		ListNode l1 = cur.next;
	    cur.next = prev;
	    prev = cur;
	    if (l1 == null) {
	    	prev = cur;
	        return cur;
	    }
	    reverseList(l1);
	    return prev;
	        
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        int p = 0; int v1 = 0; int v2 = 0;
        
        while (n1!=null || n2!=null) {
            v1 = (n1==null) ? 0 : n1.val;
            v2 = (n2==null) ? 0 : n2.val;
            // if (n1 != null) {
            //     v1 = n1.val;
            // } else {
            //     v1 = 0;
            // }
            // if (n2 != null) {
            //     v2 = n2.val;
            // } else {
            //     v2 = 0;
            // }
            int val = v1 + v2 + p;
            if (val > 9) {
                p = 1;
                val = val % 10;
            } else {
                p = 0;
            }
            if (n1 != null) n1 = n1.next;
            if (n2 != null) n2 = n2.next;
            prev.next = new ListNode(val);
            prev = prev.next;
        }
        
        if (p > 0) {
            prev.next = new ListNode(1);
        } 
        
        return dummy.next;
    }
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        if (m == n) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        ListNode beforeM = null;
        ListNode oldM = null;
        int p = 1;
        while(cur != null && p < n+1) {
            if (p < m-1) {
            	p++;
            	cur = cur.next;
                continue;
            } else if (p == m-1) {
                beforeM = cur; // New(m-1)
                cur = cur.next;
                p++;
                continue;
            } else if (p == m) {
                oldM = cur; // New(n)   
            }
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
            p++;
        }
        // Connect the reversed nth and (n+1)th, (m-1)th and mth elements
        // Now, p = n+1, prev = Old(n) = New(m), cur = temp = E(n+1) = New(n+1)
        // First, connect nth and (n+1)th, which shoud be Old(m) and Old(n+1) = New(n+1)
        if (oldM != null) {
            oldM.next = cur;
        }
        
        // Then, connect (m-1)th and mth, which should be Old(m-1) and New(m) = Old(n) 
        if (beforeM != null) {
            beforeM.next = prev;
        }
        
        // If the head changed, new list shoud start from New(m) whichi shoud be Old(n)
        if (m == 1) {
        	head = prev;
        }
        return head;
        
    }
	
	public ListNode reverseBetweenFinal(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        if (m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode prev = null;
        ListNode cur = head;
        ListNode after = null;
        dummy.next = head;
        ListNode before = dummy;
        
        for (int i = 1; i < m; i++) {
            before = before.next;
        }
        
        prev = before.next;
        cur = prev.next;
        
        for (int j = 0; j < n-m; j++) {
            after = cur.next;
            cur.next = prev;
            prev = cur;
            cur = after;
        }
        
        before.next.next = cur;
        before.next = prev;
        
        return dummy.next;
        
    }
	
	public ListNode detectCycle(ListNode head) {
        if (head == null || head.next==null) {
            return null;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        } 
        
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
            System.out.println("head = " + head.val + "; slow.next = " + slow.next.val);
        }
        return head;
    }
	
	public static ListNode reverseKGroup(ListNode head, int k) {
		if (head == null) {
            return null;
        }
        if (k < 2) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode prev = null;
        ListNode cur = head;
        ListNode after = null;
        ListNode end = head;
//        ListNode end2 = end.next;
        dummy.next = head;
        ListNode before = dummy;        

        int i = 1;
        int p = 1;
        boolean canReverse = true;
        
        for (p = 1; p < k && canReverse; p++) {
        	end = end.next;
        	if (end == null) {
        		canReverse = false;
        	}
        }
        
        while (canReverse) {
        	prev = before.next;
            cur = prev.next;
        	for (i = 1; i < k; i++) {
        		if (end != null) {
                	end = end.next;                	
                } 
                if (end == null) {
                	canReverse = false;
                } else {
//                	end2 = end.next;
                }
        		after = cur.next;
                cur.next = prev;
                prev = cur;
                cur = after;                
        	}
//        	if (canReverse) {
//        		end = end2;
//            	if (end == null) {
//            		canReverse = false;
//            	}
//        	}
        	
        	before.next.next = cur;
            before.next = prev;    
        }
        
        return dummy.next;
    }

}
