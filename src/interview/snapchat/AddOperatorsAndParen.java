package interview.snapchat;

import java.util.List;
import java.util.Arrays;

/**
 *  给一个double array， 让输出能使用＊，＋， 以及（）得到的maximum。 
 *  follow up，如果有<0的怎么处理

 *  Ask: input contains zero or negative numbers?
 *  Ask: overflow? (bigger than max_double or smaller than min_double)?
 *  
 *  如果输入全是正数，这题简单的dp也可以做
 *  如果输入允许负数和0，或者允许减号和除号，这题只能用dfs with caching 才能实现最优解
 *  括号在这里的唯一作用就是允许加法的运算优先级比乘法高，所以dp不用考虑括号
 *  
 */
public class AddOperatorsAndParen {
	
	public static void main(String[] args) {
		// test all positive data
//		List<double[]> poss = Arrays.asList(
//				null,
//				new double[0],
//				new double[]{1.1, 2.2, 3.3, 4.4, 5.5}, // ascending with all bigger than 1 data
//				new double[]{3.0, 2.0, 4.0, 5.0, 1.0}, // shuffeled with all bigger than 1 data
//				new double[]{5.0, 4.0, 3.0, 2.0, 1.1}, // descending with all biger than 1 data
//				new double[]{0.2, 0.5, 1.1, 2.2, 3.3, 4.4, 5.5}, // ascending with some smaller than 1 data
//				new double[]{3.0, 2.0, 4.0, 0.5, 5.0, 1.0, 0.2}, // shuffeled with some smaller than 1 data
//				new double[]{5.0, 4.0, 3.0, 2.0, 1.1, 0.5, 0.2}, // descending with some smaller than 1 data
//				new double[]{0.1, 0.2, 0.3, 0.4, 0.5}, // ascending with all smaller than 1 data
//				new double[]{0.3, 0.2, 0.4, 0.5, 0.1}, // shuffeled with all smaller than 1 data
//				new double[]{0.5, 0.4, 0.3, 0.2, 0.1} // descending with all smaller than 1 data
//				);
//		for (double[] data: poss) {
//			System.out.println(maxEvalPositive(data));
//		}
//		
		// test containing zero and negative data
		List<double[]> test = Arrays.asList(
				new double[]{0.0, -1.1, -2.2, -3.3, -4.4, -5.5}, // descending with zero and negative
				new double[]{-2.5, 0.0, 1.1, 2.2, 3.3, 4.4, 5.5}, // descending with negative, zero and positive
				new double[]{-1.1, -2.2, -3.3, -4.4, -5.5}, // descending with all negative
				
				new double[]{-3.0, -1.0, -2.0, 0.0, -4.0, -2.5, -6.0, -5.0, -1.0}, // shuffeled with negative and zero
				new double[]{3.0, -1.0, 2.0, 0.0, 4.0, -2.5, -6.0, 5.0, 1.0}, // shuffeled with negative, zero and positive
				new double[]{-3.0, -1.0, -2.0, -4.0, -2.5, -6.0, -5.0, -1.0}, // shuffeled with all negative
				
				new double[]{-5.0, -4.0, -3.0, -2.0, -1.0, 0}, // ascending with negative and zero
				new double[]{-5.0, -4.0, -3.0, -2.0, -1.0, 0, 1.0, 2.0, 3.0}, // ascending with negative, zero and positive
				new double[]{-5.0, -4.0, -3.0, -2.0, -1.0} // ascending with all negative
				);
		for (double[] data: test) {
			System.out.println(maxEval(data));
		}
	}

	/**
	 * @param data (all positive double numbers)
	 * @return the maximum evaluting results by adding *, + and () between double data
	 */
	/* Memorized depth first search. */
	public static double maxEvalPositive(double[] data) {
		if (data == null || data.length == 0) return 0;
		int n = data.length;
		double[][] dp = new double[n][n]; // dp[i][j] is the maximum eval results by elements from ith to jth
		return dfsPositive(data, dp, 0, data.length-1);
	}
	
	
	/**
	 * @param data (may contain zero or negative numbers)
	 * @return the maximum evaluting results by adding *, + and () between double data
	 */
	public static double maxEval(double[] data) {
		if (data == null || data.length == 0) return 0;
		int n = data.length;
		double[][][] dp = new double[n][n][2]; // dp[i][j] is the maximum eval results by elements from ith to jth
		boolean[][] visited = new boolean[n][n];
		double[] rst = dfs(data, dp, visited, 0, data.length-1);
		return rst[0];
	}
	
	private static double dfsPositive(double[] data, double[][] dp, int start, int end) { // end index inclusive
		if (dp[start][end] > 0) return dp[start][end];
		else if (start == end)  return (dp[start][end] = data[start]); 
		else {
			double maxEval = 0.0;
			for (int i = start; i < end; ++i) {
				double left = dfsPositive(data, dp, start, i), right = dfsPositive(data, dp, i+1, end);
				maxEval = Math.max(maxEval, Math.max(left + right, left * right));
			}
			return maxEval;
		}
	}
	
	private static double[] dfs(double[] data, double[][][] dp, boolean[][] visited, int start, int end) { // end index inclusive
		if (visited[start][end]) return dp[start][end];
		else if (start == end) {
			visited[start][end] = true;
			return (dp[start][end] = new double[]{data[start], data[start]}); 
		}
		double maxEval = 0 - Double.MAX_VALUE, minEval = Double.MAX_VALUE;
		for (int i = start; i < end; ++i) {
			double[] left = dfs(data, dp, visited, start, i), right = dfs(data, dp, visited, i+1, end);
			for (int j = 0; j < left.length; ++j) {
				for (int k = 0; k < right.length; ++k) {
					double plus = left[j] + right[k], multiply = left[j] * right[k];
					maxEval = Math.max(maxEval, Math.max(plus, multiply));
					minEval = Math.min(minEval, Math.min(plus, multiply));
				}
			}
		}
		visited[start][end] = true;
		dp[start][end] = new double[]{maxEval, minEval};
		return dp[start][end];
	}
	
	
}
