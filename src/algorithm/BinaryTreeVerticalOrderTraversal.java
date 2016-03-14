package algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import dataStructure.TreeNode;

public class BinaryTreeVerticalOrderTraversal {
	class TreeNodeVertical {
		public int val;
		public int vIndex;
		public TreeNodeVertical left;
		public TreeNodeVertical right;
		
		public TreeNodeVertical() { vIndex = 0; }
		public TreeNodeVertical(TreeNode node, int vIndex) { 
			this.val = node.val;
			this.vIndex = vIndex; 
		}
	}
	
	int max = 0, min = 0; 
	
	public List<List<Integer>> verticalOrder(TreeNode root) {
	    if (root == null) { return new ArrayList<List<Integer>>(); }
	    
		// compute range
		TreeNodeVertical vroot = generateVNode(root, 0);
		int n = max - min + 1;
		List<Integer>[] arr = new List[n];
		for (int i = 0; i < n; ++i) { arr[i] = new ArrayList<Integer>(); }
		
		// BFS
		Queue<TreeNodeVertical> q = new ArrayDeque<>();
		q.add(vroot);
		while (!q.isEmpty()) {
			TreeNodeVertical vnode = q.poll();
			int col = vnode.vIndex - min;
			arr[col].add(vnode.val);
			if (vnode.left != null) q.add(vnode.left);
			if (vnode.right != null) q.add(vnode.right);
		}
		
        return Arrays.asList(arr);
    }
	
	private TreeNodeVertical generateVNode(TreeNode root, int idx) { // java 在非static环境下是不能通过传参来试图改变某个本应为全局变量的参数的值的。
		if (root == null)  return null;
		if (idx > max) 		{ max = idx; }
		else if (idx < min) { min = idx; }
		
		TreeNodeVertical vroot = new TreeNodeVertical(root, idx);
		vroot.left = generateVNode(root.left, idx-1);
		vroot.right = generateVNode(root.right, idx+1);
		
		return vroot;
	}
	
	public static void main(String[] args) {
		
	}
}
