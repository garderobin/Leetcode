package interview.wealthfront;

public class MyPow {
	
	/**
	 * 两处优化：1. 用 x = 1/x 避免了全算乘法最后再来的一遍除法。
	 * 2. 用底数的 x*x 实现更优化的，次数更少的二分法。
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) {
            n = -n;
            x = 1/x;
        }
        return (n%2 == 0) ? myPow(x*x, n/2) : x * myPow(x*x, n/2);
    }
	
	public double myPow2(double x, int n) {
		if (x == 0) { return 0; }
		boolean neg = (n < 0);
		n = Math.abs(n);
		double y = subPow(x, n);
		if (neg) { y = 1/y; }
		return y;
	}
	
	private double subPow(double x, int n) {
		switch(n) {
		case 0: return 1;
		case 1: return x;
		default: double t = subPow(x, n/2);
			double y = (n % 2 == 0) ? 1 : x;
			return t * t * y;
		}	
	}
	

	
	public static void main(String[] args) {
		System.out.println(Double.MAX_VALUE); 
		System.out.println(Double.MIN_VALUE);
	}
}
