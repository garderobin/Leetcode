package algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {
	
	public int[] findOrderDiscussion(int numCourses, int[][] prerequisites) {
	    int[] indegree = new int[numCourses];
	    List<List<Integer>> adjs = new ArrayList<>(numCourses);
	    initialiseGraph(indegree, adjs, prerequisites);
	    return solveByBFS(indegree, adjs);
	}
	
	private void initialiseGraph(int[] indegree, List<List<Integer>> adjs, int[][] prerequisites){
	    int n = indegree.length;
	    while (n-- > 0) adjs.add(new ArrayList<Integer>());
	    for (int[] edge : prerequisites) {
	        indegree[edge[0]]++;
	        adjs.get(edge[1]).add(edge[0]);
	    }
	}
	
	private int[] solveByBFS(int[] indegree, List<List<Integer>> adjs){
	    int[] order = new int[indegree.length];
	    Queue<Integer> toVisit = new ArrayDeque<>();
	    for (int i = 0; i < indegree.length; i++) {
	        if (indegree[i] == 0) toVisit.offer(i);
	    }
	    int visited = 0;
	    while (!toVisit.isEmpty()) {
	        int from = toVisit.poll();
	        order[visited++] = from;
	        for (int to : adjs.get(from)) {
	            indegree[to]--;
	            if (indegree[to] == 0) toVisit.offer(to);
	        }
	    }
	    return visited == indegree.length ? order : new int[0]; 
	}
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
	    int[] indegree = new int[numCourses], order = new int[numCourses];
	    List<List<Integer>> requires = new ArrayList<>(numCourses);
  	   	int n = indegree.length;
  	   	Queue<Integer> toVisit = new ArrayDeque<>();
  	   	
  	   	// Initialize graph.
	    while (n-- > 0) requires.add(new ArrayList<Integer>());
	    for (int[] edge : prerequisites) {
	        indegree[edge[0]]++;
	        requires.get(edge[1]).add(edge[0]);
	    }
	    
	    // BFS.
	    for (int i = 0; i < indegree.length; i++) {
	        if (indegree[i] == 0) toVisit.offer(i);
	    }
	    int visited = 0;
	    while (!toVisit.isEmpty()) {
	        int from = toVisit.poll();
	        order[visited++] = from;
	        for (int to : requires.get(from)) {
	            indegree[to]--;
	            if (indegree[to] == 0) toVisit.offer(to);
	        }
	    }
	    return visited == indegree.length ? order : new int[0]; 
	}
	
	public int[] findOrderV1(int numCourses, int[][] prerequisites) {
		List<List<Integer>> requires = new ArrayList<>(numCourses);
	    int[] indegree = new int[numCourses];
	    int[] order = new int[numCourses];
	    Queue<Integer> visitable = new LinkedList<Integer>();
	    int n = indegree.length, visitedCount = 0;
	    
	    // Initialize graph
	    while (n-- > 0) requires.add(new ArrayList<Integer>());
	    for (int[] edge : prerequisites) {
	        indegree[edge[0]]++;
	        requires.get(edge[1]).add(edge[0]);
	    }
	    
	    for (int i=0; i<indegree.length; i++) {
	        if (indegree[i] == 0) visitable.offer(i);
	    }
	    while (!visitable.isEmpty()) {
	        int from = visitable.poll();
	        order[visitedCount++] = from;
	        for (int to : requires.get(from)) {
	            indegree[to]--;
	            if (indegree[to] == 0) visitable.offer(to);
	        }
	    }
	    return (visitedCount == numCourses) ? order : new int[numCourses];
    }
	
	
}
