package interview.snapchat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DAGGraphSerialization {
	
	public static void main(String[] args) {
		// Test graphSerialize()
	    DirectedGraphNode a = new DirectedGraphNode(1);
	    DirectedGraphNode b = new DirectedGraphNode(2);
	    DirectedGraphNode c = new DirectedGraphNode(3);
	    DirectedGraphNode d = new DirectedGraphNode(4);
	    a.neighbors.add(b); a.neighbors.add(d);
	    b.neighbors.add(d);
	    c.neighbors.add(a); c.neighbors.add(b);
	    d.neighbors.add(c);
	     
	    ArrayList<DirectedGraphNode> list = new ArrayList<DirectedGraphNode>();
	    list.add(a); list.add(b); list.add(c); list.add(d);
	     
	    StringBuilder sb = graphSerialize(a, new HashSet<DirectedGraphNode>(), new StringBuilder());
	    System.out.println(sb.toString());
	     
	    // Test graphDeserialize()
	    LinkedList<String> nodes = new LinkedList<>();
	    nodes.addAll(Arrays.asList(sb.toString().split(",")));
	    DirectedGraphNode root = graphDeserialize(nodes, new HashMap<Integer,DirectedGraphNode>());
	    StringBuilder sb1 = graphSerialize(root, new HashSet<DirectedGraphNode>(), new StringBuilder());
	    System.out.println(sb1.toString());
	}
	
	/* Serialize a graph by topological order. */
	static StringBuilder graphSerialize(DirectedGraphNode node, Set<DirectedGraphNode> visited, StringBuilder sb){
		// 图的序列化比N-ary Tree 的序列化多的地方 就是这个visited 也要添加并退回
		if (visited.contains(node)) {
			sb.append(node.label + ",").append("#,");
			return sb;
		}
		sb.append(node.label + ",");
		visited.add(node);
		for (DirectedGraphNode neighbor : node.neighbors) {
			sb = graphSerialize(neighbor, visited, sb); 
		}
		sb.append("#,"); // end of a visit?
		return sb;
	}

	/*
	 * ？？？不能被root访问到的（不在一个component）里面的是不是永远访问不到？
	 */
	static DirectedGraphNode graphDeserialize(LinkedList<String> list, Map<Integer,DirectedGraphNode> visited) {
		String valStr = list.poll();
		if (valStr.equals("#")) return null;
		try {
			int val = Integer.parseInt(valStr);
			if (!visited.containsKey(val)) {
				visited.put(val, new DirectedGraphNode(val));
			}
			DirectedGraphNode root = visited.get(val);
			while (!list.isEmpty()) {
				DirectedGraphNode node = graphDeserialize(list, visited);
				if (node != null) root.neighbors.add(node);
				else break; // met '#'
			}
			return root;   
		} catch (Exception e) { // currently do nothing
			return null;
		}
	}
}

//class DirectedGraphNode {
//    int label;
//    ArrayList<DirectedGraphNode> neighbors;
//    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
//}  
