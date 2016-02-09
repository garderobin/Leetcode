package algorithm;

public class BestTimeToBuyAndSellStockIII {
	
	/**
	 * 还没完全理解。
	 * 问题一：仅仅通过hold2 = Math.max(hold2,    release1 - i);就能保证两个交易之间没有日期上的overlapping吗？
	 * 问题二：为什么这个问题是贪心的？
	 * @param prices
	 * @return
	 */
	public static int maxProfitDiscussion(int[] prices) {
		int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        int day = 0; // For test only
        for(int i: prices) {                            	// Assume we only have 0 money at first        	
        	release2 = Math.max(release2, hold2 + i);     	// The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1 - i);  	// The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1 + i);     	// The maximum if we've just sold 1st stock so far.
            hold1    = Math.max(hold1,    -i);          	// The maximum if we've just buy  1st stock so far. 
            // For test only
            System.out.println("Day " + ++day + ": " + i + "\nafter:\trealease2 = " + release2 + "\thold2 = " + hold2 + "\trelease1 = " + release1 + "\thold1 = " + hold1) ;            
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
	}
	
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		// the indexes for the first buy, first sell, second buy, second sell, min after second sell,
		// all before or equals the ith day, 单调非严格递增
        int[][] twice = new int[prices.length][5]; 
        
        // the indexes for the buy, sell and min after sell, all before or equals the ith day
        int[][] once = new int[prices.length][3];
        
        
        int[] maxProfit = new int[prices.length];
        
        int last = prices.length - 1;
        int i, p1, p2;
        twice[0][0] = 0; twice[0][1] = 0; twice[0][2] = 0; twice[0][3] = 0; twice[0][4] = 0;
        once[0][0] = 0; once[0][1] = 0; once[0][2] = 0;        
        maxProfit[0] = 0;
        boolean a, b, c, d;
        for (i = 1; i < prices.length; i++) {
        	// compute once[i]
        	a = prices[i] > prices[once[i-1][1]];
        	b = prices[once[i-1][2]] < prices[once[i-1][0]];
        	c = prices[i] - prices[once[i-1][2]] > prices[once[i-1][1]] - prices[once[i-1][0]];        	
        	if (b && c) {
        		once[i][1] = i;
        		once[i][2] = i;
        		once[i][0] = once[i-1][2];
        	} else if (!b && c) {
        		once[i][1] = i;
        		once[i][2] = i;
        		once[i][0] = once[i-1][0];
        	} else if (a) {
        		once[i][1] = i;
        		once[i][2] = i;
        		once[i][0] = once[i-1][0];
        	}  else {
        		once[i][0] = once[i-1][0]; once[i][1] = once[i-1][1]; once[i][2] = once[i-1][2];
        	}
        	        	        	
        	// compute twice[i]
        	a = prices[i] > prices[twice[i-1][3]];
        	b = prices[i] - prices[twice[i-1][4]] > prices[twice[i-1][3]] - prices[twice[i-1][2]];
        	c = prices[i] - prices[twice[i-1][4]] > prices[twice[i-1][1]] - prices[twice[i-1][0]];
        	d = prices[twice[i-1][1]] - prices[twice[i-1][0]] > prices[twice[i-1][3]] - prices[twice[i-1][2]];
        	if (b && c) {
        		
        	}
        	
        	
        }
        //prices[rst[last][3]] - prices[rst[last][2]] + prices[rst[last][1]] - prices[rst[last][0]]
        return maxProfit[last];
    }

//	private static int findMinIndex(int[] prices, int start, int end) {
//		int min = start;
//		for (int i = start + 1; i <= end; i++) {
//			if (prices[i] < prices[min]) {
//				min = i;
//			}
//		}
//		return min;
//	}
}
