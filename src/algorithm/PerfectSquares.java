package algorithm;

import java.util.Arrays;

/**
 * 四种思路总结：https://leetcode.com/discuss/58056/summary-of-different-solutions-bfs-static-and-mathematics
 * 暂时只看了DP相关解法。抽时间一定要看一下BFS解法！
 * 
 * @author jasmineliu
 *
 */
public class PerfectSquares {
	
	/**
	 * DP一定要扭转从后往前，父问题调用子问题的思路，要从前往后从小到大，用子问题去推他能够解决的子问题父问题。
	 * 这种思路不是所有问题都能解决，但是非常适合解决最大最小值问题。
	 * @param n
	 * @return
	 */
	public int numSquaresDiscussionDP(int n) {
		int i, j;
		int[] f = new int[n+1];
		Arrays.fill(f, Integer.MAX_VALUE);
		f[0] = 0;
		for (i = 0; i <= n; i++) {
			for (j = 1; j * j < n; j++) {
				f[i + j * j] = Math.min(f[i] + 1, f[i + j * j]);
			}
		}
		return f[n];
	}
	
	public static int numSquares(int n) {
		int sqrt = (int)Math.sqrt(n), min = n, i, j, i_sqrt, k;
        int[][] f = new int[n + 1][sqrt + 1]; 
        //f[i][j] mins the least num squares with the allows max sqrt is j.
        for (i = 0; i < n + 1; i++) {
        	for (j = 0; j * j < n + 1; j++) {
        		f[i][j] = i;
        	}
        }
        for (i = 1; i < n + 1; i++) {
        	i_sqrt = (int)Math.sqrt(i);
        	min = i;
        	for (j = 2; j * j < i + 1; j++) {
        		if (1 + f[i - j * j][j] < i) {
        			f[i][j] = 1 + f[i - j * j][j];
                	if (f[i][j] < min) {
                		min = f[i][j];
                		for (k = j; k < sqrt + 1; k++) {
                			f[i][k] = min;
                		}
                	}
        		}            	
            }
        	f[i][i_sqrt] = min;
        }
        return f[n][sqrt];
    }
	
	public static void main(String[] args) {
		System.out.println(numSquares(13));
	}
}
