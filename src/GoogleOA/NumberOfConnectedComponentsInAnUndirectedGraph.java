package GoogleOA;

import java.util.HashSet;
import java.util.Set;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
	
	/**
	 * Union Find 算法。这一类的题目我没有做过，还不够熟悉。
	 */
	public int countComponents(int n, int[][] edges) {
	    Set<Integer> set = new HashSet<Integer>();
	    int[] father = new int[n];
	    // Initialization
	    for (int i = 0; i < n; i++) { father[i] = i; }
	    
	    // Union found
	    for (int i = 0; i < edges.length; i++) { union(edges[i][0], edges[i][1], father); }
	    
	    // Count 
	    for (int i = 0; i < n; i++){ set.add(find(i, father)); }
	    return set.size();
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
	
	/**
	 * 这种办法有几个极端case始终过不去，未完成。
	 * @param n
	 * @param edges
	 * @return
	 */
	public static int countComponentsMy(int n, int[][] edges) {
//       Stack<Integer> compStack = new Stack<Integer>(n);
		int[] c2c = new int[n+1]; // component(1~n) to component(1~n);
       int[] v2c = new int[n+1]; // vertex (0 ~ n-1) to component number (1~n);
       for (int[] edge: edges) {
    	   int x = edge[0] + 1, y = edge[1] + 1;
//    	   System.out.println("c2c[" + x + "]=" + c2c[v2c[x]] + ",\tc2c[" + y + "]=" + c2c[v2c[y]]);
    	   if (v2c[x] == 0 && v2c[y] == 0) {
//    		   compStack.push((compStack.isEmpty()) ? 1 : (compStack.peek()+1));
    		   int compIndex = Math.min(x, y);
    		   c2c[x] = compIndex;
    		   c2c[y] = compIndex;
    		   v2c[x] = compIndex;
    		   v2c[y] = compIndex;
    	   } else if (v2c[x] > 0 && v2c[y] == 0) {
    		   v2c[y] = c2c[v2c[x]];
    		   c2c[y] = v2c[y];
    	   } else if (v2c[x] == 0 && v2c[y] > 0) {
    		   v2c[x] = c2c[v2c[y]];
    		   c2c[x] = v2c[x];
    	   } else {
    		   if (c2c[v2c[x]] != c2c[v2c[y]]) {
    			   System.out.println("v2c[" + x + "]=" + v2c[x] + ",\tv2c[" + y + "]=" + v2c[y]);
    			   int compIndex = Math.min(c2c[v2c[x]], c2c[v2c[y]]);
    			   c2c[v2c[y]] = compIndex;
    			   c2c[v2c[x]] = compIndex;
    			   c2c[c2c[v2c[y]]] = compIndex;
    			   c2c[c2c[v2c[x]]] = compIndex;
//    			   v2c[y] = compIndex;
//    			   v2c[x] = compIndex;
//    			   System.out.println("after: c2c[" + x + "]=" + c2c[v2c[x]] + ",\tc2c[" + y + "]=" + c2c[v2c[y]]);
    	    	   
    		   }
    	   }
       }
       int count = 0;
       Set<Integer> set = new HashSet<Integer>(n);
       String output = "";
       System.out.println(c2c[4]);
       for (int i = n; i > 0; i--) {
    	   c2c[i] = c2c[c2c[v2c[i]]];
    	   output = (c2c[i] + ", ") + output;
    	   if (c2c[i] == 0) { count++; }
    	   else { 
    		   System.out.println("c2c[" + i + "]=" + c2c[i]);
    		   set.add(c2c[i]); 
    	   }
       }
       System.out.println("c: [" + output + "]");
       return set.size() + count;
    }
	
	
}
