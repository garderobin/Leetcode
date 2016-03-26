package interview.snapchat;

import java.util.Deque;
import java.util.LinkedList;

public class SurroundedRegions {
	/*
	 * BFS: 找边界的'0',用他们向里扩展，但凡能与边界零直接以零路相连的就是最终还能保持为0的，做标记，其余的在第二遍遍历棋盘的时候通通设为'X';
	 */
	public static void solveBFS(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) { return; }
		int r = board.length, c = board[0].length;
		
		// Mark all these points that will always remain '0' with 'y'.
        for (int i = 0; i < r; i++) {
        	if (board[i][0] == 'O') { 	explore(board, i, 0); } 	// left border
        	if (board[i][c-1] == 'O') { explore(board, i, c-1); }	// right border
        }
        for (int j = 1; j < c-1; j++) {
        	if (board[0][j] == 'O') { 	explore(board, 0, j); } 	// up border
        	if (board[r-1][j] == 'O') { explore(board, r-1, j); }	// bottom border
        }
        
        // Find all these not 'y' points and set them into 'X'.
        for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        		board[i][j] = (board[i][j] == 'y') ? 'O' : 'X';
        	}
        }
        
    }
	
	/**
	 * 首先，用DFS不够好，因为explore调用的次数相当多，棋盘大的时候会造成stack over flow
	 * 其次，不要轻易使用扁平化， 因为乘除法的运算即使引入了long还是有误差的可能性
	 * 最后，BFS的最佳数据结构始终是LinkedList,而不是Stack，要牢记。
	 * @param board
	 * @param x
	 * @param y
	 */
	private static void explore(char[][] board, int x, int y) {
		board[x][y] = 'y';
		Deque<Integer> qx = new LinkedList<Integer>(), qy = new LinkedList<Integer>();
		long r = board.length, c = board[0].length;
		qx.push(x); 
		qy.push(y);
		while (!qx.isEmpty()) {
			int posX = qx.pollFirst(), posY = qy.pollFirst();
			int[] xm = {posX, posX, posX+1, posX-1}, ym = {posY+1, posY-1, posY, posY};
			for (int i = 0; i < xm.length; i++) {
				if (xm[i] >= 0 && xm[i] < r && ym[i] >= 0 && ym[i] < c && board[xm[i]][ym[i]] == 'O') {
					qx.push(xm[i]);
					qy.push(ym[i]);
					board[xm[i]][ym[i]] = 'y';
				}
			}
		}
	}

	/*
	 * 这个方法是错误的。
	 * BFS不能用递归，棋盘过大时在这里会造成stack over flow
	 */
	@SuppressWarnings("unused")
	private static void exploreDFS(char[][] board, int x, int y) {
		int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
		board[x][y] = 'y';
		for (int i = 0; i < dx.length; i++) {
		    int xm = x + dx[i], ym = y + dy[i];
			if (xm >= 0 && xm < board.length && ym >= 0 && ym < board[0].length && board[xm][ym] == 'O') {
				exploreDFS(board, xm, ym);
			}
		}
	}
	
	public static void main(String[] args) {
		String[] test = {"XOXOXO","OXOXOX","XOXOXO","OXOXOX"};
		char[][] board = new char[test.length][test[0].length()];
		for (int i = 0; i < test.length; i++) {
			board[i] = test[i].toCharArray();
		}
		solveBFS(board);
		int r = board.length, c = board[0].length;
		for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        		System.out.print(board[i][j] + ", ");
        	}
        	System.out.println();
        }
	}
	public void solvUnionFind(char[][] board) {
		
	}
}
