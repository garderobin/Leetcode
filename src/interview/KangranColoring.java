package interview;

public class KangranColoring {
	
	/**
	 * Greedy
	 * 对于每一个篱笆，到它为止的总情况数等于它与上一个异色(x)和同色(y)的情况数和。
	 * x_old是上一个篱笆与上上一个异色的总情况数，y_old是上一个篱笆与上上一个同色的总篱笆数
	 * 当前篱笆与上一个同色的情况数延续了x_old不变 (这里不考虑y_old, 因为连续三个同色非法)
	 * 当前篱笆与上一个异色的情况等同于上一个总篱笆情况总数(x_old + y_old)乘以不选上一个篱笆颜色的情况（k-1）
	 * @param n
	 * @param k
	 * @return
	 */
	public static int numWays (int n, int k) {
		if (n < 1 || k < 1) { return 0; }
        if (n == 1) { return k; }
        if (k == 1 && n <= 2) { return 1; }
        if (k == 1 && n > 2) { return 0; }
		int diffColorCount = k, sameColorCount = 0;
		for (int i = 1; i < n; i++){
			diffColorCount = (sameColorCount + diffColorCount) * (k-1);
			sameColorCount = diffColorCount;
		}
		return diffColorCount+sameColorCount;
	}
	

	public int numWaysDiscussion(int n, int k) {
	    if(n == 0) return 0;
	    else if(n == 1) return k;
	    int diffColorCounts = k*(k-1);
	    int sameColorCounts = k;
	    for(int i=2; i<n; i++) {
	        int temp = diffColorCounts;
	        diffColorCounts = (diffColorCounts + sameColorCounts) * (k-1);
	        sameColorCounts = temp;
	    }
	    return diffColorCounts + sameColorCounts;
	}
	
	public static void main(String args) {
//		System.out.println("hi");
//		System.out.println(color(3, 2));
	}
}
