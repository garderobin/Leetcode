package algorithm;

public class MaximumSquare {
	public int maximalSquare(char[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int rows = A.length;
        int cols = A[0].length;
        int[][] f = new int[rows][cols];
        
        int i, j = 0;
        int maxLen = 0;
        for (i = 0; i < rows; i++) {
        	for (j = 0; j < cols; j++) {
        		if (i == 0 && j == 0) {
        			f[0][0] = (A[0][0] == '0') ? 0 : 1;
        		} else if (i == 0) {
        			f[0][j] = (A[0][j] == '0') ? f[0][j-1] : 1;
        		} else if (j == 0) {
        			f[i][0] = (A[i][0] == '0') ? f[i-1][0] : 1;
        		} else if (A[i][j] == '0') {
        			f[i][j] = 0;        			
        		} else {
        			f[i][j] = min(f[i-1][j-1], f[i][j-1], f[i-1][j]) + 1;
        		}
        		maxLen = Math.max(maxLen, f[i][j]);
        	}
        }
        
        return -1;
    }
	
	public static int min(int a, int b, int c) {
		return Math.min( Math.min(a, b), Math.min(b, c) );
	}
	
}
