package algorithm;

import dataStructure.TreeNode;

public class HouseRobberIII {
	
	 public int robDiscussion(TreeNode root) {
	        int[] num = dfs(root);
	        return Math.max(num[0], num[1]);
	    }
	    private int[] dfs(TreeNode x) {
	        if (x == null) return new int[2];
	        int[] left = dfs(x.left);
	        int[] right = dfs(x.right);
	        int[] res = new int[2];
	        res[0] = left[1] + right[1] + x.val;
	        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
	        return res;
	    }
	
	class RobNode {
		int val;
		public int exMax = 0; // maximum robbery gain by not robbing this node.
		public int inMax = 0; // maximum robbery gain by robbing this node.
		RobNode left, right;
		
		public RobNode(TreeNode node, int exMax, int inMax, RobNode left, RobNode right) {
			this.exMax = exMax;
			this.inMax = inMax;
			this.left = left;
			this.right = right;
		}
	}
	
	public int rob(TreeNode root) {
		if (root == null)  return 0;
        RobNode rn = robNodes(root);
		return (rn.exMax > rn.inMax) ? rn.exMax : rn.inMax;
    }
	
	private RobNode robNodes(TreeNode root) {
		if (root == null) { return null; }
		if (root.left == null && root.right == null) {
			return new RobNode(root, 0, root.val, null, null);
		}
		RobNode left = robNodes(root.left), right = robNodes(root.right);
		int inMax = root.val + (left == null ? 0 : left.exMax) + (right == null ? 0 : right.exMax);
		int exMax = (left == null ? 0 : (left.exMax > left.inMax ? left.exMax : left.inMax)) + 
				(right == null ? 0 : (right.exMax > right.inMax ? right.exMax : right.inMax));
		return new RobNode(root, exMax, inMax, left, right);
	}
	
	
}
