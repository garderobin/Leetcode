package algorithm;

public class MySqrt {
	
	/**
	 * 未完成
	 * @param n
	 * @return
	 */
	public int mySqrt(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 0) {
			return 0;
		}
		return 0;
	}
	
	/**
	 * Bit-manipulation.
	 * Since sqrt(x) is composed of binary bits, 
	 * I calculate sqrt(x) by deciding every bit from the most significant to least significant. 
	 * Since an integer n can have O(log n) bits with each bit decided within constant time, 
	 * this algorithm has time limit O(log n), actually, because an Integer can have at most 32 bits, 
	 * I can also say this algorithm takes O(32)=O(1) time.
	 * @param x
	 * @return
	 */
	public int sqrtDiscussion(int x) {
	    if(x==0)
	        return 0;
	    int h=0;
	    while((long)(1<<h)*(long)(1<<h)<=x) // firstly, find the most significant bit
	        h++;
	    h--;
	    int b=h-1;
	    int res=(1<<h);
	    while(b>=0){  // find the remaining bits
	        if((long)(res | (1<<b))*(long)(res |(1<<b))<=x)
	            res|=(1<<b);
	        b--;
	    }
	    return res;
	}
}
