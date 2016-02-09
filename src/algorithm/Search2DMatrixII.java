package algorithm;

public class Search2DMatrixII {
	
	/**
	 * If we stand on the top-right corner of the matrix and look diagonally, it's kind of like a BST, 
	 * we can go through this matrix to find the target like how we traverse the BST.
	 * 这是对二分法的一个完美抽象。之所以找到右上角到左下角这条对角线，
	 * 是因为这样做对于任一个位置来说，它的四个移动方向有两个被封死了，
	 * 剩下的两个移动方向分别代表单增和单减法，
	 * 从而符合二分法。
	 * @param matrix
	 * @param target
	 * @return
	 */
	public static boolean searchMatrixDiscussionV1(int[][] matrix, int target) {
	    if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	        return false;

	    int n = matrix.length, m = matrix[0].length;
	    int i = 0, j = m - 1;

	    while (i < n && j >= 0) {
	        int num = matrix[i][j];

	        if (num == target)
	            return true;
	        else if (num > target)
	            j--;
	        else
	            i++;
	    }

	    return false;
	}
}
