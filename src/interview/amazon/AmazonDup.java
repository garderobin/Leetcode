package interview.amazon;

import java.util.HashSet;

public class AmazonDup {
	static boolean[][][] f;
	
	public static boolean check(int[][] matrix, int k) {		
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		if (k == 0) {
			return true;
		}
		if (k > matrix.length + matrix[0].length - 2) {
			// 查全体重复
			return checkAllEleDup(matrix, k, 0, 0);
		}
				
		int i = 0, j = 0, rows = matrix.length, cols = matrix[0].length, m = cols + rows - i - 2 - k;
		int min = Math.min(rows, cols);
		f = new boolean[rows][cols][k];
		
		for (i = 0; i < min; i++, m--) {		
//			for (j = i; j < cols; j++) {
			for (j = i; j < m && j < cols; j++) {
				if (helper(matrix, k, i, j)) {
					return true;
				}
			}
			if (j == m && checkAllEleDup(matrix, k, i, j)) {
				return true;
			}
			for (j = i + 1; j < m && j < rows; j++) {
				if (helper(matrix, k, j, i)) {
					return true;
				}
			}	
			if (j == m && checkAllEleDup(matrix, k, i, j)) {
				return true;
			}
		}
		
		if (rows < cols) {
			for (i = rows - 1, j = rows - 1; j < cols; j++) {
				if (helper(matrix, k, i, j)) {
					return true;
				}
			}
		} else if (rows > cols) {
			for (i = cols - 1, j = cols - 1; j < rows; j++) {
				if (helper(matrix, k, j, i)) {
					return true;
				}
			}
		}
		
		return helper(matrix, k, 0, 0);
	}
	
	private static boolean helper(int[][] matrix, int k, int x, int y) {
//		if (k > matrix.length - x + matrix[0].length - y - 3) {
//			// 查全体重复
//			return checkAllEleDup(matrix, k, x, y);
//		}
		
		//if (boolean[][][] f)
		HashSet<Integer> visited = new HashSet<Integer>();
		int i = 0, j = 0, rows = matrix.length, cols = matrix[0].length, 
				imax = (rows < x + k) ? rows : x + k, jmax = (cols < y + k) ? cols : y + k;
		for (i = x; i < imax; i++) {
			for (j = y; j - y < k - i + x + 1 && j < jmax; j++) {
				if (!visited.add(matrix[i][j])) {
					return true;
				}
			}
		}
		if (x < rows - 1 && y < cols - 1) {
			return helper(matrix, k, x + 1, y + 1);
		}
		
		return false;
	}
	
	private static boolean checkAllEleDup(int[][] matrix, int k, int x, int y) {
		HashSet<Integer> visited = new HashSet<Integer>();
		int i = 0, j = 0, rows = matrix.length, cols = matrix[0].length;
		for (i = x; i < rows; i++) {
			for (j = y; j < cols; j++) {
				if (!visited.add(matrix[i][j])) {
					return true;
				}
			}
		}
		return false;
	}
	
}
