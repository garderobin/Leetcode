package algorithm;

public class Search2DMatrix {
	
	/**
	 * Don't treat it as a 2D-Matrix. Treat it as a sorted list.
	 * 一个典型例子：利用除余的办法来降维。这里降二维为一维。
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrixDiscussion(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0, rows = matrix.length, cols = matrix[0].length;
        int end = rows * cols - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid / cols][mid % cols] == target) {
                return true;
            } 
            if (matrix[mid / cols][mid % cols] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
	
	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}
        int rnum = matrix.length;
        int cnum = matrix[0].length;
        if (target > matrix[rnum - 1][cnum - 1] || target < matrix[0][0]) {
        	return false;
        }
        return searchRow(matrix, target, 0, rnum - 1, cnum);
        
    }
	
	private static boolean searchRow(int[][] matrix, int target, int startRow, int endRow, int cnum) {
		
		if (target > matrix[endRow][cnum - 1]) {
			//return 2;
			return false;
		}
		if (target < matrix[startRow][0]) {
			//return -1;
			return false;
		}
		if (startRow == endRow) {
			// search in one row
			return searchCol(matrix, target, startRow, 0, cnum - 1);
		}		
		int k = (endRow - startRow) / 2 + startRow;
		if (target < matrix[k][cnum - 1]) {
			return searchRow(matrix, target, startRow, k, cnum);
		}
		if (target > matrix[k][cnum - 1]) {
			return searchRow(matrix, target, k + 1, endRow, cnum);
		}
		return true;		
		
	}
	
	private static boolean searchCol(int[][] matrix, int target, int row, int startCol, int endCol) {
		if (target > matrix[row][endCol] || target < matrix[row][startCol]) {
			return false;
		}
		if (startCol == endCol) {
			return target == matrix[row][startCol];
		}
		int k = startCol + (endCol - startCol) / 2;
		if (target < matrix[row][k]) {
			return searchCol(matrix, target, row, startCol, k);
		} 
		if (target > matrix[row][k]) {
			return searchCol(matrix, target, row, k+1, endCol);
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {
		                   {1,   3,  5,  7},
		                   {10, 11, 16, 20},
		                   {23, 30, 34, 50}
		                 };
		int target = 3;
		System.out.println(searchMatrix(matrix, target));
	}
}
