package algorithm;

import dataStructure.ListNode;

public class InsertionSortList {
	
	/**
	 * 对列表的操作永远要谨记：任何时候都要考虑一个段落的开头和结尾，
	 * 结尾非常容易出现自循环！切记。
	 * @param head
	 * @return
	 */
	public static ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) { return head; }
		ListNode dummy = new ListNode(0), after, prev, target, tail;
		dummy.next = head;
        for (target = head.next; target != null; target = after) {
        	after = target.next;
        	for (prev = dummy; prev.next.val < target.val && prev.next != target; prev = prev.next) {}
        	if (!(prev.next == target)) {
        		for (tail = prev; tail.next != target; tail = tail.next) {}
        		prev = insertNodeToList(prev, tail, target);
        	}
        	
        	
        }
		return dummy.next;
    }
	
	/**
	 * 一定要注意结尾处是否避免了loop指向
	 * @param head
	 * @param target
	 * @return
	 */
	private static ListNode insertNodeToList(ListNode head, ListNode tail, ListNode target) {
		ListNode headAfter = head.next, targetAfter = target.next;
		head.next = target;
		target.next = headAfter;
		tail.next = targetAfter;
		return head;
	}
	
	public static void main(String[] args) {
		int[] arr = {3,7,2,4,1,5};
		ListNode head = new ListNode(arr);
		head = insertionSortList(head);
		System.out.println(head.getListString());
	}
}
