package GoogleOA;

public class BestTimeToBuyAndSellStockWithCooldown {
	
	/**
	 * 将DP转化成greedy.
	 * buy[i]  = max(rest[i-1]-price, buy[i-1]) 
	 * sell[i] = max(buy[i-1]+price, sell[i-1])
	 * rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
	 * 
	 * rest[i] = sell[i-1] 这一步是关键
	 * 
	 * buy[i] = max(sell[i-2]-price, buy[i-1])
	 * sell[i] = max(buy[i-1]+price, sell[i-1])
	 * 
	 * @param prices
	 * @return
	 */
	public static int maxProfit(int[] prices) {
		int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy = buy;
	    for (int price : prices) {
	        prev_buy = buy;
	        buy = Math.max(prev_sell - price, prev_buy);
	        prev_sell = sell;
	        sell = Math.max(prev_buy + price, prev_sell);
	    }
	    return sell;
    }
	
	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 0, 2};
		System.out.println(maxProfit(prices));
	}
	
}
