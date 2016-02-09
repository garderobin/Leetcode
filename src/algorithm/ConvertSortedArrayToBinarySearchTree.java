package algorithm;

import dataStructure.TreeNode;
/**
 * 易错点：
 * k值的取法
 * @author jasmineliu
 *
 */
public class ConvertSortedArrayToBinarySearchTree {
	public static TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		return helper(nums, 0, nums.length - 1);
		
    }
	
	private static TreeNode helper(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		if (start == end) {
			return new TreeNode(nums[start]);
		}
		int k = (end - start) / 2 + start + 1;		
        TreeNode root = new TreeNode(nums[k]);
        root.left = helper(nums, start, k - 1);
        root.right = helper(nums, k + 1, end);        
        return root;
	}
}
