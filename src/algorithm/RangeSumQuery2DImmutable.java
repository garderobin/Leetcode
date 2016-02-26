package algorithm;

public class RangeSumQuery2DImmutable {
	
	int[][] sums;
	
	public RangeSumQuery2DImmutable(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) { return; }
		int r = matrix.length, c = matrix[0].length;
		sums = new int[r+1][c+1];
		for (int i = 1; i < r+1; i++) {
			int rowSum = 0;
			for (int j = 1; j < c+1; j++) {
				rowSum += matrix[i-1][j-1];
				sums[i][j] = sums[i-1][j] + rowSum;
			}
		}
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2+1][col2+1] + sums[row1][col1] - sums[row1][col2+1] - sums[row2+1][col1];
    }
    
}
