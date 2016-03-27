package interview.snapchat;

import java.util.*;

/*
 * 没明白这个题想干什么？？？
 */
public class MutexLogEntry {
	
	public static void main(String [] args){
		// 1 0 1
		// 2 0 2
		// 3 0 1
		// 2 0 1
		// 1 0 2
		// 1 1 1
		// 3 0 2 
		int[][] test = {{1,0,1}, {2,0,2}, {3,0,1}, {2,0,1}, {1,0,2}, {1,1,1}, {3,0,2}};
		List<LogEntry> logs = new ArrayList<>();
		for (int[] tc: test) {
			logs.add(new LogEntry(tc[0], tc[2], (tc[1] == 0) ? true : false));
		}
		
		print(logs);
	}
	
	static void print(List<LogEntry> logs){
	    Map<Integer,Queue<GraphNode>> mutexMap = new HashMap<>();//MutexID: waiting queue of thread
	    Map<Integer,GraphNode> threadMap = new HashMap<>();
	    ArrayList<GraphNode> graph = new ArrayList<>();
	     
	    for (LogEntry log: logs) {
	    	// Create or get thread graphNode
	    	GraphNode node;
	    	if (!threadMap.containsKey(log.thID))
	    		threadMap.put(log.thID,new GraphNode(log.thID));  
	    	node = threadMap.get(log.thID);
	    	graph.add(node); 
	       
	    	// Acquire or release mutex
	    	if (log.act) { // acquire mutex
	    		if(!mutexMap.containsKey(log.mutID)) { // mutex not exist
	    			mutexMap.put(log.mutID,new LinkedList<GraphNode>());        
	    		} else { // exist mutex, add one edge to the graph
	    			node.neighbors.add(mutexMap.get(log.mutID).peek()); 
	    		}
	    		mutexMap.get(log.mutID).add(node);
	        } else { // release mutex
	        	Queue<GraphNode> q = mutexMap.get(log.mutID);
	        	GraphNode releaseID = q.poll();
	        	if (!q.isEmpty()) mutexMap.remove(log.mutID);
	        	else {
	        		GraphNode candidate = q.poll();           
        			candidate.neighbors.remove(releaseID); //不明白这一步是要干什么？
        			q.add(candidate);
        			
        			// give every other node in the waiting list to the first candidate if exist
    	        	for (int i = 1; i < q.size(); ++i) {
    	        		GraphNode waitingID = q.poll();
    	        		waitingID.neighbors.remove(releaseID);
    	        		waitingID.neighbors.add(candidate);
    	        		q.add(waitingID);
    	        	}
    	        }
	         
	        }
	    	
	    	System.out.println(hasCycle(graph));
	       
	    }
	    
	}
	  
	
	static boolean hasCycle(ArrayList<GraphNode> graph){
		HashMap<GraphNode,Integer> visited = new HashMap<>();
		for (GraphNode node : graph) visited.put(node,0); 
		for (GraphNode node : graph) {
			if (visited.get(node)==0) 
	          if (dfs(node,visited)) return true;
		}
	    return false;
	}
	
	
	static boolean dfs(GraphNode node, HashMap<GraphNode,Integer> visited){
		if(visited.get(node) == 1) return true;
		visited.put(node,1);
	    for (GraphNode neighbor: node.neighbors) {
	    	if (visited.get(neighbor) != 2)
	    		if(dfs(neighbor,visited)) return true;
	    }
	    visited.put(node,2);
	    return false;
	}

}

class LogEntry{
    int thID;
    int mutID;
    boolean act; //true acquire  false release
    LogEntry(int thID, int mutID, boolean act){
        this.thID = thID;
        this.mutID = mutID;
        this.act = act;
    }
}
class GraphNode{
    int node;
    Set<GraphNode> neighbors;
    GraphNode(int node){
      this.node = node;
      this.neighbors = new HashSet<GraphNode>();
    }
}