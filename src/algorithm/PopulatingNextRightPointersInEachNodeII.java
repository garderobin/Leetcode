package algorithm;

import dataStructure.TreeLinkNode;

public class PopulatingNextRightPointersInEachNodeII {
	
	/**
	 * 一般的bfs需要extra space,这里的递归不需要extra space，只要多个指针就好了。
	 * 这里是按照操作链表的思想来操作树，非常相似。
	 * 类似的，对于链表也可以用树常用的递归操作。
	 * @param root
	 */
	//based on level order traversal
    public void connectDiscussion(TreeLinkNode root) {

        TreeLinkNode head = null; //head of the next level
        TreeLinkNode prev = null; //the leading node on the next level
        TreeLinkNode cur = root;  //current node of current level

        while (cur != null) {

            while (cur != null) { //iterate on the current level
                //left child
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                //right child
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                //move to next node
                cur = cur.next;
            }

            //move to next level
            cur = head;
            head = null;
            prev = null;
        }

    }
}
