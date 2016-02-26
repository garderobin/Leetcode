package dataStructure;

public class BinaryIndexedTree {
	int[][] sums, vals;
	int r, c;

	public BinaryIndexedTree(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {}
        r = matrix.length;
        c = matrix[0].length;
        sums = new int[r+1][c+1];
        vals = matrix;
        for (int i = 0; i < r; i++) {
        	for (int j = 0; j < c; j++) {
        		update(i, j, matrix[i][j]);
        	}
        }
    }

    public void update(int row, int col, int val) {
        if (r == 0 || c == 0) { return; }
        int delta = vals[row][col] - val;
        vals[row][col] = val;
        for (int i = row+1; i < r+1; i += (i & (-i))) {
        	for (int j = col+1; j < c+1; j += (j & (-j))) {
        		sums[i][j] += delta;
        	}
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
    	if (r == 0 || c == 0)  { return 0; }
    	return sum(row2+1, col2+1) + sum(row1, col1) - sum(row1, col2+1) - sum(row2+1, col2);
    }
    
    public int sum(int row, int col) {
    	int sum = 0;
    	for (int i = row; i > 0; i -= (i & (-i))) {
    		for (int j = col; j > 0; j -= (j & (-j))) {
    			sum += sums[i][j];
    		}
    	}
    	return sum;
    }
}
