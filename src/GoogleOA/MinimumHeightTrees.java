package GoogleOA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumHeightTrees {
	/**
	 * 叶子节点度数为1，这一点是不能更改的。我们从叶子节点出发，一层一层向上找根节点。
	 * We start from every end, by end we mean vertex of degree 1 (aka leaves). 
	 * We let the pointers move the same speed. When two pointers meet, 
	 * we keep only one of them, until the last two pointers meet or one step away we then find the roots.
	 * @param n
	 * @param edges
	 * @return
	 */
	public static List<Integer> findMinHeightTreesDiscussion(int n, int[][] edges) {
	    if (n == 1) return Collections.singletonList(0); //这个用法很新颖。

	    List<Set<Integer>> adj = new ArrayList<>(n);
	    for (int i = 0; i < n; ++i)  { adj.add(new HashSet<Integer>()); }
	    for (int[] edge : edges) {
	        adj.get(edge[0]).add(edge[1]);
	        adj.get(edge[1]).add(edge[0]);
	    }

	    List<Integer> leaves = new ArrayList<>();
	    for (int i = 0; i < n; ++i) { 
	    	if (adj.get(i).size() == 1)  { leaves.add(i); }
	    }

	    while (n > 2) { // 为什么这个阈值是2？
//	    	printList("leaves", leaves);
	        n -= leaves.size();
//	        System.out.println(n);
	        List<Integer> newLeaves = new ArrayList<>();
	        for (int i : leaves) {
	            int j = adj.get(i).iterator().next(); //这一点比取get(0)要安全一些。
	            adj.get(j).remove(i);
//	            printSet("remove " + i + " from adj[" + j + "] ", adj.get(j));
	            if (adj.get(j).size() == 1) { 
	            	newLeaves.add(j); 
	            }
	        }
	        leaves = newLeaves;
	    }
	    return leaves;
	}
	
	private static void printSet(String p, Set<Integer> leaves) {
		StringBuilder sb = new StringBuilder(p + " = [");
		for (int e: leaves) {
			sb.append(e + ", ");
		}
		System.out.println(sb.toString() + "]");
	}
	
	private static void printList(String p, List<Integer> leaves) {
		StringBuilder sb = new StringBuilder(p + " = [");
		for (int e: leaves) {
			sb.append(e + ", ");
		}
		System.out.println(sb.toString() + "]");
	}

	public static void main(String[] args) {
		//n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
		int[] e1 = {0,3};
		int[] e2 = {1,3};
		int[] e3 = {2,3};
		int[] e4 = {4,3};
		int[] e5 = {5,4};
		int[][] edges = new int[5][2];
		edges[0] = e1;
		edges[1] = e2;
		edges[2] = e3;
		edges[3] = e4;
		edges[4] = e5;
		System.out.println(findMinHeightTreesDiscussion(6, edges));
	}
	
	public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> rst = new ArrayList<Integer>();
		if (n == 1) {
        	rst.add(0);
        	return rst;
        } else if (edges.length == 0) {
        	for (int i = 0; i < n; i++) { rst.add(i); }
        	return rst;
        } else {
        	// Initialize adjacent lists and visited set.
        	List<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        	for (int i = 0; i < n; i++) {
        		adj.add(new ArrayList<Integer>());
        	}
        	for (int i = 0; i < edges.length; i++) {
        		adj.get(edges[i][0]).add(edges[i][1]);
        		adj.get(edges[i][1]).add(edges[i][0]);
        	}
        	
        	// Calculate minimum height for each node as root
        	int[] minHeights = new int[n];
        	int curMin = n;
        	heightForSingleRoot(0, minHeights, adj, new HashSet<Integer>(n), 1);
        	for (int i = 0; i < n; i++) { // choose each node for root
//        		minHeights[i] = heightForSingleRoot(i, minHeights, adj, new HashSet<Integer>(n), 1);
        		curMin = Math.min(curMin, minHeights[i]);
        	}
        	
        	// Get MHT roots
        	for (int index = 0; index < n; index++) {
        		if (minHeights[index] == curMin) { rst.add(index); }
        	}
        }
		
		return rst;
		
    }
	
	private static int heightForSingleRoot(int index, int[] minHeights, List<ArrayList<Integer>> adj, Set<Integer> visited, int prev) {
		if (visited.size() == minHeights.length) { return prev; }
//		if (minHeights[index] > 0)  { return minHeights[index] - prev; }
		ArrayList<Integer> nbs = adj.get(index);
		int maxSubHeight = 0;
		for (int nb: nbs) {
			if (!visited.add(nb)) { continue; }
			int subHeight = heightForSingleRoot(index, minHeights, adj, visited, prev+1);
			minHeights[index] = 1 + (Math.max(prev, subHeight));
			maxSubHeight = Math.max(maxSubHeight, subHeight);
		}
		if (minHeights[index] > 0) { minHeights[index] = Math.min(minHeights[index], prev + 1 + maxSubHeight); }
		return 1 + maxSubHeight;
	}
	
}
