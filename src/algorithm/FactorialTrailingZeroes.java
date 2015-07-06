package algorithm;

public class FactorialTrailingZeroes {
	public static int trailingZeroes(int n) {
		if (n < 5) {
			return 0;
		}
		int sum = 0;
		while (n > 4) {
			n /= 5;
			sum = sum + n;					
		}
		return sum;        
    }
	
	public static void main(String[] args) {
		System.out.println(trailingZeroes(69));
	}
	
	public static void test(int x) {
		long y = Long.valueOf(x);
		int f = 1;
		int i = 1;
		int c = 1;
		for (i = 5; i < y + 1; i += 5) {
			f = f * i * 2;
//			if (i % 2 == 0 || i % 5 == 0) {
//				f *= i;
//			}
			if (f % 10 == 0) {
				f /= 10;
				c++;				
			}
		}
		System.out.println(c);
	}
}
