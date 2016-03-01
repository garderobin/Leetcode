package interview;

import java.util.HashSet;
import java.util.Set;

public class KangranOscar {
	
	public static int solutionV2(int N, int K, int[] nums) {
		Set<Integer> e1 = new HashSet<Integer>(), e2 = new HashSet<Integer>();
		int pair = 0;
		for (int a: nums) {
			boolean b1 = e1.contains(a), b2 = e2.contains(a);
			if (b1 && b2) {
				pair += 2;
			} else if (b1) {
				++pair;
				if (a <= Integer.MAX_VALUE - K) { e1.add(a + K); }
			} else if (b2) { 
				++pair;
				if (a - K > 0) 					{ e2.add(a - K);  }
			} else {
				if (a <= Integer.MAX_VALUE - K) { e1.add(a + K); }
				if (a - K > 0) 					{ e2.add(a - K);  }
			}
			
		}
		return pair;
	}
	
	public static int solutionV1(int N, int K, int[] nums) {
		Set<Integer> e1 = new HashSet<Integer>(), e2 = new HashSet<Integer>();
		int pair = 0;
		for (int a: nums) {
			boolean b1 = e1.contains(a), b2 = e2.contains(a);
			if (b1 && b2) {
				pair += 2;
			} else if (b1) {
				++pair;
				if (a <= Integer.MAX_VALUE - K && !e1.add(a + K)) { ++pair; }
			} else if (b2) { 
				++pair;
				if (a - K > 0 && !e2.add(a - K)) { ++pair; }
			} else {
				if (a <= Integer.MAX_VALUE - K) { e1.add(a + K); }
				if (a - K > 0) 					{ e2.add(a - K);  }
			}
			
		}
		return pair;
	}
	
	
	public static void test() {
//		int nums[] = {1,5,3,4,2};
//		int N = 5, K = 2;
//		int nums[] = {363374326, 364147530, 61825163, 1073065718, 1281246024, 1399469912, 428047635, 491595254, 879792181, 1069262793};
//		int N = 10, K = 1;
		int nums[] = {1,5,3,2,4,6};
		int N = 5, K = 2;
		System.out.println(solutionV2(N, K, nums));
	}
	
	
	public static void main(String[] args) {
		test();
	}
}
