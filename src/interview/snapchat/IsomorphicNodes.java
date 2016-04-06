package interview.snapchat;

import java.util.List;

/**
 * JS version: 找两个DOM中的相同位置元素，就是找相同深度相同次序的HTML标签
 * Java version: 一棵树，每个节点有一个父节点，多个子节点；给定一个子节点，找到另一颗树中相同位置的节点 
 * @author jasmineliu
 *
 */
public class IsomorphicNodes {
	
	public static void main(String[] args) {
		//TODO
	}
	
	/**
	 * Return the node in tree root2 who has the same position with node1 in tree root1.
	 * @param root1
	 * @param root2
	 * @param node1
	 * @return
	 */
	public static Node findIsomorphicNode(Node root1, Node root2, Node node1) {
		if (root1 == null || root2 == null || node1 == null) return null;
		if (root1.equals(node1)) return root2;
		if (root1.children == null || root1.children.isEmpty() || root2.children == null || root2.children.isEmpty()) return null;
		for (int i = 0, limit = Math.min(root1.children.size(), root2.children.size()) - 1; i < limit; ++i) {
			Node rst = findIsomorphicNode(root1.children.get(i), root2.children.get(i), node1);
			if (rst != null) return rst;
		}
		return null;
	}
	
	
	static class Node {
		int val;
		int pos;
		List<Node> children;
		
		Node(int val, int pos) {
			this.val = val;
			this.pos = pos;
		}
		
		Node addChild(int val) {
			Node child = new Node(val, children.size() + 1);
			children.add(child);
			return child;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((children == null) ? 0 : children.hashCode());
			result = prime * result + pos;
			result = prime * result + val;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			
			Node other = (Node) obj;
			if (pos != other.pos) return false;
			if (val != other.val) return false;
			
			return true;
		}
		
		
	}
}
