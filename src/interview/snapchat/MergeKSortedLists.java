package interview.snapchat;

import dataStructure.ListNode;

public class MergeKSortedLists {
	/**
	 * 我的想法，分治。
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) { return null; }
		if (lists.length == 1) { return lists[0]; }
        return divide(lists, 0, lists.length - 1);
    }
	
	private static ListNode divide(ListNode[] lists, int start, int end) {
		if (start == end) { return lists[start]; }
		if (start + 1 == end) {
			return mergeTwoLists(lists[start], lists[end]);
		}
		int k = (end - start)/2 + start;
		return mergeTwoLists(divide(lists, start, k), divide(lists, k+1, end));
	}
	
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {		
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
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(0);
		ListNode[] lists = {l1, l2};
		ListNode rst = mergeKLists(lists);
		System.out.println(rst.getListString());
	}
}
