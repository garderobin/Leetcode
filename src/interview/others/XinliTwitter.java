package interview.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XinliTwitter {
	static int[] coprimeCount(int[] A) {
		int[] B = new int[A.length];
		for (int i = 0; i < A.length; ++i) {
			B[i] = coprimeCount(A[i]);
		}
		return B;
	}
	
	static int coprimeCount(int n) {
		int count = 1;
		if (isPrime(n)) return n-1;
		for (int i = 2; i < n; ++i) {
			if (gcd(i, n) == 1) ++count;
		}
		return count;
	}
	
	static int gcd(int a, int b){
        if (b==0) return a;
        else return gcd(b,a%b);
    }
	
	public static int factorialRemainder(int n) {
		if (n == 1) return 0;
		if (n < 4) return n;
		return 1 + countPrimes(n);
	}
	
	public static int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        int limit = (int) Math.sqrt(n);
        for (int i=2; i<n; i++){
            isPrime[i] = true;
        }
        for (int i=2; i<=limit; i++){
            if (!isPrime[i]) continue;
            for (int j=i*i; j<n; j+=i){
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i=2; i<n; i++){
            if(isPrime[i]) count++;
        }
        return count;
    }
	
	public static boolean isPrime(int number) {
	      if (number == 1)
	            return false;
	      if (number == 2)
	            return true;
	      if (number % 2 == 0)
	            return false;
	      for (int d = 3; d <= (int) Math.sqrt(number); d++)
	            if (number % d == 0)
	                  return false;
	      return true;
	}

	
	public static int factorial(int n) {
		if (n == 0) return 0;
		if (n == 1)  return 1;
		return n * factorial(n-1);
	}
	
	public static void main(String[] args) {
//		System.out.println(i + ", " + fct + ", " + fct % i + ", " + (i-1));
//		int n = 100;
//		for (int i = 2; i <= n; ++i) {
//			int fct = factorial(i-1);
//			System.out.println("rst: " + i + ": " + factorialRemainder(i));
//		}
		
		int[] A  = {1,3};
		for (int e: A) System.out.println(coprimeCount(e));
		int[] B = coprimeCount(A);
		for (int e: B) {System.out.println(e); }
 	}
	
	public static void test() {
//		int test[] = {2, 3, 8, 10, 16, 19, 32, 36, 64, 69, 100};
//		int test[] = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
//		int[] test = {1, 3, 7, 14, 21, 31, 43, 57, 73, 91};
		int[] test = {0, 1, 2, 5, 8, 50, 61, 72, 85, 98};
//		HashSet<Integer> set = new HashSet<Integer>();
		List<Integer> sum = new ArrayList<Integer>();
		for (int i = 0; i < 10; ++i) {
			for (int j = i+1; j < 10; ++j) {
//				if (!set.add(test[i] + test[j])) { System.out.println(test[i] + ", " + test[j]); }
				sum.add(test[i] + test[j]);
			}
		}
		Collections.sort(sum);
		System.out.println(sum);
	}
}
