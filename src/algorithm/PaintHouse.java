package algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class PaintHouse {
	public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) { return 0; }
        int a = costs[0][0], b = costs[0][1], c = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            int ta = Math.min(b, c) + costs[i][0];
            int tb = Math.min(a, c) + costs[i][1];
            int tc = Math.min(a, b) + costs[i][2];
            a = ta; b = tb; c = tc;
        }
        
        return Math.min(Math.min(a, b), c);
    }
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		final ArrayList<Integer> fl = new ArrayList<Integer>();
		Collections.addAll(list, 1,2,3,4,5,6,7,8);
		Collections.addAll(fl, 1,2,3,4,5,6,7,8);
		System.out.println(list);
		System.out.println(fl);
	}
}
