package algorithm;

public class PowerOfTwo {
	public boolean isPowerOfTwo(int n) {
		if (n < 2) {
			return n==1;
		}
		return (n % 2 == 0) && isPowerOfTwo (n/2);
	}
	/* 超时
	public boolean isPowerOfTwo(int n) {
        int p = 1;
        while (p < n) {
        	p = p << 1;
        }
        return (p==n);
    }
    */
}
