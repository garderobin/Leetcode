package interview.snapchat;

import java.util.*;

public class MazeBreakWallII {

	public static void main(String[] args) {
		ArrayList<int[][]> test = new ArrayList<int[][]>();
		test.add(new int[][]{{1,5,5,5,5,5},{5,0,0,0,0,5},{5,0,0,0,0,5},{5,0,0,0,5,9}});
		test.add(new int[][]{{1,5,5},{0,5,0},{0,0,9}});
		test.add(new int[][]{{1,5,5,0,5},{0,5,0,0,0},{0,5,0,5,0},{5,0,5,5,9}});

		for (int[][] t: test) System.out.println(maze(t, 1, 9, 0, 5));
	}
	
	
	/** Compute the minimum number of walls needed to get through the maze. */
	/* DFS with caching: O(NlogN) time, O(N^2) space.*/
	public static int maze(int[][] input, final int LABEL_ENTRANCE, final int LABEL_EXIT, final int LABEL_ROAD, final int LABEL_WALL) {
		if (input == null || input.length == 0 || input[0].length == 0) return 0;
		
		final int[] dir = {0, -1, 0, 1, 0}; // no diagonal move allowed here.
		final int myEntr = 2, myExit = 9, myRoad = 0, myWall = 5;
		int sx = -1, sy = -1, ex = -1, ey = -1, row = input.length, col = input[0].length; 
		int[][] board = new int[row][col];
		
		// Back up the input board and find the start and end point.
		for (int i = 0; i < row; ++i) { 
			for (int j = 0; j < col; ++j) {
				if (input[i][j] == LABEL_ENTRANCE) {
					sx = i; 
					sy = j;
					board[i][j] = myEntr;
				} else if (input[i][j] == LABEL_EXIT) {
					ex = i;
					ey = j;
					board[i][j] = myExit;
				} else if (input[i][j] == LABEL_ROAD) {
					board[i][j] = myRoad;
				} else {
					board[i][j] = myWall;
				}
			}
		}
		
		// Paint start point component with my entrance label which means it needs no wall break to reach.
		if (!paintComponent(board, sx, sy, dir, myRoad, myEntr, myExit)) return 0; 
		
		// Paint end point component with my exit neighbor label thus any one of it is reached equals to the exit is reached.
		if (!paintComponent(board, ex, ey, dir, myRoad, myExit, myEntr)) return 0; 
		
		return minWallBreak(board, sx, sy, dir, myEntr, myExit, Integer.MAX_VALUE, 0);
	}
	
	
	/** 
	 * Paint a given label to positions that has a specific original label and can be reached from the start point without breaking walls.
	 * If a stop label is found, stop painting and return false.
	 */
	/* Union find: O(NlogN) time, O(NlogN) space*/
	private static boolean paintComponent(int[][] board, int sx, int sy, 
			final int[] dir, final int lbOrig, final int lbPaint, final int lbStop) {
		
		for (int i = 1, row = board.length, col = board[0].length; i < dir.length; ++i) {
			int x = sx + dir[i-1], y = sy + dir[i];
			if (x >= 0 && x < row && y >= 0 && y < col) {
				if (board[x][y] == lbStop) return false;
				else if (board[x][y] == lbOrig) {
					board[x][y] = lbPaint;
					if (!paintComponent(board, x, y, dir, lbOrig, lbPaint, lbStop)) return false;
				}
			}
		}
		return true;
	}
	
	
	/** Count minimum number of walls to break for building a path from the given start position to end component.*/
	/* DFS: O(NlogN) time, O(NlogN) space*/
	private static int minWallBreak(int[][] board, int sx, int sy, final int[] dir, final int lbStart, final int lbStop, int min, int count) {
		if (count >= min) return min; 

		for (int i = 1, row = board.length, col = board[0].length; i < dir.length; ++i) {
			int x = sx + dir[i-1], y = sy + dir[i];
			if (x >= 0 && x < row && y >= 0 && y < col) {
				if (board[x][y] == lbStop) return count < min ? count : min;
				else {
					int numInitWall = Math.abs(board[x][y] % 2), prevMin = (0 - board[x][y] - numInitWall) / 2 - 1; //负数除余还是负数！！切记切记！
					if (board[x][y] == lbStart) { // met a start's neighbor for first time, counter reset to zero.
						board[x][y] = -2;
						min = minWallBreak(board, x, y, dir, lbStart, lbStop, min, 0);
					} else if (board[x][y] >= 0 || (board[x][y] < 0 && prevMin > count)) {
						board[x][y] = (-1 - count) * 2 - numInitWall;
						min = minWallBreak(board, x, y, dir, lbStart, lbStop, min, count + numInitWall);
					}	
				}
			}
		}
		return min;
	}

}