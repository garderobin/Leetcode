package algorithm;

public class ClimbingStairs {
	public int climbStairs(int n) {
		if (n < 2) {
			return n;
		}
		int[] f = new int[n+1];
		f[0] = 0; // n = 0
		f[1] = 1; // n = 1, one step
		f[2] = 2; // n = 2, two steps
		//f[3] = 3; // n = 3, three steps
		for (int i = 3; i <= n; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		
		return f[n];
		
    }
}
