package algorithm;

public class SetMatrixZeroes {
	
	/**
	 * O(1) space.
	 * store states of each row in the first of that row, and store states of each column in the first of that column. 
	 * Because the state of row0 and the state of column0 would occupy the same cell, 
	 * I let it be the state of row0, and use another variable "col0" for column0. 
	 * In the first phase, use matrix elements to set states in a top-down way. 
	 * In the second phase, use states to set matrix elements in a bottom-up way.
	 * @param matrix
	 */
	public void setZeroesDiscussion(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}		
		int rows = matrix.length, cols = matrix[0].length, i, j, col0 = matrix[0][0];
		for (i = 0; i < rows; i++) {
	        if (matrix[i][0] == 0) col0 = 0;
	        for (j = 1; j < cols; j++)
	            if (matrix[i][j] == 0)
	                matrix[i][0] = matrix[0][j] = 0;
	    }

	    for (i = rows - 1; i >= 0; i--) {
	        for (j = cols - 1; j >= 1; j--)
	            if (matrix[i][0] == 0 || matrix[0][j] == 0)
	                matrix[i][j] = 0;
	        if (col0 == 0) matrix[i][0] = 0;
	    }
	}
	
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}		
		int m = matrix.length, n = matrix[0].length, i, j;
		boolean[] bm = new boolean[m];
		boolean[] bn = new boolean[n];
		for (i = 0; i < m; i++) {			
			for (j = 0; j < n; j++) {				
				if (matrix[i][j] == 0) {
					bm[i] = true;
					bn[j] = true;
				} 
			}
		}
		
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				if (bm[i] || bn[j]) {
					matrix[i][j] = 0;
				}
			}
		}
    }
}
