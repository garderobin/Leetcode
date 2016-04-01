package algorithm;

import java.util.ArrayList;
import java.util.List;

public class PathSum {
	class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		
		if (root.left == null && root.right == null) {
			return (sum == root.val);
		} else if (root.left != null && root.right == null) {
			return hasPathSum(root.left, sum - root.val);
		} else if (root.left == null && root.right != null) {
			return hasPathSum(root.right, sum - root.val);
		} else  {
			return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val));
		}
        
    }
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        
        return helper(root, sum, rst, new ArrayList<Integer>(), 0);
    }

	private List<List<Integer>> helper(TreeNode root, int sum,
			List<List<Integer>> rst, List<Integer> path, int cur) {
		if (root == null) {
			return rst;
		}
		cur += root.val;
		path.add(cur);		
		if (root.left == null && root.right == null) {
			if (cur == sum) {
				rst.add(new ArrayList<Integer>(path));				
			} else {
				path.remove(path.size() - 1);
			}
			return rst;
		}
		rst = helper(root.left, sum, rst, new ArrayList<Integer>(path), cur);
		rst = helper(root.right, sum, rst, new ArrayList<Integer>(path), cur);				
		return rst;
	}
}
