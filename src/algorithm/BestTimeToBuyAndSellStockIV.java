package algorithm;

public class BestTimeToBuyAndSellStockIV {
	
	/**
	 * Concise DP using quickSolve to tackle some corner cases to avoid time limit exception.
	 * tmpMax means the maximum profit of just doing at most i-1 transactions, using at most first j-1 prices, and buying the stock at price[j] 
	 * - this is used for the next loop.
	 * 当i = 1时, 因为t[i-1][j-1]没有经过初始化赋值，所以都是0，因此这时的tmpMax取决于哪一天的prices[j]最小，这是在决定第一笔买入应该选在哪一天。
	 * 这是一个非常好的使用隔代临时变量来减少遍历的例子。
	 * t[i][j] = max{t[i-1][k] + prices[j] - prices[k+1] | 0<=k<=j-1 }
	 * @param k
	 * @param prices
	 * @return
	 */
	public static int maxProfitDiscussion(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int m = 0; m < len; m++) {
        	System.out.print(t[0][m] + "\t");
        }
        System.out.println();
        for (int i = 1; i <= k; i++) {
            int tmpMax = -prices[0];
            for (int j = 1; j < len; j++) {
            	//System.out.println("prices[" + j + "] = " + prices[j]);
            	//System.out.println("Before:\tt[" + i + "][" + (j-1) + "] = " + t[i][j] + ",\ttmpMax = " + tmpMax);
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]); 
                //System.out.println("After:\tt[" + i + "][" + j + "] = " + t[i][j] + ",\ttmpMax = " + tmpMax);
            }
        }
        return t[k][len - 1];
    }
	

	/**
	 * 如果 k >= len/2，意味着只要隔天有利润差就可以购买。
	 * 这是因为连续涨价的transaction可以合并，而不连续涨价的情况理论上最多发生 len/2 次。
	 * @param prices
	 * @return
	 */
    private static int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++) {
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
	
	/**
	 * My version. Memory out when k is extremely large. Not accepted.
	 * @param k
	 * @param prices
	 * @return
	 */
	public int maxProfit(int k, int[] prices) {
        if (k <= 0 || prices == null || prices.length < 2) {
        	return 0;
        }
        int[] hold = new int[k];
        int[] release = new int[k];
        
        for (int i = 0; i < k; i++) {
        	hold[i] = Integer.MIN_VALUE;
        	release[i] = Integer.MIN_VALUE;
        }
        for (int i: prices) {
        	for (int j = k - 1; j > 0; j--) {
        		release[j] = Math.max(release[j], hold[j] + i);
        		hold[j] = Math.max(hold[j], release[j-1] - i);
        	}
        	// release[0] and hold[0]
        	release[0] = Math.max(release[0], hold[0] + i);     	// The maximum if we've just sold 1st stock so far.
            hold[0]    = Math.max(hold[0], -i);          	// The maximum if we've just buy  1st stock so far.
        }
        
        return release[k-1];
    }
	
	public static void main(String[] args) {
		int[] prices = {2,5,3,7,4,9,6,8,11,13,15};
		System.out.println(maxProfitDiscussion(4, prices));
	}
}
