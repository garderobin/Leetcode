package interview.snapchat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeVerticalOrderTraversal2 {
	
	public List<List<Integer>> verticalOrder(TreeNode root) {
	    if(root == null) return new ArrayList<List<Integer>>();
	    
	    int[] m = computeRange(root, 0, 0, 0); // m = {max, min};
	    List<List<Integer>> rst = new ArrayList<>(m[0] - m[1] + 1);
	    for(int i = m[1]; i <= m[0]; ++i) rst.add(new ArrayList<>());
	    Deque<TreeNode> q = new ArrayDeque<>();
	    Deque<Integer> idx = new ArrayDeque<>();
	    idx.add(-m[1]);
	    q.add(root);
	    
	    while (!q.isEmpty()) {
	        TreeNode node = q.pollFirst();
	        int i = idx.pollFirst();
	        
	        rst.get(i).add(node.val);
	        
	        if (node.left != null) {
	            q.offerLast(node.left);
	            idx.offerLast(i - 1);
	        }
	        if (node.right != null) {
	            q.offerLast(node.right);
	            idx.offerLast(i + 1);
	        }
	    }
	    return rst;
	}
	private int[] computeRange(TreeNode root, int idx, int max, int min){
	    if(root == null) return new int[]{max, min};
	    max = Math.max(max, idx);
	    min = Math.min(min, idx);
	    int[] m1 = computeRange(root.left, idx - 1, max, min);
	    return computeRange(root.right, idx + 1, Math.max(max, m1[0]), Math.min(min, m1[1]));
	}
}
