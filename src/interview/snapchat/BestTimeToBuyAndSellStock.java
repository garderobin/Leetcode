package interview.snapchat;

public class BestTimeToBuyAndSellStock {
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] maxPrice = new int[prices.length]; //maxPrice[i]: the max price date after or include the ith day
        int[] minPrice = new int[prices.length]; //minPrice[i]: the min price date before or include the ith day
        int[] maxProfit = new int[prices.length];        
       
        maxPrice[0] = prices[0];
        minPrice[0] = prices[0];
        maxProfit[0] = 0; //wrong
        int i, j;        
        for (i = 1; i < prices.length - 1; i++) {
        	minPrice[i] = (prices[i] < minPrice[i - 1]) ? prices[i] : minPrice[i - 1];
        	maxPrice[i] = prices[i];
        	for (j = i + 1; j < prices.length - 1; j++) {
        		if (prices[j] > prices[j - 1]) {
        			maxPrice[i] = prices[j];
        		}        		
        	}
        	maxProfit[i] = maxPrice[i] - minPrice[i];
        }
        
        return maxProfit[prices.length - 1];
    }
	
	public static int maxProfit2(int[] prices) {
		if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] maxDate = new int[prices.length]; //maxDate[i]: the max price date after or include the ith day
        int[] minDate = new int[prices.length]; //minDate[i]: the min price date before or include the ith day       
        minDate[0] = 0;
        maxDate[prices.length - 1] = prices.length - 1;
        
        int profit, i;        
        for (i = 1; i < prices.length; i++) {
        	minDate[i] = (prices[i] < prices[minDate[i - 1]]) ? i : minDate[i - 1];        	
        }        
        for (i = prices.length - 2; i >= 0; i--) {
        	maxDate[i] = (prices[i] > prices[maxDate[i + 1]]) ? i : maxDate[i + 1];     
        }
       
        int maxProfit = prices[maxDate[0]] - prices[minDate[0]];
        for (i = 1; i < prices.length; i++) {
        	profit = prices[maxDate[i]] - prices[minDate[i]];
        	maxProfit = (profit > maxProfit) ? profit: maxProfit;
        }
        
        return maxProfit;
    }
	
	public static int maxProfitDiscussion(int[] prices) {
		if (prices == null || prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int profit = 0;
        for(int i=1; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
	}
}
