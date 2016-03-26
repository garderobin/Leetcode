package interview.snapchat;

public class MazeBreakWall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] input = {{1,5,5,5,5,0},{0,5,0,5,5,0},{0,5,0,0,0,0},{0,5,0,0,5,5},{0,0,0,5,0,9}};
//		int[][] input = {{1,5,5},{0,5,0},{0,0,9}};
		System.out.println(maze(input));
	}
	private static int maze(int[][] input){
		int row = input.length;
		int col = input[0].length;
		int m=0,n=0,a=0,b=0;
		int[] min = new int[1];
		min[0] = Integer.MAX_VALUE;
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				if(input[i][j]==1){
					m = i;
					n = j;
				}	
				if(input[i][j]==9){
					a = i;
					b = j;
				}
			}
		}
		
		CheckAround(input, row, col, m, n, min);
		CountWall(input, row, col, a, b, min, 0);
		return min[0];

	}
	private static void CheckAround(int[][] input, int row, int col, int m, int n, int[] min){
		if(m>0){
			if(input[m-1][n]==0){
				input[m-1][n] = 1;
				CheckAround(input, row, col, m-1,n, min);
			}
		}
		if(m<row-1){
			if(input[m+1][n]==0){				
				input[m+1][n] = 1;
				CheckAround(input, row, col, m+1, n, min);
			}
		}
		
		if(n>0){				
			if(input[m][n-1]==0){
				input[m][n-1] = 1;
				CheckAround(input, row, col, m, n-1, min);
			}
		}
		if(n<col-1){
			if(input[m][n+1]==0){
				input[m][n+1] = 1;
				CheckAround(input, row, col, m, n+1, min);
			}
		}
	}
	private static void CountWall(int[][] input, int row, int col, int a, int b, int[] min, int count){
		if(count>=min[0])
			return;
		if(a>0){
			if(input[a-1][b]==1)
				min[0] = min[0]<count?min[0]:count;
			if(input[a-1][b]==5){
				input[a-1][b] = 9;
				CountWall(input, row, col, a-1, b, min, count+1);
			}
		}
		if(a<row-1){
			if(input[a+1][b]==1)
				min[0] = min[0]<count?min[0]:count;
			if(input[a+1][b]==5){				
				input[a+1][b] = 9;
				CountWall(input, row, col, a+1, b, min, count+1);
			}
		}
		
		if(b>0){	
			if(input[a][b-1]==1)
				min[0] = min[0]<count?min[0]:count;
			if(input[a][b-1]==5){
				input[a][b-1] = 9;
				CountWall(input, row, col, a, b-1, min, count);
			}
		}
		if(b<col-1){
			if(input[a][b+1]==1)
				min[0] = min[0]<count?min[0]:count;
			if(input[a][b+1]==5){
				input[a][b+1] = 9;
				CountWall(input, row, col, a, b+1, min, count);
			}
		}
	}
}