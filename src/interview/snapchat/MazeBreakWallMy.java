package interview.snapchat;
//0 as start part, -9 as right part, -5 as obstacle
public class MazeBreakWallMy {
	
	static final int[] dir = {0, 1, 0, -1, 0};
	
	public static boolean solution(int[][] board) {
		if (board == null || board.length == 0)  return true;
		int sx = -1, sy = -1, ex = -1, ey = -1;
		
		// Find the start point and end point
		outer:
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[i].length; ++j) {
				switch (board[i][j]) {
				case 1:
					sx = i;
					sy = j;
					if (ex >= 0) break outer;
					break;
				case 9:
					ex = i;
					ey = j;
					if (sx >= 0) break outer;
					break;
				default:;	
				}
			}
		}
		
		// Mark s-party(start point reachable points) and e-party(end point reachable points)
		board = markReach(board, 1, sx, sy);
		board = markReach(board, 9, ex, ey);
		
		// Union find to generate a path between s-party and e-party
		
		
		return false;
//		return findPathOut(board, sx, sy);
	}
	
	private static int[][] markReach(int[][] board, int markNum, int cx, int cy) {
		board[cx][cy] = markNum;
		for (int i = 1; i < dir.length; ++i) {
			int x = cx + dir[i-1], y = cy + dir[i];
			if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || (board[x][y] == 5)) continue;
			board = markReach(board, markNum, x, y);
		}
		return board;
	}
	
}
