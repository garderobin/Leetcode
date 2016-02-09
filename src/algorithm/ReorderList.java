package algorithm;

import dataStructure.ListNode;

public class ReorderList {
	public static ListNode reorderList(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) { return head; }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev, after, cur, slow = dummy, fast = dummy, last;
        
        // Reverse the right half part or the list
        for (; fast.next != null && fast.next.next != null; slow = slow.next, fast = fast.next.next) {}
        for (prev = slow.next, cur = prev.next; cur != null; ) {
        	after = cur.next;
        	cur.next = prev;
        	//prev.next = null; //这一句至关重要！反序的时候必须要把原序取消，否则会loop!        
        	prev = cur;
        	cur = after;
        }
        
        slow.next.next = null; //解决右半部分最后两个元素自循环！！
        slow.next = null;  //切断左右联系！！
        
        // Link the left part and right part one by one
        for (cur = head; cur != null; cur = slow, prev = fast) {
        	slow = cur.next; 
        	fast = prev.next; 
        	cur.next = prev; 
        	if (slow == null && prev.next != null) {  //这样的处理必不可少，因为奇偶对最后一个元素的连接不同！
        		prev.next.next = null; 
        	} else {
        		prev.next = slow;
        	}
        }
        return head;
    }
	
	public static void main(String[] args) {
		int[] list = {1,2,3};
		ListNode head = new ListNode(list);
        head = reorderList(head);
		String s = head.getListString();
		System.out.println(s);
	}
}
