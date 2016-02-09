package algorithm;

import dataStructure.ListNode;

public class PartitionList {
	public static ListNode partition(ListNode head, int x) {
		if (head == null || head.next == null) { return head; }
	    ListNode dl = new ListNode(0), dr = new ListNode(0), left = dl, right = dr;
	    for (; head != null; head = head.next) {
			if (head.val < x) {
				left.next = head;					
				left = head;
			} else {
				right.next = head;
				right = head;
			}
		}
		left.next = dr.next;
		right.next = null; //这句绝对不能忘了，否则会造成死循环
		return dl.next; 
    }
	
	public static void main(String[] args) {
		int[] list = {1,4,3,2,5,2};
		ListNode head = new ListNode(list);
		head = partition(head, 3);
		System.out.println(head.getListString());
	}
}
