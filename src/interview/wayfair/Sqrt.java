package interview.wayfair;

public class Sqrt {
	
	/*
	 * Newton method. Cool!!!
	 */
	public int sqrtV3(int x) {
		long r = x;
	    while (r*r > x)
	        r = (r + x/r) / 2;
	    return (int) r;
	}
	
	
	/*
	 * Bit manipulation. O(1) time.
	 */
	public int sqrtV2(int x) {
	    long ans = 0;
	    long bit = 1l << 16;
	    while(bit > 0) {
	        ans |= bit;
	        if (ans * ans > x) {
	            ans ^= bit;
	        }
	        bit >>= 1;
	    }
	    return (int)ans;
	}
	
	/*
	 * Binary Search. O(logN) time.
	 */
	public int sqrtV1(int x) {
	    if (x == 0) return 0;
	    int left = 1, right = Integer.MAX_VALUE;
	    while (true) {
	        int mid = left + (right - left)/2;
	        if (mid > x/mid) {
	            right = mid - 1;
	        } else {
	            if (mid + 1 > x/(mid + 1))
	                return mid;
	            left = mid + 1;
	        }
	    }
	}
}
