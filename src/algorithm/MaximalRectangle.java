package algorithm;

public class MaximalRectangle {
	/**
	 * height counts the number of successive '1's above (plus the current one). 
	 * The value of left & right means the boundaries of the rectangle 
	 * which contains the current point with a height of value height.
	 * 理解了但还不能复原。
	 * @param matrix
	 * @return
	 */
	public int maximalRectangleDiscussion(char[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int m = matrix.length, n = matrix[0].length;
		int[] left = new int[n], right = new int[n], height = new int[n];
		for (int i = 0; i < n; i++) {
			left[i] = 0;
			right[i] = n;
			height[i] = 0;
		}
	    
	    int maxA = 0, cur_left, cur_right, i, j;
	    for(i = 0; i < m; i++) {
	        cur_left = 0;
	        cur_right = n; 
	        for(j = 0; j < n; j++) { // compute height (can do this from either side)
	            if(matrix[i][j] == '1') height[j]++; 
	            else height[j] = 0;
	        }
	        for(j = 0; j < n; j++) { // compute left (from left to right)
	            if(matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
	            else {
	            	left[j] = 0; 
	            	cur_left = j + 1;
	            }
	        }
	        // compute right (from right to left)
	        for(j = n - 1; j >= 0; j--) {
	            if(matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
	            else {
	            	right[j] = n; 
	            	cur_right = j;
	            }    
	        }
	        // compute the area of rectangle (can do this from either side)
	        for(j = 0; j < n; j++)
	            maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);
	    }
	    return maxA;
	}
	
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int rows = matrix.length, cols = matrix[0].length, maxR = 0, i, temp = 0;
		int [][][] f;
		if (rows >= cols) {
			f = new int[rows][cols][cols];
			
			// First row.
			for (i = 0; i < rows; i++) {
				f[0][i][1] = matrix[0][i];
				temp += matrix[0][i];
			}
			maxR = (temp == 0) ? 0 : 1;
			
		}
        
		return maxR;
    }
}
