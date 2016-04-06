package interview.snapchat;

public class BestTimeToBuyAndSellStockII {
	//static int maxProfit[][];
	public static int maxProfit(int[] prices) {
        //int[][] maxProfit = new int[prices.length][prices.length];
        //maxProfit = helper(prices, maxProfit, 0, prices.length - 1);
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int[] maxProfit = new int[prices.length];
        maxProfit[0] = 0;
        int i, profit;
        for (i = 1; i < prices.length; i++) {
        	profit = (prices[i] - prices[i - 1] > 0) ? prices[i] - prices[i - 1] : 0;
        	maxProfit[i] = maxProfit[i - 1] + profit;
        }
		return maxProfit[prices.length - 1];
    }

//	private static int[][] helper(int[] prices, int[][] maxProfit, int start, int end) {
//		if (start == end) {
//			maxProfit[start][end] = 0;
//		}
//		int i, j, profit;
//		maxProfit[start][start] = 0;
//		for (i = start; i <= end; i++) {
//			for (j = i + 1; j <= end; j++) {
//				profit = (prices[j] - prices[j - 1] > 0) ? prices[j] - prices[j - 1] : 0;
//				maxProfit[i][j] = maxProfit[i][j - 1] + profit;
//			}
//		}
//		return null;
//	}
}
