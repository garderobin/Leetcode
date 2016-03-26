package interview.snapchat;

/*
 * DP 棋盘走K步 从A到B，总共有多少种方法 
 * Ask: do we
 */
public class PathFromAToB {
    public static void main(String[] args) {
        int result0 = pathNum(new Point(5,5),new Point(3,3),8,10);
    	int result = countPathByExactLength(5, 5, 3, 3, 8, 8, 10);
        System.out.println(result0 + ", " + result);
    }
    
    /*
     * Count unique paths of exact length k from start to end position.
     * Time and space complexity are both O(Max(M, K) * Max(N, K) * K)
     * Ask: what if start equals to end? Output 1 or 0?
     * Ask: allow visiting a position more than once in a same path?
     */
    public static int countPathByExactLength(int sx, int sy, int ex, int ey, int row, int col, int k) {
    	if (k <= 0 || sx > row || sx < 0 || ex > row || ex < 0 || sy > col || sy < 0 || ey > col || ey < 0) return 0; // invalid input
    	// Make sure we start from a upper, left point to a lower, right point
    	if (sx > ex) { 
    		int tsx = sx, tsy = sy;
    		sx = ex; sy = ey;
    		ex = tsx; ey = tsy;
    	}
    	if (sy > ey) { //依纵中轴对称
    		sy = col - 1 - sy;
    		ey = col - 1 - ey;
    	}
    	
    	// Resize the board to limit memory usage in further DP board to at most k * k 
    	int offset = (k - Math.max(ey - ex, sy - sx))/2; // offset means the maximum allows outwards distance.
    	if (offset < 0) return 0; // k steps are not enough to reach B from A
    	if (sx > offset) {
    		ex -= sx - offset;
    		sx = offset;
    	}
    	if (sy > offset) {
    		ey -= sy - offset;
    		sy = offset;
    	}
    	
    	return countPathFromUpLeftCornorByLength(sx, sy, ex, ey, Math.min(row, ex + offset + 1), Math.min(col, ey + offset + 1), k);
//    	return pathNum(new Point(sx, sy), new Point(ex, ey), Math.min(row, ex + offset + 1), k);
    }
    
    private static int countPathFromUpLeftCornorByLength(int sx, int sy, int ex, int ey, int row, int col, int k) {
    	int[] dir = {0, 1, -1, -1, 0, -1, 1, 1, 0};
    	int[][][] dp = new int[row][col][2];
    	dp[sx][sy][0] = 1;
    	// Time and space complexity are both O(row*col*k))
    	for (int i = 0; i < k; ++i) { 								// update the board for k times
    		for (int m = 0; m < row; ++m) {
    			for (int n = 0; n < col; ++n) {						// update neighbors
    				int cur = 0;
    				for (int d = 1; d < dir.length; ++d) {
    					int x = m + dir[d-1], y = n + dir[d];
    					if (x >= 0 && x < row && y >= 0 && y < col) {
    						cur += dp[x][y][i%2];
    					}
    				}
    				dp[m][n][(i+1)%2] = cur;
    			}
    		}
    	}
    	return dp[ex][ey][k%2];
    }
    
    
    /*
     * Count unique paths of exact length k from start to end position.
     */
    public static int countPathByExactLengthLimit(int sx, int sy, int ex, int ey, int rol, int col, int k) {
    	return -1;
    }
      
    
    public static int pathNum(Point A, Point B, int size, int k) {
      	int[][][]dp = new int[size][size][2];
      	dp[A.x][A.y][0] = 1;
      	int i;
      	// O(M*N*K) //
      	for(i = 1; i<=k ; i++){
      		for(int m = 0; m < size; m++){
      			for(int n = 0; n < size; n++){
      				dp[m][n][i%2] = sumNeighbor(dp,i,m,n,size); 
      			}	
      		}
      	}
      	return dp[B.x][B.y][k%2];
  		
  	}
  	

  	public static int sumNeighbor(int[][][]dp, int i, int x, int y, int size){
  		int[] dx = {1, 1,1,-1,-1,-1,0, 0 };
  		int[] dy = {1,-1,0, 1,-1, 0,1,-1 };
  		int sum = 0;
  		for(int k = 0; k<8; k++){
  			int newX = x + dx[k];
  			int newY = y + dy[k];
              
  			if(newX>=0 && newX<size && newY>=0 && newY<size){
  				sum += dp[newX][newY][(i-1)%2];
  			}
  		}
  		return sum;
  	}

}

class Point{
  	int x;
  	int y;
  	Point(int x, int y){
  		this.x = x;
  		this.y = y;
  	}
}
