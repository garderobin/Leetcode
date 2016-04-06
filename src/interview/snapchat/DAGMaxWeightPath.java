package interview.snapchat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Find the maximum total weight path with given steps(depth) and start node.
 * 这里是dfs的解法，这个题的BFS版本参见SnapsMaxWeightPath.java
 */
public class DAGMaxWeightPath {
	
	static int max = 0;
	/* backtrack */
	public static ArrayList<Integer> maxWeightPath(DirectedGraphNode node, 
	                      Set<DirectedGraphNode> visited, Path path, int depth) {
		ArrayList<Integer> result = new ArrayList<>();
		if (depth == 0){
			if (path.weightSum + node.label > max) {
	            path.nodes.add(node.label);    
	            result = new ArrayList<Integer>(path.nodes);
	            path.nodes.remove(path.nodes.size()-1);
	            max = path.weightSum + node.label;
	            return result;
	         }
		}
		visited.add(node);    
		for (DirectedGraphNode neighbor : node.neighbors) {
			if (!visited.contains(neighbor)){
				path.nodes.add(node.label);
				path.weightSum += node.label;

				result = maxWeightPath(neighbor, visited, path, depth-1);

				path.nodes.remove(path.nodes.size()-1);
				path.weightSum -= node.label; 
			}
		}

		visited.remove(node); 
		return result;
	}
	
	
	public static void main(String[] args) {
		DirectedGraphNode e = new DirectedGraphNode(5);
		DirectedGraphNode f = new DirectedGraphNode(6);
		DirectedGraphNode g = new DirectedGraphNode(7);
		DirectedGraphNode h = new DirectedGraphNode(8);
		e.neighbors.add(f);
		e.neighbors.add(g);
		f.neighbors.add(h);
		g.neighbors.add(h);
		//为什么weight是2？
	    ArrayList<Integer> result = maxWeightPath(e, new HashSet<DirectedGraphNode>(), new Path(0), 2);
//		Path path = new Path(0);
//		Set<DirectedGraphNode> visited = new HashSet<>();
//	    int depth = 2;
//	    ArrayList<Integer> result = maxWeightPath(e, visited, path, depth);
	    System.out.println(result);
	}

}

class Path{
    int weightSum;
    ArrayList<Integer> nodes;
    Path(int weightSum){
    	this.weightSum = weightSum;
    	nodes = new ArrayList<Integer>();
    }
}
