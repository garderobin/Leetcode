package algorithm;

public class GameOfLife {
	
	/**
	 * 最佳答案：1.利用模2来保存是与非的状态。
	 * 2. 利用位置的二维数组来实现对邻居的遍历。
	 * State transitions
	 * 0 : dead to dead
	 * 1 : live to live
	 * 2 : live to dead
	 * 3 : dead to live
	 * @param board
	 */
	public void gameOfLifeDiscussion(int[][] board) {
		int[][] dir ={{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
	    for(int i = 0;i < board.length;i++){
	        for(int j = 0;j < board[0].length;j++){
	            int live = 0;
	            for(int[] d:dir){
	                if(d[0]+i<0 || d[0]+i>=board.length || d[1]+j<0 || d[1]+j>=board[0].length) continue;
	                if(board[d[0]+i][d[1]+j]==1 || board[d[0]+i][d[1]+j]==2) live++;
	            }
	            if(board[i][j]==0 && live==3) board[i][j]=3;
	            if(board[i][j]==1 && (live<2 || live>3)) board[i][j]=2;
	        }
	    }
	    for(int i = 0; i < board.length; i++){
	        for(int j = 0;j < board[0].length; j++){
	            board[i][j] %= 2;
	        }
	    }
	}
	
	/**
	 * 我的想法，O(1) space, O(mn) time, 走两遍
	 * 第一遍，统计live neighbors数目，用正号代表这个cell原本生存（1）
	 * 用符号代表这个cell原本死亡（0）
	 * 第二遍遍历再改变状态
	 * @param board
	 */
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int i, j, m = board.length, n = board[0].length, nblive;
		
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				nblive = numLiveNeighbors(board, i, j);
				nblive = (nblive == 0) ? 9 : nblive;
				board[i][j] = numLiveNeighbors(board, i, j) * ((board[i][j] > 0) ? 1 : -1);
			}
		}
		
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				if (Math.abs(board[i][j]) == 3 || board[i][j] == 2) {
					board[i][j] = 1;
				} else {
					board[i][j] = 0;
				}
			}
		}
        
    }
	
	/***
	 * Bit-manipulation.
	 * Store the next state in the second least significant bit and after updating all states, 
	 * traverse the array again and right shift every number by 1 bit.
	 * 
	 * @param board
	 */
	public void gameOfLifeDiscussion1(int[][] board) {
	    if(board==null||board.length==0)    return;
	    for(int i=0; i<board.length; i++)
	        for(int j=0; j<board[0].length; j++){
	            int alive=0;
	            for(int x=-1; x<=1; x++)
	                for(int y=-1; y<=1; y++)
	                    if(!(x==0&&y==0)&&isAlive(board, i+x, j+y))
	                        alive++;
	            if((board[i][j]&1)==1&&alive>=2&&alive<=3)
	                board[i][j]|=1<<1;
	            if((board[i][j]&1)==0&&alive==3)
	                board[i][j]|=1<<1;
	        }
	    for(int i=0; i<board.length; i++)
	        for(int j=0; j<board[0].length; j++)
	            board[i][j]=board[i][j]>>1;
	}
	private boolean isAlive(int[][] board, int row, int col){
	    return row>=0&&row<board.length&&col>=0&&col<board[0].length&&(board[row][col]&1)==1;
	}
	
	private int numLiveNeighbors(int[][] board, int x, int y) {
		int count = 0, im = board.length - 1, in = board[0].length - 1;
		boolean r0 = x > 0, rm = x < im, c0 = y > 0, cn = y < in;
		count += (r0 && c0 && board[x-1][y-1] > 0) ? 1 : 0;
		count += (r0 && board[x-1][y] > 0) ? 1 : 0;
		count += (r0 && cn && board[x-1][y+1] > 0) ? 1 : 0;
		count += (c0 && board[x][y-1] > 0) ? 1 : 0;
		count += (cn && board[x][y+1] > 0) ? 1 : 0;
		count += (rm && c0 && board[x+1][y-1] > 0) ? 1 : 0;
		count += (rm && board[x+1][y] > 0) ? 1 : 0;
		count += (rm && cn && board[x+1][y+1] > 0) ? 1 : 0;
		
		return count;
	}
	
//	private int countLiveNeighbors(int[][] board, int x, int y) {
//		int count = 0, rows = board.length - 1, cols = board[0].length - 1;
//		//上左，上中，上右，中左，中右，下左，下中，下右
//		boolean a = (rows > 0), b = (cols > 0);
//		if (x == 0 && y == 0) {
//			count += (b && board[0][1] > 0) ? 1 : 0;
//			count += (a && b && board[1][1] > 0) ? 1 : 0;
//			count += (a && b && board[1][0] > 0) ? 1 : 0;
//		} else if (x == rows && y == cols) {
//			count += (a && b && board[rows-1][cols-1] > 0) ? 1 : 0;
//			count += (a && board[rows-1][cols] > 0) ? 1 : 0;
//			count += (b && board[rows][cols-1] > 0) ? 1 : 0;
//		} else if (x == 0 && y == cols) {
//			count += (b && board[0][cols-1] > 0) ? 1 : 0;
//			count += (a && b && board[1][cols-1] > 0) ? 1 : 0;
//			count += (a && board[1][cols] > 0) ? 1 : 0;
//		} else if (x == rows && y == 0) {
//			count += (a && board[rows-1][0] > 0) ? 1 : 0;
//			count += (a && b && board[rows-1][1] > 0) ? 1 : 0;
//			count += (board[rows][0] > 0) ? 1 : 0;
//		} else if (x == 0) {
//			count += (board[0][y-1] > 0) ? 1 : 0;
//			count += (board[0][y+1] > 0) ? 1 : 0;
//			count += (a && board[1][y-1] > 0) ? 1 : 0;
//			count += (a && board[1][y] > 0) ? 1 : 0;
//			count += (a && board[1][y+1] > 0) ? 1 : 0;
//		} else if (y == 0) {
//			count += (board[x-1][0] > 0) ? 1 : 0;
//			count += (b && board[x-1][1] > 0) ? 1 : 0;
//			count += (b && board[x][1] > 0) ? 1 : 0;
//			count += (board[x+1][0] > 0) ? 1 : 0;
//			count += (b && board[x+1][1] > 0) ? 1 : 0;
//		} else if (x == rows) {
//			count += (board[rows-1][y-1] > 0) ? 1 : 0;
//			count += (board[rows-1][y] > 0) ? 1 : 0;
//			count += (board[rows-1][y+1] > 0) ? 1 : 0;
//			count += (board[rows][y-1] > 0) ? 1 : 0;
//			count += (board[rows][y+1] > 0) ? 1 : 0;
//		} else if (y == cols) {
//			count += (board[x-1][cols-1] > 0) ? 1 : 0;
//			count += (board[x-1][cols] > 0) ? 1 : 0;
//			count += (board[x][cols-1] > 0) ? 1 : 0;
//			count += (board[x+1][cols-1] > 0) ? 1 : 0;
//			count += (board[x+1][cols] > 0) ? 1 : 0;
//		} else {
//			count += (board[x-1][y-1] > 0) ? 1 : 0;
//			count += (board[x-1][y] > 0) ? 1 : 0;
//			count += (board[x-1][y+1] > 0) ? 1 : 0;
//			count += (board[x][y-1] > 0) ? 1 : 0;
//			count += (board[x][y+1]  > 0) ? 1 : 0;
//			count += (board[x+1][y-1] > 0) ? 1 : 0;
//			count += (board[x+1][y] > 0) ? 1 : 0;
//			count += (board[x+1][y+1] > 0) ? 1 : 0;
//		}
//		
//		return count;
//		
//	}
	
	//[[1,0,0,0,0,1],[0,0,0,1,1,0],[1,0,1,0,1,0],[1,0,0,0,1,0],[1,1,1,1,0,1],[0,1,1,0,1,0],[1,0,1,0,1,1],[1,0,0,1,1,1],[1,1,0,0,0,0]]
}
