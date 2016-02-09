package algorithm;

public class WordSearch {
	public static boolean exist(char[][] board, String word) {
		if (word == null || word.length() == 0) { return true; }
		if (board == null || board.length == 0 || board[0].length == 0) { return false; }
        char c = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		if (board[i][j] == c) {
        			if (backtrack(board, word, new boolean[board.length][board[0].length], i, j, 1, 'n')) { return true; }
        		}
        	}
        }
		return false;
    }
	
	private static boolean backtrack(char[][] board, String word, boolean[][] visited, int x, int y, int offset, char direction) {
		if (offset == word.length()) { return true; }
		visited[x][y] = true;
		char c = word.charAt(offset);
		boolean up, down, left, right;
		up = (x > 0 && c == board[x-1][y]);
		down = (x < board.length - 1 && c == board[x+1][y]);
		left = (y > 0 && c == board[x][y-1]);
		right = (y < board[0].length - 1 && c == board[x][y+1]);
		if (up && direction != 'd' && !visited[x-1][y]) { 
			if (backtrack(board, word, visited, x-1, y, offset+1, 'u')) { return true; }
			visited[x-1][y] = false;
		}
		if (down && direction != 'u' && !visited[x+1][y]) { 
			if (backtrack(board, word, visited, x+1, y, offset+1, 'd')) { return true; }
			visited[x+1][y] = false;
		}
		if (left && direction != 'r' && !visited[x][y-1]) {
			if (backtrack(board, word, visited, x, y-1, offset+1, 'l')) { return true; }
			visited[x][y-1] = false;
		}
		if (right && direction != 'l'&& !visited[x][y+1]) {
			if (backtrack(board, word, visited, x, y+1, offset+1, 'r')) { return true; }
			visited[x][y+1] = false; 
		}
		return false;
	}
	
	public static void main(String[] args) {
		String[] arr = {"ABCE","SFCS","ADEE"};
		char[][] board = boardSerialize(arr);
//		["ABCE","SFCS","ADEE"]
//				"ABCCED"
		String word = "ABCCED";
		System.out.println(exist(board, word));
	}
	
	private static char[][] boardSerialize(String[] arr) {
		int cols = arr.length, rows = arr[0].length();
		char[][] board = new char[rows][cols];
		for (int j = 0; j < cols; j++) {
			for (int i = 0; i < rows; i++) {
				board[i][j] = arr[j].charAt(i);
			}
		}
		return board;
	}
	
}
