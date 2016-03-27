package interview.snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DAGCycle {
	public static void main(String[] args) {
	     DirectedGraphNode a = new DirectedGraphNode(1);
	     DirectedGraphNode b = new DirectedGraphNode(2);
	     DirectedGraphNode c = new DirectedGraphNode(3);
	     DirectedGraphNode d = new DirectedGraphNode(4);
	     a.neighbors.add(b);
	     a.neighbors.add(d);
	     
	     c.neighbors.add(a);
	     d.neighbors.add(c);
	     
	     c.neighbors.add(b);
	     b.neighbors.add(d);
	     
	     ArrayList<DirectedGraphNode> list = new ArrayList<DirectedGraphNode>();
	     list.add(a);
	     list.add(d);
	     list.add(b);
	     list.add(c);
	     boolean res = cycle(list);
	     System.out.println(res);
	}
	
	/*
	 * visited: 0 as never visited
	 * 			1 as in topological visiting but not finished
	 * 			2 as finished visiting
	 */
	public static boolean cycle(ArrayList<DirectedGraphNode> graph) {
		Map<DirectedGraphNode,Integer> visited = new HashMap<>();
		for (DirectedGraphNode node : graph) visited.put(node,0);
	     
		for (DirectedGraphNode node : graph) {
			if (visited.get(node)==0) {
				if (dfs(node,visited)) return true;
			}
		}    
	    return false;
	}
	  
	static boolean dfs(DirectedGraphNode node, Map<DirectedGraphNode,Integer> visited) {
	    if (visited.get(node) == 1) return true; // node is visited twice in a same dfs procedure from a same start.
	    visited.put(node,1); // mark visited
	    for (DirectedGraphNode neighbor : node.neighbors) {
	    	if (visited.get(neighbor) != 2) { // only consider those not finished nodes
	    		if (dfs(neighbor,visited)) return true;
	    	}
	    }
	    visited.put(node,2); // topological visit finished
	    return false;
	}
}

//class DirectedGraphNode {
//    int label;
//    ArrayList<DirectedGraphNode> neighbors;
//    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
//}
