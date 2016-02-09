package algorithm;

import dataStructure.ListNode;

public class RemoveDuplicatesfromSortedListII {
	
	/**
	 * 极少有链表题目有两个以上指针的
	 * 把每一次逻辑点临界条件想清楚是很容易的也是必须的。
	 * 本题只有两种情况，prev跟着往前进(没有遇到重复); 或者prev的next变化而prev自己不往前走。
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicatesDiscussion(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head, prev = dummy;
        while(cur!=null){ 
            while(cur.next != null && cur.val == cur.next.val){
                cur = cur.next;
            }
            if(prev.next==cur){
                prev = prev.next;
            }
            else{
                prev.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
	
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head, prev = dummy, after = head.next;
        while (after != null) {
        	if (cur.val != after.val) {
        		prev = prev.next;
        		cur = cur.next;
        		after = after.next;
        		continue;
        	}
        	while (after != null && cur.val == after.val) {
        		cur = cur.next;
        		after = after.next;
        		prev.next = cur;
        	}
        	prev.next = after;
        	cur = after;
        	if (after != null) {after = after.next;} //这句非常重要，太容易被忘记的边界情况
        }
        
        return dummy.next;
    }
}
