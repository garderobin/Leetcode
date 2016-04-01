package algorithm;

// not correct!
public class NumberOfOneBits {
	
	/**
	 * https://leetcode.com/discuss/30605/simple-java-solution-bit-shifting
	 * @param n
	 * @return
	 */
	
	/* An Integer in Java has 32 bits, e.g. 0010 1000 0111 1001 0100 0010 0001 1010.
		To count the 1s in the Integer representation with put the input int n in bit AND with 1 
		(that is represented as 00000000000000000000000000000001), 
		and if this operation result is 1, that means that the last bit of the input integer is 1. 
		Thus we add it to the 1s count.
		ones = ones + (n & 1); 
		
		Then we shift the input Integer by one on the right, to check for the next bit.
		
		n = n>>>1;
		We need to use bit shifting unsigned operation >>> (while >> depends on sign extension)
		
		We keep doing this until the input Integer is 0.
		In Java we need to put attention on the fact that the maximum integer is 2147483647. 
		Integer type in Java is signed and there is no unsigned int. 
		So the input 2147483648 is represented in Java as -2147483648 
		(in java int type has a cyclic representation, that means Integer.MAXVALUE+1==Integer.MINVALUE). This force us to use
		
		n!=0
		in the while condition and we cannot use
		
		n>0
		because the input 2147483648 would correspond to -2147483648 in java 
		and the code would not enter the while if the condition is n>0 for n=2147483648.
	*/
	public static int hammingWeightDiscussion(int n) {
        int ones = 0;
        while(n!=0) {        	
            ones = ones + (n & 1);            
            n = n>>>1; 
        }
        return ones;
    }
	
	public int hammingWeight(int n) {
		if (n == 0) {
			return 0;
		}
        int c = 1; // the last result by dividing 2 or 3
        while (n > 1) {
        	c += (n & 1);
        	n /= 2;
        }        
        return c;
    }
	
	public static void main(String[] args) {
//		int num = 128;
////		System.out.println(0 << 1);
//		for (int i = 0, j = 1; i <= num; ++i) {
////			if (i == j << 1) { 
////				System.out.println(); 
//////				j <<= 1;
////			}
////			System.out.print(hammingWeightDiscussion(i) + ", ");
//			System.out.println(i - (i & (-i)));
//		}
	}

}
