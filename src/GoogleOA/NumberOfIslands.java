package GoogleOA;

import java.util.HashSet;
import java.util.Set;

public class NumberOfIslands {
	
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) { return 0; }
		int rst = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					explore(grid, i, j);
					rst++;
				}
			}
		}
		return rst;
	}
	
	private void explore(char[][] grid, int x, int y) {
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		grid[x][y] = 'x';
		for (int i = 0, r = grid.length, c = grid[0].length; i < dx.length; i++) {
			int xm = x + dx[i], ym = y + dy[i];
			if (xm >= 0 && xm < r && ym >= 0 && ym < c && grid[xm][ym] == '1') { //等于‘1’这个条件千万别忘了带上
				explore(grid, xm, ym);
			}
		}
	}
	
	/**
	 * 这种做法是原始的union found,没有用递归来优化
	 * @param grid
	 * @return
	 */
	public int numIslandsUnionFound(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) { return 0; }
		int r = grid.length, c = grid[0].length;
        long[][] islands = new long[r][c];
        
        // Initialize islands
        for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        		islands[i][j] = getIslandID(grid, i, j);
        	}
        }
        
        // Union find
        for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        		if (grid[i][j] == '1') {
        			// union its east, west, north, south neighbor.
        			if (i > 0) { 	union(grid, i, j, i-1, j, islands); }
        			if (i < r-1) { 	union(grid, i, j, i+1, j, islands); } 
        			if (j > 0) { 	union(grid, i, j, i, j-1, islands); }
        			if (j < c-1) { 	union(grid, i, j, i, j+1, islands); }
        		} 
        	}
        }
        
        // Count islands
        Set<Long> uniqueIslands = new HashSet<Long>();
        for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        	    if (islands[i][j] >= 0) { uniqueIslands.add(islands[i][j]); }
        	}
        }
        
		return uniqueIslands.size();
    }
	
	private void union(char[][] grid, int x1, int y1, int x2, int y2, long[][] islands) {
		int mod = grid[0].length;
		long id1 = find(grid, x1, y1, islands), id2 = find(grid, x2, y2, islands);
		if (!(id1 < 0 || id2 < 0)) {
			islands[(int)(id2/mod)][(int)(id2%mod)] = id1; //这里非常关键，顺序很重要！调用的时候要小心
		}
	}
	
	private long find(char[][] grid, int x, int y, long[][] islands) {
		if (islands[x][y] == getIslandID(grid, x, y)) { return islands[x][y]; }
		int mod = grid[0].length;
		long id = islands[x][y];
		islands[x][y] = find(grid, (int)(id/mod), (int)(id%mod), islands);
		return islands[x][y];
	}
	
	private long getIslandID(char[][] grid, int x, int y) {
		return (grid[x][y] == '1') ? x * grid[0].length + y : -1; 
	}
}
