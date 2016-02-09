package algorithm;

public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int row = 0, col = 0, num = 1, start = 0, end = n-1;
        for (start = 0, end = n-1; start < end; end = n-1-(++start)) {
        	// first inclusive, last exclusive style        	
        	for (col = start; col < end; col++) {	// rightward
        		matrix[start][col] = num++;
        	}
        	for (row = start; row < end; row++) {	// downward
        		matrix[row][end] = num++;
        	}
        	for (col = end; col > start; col--) {	// leftward
        		matrix[end][col] = num++;
        	}
        	for (row = end; row > start; row--) {	// upward
        		matrix[row][start] = num++;
        	}
        }
        
        if (start == end) {
        	matrix[start][end] = num++;
        }
        
        return matrix;
    }
}
