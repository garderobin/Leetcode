package amazon_test;

public class Xinli_OA_1 {

//	public static Position locationOfTargetValue(int[][] matrix, int rowCount, int columnCount, int targetValue) {
//		//find row
//		int r = findRow(matrix, rowCount, columnCount, targetValue, 0, rowCount-1);
//		int c = findColumn(matrix, rowCount, columnCount, targetValue, row)
//	}
	
	private static int findRow(int[][] matrix, int rowCount, int columnCount, int targetValue, int rs, int re) {
		int r = (re - rs)/2 + rs;
		if (matrix[re][columnCount-1] < targetValue) {
			return -1;
		}
		if (matrix[rs][0] > targetValue) {
			return -1;
		}
		if (matrix[r][0] > targetValue) {
			return findRow(matrix, rowCount, columnCount, targetValue, rs, r-1);
		} 
		if (matrix[r][columnCount-1] < targetValue) {
			return findRow(matrix, rowCount, columnCount, targetValue, r+1, re);
		}
		return r;
	}
	
	
	public static void main(String[] args) {
		System.out.println();
	}
	
	class Position {
		public int x;
		public int y;
	}
}
