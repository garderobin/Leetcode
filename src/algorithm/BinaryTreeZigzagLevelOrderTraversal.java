package algorithm;

import java.util.ArrayList;
import java.util.List;

import dataStructure.TreeNode;

public class BinaryTreeZigzagLevelOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();	
		if (root == null) { return rst; }
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(root.val);
		
        // Layer Traversal
        ArrayList<TreeNode> curLayer = new ArrayList<TreeNode>();
        ArrayList<TreeNode> nextLayer = new ArrayList<TreeNode>();
        curLayer.add(root); 
        boolean reversing = true;
        
        do {
        	rst.add(list);
        	list = new ArrayList<Integer>(); 
        	if (reversing) {
        		for (int i = curLayer.size(); i > 0; i--) {
        			TreeNode node = curLayer.get(i - 1);
            		if (node.right != null) {
                   		nextLayer.add(0, node.right); 
                   		list.add(node.right.val);
                   	} 
               		if (node.left != null) {
               			nextLayer.add(0, node.left); 
               			list.add(node.left.val);
               		}            		
                }
        	} else {
        		for (TreeNode node : curLayer) {
            		if (node.left != null) {
                   		nextLayer.add(node.left); 
                   		list.add(node.left.val);
                   	} 
               		if (node.right != null) {
               			nextLayer.add(node.right); 
               			list.add(node.right.val);
               		}            		
                }
        	}
        	
            curLayer = nextLayer;            
            nextLayer = new ArrayList<TreeNode>(); 
            reversing = !(reversing);
        } while (curLayer.size() > 0);
        
        return rst;
    }
}
