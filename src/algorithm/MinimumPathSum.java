package algorithm;

public class MinimumPathSum {
	
	/**
	 * 行和列千万不能搞错
	 * 保险起见不要用m, n之类的不明确表达
	 * 一切都用明确表达，cols, rows
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int i, j, rows = grid.length, cols = grid[0].length;
        int[][] f = new int[rows][cols];
        f[0][0] = grid[0][0];
        for (i = 1; i < cols; i++) {
        	f[0][i] = f[0][i-1] + grid[0][i];
        }
        for (j = 1; j < rows; j++) {
        	f[j][0] = f[j-1][0] + grid[j][0];
        }
        for (i = 1; i < cols; i++) {
        	for (j = 1; j < rows; j++) {
        		f[i][j] = Math.min(f[i-1][j], f[i][j-1]) + grid[i][j];
        	}
        }
        
        return f[rows-1][cols-1];
    }
}
