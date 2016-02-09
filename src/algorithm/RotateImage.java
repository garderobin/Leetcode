package algorithm;

public class RotateImage {
	
	/***
	 * 一次沿对角线的轴对称，再加一次沿中线的轴对称
	 * 空间想象能力
	 * @param matrix
	 */
	public void rotateDiscussion(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
	
	public void rotate(int[][] matrix) {
        int n = matrix.length, circle = n / 2, col, i, xnext, ynext, x, y, cur, next, offset;
        for (offset = 0; offset < circle; offset++, n--) {         	
        	for (col = offset; col < n - 1; col++) {
        		x = offset;
            	y = col;
            	cur = matrix[x][y];
        		for (i = 0; i < 4; i++) {
        			xnext = y;
            		ynext = matrix.length - 1 - x;
            		next = matrix[xnext][ynext];
            		matrix[xnext][ynext] = cur;
            		x = xnext;
            		y = ynext;
            		cur = next;
        		}
        	}
        }
        
    }
	
	
}
