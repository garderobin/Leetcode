package algorithm;

import java.util.ArrayList;
import java.util.List;

import dataStructure.TreeNode;

public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {		
        List<String> rst = new ArrayList<String>();
        if (root == null) {
			return rst;
		}
        return helper(rst, new StringBuilder(), root);
    }
	
	private List<String> helper(List<String> rst, StringBuilder sb, TreeNode node) {
		sb.append((sb.length() == 0) ? node.val : "->" + node.val);
		if (node.left == null && node.right == null) {
			rst.add(sb.toString());
			return rst;
		}
		if (node.left != null) { 			
			rst = helper(rst, new StringBuilder(sb), node.left);
		}
		if (node.right != null) {
			rst = helper(rst, new StringBuilder(sb), node.right);
		}
		return rst;
	}
}
