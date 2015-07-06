package algorithm;

// not correct!
public class NumberOfOneBits {
	public int hammingWeight(int n) {
		if (n == 0) {
			return 0;
		}
        int c = 1; // the last result by dividing 2 or 3
        while (n > 1) {
        	if (n % 2 == 1) {
        		c++;
        	}
        	n /= 2;
        }        
        return c;
    }
	

}
