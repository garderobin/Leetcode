package algorithm;

import java.util.ArrayList;
import java.util.List;

import dataStructure.TreeNode;

public class UniqueBinarySearchTreesII {
	
	
	
	/**
     * Recursion only.
     */ 
    public List<TreeNode> generateTreesDiscussion1(int n) {
        return genTrees(1,n);
    }

    public List<TreeNode> genTrees (int start, int end){
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(start>end) {
            list.add(null);
            return list;
        }
        if(start == end){
            list.add(new TreeNode(start));
            return list;
        }
        List<TreeNode> left,right;
        for(int i=start;i<=end;i++) {
            left = genTrees(start, i-1);
            right = genTrees(i+1,end);
            for(TreeNode lnode: left) {
                for(TreeNode rnode: right){
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }

        }

        return list;
    }
	
	/**
	 * 我不明白stackOverFlow是由什么造成的？
	 * @param n
	 * @return
	 */
	public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> rst = new ArrayList<TreeNode>();        
        if (n < 1) { return rst; }        
        for (int i = 0; i <= n; i++) {
        	helper(rst, 1, n, i);
        }
        return rst;
    }
	
	private static List<TreeNode> helper(List<TreeNode> rst, int start, int end, int k) {
//		TreeNode cur = new TreeNode(end);
		List<TreeNode> l1, l2;
		int i, j, len1, len2;
		// k = 0
		if (k == 0 || k == end) {
			l1 = helper(rst, 0, end-1, 0);
			if (k == 0) {
				for (TreeNode node : l1) {
					TreeNode root = new TreeNode(end);
					root.left = node;
					rst.add(root);					
				}
//				return rst;
			} else if (k == end) {
				for (TreeNode node : l1) {
//					TreeNode root = AddToRightMostLeaf(node, cur);
					TreeNode root = AddToRightMostLeaf(node, new TreeNode(end));
					rst.add(root);
				}
//				return rst;
			}
			
		}  else {
			// 分成（start，k）(k+1, end)两组
			l1 = helper(rst, start, k, 0);
			l2 = helper(rst, k+1, end, 0);
			len1 = l1.size();
			len2 = l2.size();
			for (i = 0; i < len1; i++) {
				for (j = 0; j < len2; j++) {
					TreeNode root = l2.get(i);
					root.left = l1.get(i);
					AddToRightMostLeaf(root, new TreeNode(end));
					rst.add(root);
				}
			}
		}
		return rst;
	}
	
	private static TreeNode AddToRightMostLeaf(TreeNode root, TreeNode target) {
		if (root == null) { return null; }
		if (root.right == null) { 
			root.right = target;
			return root; 
		}
		return AddToRightMostLeaf(root.right, target);
	}
	
	public static void main(String[] args) {
		List<TreeNode> list = generateTrees(4);
		for (TreeNode root : list) {
			System.out.println(SerializeAndDeserializeBinaryTree.serialize(root));
		}
	}
}
