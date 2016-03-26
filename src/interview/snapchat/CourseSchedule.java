package interview.snapchat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
	
	/**
	 * BFS. 用adjacent matrix 和indegree表作为图形辅助数据结构，queue用来表示当前没有任何入度的点集。
	 * 尽量避免多余的动态序列，才能快速且优美。
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinishDiscussion2(int numCourses, int[][] prerequisites) {
	    int[][] matrix = new int[numCourses][numCourses]; // i -> j
	    int[] indegree = new int[numCourses];

	    // Initialize adjacent matrix
	    for (int i=0; i<prerequisites.length; i++) {
	        int ready = prerequisites[i][0];
	        int pre = prerequisites[i][1];
	        if (matrix[pre][ready] == 0) // prevent duplicate case
	            indegree[ready]++; 
	        matrix[pre][ready] = 1;
	    }

	    int count = 0;
	    Queue<Integer> queue = new ArrayDeque<Integer>();
	    for (int i=0; i<indegree.length; i++) {
	        if (indegree[i] == 0) queue.offer(i);
	    }
	    while (!queue.isEmpty()) {
	        int course = queue.poll();
	        count++;
	        for (int i=0; i<numCourses; i++) {
	            if (matrix[course][i] != 0) {
	                if (--indegree[i] == 0)
	                    queue.offer(i);
	            }
	        }
	    }
	    return count == numCourses;
	}
	
	/**
	 * DFS, 不要拘泥于topo sort.
	 * 图论用recursion远比用iteration可靠得多
	 * 因为iteration很难控制对iteration的大量操作
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinishDiscussion(int numCourses, int[][] prerequisites) {
        @SuppressWarnings("unchecked")
		ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList<Integer>();

        boolean[] visited = new boolean[numCourses];
        for(int i=0; i<prerequisites.length;i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for(int i=0; i<numCourses; i++){
            if(!dfs(graph, visited, i)) return false;
        }
        return true;
    }

    private boolean dfs(ArrayList<Integer>[] graph, boolean[] visited, int course){
        if(visited[course]) return false;
        else visited[course] = true;

        for(int i=0; i<graph[course].size();i++){
            if(!dfs(graph, visited, (int)graph[course].get(i))) return false;
        }
        visited[course] = false;
        return true;
    }
	
	/**
	 * topological sort to detect a cycle in a graph.
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses < 1 || prerequisites == null || prerequisites.length < 2) { return true; }
		/*	L ← Empty list that will contain the sorted elements
			S ← Set of all nodes with no incoming edges */
		int advLen = prerequisites.length;
		LinkedList<Integer> s = new LinkedList<Integer>(), advList, preList;
		HashMap<Integer, LinkedList<Integer>> requires = new HashMap<>(numCourses), supports = new HashMap<>(numCourses);
		
		// Initialization
		for (int i = 0; i < advLen; i++) {
			int adv = prerequisites[i][0];
			int pre = prerequisites[i][1];
			advList = supports.get(pre);
			preList = requires.get(adv);
			if (advList == null || advList.size() == 0) {
				advList = new LinkedList<Integer>();
			}
			advList.add(adv);
			if (preList == null || preList.size() == 0) {
				preList = new LinkedList<Integer>();
			}
			preList.add(pre);
			requires.put(adv, preList);
			supports.put(pre, advList);			
		}
		
		// Find all nodes with no incoming edges
		for (int i = 0; i < numCourses; i++) {
			if (!supports.containsKey(i)) {
				s.add(i);
			}
		}
		
		// Topological sort using Kahn's algorithm
		while (!s.isEmpty()) {
			int adv = s.poll();
			preList = requires.get(adv);
			for (Integer pre : preList) { //for each node pre with an edge e from adv to pre do
				//remove edge (adv,pre) from the graph
				
				advList = supports.get(pre);
				advList.remove(advList.indexOf(adv)); // java.util.ConcurrentModificationException!!!
				preList = requires.get(adv);
				preList.remove(preList.indexOf(pre)); // java.util.ConcurrentModificationException!!!
				
				// if pre has no other incoming edges then insert pre into S
				if (advList.isEmpty()) {
					s.add(pre);
					supports.remove(pre);
				} else {
					supports.put(pre, advList);
				}
				
				if (preList.isEmpty()) {
					requires.remove(adv);
				} else {
					requires.put(adv, preList);
				}
				requires.put(adv, preList);
			}
		}
		
		return  requires.size() == 0;
    }
}
