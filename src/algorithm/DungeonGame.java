package algorithm;

public class DungeonGame {
	public int calculateMinimumHP(int[][] dungeon) {
		if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) { return 0; }		
		int rows = dungeon.length, cols = dungeon[0].length, i, j, right, down;
        int[][] h = new int[rows][cols];
        h[rows-1][cols-1] = Math.max(1 - dungeon[rows-1][cols-1], 1);
        for (i = rows - 2; i >= 0; i--) {
        	h[i][cols-1] = Math.max(h[i+1][cols-1] - dungeon[i][cols-1], 1);
        }
        for (j = cols - 2; j >= 0; j--) {
        	h[rows-1][j] = Math.max(h[rows-1][j+1] - dungeon[rows-1][j], 1);
        }
        for (i = rows - 2; i >= 0; i--) {
        	for (j = cols - 2; j >= 0; j--) {
        		right = Math.max(h[i][j+1] - dungeon[i][j], 1);
        		down = Math.max(h[i+1][j] - dungeon[i][j], 1); //过程比较切不可以在最后才和阈值做比，想想青蛙过河
        		h[i][j] = Math.min(right, down);
        	}
        }
        return h[0][0];
    }
	
	/**
	 * 题型：如何满足过程中边界条件的DP问题。
	 * 给定过程中允许出现的min, 求 max{过程中实际出现的min}
	 * 这里求左上角为最终值，自然要由右下角作为起始值。由这一点就应该马上想到，此题需要反推:
	 * 对每一个位置（i,j）需要的最小血量如果是h(i,j),
	 * 它如果能成功往右走需要的最小血量 经过本格掉血 >= 右格需要的最小血量；
	 * 同样，如果能成功往下走需要的最小血量 经过本格掉血 >= 下格需要的最小血量。
	 * 那么h(i,j)就是右、下需要最小血量的min(只需要向对血值要求低的方向走就可以了，另外那个方向直接放弃），同时h(i,j)永远至少为1
	 * 由此我们可以由走出最后一格以后需要的最小血量为1 算出最后一格需要的最小血量
	 * 以此作为初始值，从下往上，从右往左推到左上角即可
	 * 
	 * @param dungeon
	 * @return
	 */
	public int calculateMinimumHPDiscussion(int[][] dungeon) {
	    if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;

	    int m = dungeon.length;
	    int n = dungeon[0].length;

	    int[][] health = new int[m][n];

	    health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

	    for (int i = m - 2; i >= 0; i--) {            
	        health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
	    }

	    for (int j = n - 2; j >= 0; j--) {
	        health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
	    }

	    for (int i = m - 2; i >= 0; i--) {
	        for (int j = n - 2; j >= 0; j--) {
	            int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
	            int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
	            health[i][j] = Math.min(right, down);
	        }
	    }

	    return health[0][0];
	}
}
