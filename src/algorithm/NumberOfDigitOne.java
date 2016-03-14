package algorithm;

public class NumberOfDigitOne {
	public static int countDigitOne(int n) {
		if (n < 1) {
			return 0;
		}
		if (n < 10) {
			return 1;
		}
		if (n == 10) {
			return 2;
		}
		int m, last_digit, count;
		m = 1; count = 0;
		while (n > 0) {
			last_digit = n - (int)(n/10) * 10;
			count += y(last_digit, m);						
			m *= 10; 
			n /= 10;
		}
				
		return count;
        
    }
	/**
	 * 存疑，还没有弄懂
	 * @param n
	 * @return
	 */
	public int countDigitOneDiscussion(int n) {
	    int ones = 0;
	    for (long m = 1; m <= n; m *= 10)
	        ones += (n/m + 8) / 10 * m + (n/m % 10 == 1 ? n%m + 1 : 0);
	    return ones;
	}
	
	private static int y(int digit, int m) {
		if ((digit == 1 && m == 10) || (digit > 0 && m == 1)) {
			return 1;
		}		
		if (digit == 0) {
			return 0;
		}		
		if (digit == 1) {
			m /= 10;
			return m + 10 * y(1, m) + 1;
		}
		return m + digit * y(1, m);
		
	}
	
	public static void main(String args[]) {
		//test digit
//		int n = 237;
		System.out.println(countDigitOne(1100));	
	}
}
