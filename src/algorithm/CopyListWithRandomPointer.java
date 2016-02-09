package algorithm;

import java.util.HashSet;

import dataStructure.RandomListNode;

public class CopyListWithRandomPointer {
	/**
	 * 方法的精妙在于，我们完全可以先对原数据结构动手脚，
	 * 只要能复原，一切都可以利用。
	 * The idea is to associate the original node with its copy node in a single linked list. 
	 * In this way, we don't need extra space to keep track of the new nodes.
	 * @param head
	 * @return
	 */
	public RandomListNode copyRandomListDiscussion(RandomListNode head) {
	    RandomListNode iter = head, next;

	    // First round: make copy of each node,
	    // and link them together side-by-side in a single list.
	    while (iter != null) {
	        next = iter.next;

	        RandomListNode copy = new RandomListNode(iter.label);
	        iter.next = copy;
	        copy.next = next;

	        iter = next;
	    }

	    // Second round: assign random pointers for the copy nodes.
	    iter = head;
	    while (iter != null) {
	        if (iter.random != null) {
	            iter.next.random = iter.random.next;
	        }
	        iter = iter.next.next;
	    }

	    // Third round: restore the original list, and extract the copy list.
	    iter = head;
	    RandomListNode pseudoHead = new RandomListNode(0);
	    RandomListNode copy, copyIter = pseudoHead;

	    while (iter != null) {
	        next = iter.next.next;

	        // extract the copy
	        copy = iter.next;
	        copyIter.next = copy;
	        copyIter = copy;

	        // restore the original list
	        iter.next = next;

	        iter = next;
	    }

	    return pseudoHead.next;
	}
	
	public RandomListNode copyRandomList(RandomListNode head) {
		HashSet<RandomListNode> visited = new HashSet<RandomListNode>();
		HashSet<RandomListNode> created = new HashSet<RandomListNode>();
//		RandomListNode node = new RandomListNode(head.label);
//		created.add(node);
		return head;        
    }
	
	private RandomListNode copyNode(HashSet<RandomListNode> visited,
			HashSet<RandomListNode> created, RandomListNode cur) {
		RandomListNode copy = new RandomListNode(cur.label);
		if (visited.add(cur.next)) {
			copy.next = copyNode(visited, created, cur.next);			
		} else {
			//cur.next = created
		}
		if (visited.add(cur.random)) {
			copy.random = copyNode(visited, created, cur.random);
		} else {
			
		}
		
		return copy;
	}
}
