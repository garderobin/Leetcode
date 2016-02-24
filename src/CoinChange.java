import java.util.Arrays;

public class CoinChange {
	public int coinChange(int[] coins, int amount) {
		if (amount == 0) { return 0; }
        int[] f = new int[amount+1];
        f[0] = 0;
        Arrays.sort(coins);
        for (int i = 1, limit = amount + 1; i < limit; i++) {
        	f[i] = -1;
        	for (int j = 0; j < coins.length && coins[j] <= i; j++) {
        		if ((f[i-coins[j]]) >= 0) { 
        			if (f[i] < 0 || f[i] > f[i-coins[j]] + 1) {
        				f[i] = f[i-coins[j]] + 1;
        			}
        		}
        	}
        }
		return f[amount];
    }
}
