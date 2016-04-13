package interview.snapchat;


/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=182651&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
 * 题目： tic tac toe的m*n版本，也就两个人是在一个m*n的board上玩。（LZ最讨厌玩游戏了。。。）. from: 1point3acres.com/bbs 
规则如下：. Waral 鍗氬鏈夋洿澶氭枃绔�,
（a）获胜方式依然是横竖对角线有三个连在一起的symbol。
（b）每次movement不能任意放置，只能放在 每一列 的 最下方的空白处，也就是说每个玩家每轮最多只有 m （行数）个选择。(我怎么觉得是n（列数）个选择？）
要求实现以下API：
（1）valid()。检查当前board是否有效，有效board必须满足 (i) 没有人获胜  (ii) 不能违背第二条规则。
（2）nextMove()。返回当前玩家的任意一个movement，要求对手无法获胜，如果找不到报错（我选择了返回-1）

脑子实在累了，讨论了一下给出了bruteforce的方案。
（1）valid主要难点在怎么判断已经有人赢了，LZ用check8个方向一共16个格子的方法，于是O(16*N^2)。跟小哥交涉，表示常数可以减小，不过16也合理可以写。
（2）假设当前为player A， 则枚举A的n个选择，每个选择会对应B的n个选择，复杂度依然是O(16*N^2)。
但实际上如果不保存board state，不管是A还是B都得先找到每一列放置的位置，每次都扫描一遍就会多出O(N)的时间，总时间会变成O(N^3)，所以需要保存一下状态。
加上一些细节的调整，最后写完但依然没时间测试debug了，两人一起表示过了一遍应该work。。。。

4. Non-partisan tic-tac-toe
Consider the game of non-partisan tic-tac-toe. Two players take turns drawing Xs in the nine sites of a three-by-three square grid, labeled as below:
123 456 789
When there are three Xs in a row, column, or diagonal, the player who played the last one wins. For example: if Alice plays 1, Bob plays 6, Alice plays 5, Bob plays 4, then Bob wins because the row 4 − 5 − 6 has been completed. If Alice plays 5, Bob plays 9, Alice plays 1, then Alice wins because the diagonal 1 − 5 − 9 has been completed.
Alice can always win, by playing in square 5 on the first move; then if Bob plays in square n, Alice answers with 10 − n and wins.

 */
public class TicTacToe {
	char[][] board; // default: '\u0000'
//	int[] unreach;
	
	public TicTacToe() {
		board = new char[3][3];
	}
	
	public TicTacToe(int m, int n) { // m: rows, n: columns
		board = new char[m][n];
//		unreach = new int[n];
	}
	
	/**
	 * 检查当前board是否有效，有效board必须满足 (i) 没有人获胜  (ii) 不能违背第二条规则。
	 * 限制： (m + n - 1) * 2 < MAX_INT 
	 * O(N) time, O(N) space. N代表所有点的个数。O(mn) time, O(m + n) space
	 * 关键想法：给对角线编号；用异或来表达另一个玩家的id.
	 */
	public boolean valid() {
		if (board == null || board.length == 0 || board[0].length == 0) return false;
		int m = board.length, n = board[0].length, dgnNum = (m < 3 || n < 3) ? 0 : (m + n - 1) * 2;
		int[][] rows = new int[m][2], cols = new int[n][2], dgns = new int[dgnNum][2]; //dgns[k] = [i, j]在第k条对角线上有i个连续的X,j个连续的O。
		boolean[] unset = new boolean[n];
		
		for (int i = m - 1; i >= 0; --i) { //仍然把行数放外圈是为了能够连续遍历内存
			for (int j = n - 1; j >= 0; --j) {
				if (board[i][j] == '\u0000') {
					unset[j] = true;
				} else {
					// Check Rule 2
					if (unset[j]) return false; // someone took a none-bottom blank, breaking Rule (2).
					unset[j] = false;
					
					// Check winner
					int p1 = board[i][j] == 'X' ? 0 : 1, p2 = p1 ^ 1; // id for players
					
					rows[i][p2] = 0; // check vertical or horizonal winner
					cols[j][p2] = 0; 
					if (++rows[i][p1] == 3 || ++cols[j][p1] == 3) return false; 
					
					
					if (dgnNum > 0) { // check diagonal Winner
						int dgnLeft = i + j, dgnRight = (i < j) ? -1 - i + j + n : i - j; // 参见numbers的图
						dgns[dgnLeft][p2] = 0; 
						dgns[dgnRight][p2] = 0;
						if (dgns[dgnLeft][p1] == 3 || dgns[dgnRight][p1] == 3) return false;
					}
					
					// Update unreach
					
					
				}
				
			}
		}
		
		return true;
	}
	
	/**
	 * 返回当前玩家的任意一个movement，要求对手无法获胜，如果找不到报错（我选择了返回-1）
	 * Ask: 有没有可能和棋？双方都无法再走了，但是没有连续三个出现。
	 * Ask: what's the current player's color? 'X' or 'O'? 这里我假设是'X', 它的代号是0
	 * Ask: need to check valid in the start of nextMove()? 这里我默认board是valid了
	 * (因为一个只能标'X'一个只能标'O'，两人的动作并不一样，所以这不是一个impartial game， 不能用Sprague-Grundy functions))
	 */
	public int nextMove() {
		// Back up board and unreach
		int m = board.length, n = board[0].length;
		char[][] grid = new char[m][n];
		int[] unreach = new int[n];
		for (int i = m - 1; i >= 0; --i) { //仍然把行数放外圈是为了能够连续遍历内存
			for (int j = n - 1; j >= 0; --j) {
				grid[i][j] = board[i][j];
				if (board[i][j] == '\u0000') unreach[j] = i;
			}
		}
		
		// Traverse columns to find a must-win move
		for (int j = 0; j < n; ++j) {
			if (unreach[j] >= 0 && canWin(unreach[j]--, j, 0, grid, unreach)) {
				return j; 
			}
		}
		return -1;
	}
	

	public boolean canWin(int x, int y, int player, char[][] grid, int[] unreach) {
		// Check immediate win: the 10 three-consecutive positions it may result in a winner
		char sign = (player == 0) ? 'X' : 'O';
		grid[x][y] = sign;
		int m = grid.length, n = grid[0].length;
		if (x+2 < m && grid[x+1][y] == sign && grid[x+2][y] == sign) return true; // vertical wins
		boolean w = y-1 >= 0 && grid[x][y-1] == sign, e = y+1 < n && grid[x][y+1] == sign,
				nw = x-1 >= 0 && y-1 >= 0 && grid[x-1][y-1] == sign,
				ne = x-1 >= 0 && y+1 < n && grid[x-1][y+1] == sign,
				se = x+1 < m && y+1 < n && grid[x+1][y+1] == sign,
				sw = x+1 < m && y-1 >= 0 && grid[x+1][y-1] == sign ;
		if ((w && e) || (w && y-2 >= 0 && grid[x][y-2] == sign) ||
				(e && y+2 < n && grid[x][y+2] == sign)) { return true; } // horizontal wins
		if ((nw && se) || (sw && ne) || 
				(nw && x-2 >= 0 && y-2 >= 0 && grid[x-2][y-2] == sign) ||
				(ne && x-2 >= 0 && y+2 < n && grid[x-2][y+2] == sign) ||
				(se && x+2 < m && y+2 < n && grid[x+2][y+2] == sign) ||
				(sw && x+2 < m && y-2 >= 0 && grid[x+2][y-2] == sign)) { return true; } // diagnal wins
		
		// Traverse the other player's next possible moves
		for (int j = 0; j < n; ++j) {
			if (unreach[j] >= 0 && canWin(unreach[j]--, j, player ^ 1, grid, unreach)) return false;
		}
		grid[x][y] = '\u0000';
		return true;
	}
}
