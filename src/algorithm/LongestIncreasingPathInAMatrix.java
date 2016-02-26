package algorithm;
public class LongestIncreasingPathInAMatrix {
	public static int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0) { return 0; }
		int row = matrix.length, col = matrix[0].length;
        int[][] lens = new int[row][col];
	    for (int i = 0, curMax = 1; i < row; i++) {
	    	for (int j = 0; j < col; j++) {
	    		curMax = Math.max(curMax, dfs(matrix, lens, i, j));
	    	}
	    }
        return dfs(matrix, lens, 0, 0);
    }
	
	private static int dfs(int[][] matrix, int[][] lens, int r, int c) {
		if (lens[r][c] == 0) {
			int v  = matrix[r][c], l = 1;
			if (r > 0 && v < matrix[r-1][c]) {  // up
				l = 1 + dfs(matrix, lens, r-1, c);
			}
			if (r < matrix.length-1 && v < matrix[r+1][c]) {  // down
				l = Math.max(l, 1 + dfs(matrix, lens, r+1, c)); 
			}
			if (c > 0 && v < matrix[r][c-1]) {  // left 
				l = Math.max(l, 1 + dfs(matrix, lens, r, c-1)); 
			}
			if (c < matrix[0].length - 1 && v < matrix[r][c+1]) { // right
				l = Math.max(l, 1 + dfs(matrix, lens, r, c+1)); 
			}
			lens[r][c] = l;
		}
		return lens[r][c];
	}
}
