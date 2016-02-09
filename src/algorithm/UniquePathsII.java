package algorithm;

public class UniquePathsII {
	
	/**
	 * 这个是直接操作obstacleGrid, 所以extra space O(1).
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstaclesDiscuss2(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0) return 0;

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else if(i == 0 && j == 0)
                    obstacleGrid[i][j] = 1;
                else if(i == 0)
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] * 1;// For row 0, if there are no paths to left cell, then its 0,else 1
                else if(j == 0)
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] * 1;// For col 0, if there are no paths to upper cell, then its 0,else 1
                else
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }

        return obstacleGrid[rows - 1][cols - 1];

    }
	
	public int uniquePathsWithObstaclesDiscuss(int[][] obstacleGrid) {
	    int width = obstacleGrid[0].length;
	    int[] dp = new int[width];
	    dp[0] = 1;
	    for (int[] row : obstacleGrid) {
	        for (int j = 0; j < width; j++) {
	            if (row[j] == 1)
	                dp[j] = 0;
	            else if (j > 0)
	                dp[j] += dp[j - 1];
	        }
	    }
	    return dp[width - 1];
	}
	
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int[][] A = obstacleGrid;
		if (A == null || A.length == 0 || A[0].length == 0) {
			return 0;
		}
		int rows = A.length, cols = A[0].length, i, j, temp = 1;
        int[][] f = new int[rows][cols];
        for (i = 0; i < rows; i++) {
        	temp &= A[i][0] ^ 1;
        	f[i][0] = temp;
        }
        temp = 1;
        for (j = 0; j < cols; j++) {
        	temp &= A[0][j] ^ 1;
        	f[0][j] = temp;
        }
        for (i = 1; i < rows; i++) {
        	for (j = 1; j < cols; j++) {
        		if (A[i][j] == 1) {
        			f[i][j] = 0;
        		} else {
        			f[i][j] = f[i - 1][j] + f[i][j - 1];
        		}
        	}
        }
        return f[rows - 1][cols - 1];
    }
	
	public static void main(String[] args) {
		int[][] A = {{1, 0}};
		System.out.println(uniquePathsWithObstacles(A));
	}
}
