package algorithm;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> spiral = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return spiral;
		}
		
		int rowDown = matrix.length - 1, colRight = matrix[0].length - 1, rowUp = 0, colLeft = 0, i = 0;
		
		for (; rowUp < rowDown && colLeft < colRight; rowUp++, rowDown--, colLeft++, colRight--) {
			// All four directions are in first inclusive, last exclusive style traversal.			
			for (i = colLeft; i < colRight; i++) { // rightward
				spiral.add(matrix[rowUp][i]);
			}
			for (i = rowUp; i < rowDown; i++) { // downward
				spiral.add(matrix[i][colRight]);
			}
			for (i = colRight; i > colLeft; i--) { // leftward
				spiral.add(matrix[rowDown][i]);
			}
			for (i = rowDown; i > rowUp; i--) { // upward
				spiral.add(matrix[i][colLeft]);
			}
			
		}
		if (rowDown - rowUp + colRight - colLeft == 0) {
		    spiral.add(matrix[rowDown][colRight]);
		    return spiral;
		} else if (rowDown == rowUp) {
			for (i = colLeft; i <= colRight; i++) {
				spiral.add(matrix[rowDown][i]);
			}
		} else if (colLeft == colRight) { 
			for (i = rowUp; i <= rowDown; i++) {
				spiral.add(matrix[colRight][i]);
			}
		}
        
		return spiral;
    }
}
