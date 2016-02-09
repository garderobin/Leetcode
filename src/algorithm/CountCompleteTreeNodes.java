package algorithm;

import dataStructure.TreeNode;

public class CountCompleteTreeNodes {
	
	/**
	 * Discussion 标准答案
	 * @param root
	 * @return
	 */
	public static int countNodes(TreeNode root) {
		int h = height(root);
        return h < 0 ? 0 :
               height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                                         : (1 << h-1) + countNodes(root.left);
	}
	
	private static int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
	
	/*
	public static int countNodes(TreeNode root) {
        int count = 0;
        if (root == null) {
        	return count;
        }
        
        count = myCountNodes(root, 1, 0, 1);                
        return count;
    }
	
	public static int myCountNodes(TreeNode root, int levelCount, int leefCount, int count) {
		if (root.left == null && root.right == null) {
			if (levelCount > 1) {
				count -= levelCount;
				levelCount = 0;
				leefCount++;
				count += leefCount;
				//return myCountNodes
			}
		}
		if (root.left != null && root.right != null) {
			levelCount *= 2;
			if (levelCount == 0) {
				leefCount += 2;
				//return
			}
			return myCountNodes(root.left, levelCount, 0, count + levelCount);
		} else if (root.left != null && root.right == null) {
			leefCount++;
			//return myCountNodes(root)
			
		}
		return count;
	}
	*/
}
