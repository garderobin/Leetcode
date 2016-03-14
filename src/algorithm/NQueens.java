package algorithm;

import java.util.ArrayList;
import java.util.List;

/* See C++ version. */
public class NQueens {
	class Pos {
		int x;
		int y;
		
		public Pos() {
			
		}
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static List<List<String>> solveNQueens(int n) {
        List<List<String>> rst = new ArrayList<List<String>>();
        //int[] y = new int[n];
//        int[] y = solveNQueenPosition(n);
//        int i, j, k;
//        for (i = 0; i < n; i++) {
//        	for (j = 0; j < n)
//        }
//        return rst;
        
        //ArrayList<Pos> qs = new ArrayList<Pos>(n);
        
        
        //return helper(n, rst, new ArrayList<Pos>(n), new int[n]);
        return helper(n, rst, new ArrayList<String>(n), new int[n]);
    }

//	private static int[] solveNQueenPosition(int n) {
//		int[] y = new int[n]; //the y-index for each queen on row 0 ~ n-1
//		return y;
//	}
	
	private static List<List<String>> helper(int n, List<List<String>> rst, List<String> sol, int[] y) {
		int i, j;
        for (i = 0; i < n; i++) {
        	for (j = 0; j < n; j++) {
        		
        	}
        }
		return rst;
	}
	
}
