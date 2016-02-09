package algorithm;

import java.util.LinkedList;
import dataStructure.TreeNode;

public class BSTIterator {
	LinkedList<Integer> leftList, rightList;
	TreeNode root;
	
	public BSTIterator(TreeNode root) {
		if (root == null) { return; } 
        this.root = root;
        leftList = inorderTraversal(root.left);
        rightList = inorderTraversal(root.right);
    }
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !((leftList == null || leftList.size() == 0) && root == null && (rightList == null || rightList.size()==0));
    }

    /** 
     * 目标有两个： 1是找到最小值并返回，2是删除最小节点。两个缺一不可。
     * @return the next smallest number 
     */
    public int next() {
        if (!(leftList == null || leftList.isEmpty())) { return leftList.poll(); }
        if (root != null) { 
        	int rst = root.val;
        	root = null; //这一步很容易忘
        	return rst;
        }
        return rightList.poll(); 
    }
    
    public LinkedList<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> rst = new LinkedList<Integer>();
        if (root == null) { return rst; }
        rst.addAll(inorderTraversal(root.left)); 
        rst.add(root.val);
        rst.addAll(inorderTraversal(root.right)); 
        return rst;
    }

	public static void main(String[] args) {
		TreeNode root = SerializeAndDeserializeBinaryTree.deserialize("[1]");
		BSTIterator iter = new BSTIterator(root);
		System.out.println(iter.hasNext());
		System.out.println(iter.next());
		System.out.println(iter.hasNext());
//		System.out.println(iter.next());
	}

}
