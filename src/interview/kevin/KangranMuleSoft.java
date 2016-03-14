package interview.kevin;

public class KangranMuleSoft {
	
	/* 第一题one-pass. O(n) time, O(1) space.*/
	public static boolean solutionP1(int[] A) {
		if (A == null || A.length < 3) { return true; }
		int d1 = -1, d2 = -1;
		for (int i = 1; i < A.length; ++i) {
			if (A[i-1] > A[i]) {
				if 		(d1 < 0) { d1 = i; }
				else if (d2 < 0) { d2 = i; }
				else 			 { return false; }
			}
		}
		if 		(d1 < 0) { return true; }
		else if (d2 < 0) { return (d1 == 1 || A[d1] >= A[d1-2]) && (d1 == A.length-1 || A[d1-1]<=A[d1+1]); } 
		else 			 { return A[d2] <= A[d1] && A[d2-1] <= A[d1-1]; }
	}
	
	
	/* 第二题归位法。O(n) time, O(1) space.*/
	public static int solutionP2V2(int[] A) {
		int max = 1;
		for (int i = 0, cur = 1; i < A.length; ++i) {
			for (cur = 1; i != A[i]; ++cur ) { swap(A, i, A[i]); }
			max = (cur > max) ? cur : max;
		}
		return max;
	}
	
	public static void swap(int[] A, int n, int m) {
		int temp = A[n];
		A[n] = A[m];
		A[m] = temp;
	}
	
	
	public static int solutionP2V1(int[] A) {
		int max = 0;
		boolean [] visited = new boolean[A.length];
		for (int i = 0, cur = 1; i < A.length; cur = 1, visited[i++] = true) {
			if (visited[i]) { continue; }
			for (int initial = A[i], j = initial; A[j] != initial; j = A[j]) {
				++cur;
				visited[j] = true;
			}
			max = (cur> max) ? cur : max;
		}
		
		return max;
	}
	
	/* 第一题one-pass. O(n) time, O(1) space.*/
	public static boolean solutionP1Test(int[] A) {
		if (A == null || A.length < 3) { return true; }
		int d1 = -1, d2 = -1;
		for (int i = 1; i < A.length; i++) {
			if (A[i-1] > A[i]) {
				if (d1 < 0) { d1 = i; }
				else if (d2 < 0) { d2 = i; }
				else { return false; }
			}
		}
		if (d1 < 0) { return true; }
		else if (d2 < 0) { 
			return (d1 == 1 || A[d1] >= A[d1-2]) && (d1 == A.length - 1 || A[d1-1] <= A[d1+1]);
//			if (d1 == 1) {
//				return (A[0] < A[2]);
//			} else if (d1 == A.length - 1) {
//				return A[d1] >= A[d1-2];
//			} else {
//				return A[d1] >= A[d1-2] && A[d1-1] <= A[d1+1];
//			}
		} else { return A[d2] <= A[d1] && A[d2-1] <= A[d1-1]; }
	}
	
	
	public static int solutionP2Test(int[] A){ 
		int max = 0;
		for (int i = 0, cur = 1; i < A.length; i++, cur = 1) {
			if (i == A[i]) { continue; }
			for (int j = i; j != A[j]; ++cur ) {
//				System.out.print("j=" + j + ",\tA[" + j + "]=" + A[j] + ",\tA=[");
//				for (int a: A) {
//					System.out.print(", " + a);
//				}
//				System.out.println("]");
				swap(A, j, A[j]);
			}
			max = (cur> max) ? cur : max;
		}
		
		return max;
	}
	
	
	
	public static void test() {
//		int nums[] = {1,5,3,2,4,6};
//		int [] A = {5, 4, 0, 3, 1, 6, 2}; //e: 4
//		int[] A = {1,3,4,0,5,2}; // 3
//		int[] A = {1,0,4,3,5,2};
//		int[] A = {0,1,2,3,4,5};
//		int[] A = {5,4,3,2,1,0};
//		int[] A = {5,3,0,2,4,1}; //e: 5
//		int[] A = {5,4,0,2,3,1}; //e: 6
//		int[] A = {0,1,2,3,4,5}; //e: 1
//		int[] A = {1, 3, 5, 7, 9, 10, 0, 2, 4, 6, 8};
//		int[] A = {1, 10, 5, 7, 4};
//		int[] A = {1, 3, 5, 3, 7};
//		int[] A = {0,2,0,0,0,1,3};
//		int[] A = {1, 3, 5, 0, 3, 7};
//		int[] A = {9, 1, 2, 3};
//		int[] A = {1, 3, 5, 0, 4, 9};
//		int[] A = {1, 5, 10, 3, 7};
		int[] A = {1, 3, 5, 0};
		System.out.println(solutionP1(A));
	}
	
	
	public static void main(String[] args) {
		test();
	}
}
