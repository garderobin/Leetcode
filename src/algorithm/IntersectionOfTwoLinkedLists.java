package algorithm;

import dataStructure.ListNode;

public class IntersectionOfTwoLinkedLists {
	
	/**
	 * 美妙的想法：A走完了走B，B走完了走A，总之短的
	 * @param headA
	 * @param headB
	 * @return
	 */
	public static ListNode getIntersectionNodeDiscussion2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode node1 = headA, node2 = headB;
        //System.out.println("loop: node1 = " + node1.val + ",\tnode2 = " + node2.val);
        while (node1 != node2) {        	
            node1 = node1.next;
            node2 = node2.next;
            if (node1 == node2) return node1; // in case node1 == node2 == null
            if (node1 == null) node1 = headB;
            if (node2 == null) node2 = headA;
            //System.out.println("loop: node1 = " + node1.val + ",\tnode2 = " + node2.val);
        }
        return node1;
    }
	
	/**
	 * 我的想法：把A和B连接起来找循环起点
	 * @param headA
	 * @param headB
	 * @return
	 */
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		
		ListNode dummyA = new ListNode(0), dummyB = new ListNode(0),
				prevA = dummyA, curA = headA;
		dummyA.next = headA;
		dummyB.next = headB;
		
		while (curA != null) {			
			prevA = curA;
			curA = curA.next;
		}
		
		prevA.next = dummyB;
		ListNode inter = LinkedListCycle.detectCycle(headA);
		prevA.next = null;
		
		return inter;
		
    }

	public static void main(String[] args) {
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode c1 = new ListNode(3);
		ListNode c2 = new ListNode(4);
		ListNode c3 = new ListNode(5);
		ListNode b1 = new ListNode(-1);
		ListNode b2 = new ListNode(-2);
		ListNode b3 = new ListNode(-3);
		a1.next = a2;
		a2.next = c1;
		c1.next = c2;
		c2.next = c3;
		b1.next = b2;
		b2.next = b3;
		b3.next = c1;
		System.out.println(getIntersectionNodeDiscussion2(a1, b1).val);
	}
	
	public static ListNode getIntersectionNodeDiscussion(ListNode headA, ListNode headB) {
	    int lenA = length(headA), lenB = length(headB);
	    // move headA and headB to the same start point
	    while (lenA > lenB) {
	        headA = headA.next;
	        lenA--;
	    }
	    while (lenA < lenB) {
	        headB = headB.next;
	        lenB--;
	    }
	    // find the intersection until end
	    while (headA != headB) {
	        headA = headA.next;
	        headB = headB.next;
	    }
	    return headA;
	}

	private static int length(ListNode node) {
	    int length = 0;
	    while (node != null) {
	        node = node.next;
	        length++;
	    }
	    return length;
	}
}
