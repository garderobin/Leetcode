package interview.snapchat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * snap with weights, snap has next snaps list
 * input是start snap, max steps求出一个max total weights.
 * Ask: allow loops/cycles? 这里默认没有loop
 * 
 * BFS的方法做就可以 (因为指定了k的步数)
 * 这个题的DFS做法参见DAGMaxWeightPath.java
 * follow up: start snap是一个list怎么办
 * follow up: 如果每个snap都可以是start nap怎么办， memorization search
 *
 */
public class SnapsMaxWeightPath {
	
	public static void main(String[] args) {
//		testSingleStart();
		testMultiStarts();
	}

	// BFS, O(N^2)
	public static int maxTotalWeights(WeightedSnap startSnap, int k) {
		Deque<WeightedSnap> q0 = new ArrayDeque<>(), q1 = new ArrayDeque<>();
		Deque<Integer> qw0 = new ArrayDeque<>(), qw1 = new ArrayDeque<>();
		q0.offer(startSnap);
		qw0.offer(startSnap.weight);
		int maxTotal = startSnap.weight;
		for (int i = 0; i < k; ++i) { // k steps
			while (!q0.isEmpty()) {
				WeightedSnap snap = q0.poll();
				int w = qw0.poll();				
				for (WeightedSnap nb: snap.nextSnaps) {
					q1.addLast(nb);
					int curTotal = w + nb.weight;
					qw1.addLast(curTotal);
					if (i == k-1 && curTotal > maxTotal) maxTotal = curTotal;
				}
			}
			q0 = q1; //放心用，java传引用而不是地址
			qw0 = qw1;
			q1 = new ArrayDeque<>();
			qw1 = new ArrayDeque<>();
		}
		return maxTotal;
	}
	
	
	public static int maxTotalWeights(List<WeightedSnap> startList, int k) {
		Deque<WeightedSnap> q0 = new ArrayDeque<>(), q1 = new ArrayDeque<>();
		Deque<Integer> qw0 = new ArrayDeque<>(), qw1 = new ArrayDeque<>();
		int maxTotal = Integer.MIN_VALUE;
		for (WeightedSnap start: startList) {
			q0.offer(start);
			qw0.offer(start.weight);
			if (start.weight > maxTotal) maxTotal = start.weight;
		}
		for (int i = 0; i < k; ++i) { // k steps
			while (!q0.isEmpty()) {
				WeightedSnap snap = q0.poll();
				int w = qw0.poll();				
				for (WeightedSnap nb: snap.nextSnaps) {
					q1.addLast(nb);
					int curTotal = w + nb.weight;
					qw1.addLast(curTotal);
					if (i == k-1 && curTotal > maxTotal) maxTotal = curTotal;
				}
			}
			q0 = q1; //放心用，java传引用而不是地址
			qw0 = qw1;
			q1 = new ArrayDeque<>();
			qw1 = new ArrayDeque<>();
		}
		return maxTotal;
	}
	
	/**
	 * 其实只是一个list作为start的情况的特殊化，即startList就是全体list
	 */
	public static int maxTotalWeightsNoStart(List<WeightedSnap> snaps, int k) {
		return maxTotalWeights(snaps, k);
	}
	
	public static void testSingleStart() {
		int k = 2, snapNum = 10;
		WeightedSnap[] s = new WeightedSnap[snapNum];
		for (int i = 0; i < snapNum; ++i) s[i] = new WeightedSnap(i);		
		s[0].nextSnaps = Arrays.asList(s[2], s[4], s[6]);
		s[2].nextSnaps = Arrays.asList(s[3], s[9]);
		s[4].nextSnaps = Arrays.asList(s[5], s[8]);
		s[6].nextSnaps = Arrays.asList(s[1], s[7]);
		System.out.println(maxTotalWeights(s[0], k));
	}
	
	public static void testMultiStarts() {
		int k = 2, snapNum = 20;
		WeightedSnap[] s = new WeightedSnap[snapNum];
		for (int i = 0; i < snapNum; ++i) s[i] = new WeightedSnap(i);	
		s[0].nextSnaps = Arrays.asList(s[12], s[14], s[16]);
		s[12].nextSnaps = Arrays.asList(s[3], s[19]);
		s[14].nextSnaps = Arrays.asList(s[5], s[18]);
		s[16].nextSnaps = Arrays.asList(s[1], s[17]);
		s[8].nextSnaps = Arrays.asList(s[2], s[4], s[6]);
		s[2].nextSnaps = Arrays.asList(s[1], s[13]);
		s[4].nextSnaps = Arrays.asList(s[5], s[19]);
		s[6].nextSnaps = Arrays.asList(s[7], s[14]);
		s[9].nextSnaps = Arrays.asList(s[1], s[3], s[7]);
		s[1].nextSnaps = Arrays.asList(s[2], s[5]);
		s[3].nextSnaps = Arrays.asList(s[17], s[12]);
		s[7].nextSnaps = Arrays.asList(s[4], s[10]);
		System.out.println(maxTotalWeights(Arrays.asList(s[0], s[8], s[9]), k));
	}
	
	public static void testNoStart() {
		
	}
}

/**
 * 这个数据结构等于UndirectedGraphNode
 */
class WeightedSnap {
	int weight;
    List<WeightedSnap> nextSnaps;
    WeightedSnap(int weight){
    	this.weight = weight;
    	nextSnaps = new ArrayList<WeightedSnap>();
    }
}