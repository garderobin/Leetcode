package dataStructure;

/**
 * 1. Create a new datatype for a singly linked list, that holds integers. Do not re-use any existing linked list datatype from your language.
 * @author Penghui(Jasmine) Liu
 *
 */
public class ListNode {
	public int val;
	public ListNode next;
	
	
	public ListNode(int x) {
		val = x;
	}
	
	/**
	 * Constructor: create a linked list by a given integer array.
	 * @param list
	 */
	public ListNode(int[] list) {
		if (list == null || list.length == 0) {
			next = null;
		}
		else {
			val = list[0];
			ListNode cur = this, after;		
			for (int i = 1; i < list.length; i++) {
				after = new ListNode(list[i]);
				cur.next = after;
				cur = after;
			}
		}
		
	}
	
	/**
	 * Helper: get print information of the linked list
	 * @return
	 */
	public String getListString() {		
		StringBuilder sb = new StringBuilder(val + ",");
		for (ListNode cur = next; cur != null; cur = cur.next) {
			sb.append(cur.val + ",");			
		}
		return sb.toString();
	}
	
	/**
	 * Compare whether two given linked lists are equal.
	 * @param l1
	 * @param l2
	 * @return
	 */
	public boolean equals(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) { return true; }
		while (l1 != null && l2 != null) {
			if (l1.val != l2.val) { return false; }
			l1 = l1.next;
			l2 = l2.next;
		}
		return l1 == null && l2 == null;
	}
	
}
