package interview.snapchat;

/*
 * This is instance version. Static version see MazeBreakWall.
 */
public class Maze {
	char[][] input;
	char LABEL_ENTRANCE, LABEL_EXIT, LABEL_ROAD, LABEL_WALL;
	int[] dir;
	
	public Maze(final char LABEL_ENTRANCE, final char LABEL_EXIT, final char LABEL_ROAD, final char LABEL_WALL, boolean allowDiagonalWalk) {
		dir = allowDiagonalWalk ? new int[]{0, -1, -1, 1, 1, -1, 0, 1, 0} : new int[]{0, -1, 0, 1, 0};
		this.LABEL_ENTRANCE = LABEL_ENTRANCE;
		this.LABEL_EXIT = LABEL_EXIT;
		this.LABEL_ROAD = LABEL_ROAD;
		this.LABEL_WALL = LABEL_WALL;
	}
	
	public Maze(char[][] input, final char LABEL_ENTRANCE, final char LABEL_EXIT, final char LABEL_ROAD, final char LABEL_WALL, boolean allowDiagonalWalk) {
		dir = allowDiagonalWalk ? new int[]{0, -1, -1, 1, 1, -1, 0, 1, 0} : new int[]{0, -1, 0, 1, 0};
		this.input = input;
		this.LABEL_ENTRANCE = LABEL_ENTRANCE;
		this.LABEL_EXIT = LABEL_EXIT;
		this.LABEL_ROAD = LABEL_ROAD;
		this.LABEL_WALL = LABEL_WALL;
	}
	
	
	/* 起点标1，终点标9，通路标0， 障碍标5，起点和重点要靠自己从输入中探测。看是否存在能从起点走到终点的路径。*/
	public boolean canGoThrough(char[][] board) {
		if (board == null || board.length == 0)  return true;
		int sx = -1, sy = -1;
		
		outer: // Find the start point
		for (sx = 0; sx < board.length; ++sx) {
			for (sy = 0; sy < board[sx].length; ++sy) {
				if (board[sx][sy] == '1') break outer;
			}
		}
		
		return findPathOut(board, sx, sy);
	}
	
	
	public int minWallBreak(char[][] input) {
		this.input = input;
		return minWallBreak();
	}
	
	/** Compute the minimum number of walls needed to get through the maze. */
	/* DFS with caching: O(NlogN) time, O(N^2) space.*/
	public int minWallBreak() {
		if (input == null || input.length == 0 || input[0].length == 0) return 0;
		
		final int myEntr = 1, myExit = 9, myRoad = 0, myWall = 5;
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
		
		// Paint start and end point components.
		if (!paintComponent(board, sx, sy, dir, myRoad, myEntr, myExit)) return 0; 
		paintComponent(board, ex, ey, dir, myRoad, myExit, myEntr); //开头如果找不到结尾，结尾也一定找不到开头，所以不用检查了 
		
		return findMinBreak(board, sx, sy, dir, myEntr, myExit, Integer.MAX_VALUE, 0);
	}
	
	/* Mark '2' as reachable. DFS */
	private boolean findPathOut(char[][] board, int cx, int cy) {
		for (int i = 1; i < dir.length; ++i) {
			int x = cx + dir[i-1], y = cy + dir[i];
			if (x < 0 || x >= board.length || y < 0 || y >= board[0].length
					|| (board[x][y] > '0' && board[x][y] < '9')) continue;
			if (board[x][y] == '9') return true;
			board[x][y] = '2';
			if (findPathOut(board, x, y)) return true;
		}
		return false;
	}
	
	
	
	/** 
	 * Paint a given label to positions that has a specific original label and can be reached from the start point without breaking walls.
	 * If a stop label is found, stop painting and return false.
	 */
	/* Union find: O(NlogN) time, O(NlogN) space*/
	private boolean paintComponent(int[][] board, int sx, int sy, final int[] dir, final int lbOrig, final int lbPaint, final int lbStop) {
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
	private int findMinBreak(int[][] board, int sx, int sy, final int[] dir, final int lbStart, final int lbStop, int min, int count) {
		if (count >= min) return min; 

		for (int i = 1, row = board.length, col = board[0].length; i < dir.length; ++i) {
			int x = sx + dir[i-1], y = sy + dir[i];
			if (x >= 0 && x < row && y >= 0 && y < col) {
				if (board[x][y] == lbStop) return count < min ? count : min;
				else {
					int numInitWall = board[x][y] & 1, prevMin = (0 - board[x][y] - numInitWall) / 2 - 1; //负数除余还是负数！！切记切记！
					if (board[x][y] == lbStart) { // met a start's neighbor for first time, counter reset to zero.
						board[x][y] = -2;
						min = findMinBreak(board, x, y, dir, lbStart, lbStop, min, 0);
					} else if (board[x][y] >= 0 || (board[x][y] < 0 && prevMin > count)) {
						board[x][y] = (-1 - count) * 2 - numInitWall;
						min = findMinBreak(board, x, y, dir, lbStart, lbStop, min, count + numInitWall);
					}	
				}
			}
		}
		return min;
	}

	
	
//	public static void main(String[] args) {
//		String[] test = {
//				"155550",
//				"050550",
//				"050000",
//				"050050",
//				"000509"
//		};
//		char[][] board = new char[test.length][test[0].length()];
//		for (int i = 0; i < test.length; ++i) board[i] = test[i].toCharArray();
//		
//		System.out.println(solution(board));
//	}

}
