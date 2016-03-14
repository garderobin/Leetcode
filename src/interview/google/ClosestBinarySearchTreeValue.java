package interview.google;

import dataStructure.TreeNode;

public class ClosestBinarySearchTreeValue {
	
	public static int closestValueDiscussion(TreeNode root, double target) {
	    int a = root.val;
	    TreeNode kid = target < a ? root.left : root.right;
	    if (kid == null) return a;
	    int b = closestValueDiscussion(kid, target);
	    return Math.abs(a - target) < Math.abs(b - target) ? a : b;
	}
	
	public int closestValue(TreeNode root, double target) {
        int curClosest = (target > 0) ? Integer.MIN_VALUE: Integer.MAX_VALUE ;
        return findClosestValue(root, target, curClosest, false, false);
    }
	
	public int findClosestValue(TreeNode root, double target, int curClosest, boolean floorFound, boolean ceilFound) {
		if (floorFound && ceilFound) { return curClosest; }
		curClosest = (Math.abs(root.val - target) < Math.abs(curClosest - target)) ? root.val : curClosest;
		
		if (target < root.val) {
			return (root.left == null) ? curClosest : findClosestValue(root.left, target, curClosest, root.val == Math.floor(target), ceilFound);
		} else {
			return (root.right == null) ? curClosest : findClosestValue(root.right, target, curClosest, floorFound, root.val == Math.ceil(target));
		}
	}
}
