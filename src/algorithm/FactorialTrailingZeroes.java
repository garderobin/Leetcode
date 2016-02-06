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
	
	public static int trailingZeroesTD(int n) {
        int count = 0;
        int number = 0;
        int rank = (int)Math.log(n)/(int)Math.log(5);
        System.out.println(Math.log(n) + "\t" + (int)Math.log(n) + "\t" + Math.log(5) + "\t" + (int)Math.log(5) + "\t" + (int)(Math.log(n)/Math.log(5)));
        //System.out.println(rank);
        for(int i=rank; i>0; i--){
            int a = n/(int)Math.pow(5,i);
            //System.out.println("a = " + a + "\tpow = " + Math.pow(5,i) + "\t count = " + count + "\ti = " + i);
            count = a - count;
            number += count*i;
            count = a;
        }
        return number;
    }

	
	public static void main(String[] args) {
		System.out.println(trailingZeroesTD(2147483647));
		System.out.println(trailingZeroesTD(2147483646));
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
