package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import dataStructure.Point;

public class MaxPointsOnALine {
	
	public static int maxPointsDiscussion(Point[] points) {
        if (points==null) return 0;
        if (points.length<=2) return points.length;

        Map<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
        int result=0;
        for (int i=0;i<points.length;i++){ 
            map.clear();
            int overlap=0,max=0;
            for (int j=i+1;j<points.length;j++){
                int x=points[j].x-points[i].x;
                int y=points[j].y-points[i].y;
                if (x==0&&y==0){
                    overlap++;
                    continue;
                }
                int gcd=generateGCD(x,y);
                if (gcd!=0){
                    x/=gcd;
                    y/=gcd;
                }

                if (map.containsKey(x)){
                    if (map.get(x).containsKey(y)){
                        map.get(x).put(y, map.get(x).get(y)+1);
                    }else{
                        map.get(x).put(y, 1);
                    }                       
                }else{
                    Map<Integer,Integer> m = new HashMap<Integer,Integer>();
                    m.put(y, 1);
                    map.put(x, m);
                }
                max=Math.max(max, map.get(x).get(y));
            }
            result=Math.max(result, max+overlap+1);
        }
        return result;


    }
    private static int generateGCD(int a,int b){

        if (b==0) return a;
        else return generateGCD(b,a%b);

    }
	
	
	public static int maxPoints(Point[] points) {
		if (points == null) { return 0; }
		else if (points.length < 3) { return points.length; }
		
		int max = 1;
		Map<Double, Set<Double>> lineMap = new HashMap<>(); // Key: slope, Value: intercept with y-axis.
        Map<Integer, List<Integer>> v = new HashMap<>(), vd = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> pc = new HashMap<>();
        Queue<Integer> xq = new PriorityQueue<>();
        
        // count max points on vertical or horizon lines, O(N) time.
        for (Point p: points) {
        	if (pc.containsKey(p.y)) { // thinking about duplicates
        		Map<Integer, Integer> xcm = pc.get(p.y); // x count map
        		if (xcm.containsKey(p.x)) { xcm.put(p.x, xcm.get(p.x) + 1); }
        		else 					  { xcm.put(p.x, 1); }
        		pc.put(p.y, xcm);
        		max = Math.max(pc.get(p.y).size(), max);
        	} else {
        		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        		m.put(p.x, 1);
        		pc.put(p.y, m);
        	}  
        	
        	if (v.containsKey(p.x)) {
        		vd.get(p.x).add(p.y);
        		if (pc.get(p.y).get(p.x) == 1) { v.get(p.x).add(p.y); }
        		max = Math.max(vd.get(p.x).size(), max);
        	} else {
        		List<Integer> q = new ArrayList<Integer>();
        		q.add(p.y);
        		v.put(p.x, q);
        		vd.put(p.x, new ArrayList<Integer>(q));
        		xq.offer(p.x);
        	}
        	
        }
        
        // serializing, O(N)
        int[] vpos = new int[xq.size()]; // the indexes of x from left to right
        for (int i = 0; i < vpos.length; ++i) {  vpos[i] = xq.poll();  }
        
        // count max points on every other possible slope, O(N^3)
        for (int i = 0, xn = vpos.length; i < xn; ++i) {
			int x0 = vpos[i];
			for (int y0: v.get(x0)) {
				for (int j = i+1; j < xn; ++j) {
					int x1 = vpos[j];
					for (int y1: v.get(x1)) {
						double slope = (double)(y1 - y0) / (x1 - x0), intercept = (double)(y0 * x1 - y1 * x0) / (x1 - x0);
						if (!lineMap.containsKey(slope)) { lineMap.put(slope, new HashSet<Double>()); }
						if (lineMap.get(slope).add(intercept)) { // a new line found whose left-most point is (xs, ys)
							int cur = pc.get(y0).get(x0) + pc.get(y1).get(x1);
							
							//这个循环或许还可以优化？
							for (int k = j+1; k < xn; ++k) { // for every right x, check if there exists a expected y.
								int xe = vpos[k], ye = (int) (Math.round((double)((y1-y0) * (xe-x0)) / (x1-x0)) + y0);
								if (pc.containsKey(ye) && pc.get(ye).containsKey(xe)) { 
									if ( (y1-y0)*(xe-x0) == (x1-x0)*(ye-y0) ) { // in case of inaccuracy caused by dividing.
										cur += pc.get(ye).get(xe); 
									}
								}
							}
							max = (cur > max) ? cur : max;
						}
					}
				}
				
			}
		}
        
        
        return max;
    }
	
	
	public static void main(String[] args) {
//		int[][] test = {{0,0},{-1,-1},{2,2}};
//		int[][] test = {{0,0},{1,1},{0,0}};
//		int[][] test = {{3,1}, {12, 3}, {3, 1}, {-6 ,-1}};
//		int[][] test = { {560,248},{0,16},{30,250},{950,187},{630,277},{950,187},{-212,-268},{-287,-222},{53,37},{-280,-100},
//		                 {-1,-14},{-5,4},{-35,-387},{-95,11},{-70,-13},{-700,-274},{-95,11},{-2,-33},{3,62},{-4,-47},{106,98},
//		                 {-7,-65},{-8,-71},{-8,-147},{5,5},{-5,-90},{-420,-158},{-420,-158},{-350,-129},{-475,-53},{-4,-47},{-380,-37},
//		                 {0,-24},{35,299},{-8,-71},{-2,-6},{8,25},{6,13},{-106,-146},{53,37},{-7,-128},{-5,-1},{-318,-390},{-15,-191},{-665,-85},
//		                 {318,342},{7,138},{-570,-69},{-9,-4},{0,-9},{1,-7},{-51,23},{4,1},{-7,5},{-280,-100},{700,306},{0,-23},{-7,-4},{-246,-184},
//		                 {350,161},{-424,-512},{35,299},{0,-24},{-140,-42},{-760,-101},{-9,-9},{140,74},{-285,-21},{-350,-129},{-6,9},{-630,-245},{700,306},
//		                 {1,-17},{0,16},{-70,-13},{1,24},{-328,-260},{-34,26},{7,-5},{-371,-451},{-570,-69},{0,27},{-7,-65},{-9,-166},{-475,-53},{-68,20},{210,103},
//		                 {700,306},{7,-6},{-3,-52},{-106,-146},{560,248},{10,6},{6,119},{0,2},{-41,6},{7,19},{30,250}};
//		int[][] test = {{1,1}, {1,1}, {1,1}};
		int[][] test = {{0,0},{1,1},{1,-1}};
		Point[] points = new Point[test.length];
		for (int i = 0; i < test.length; ++i) {
			points[i] = new Point(test[i][0], test[i][1]);
		}
		System.out.println(maxPoints(points));
	}
}
