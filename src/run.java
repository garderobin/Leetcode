
import java.util.List;

import algorithm.BinaryTreeInorderTraversal;
import algorithm.ConstructBSTByInorderAndPostorderTraversal;
import algorithm.ConstructBSTByInorderAndPreorderTraversal;
import algorithm.ContainsDuplicate;
import algorithm.FindMedianTwoSortedArrays;
import algorithm.IntReverseBit;
import algorithm.JumpGame;
import algorithm.ReverseSingleLinkedList;
import datastructure.ListNode;
import datastructure.TreeNode;


public class run {
	
	public static void main (String args[]) {
		testConstructBSTByInorderAndPostorder();
	}
	
	public static void testConstructBSTByInorderAndPostorder() {
	int[] postorder = {2, 1};
	int[] inorder = {2, 1};
	TreeNode root = ConstructBSTByInorderAndPostorderTraversal.buildTree(inorder, postorder);
	List<Integer> preList= BinaryTreeInorderTraversal.preorderTraversal(root);
	List<Integer> inList = BinaryTreeInorderTraversal.inorderTraversal(root);
	
	System.out.println("Final preorder: ");
	for (Integer pre : preList) {
		System.out.print(pre + "\t");
	}
	
	System.out.println("\nFinal inorder: ");
	for (Integer in : inList) {
		System.out.print(in + "\t");
	}
}
	
//	public static void testConstructBSTByInorderAndPreOrder() {
//		int[] preorder = {1, 2, 3};
//		int[] inorder = {3, 2, 1};
//		TreeNode root = ConstructBSTByInorderAndPreorderTraversal.buildTree(preorder, inorder);
//		List<Integer> preList= BinaryTreeInorderTraversal.preorderTraversal(root);
//		List<Integer> inList = BinaryTreeInorderTraversal.inorderTraversal(root);
//		
//		System.out.println("Final preorder: ");
//		for (Integer pre : preList) {
//			System.out.print(pre + "\t");
//		}
//		
//		System.out.println("\nFinal inorder: ");
//		for (Integer in : inList) {
//			System.out.print(in + "\t");
//		}
//	}
	
	
	public static void testReverseList() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;

		ReverseSingleLinkedList sol = new ReverseSingleLinkedList();
		ListNode head = sol.reverseList(n1);	
		
		while(head!=null) {		
			System.out.println(head.val);
			head = head.next;			
		}
	}
	
	public static void testReverseBetween() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		//ListNode n6 = new ListNode(6);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		//n5.next = n6;
		int m = 3;
		int n = 4;

		ReverseSingleLinkedList sol = new ReverseSingleLinkedList();
		ListNode head = sol.reverseBetween(n1, m, n);	
		
		while(head!=null) {		
			System.out.println(head.val);
			head = head.next;			
		}
	}
	
	public static void testIntReverseBit() {
		int n = 1;
		IntReverseBit irb = new IntReverseBit();
		System.out.println(irb.reverseBits(n));
	}
	
	public static void testAddTwoNumbers() {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(8);
		ListNode l2 = new ListNode(0);

		ReverseSingleLinkedList irb = new ReverseSingleLinkedList();
		ListNode ls = irb.addTwoNumbers(l1, l2);
		
		while(ls!=null) {		
			System.out.println(ls.val);
			ls = ls.next;			
		}
	}
	
	public static void testFindMedianSortedArrays() {
		int[] nums1 = {1,2,3,4};
		int[] nums2 = {5,6,7,8,9};
		//int[] nums2 = {5,6,7,8};
		FindMedianTwoSortedArrays fm = new FindMedianTwoSortedArrays();
		System.out.println(fm.findMedianSortedArrays(nums1, nums2));
	}

	public static void testJumpGame() {
		int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		JumpGame jg = new JumpGame();
		System.out.println(jg.canJump(nums));
	}
	
	public static void testDetectCycle() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);
		ListNode n8 = new ListNode(8);
		ListNode n9 = new ListNode(9);
		ListNode n10 = new ListNode(10);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;	
		n9.next = n4;
//		n9.next = n10;
//		n10.next = n5;
		ReverseSingleLinkedList sol = new ReverseSingleLinkedList();
		System.out.println(sol.detectCycle(n1).val);
	}
	
	public static void testJump() {
		int[] nums = {2,3,0,1,4};
		System.out.println(JumpGame.jump(nums));
	}
	
	public static void testContainsNearbyDuplicate() {
		int[] nums = {1,0,1,1};
		int k = 1;
		System.out.println(ContainsDuplicate.containsNearbyDuplicate(nums, 2));
	}
	
	public static void testReverseKGroup() {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		int k = 2;
		ListNode head = ReverseSingleLinkedList.reverseKGroup(n1, k);
		while(head!=null) {		
			System.out.println(head.val);
			head = head.next;			
		}
	}
}
