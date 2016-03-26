package interview.snapchat;

public class Maze {
	static final int[] dir = {0, 1, 0, -1, 0};
//	static int count = 0;
	/*
	 * 起点标1，终点标9，通路标0， 障碍标5，起点和重点要靠自己从输入中探测。看是否存在能从起点走到终点的路径。
	 */
	public static boolean solution(char[][] board) {
		if (board == null || board.length == 0)  return true;
		int sx = -1, sy = -1;
		
		// Find the start point
		outer:
		for (sx = 0; sx < board.length; ++sx) {
			for (sy = 0; sy < board[sx].length; ++sy) {
				if (board[sx][sy] == '1') break outer;
			}
		}
		
		return findPathOut(board, sx, sy);
	}
	
	/*
	 * Mark '2' as reachable. DFS
	 */
	private static boolean findPathOut(char[][] board, int cx, int cy) {
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
	
	public static void main(String[] args) {
		String[] test = {
				"155550",
				"050550",
				"050000",
				"050050",
				"000509"
		};
		char[][] board = new char[test.length][test[0].length()];
		for (int i = 0; i < test.length; ++i) board[i] = test[i].toCharArray();
		
		System.out.println(solution(board));
	}

}
