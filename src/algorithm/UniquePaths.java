package algorithm;

public class UniquePaths {
	public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];      
        int i, j;
        for (j = 0; j < n; j++) {
        	f[0][j] = 1;
        }
        for (i = 0; i < m; i++) {
        	f[i][0] = 1;
        }
        for (i = 1; i < m; i++) {
        	for (j = 1; j < n; j++) {
        		f[i][j] = f[i - 1][j] + f[i][j - 1];
        	}
        }
        return f[m - 1][n - 1];
    }
	
//	private int calcCombination(int max, int min) {
//		int rst = 1, a = max + 1, b = max - min + 1;
//		for (int i = min + 1; i < a; i++) {
//			rst *= i;
//		}
//		for (int j = 1; j < b; j++) {
//			rst /= j;
//		}
//		return rst;
//	}
}
