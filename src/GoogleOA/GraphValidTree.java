package GoogleOA;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
 * 思路一：用union find来确保connectivity, 用DFS来确保没有回路
 * 思路二：BFS一次性解决战斗，用visited控制回路，用visited的size所有点都访问到了
 */
public class GraphValidTree {
	
	/*
	 * Union find solution
	 */
	public boolean validTree(int n, int[][] edges) {
		if (n == 1) { return true; }
		if (n <= 0 || (edges == null) || (n - 1 != edges.length)) { return false; }
        
		Set<Integer> components = new HashSet<Integer>();
	    int[] father = new int[n];
	    // Initialization
	    for (int i = 0; i < n; i++) { father[i] = i; }
	    
	    // Union found
	    for (int i = 0; i < edges.length; i++) { union(edges[i][0], edges[i][1], father); }
	    
	    // Count 
	    for (int i = 0; i < n; i++) { components.add(find(i, father)); }
	    return components.size() == 1;
    }
	
	public static boolean validTreeBFS(int n, int[][] edges) {
		if (n == 1) { return true; }
		if (n <= 0 || (edges == null) || (n - 1 != edges.length)) { return false; }
		Set<Integer> visited = new HashSet<Integer>();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		Deque<Integer> layer = new LinkedList<Integer>();
		
		// Initialization adjacent list
		for (int i = 0; i < n; i++) { adj.add(new ArrayList<Integer>()); }
	    for (int[] e: edges) {
	    	adj.get(e[0]).add(e[1]);
	    	adj.get(e[1]).add(e[0]);
	    }
	    
	    // BFS
	    visited.add(0);
	    layer.add(0);
	    for (int v = 0; !layer.isEmpty(); v = layer.pollFirst()) {
	    	for (int nb: adj.get(v)) {
	    		adj.get(nb).remove((Integer)v); //这一步非常重要，必须转换成object否则会边长按index删除。
		    	if (!visited.add(nb)) { return false; } // loop path detected.
		    	layer.push(nb);
		    }
	    }
	    return visited.size() == n;
	}
	
	public static void main(String[] args) {
		int[][] edges = {{0,1},{0,2},{2,3},{2,4}};
		int n = 5;
		System.out.println(validTreeBFS(n, edges));
	}
	
	/**
	 * Recursively find and update the first appearance of a node's equivalence using the thought of DFS.
	 */
	int find(int node, int[] father) {
	    if (father[node] == node) { return node; }
	    father[node] = find(father[node], father);
	    return father[node];
	}

	void union(int node1, int node2, int[] father) {
	    father[find(node1, father)] = find(node2, father);
	}
	
//	private boolean dfs (int)
}
