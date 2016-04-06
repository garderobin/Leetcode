package interview.snapchat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

import dataStructure.UndirectedGraphNode;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 当前的算法只能处理一个component。如果是个稀疏图，含有很多森林，以一个点为起点则必挂。
 * 
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
	
	/*
	 * Memorized depth-first-search. (with HashMap)
	 * O(logE), E is the total number of edges
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	    HashMap<Integer,UndirectedGraphNode> map = new HashMap<Integer,UndirectedGraphNode>();
	    return cloneNode(node,map);
	}
	private UndirectedGraphNode cloneNode(UndirectedGraphNode node, HashMap<Integer,UndirectedGraphNode> map) {
	    if (node == null) return null;
	    if (map.containsKey(node.label)) {
	        return map.get(node.label);
	    } else {
	        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
	        map.put(node.label, clone);
	        for (UndirectedGraphNode nb: node.neighbors) {
	            clone.neighbors.add(cloneNode(nb, map));
	        }
	        return clone;
	    }
	}
	
	
	/*
	 * BFS using queue, O(E)
	 */
	public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) {
        if (node == null) return null;

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label); //new node for return
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>(); //store visited nodes

        map.put(newNode.label, newNode); //add first node to HashMap

        Deque<UndirectedGraphNode> q = new ArrayDeque<>(); //to store **original** nodes need to be visited
        q.add(node); //add first **original** node to queue

        while (!q.isEmpty()) { //if more nodes need to be visited
            UndirectedGraphNode n = q.pollFirst(); //search first node in the queue
            for (UndirectedGraphNode neighbor : n.neighbors) {
                if (!map.containsKey(neighbor.label)) { //add to map and queue if this node hasn't been searched before
                    map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    q.offerLast(neighbor);
                }
                map.get(n.label).neighbors.add(map.get(neighbor.label)); //add neighbor to new created nodes
            }
        }

        return newNode;
    }
	
	
	// Memorized DFS. (DFS + HashSet + HashMap)
	public UndirectedGraphNode cloneGraphV0(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		HashSet<Integer> vLabels = new HashSet<>(); // visited labels
		HashMap<Integer, UndirectedGraphNode> cNodes = new HashMap<>();	// created nodes
		vLabels.add(node.label);		
		return cloneNodesV0(vLabels, cNodes, node);
    }
	
	private UndirectedGraphNode cloneNodesV0(HashSet<Integer> vLabels, HashMap<Integer, 
			UndirectedGraphNode> cNodes, UndirectedGraphNode node) {
		UndirectedGraphNode node2 = new UndirectedGraphNode(node.label);
		cNodes.put(node.label, node2);
		ArrayList<UndirectedGraphNode> neighbors = new ArrayList<>(node.neighbors.size());
		for (UndirectedGraphNode nb : node.neighbors) {			
			if (!vLabels.add(nb.label)) {
				neighbors.add(cNodes.get(nb.label));
			} else {				
				UndirectedGraphNode cur = cloneNodesV0(vLabels, cNodes, nb);	
				neighbors.add(cur);
			}			
		}
		node2.neighbors = neighbors;
		return node2;
		
	}
}
