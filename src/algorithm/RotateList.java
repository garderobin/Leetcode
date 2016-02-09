package algorithm;

import dataStructure.ListNode;

public class RotateList {
	public static ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}
        ListNode dummy = new ListNode(0); 
        dummy.next = head;
        ListNode slow = dummy, fast = dummy; // 必须先指定完dummy.next再定义slow和fast        
        int i, n;        
        
        for (n = 0; fast.next != null; n++) {	// 先找list长度， 这也可以顺便省略fast领先slow的操作
        	fast = fast.next;
        }
        
        for (i = 0; i < n - k % n; i++) { //慢针走指定步数，这里必须是k % n而不是k 因为可循环，所以k允许非常大的值
        	slow = slow.next;
        }

        fast.next = head;
        dummy.next = slow.next;
        slow.next = null;
        return dummy.next;
    }
	
	public static void main(String args[]) {
		ListNode head = new ListNode(1),
				n2 = new ListNode(2);
		head.next = n2;
		head = rotateRight(head, 1);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		
	}
	
}
