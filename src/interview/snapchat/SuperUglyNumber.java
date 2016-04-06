package interview.snapchat;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
 * For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 * Note:
 * (1) 1 is a super ugly number for any given primes.
 * (2) The given numbers in primes are in ascending order.
 * (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 *
 */
public class SuperUglyNumber {
	public static void main(String[] args) {
		int[] primes = {2, 7, 13, 19};
		nthSuperUglyNumberHeap(12, primes);
	}
	
	/* O(log(k)N) time, O(n+k) space. 
	 * But when k and n are considerably small, it cost more time due to the use of higher level object.
	 * Can be improved by indexed heap (see IndexMinPQ.java)*/
	public static int nthSuperUglyNumberHeap(int n, int[] primes) {
	    int[] ugly = new int[n];

	    PriorityQueue<Num> pq = new PriorityQueue<>((Num o1, Num o2) -> o1.val - o2.val);
	    for (int p: primes) pq.offer(new Num(p, 1, p));
	    ugly[0] = 1;

	    for (int i = 1; i < n; i++) {
	        ugly[i] = pq.peek().val;
	        while (pq.peek().val == ugly[i]) {
	            Num next = pq.poll();
	            pq.offer(new Num(next.p * ugly[next.idx], 1 + next.idx, next.p));
	        }
	    }

	    return ugly[n - 1];
	}

	static class Num {
	    int val;
	    int idx;
	    int p;

	    public Num(int val, int idx, int p) {
	        this.val = val;
	        this.idx = idx;
	        this.p = p;
	    }
	}
	
	/* Optimized DP solution having deduced redundant multiplication, O(nk) time, O(n+2k) space. */
	public int nthSuperUglyNumberV2(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);

        int next = 1;
        for (int i = 0; i < n; i++) {
            ugly[i] = next;
            next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                //skip duplicate and avoid extra multiplication
                if (val[j] == ugly[i]) val[j] = ugly[idx[j]++] * primes[j];
                //find next ugly number
                next = Math.min(next, val[j]);
            }
        }

        return ugly[n - 1];
    }
	
	/* Common DP solution with redundant multiplication, O(nk) time, O(n+k) space. */
	public static int nthSuperUglyNumberV1(int n, int[] primes) {
		int[] rst = new int[n], pos = new int[primes.length];
        rst[0] = 1;
	
	    for (int i = 1; i < n; i++) {
	    	rst[i] = Integer.MAX_VALUE;
	        for (int j = 0; j < primes.length; ++j) {
	            rst[i] = Math.min(rst[i], primes[j] * rst[pos[j]]);
	        }
	
	        for (int j = 0; j < primes.length; ++j) { 
	            if (rst[i] == primes[j] * rst[pos[j]]){ // 用过的不再用了，这个index加就是为了下一次乘法的时候往后面选择对象
	                ++pos[j]; //这里不能break, 因为这种情况可能出现不止一次，每一次都不能再用了。
	            }
	        }
	    }

       return rst[n - 1];
	}
	
	
}
