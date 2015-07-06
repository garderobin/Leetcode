package algorithm;

/**
 * 看了答案的结果
 * 需要特别留意这种用递归函数本身的返回值来控制是否切换分支的逐步回溯
 * 不同于使用长度来判断切换分支的回溯
 * @author jasmineliu
 *
 */
public class SolveSudoku {
	public static void solveSudoku(char[][] board) {
		mySolveSudoku(board);        
    }
	
	private static boolean mySolveSudoku(char[][] board) {
		int i, j, k;
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					continue;
				}
				for (k = 1; k < 10; k++) {
					board[i][j] = (char)(k + '0');
					if (isValidSudoku(board) && mySolveSudoku(board)) {
						return true;
					}
					board[i][j] = '.';
				}
				return false;
			}
		}
		return true;
	}

	public static boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9) {
			return false;
		}
			
		int i, j, k, temp;
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] area = new boolean[9][9];               
        char cur = '.';
        
        for (i = 0; i < 9; i++) {
        	if (board[i].length != 9) {
        		return false;
        	}
        	
        	for (j = 0; j < 9; j++) {
        		cur = board[i][j];        		        		        		
        		if (cur > '0' && cur <= '9') {
        			k = getAreaNumberByPosition(i, j);
        			temp  = (int)cur - 49;
        			if (rows[i][temp] || cols[j][temp] || area[k][temp]) {
        				return false;
        			} 
        			rows[i][temp] = true;
        			cols[j][temp] = true;
        			area[k][temp] = true;
        		}        		    			
        	}
        }        
        return true;
    }
	
	public static int getAreaNumberByPosition(int i, int j) {
		int x = i / 3;
		int y = j / 3;		
		return 3 * x  + y;
	}
}
