package interview.snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import dataStructure.UndirectedGraphNode;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		HashSet<Integer> vLabels = new HashSet<>(); // visited labels
		HashMap<Integer, UndirectedGraphNode> cNodes = new HashMap<>();	// created nodes
		vLabels.add(node.label);		
		return cloneNodes(vLabels, cNodes, node);
    }
	
	private UndirectedGraphNode cloneNodes(HashSet<Integer> vLabels, HashMap<Integer, 
			UndirectedGraphNode> cNodes, UndirectedGraphNode node) {
		UndirectedGraphNode node2 = new UndirectedGraphNode(node.label);
		cNodes.put(node.label, node2);
		ArrayList<UndirectedGraphNode> neighbors = new ArrayList<UndirectedGraphNode>(node.neighbors.size());
		for (UndirectedGraphNode nb : node.neighbors) {			
			if (!vLabels.add(nb.label)) {
				neighbors.add(cNodes.get(nb.label));
			} else {				
				UndirectedGraphNode cur = cloneNodes(vLabels, cNodes, nb);	
				neighbors.add(cur);
			}			
		}
		node2.neighbors = neighbors;
		return node2;
		
	}
}
