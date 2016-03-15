package interview.wayfair;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class KthRandomElementFromArray {
	
	public static void main(String[] args) {
//		int[] test = {1,2,3,4,5,6,7,8,9,10};
//		int k = 5;
//		int[] test = {1, 2};
		int[] check = new int[10];
//		for (int i = 0; i < 5; ++i) {
//			int rst = singleRandomElementFromArray(test);
//			++check[rst-1];
//			int[] rst = kRandomElementsFromArray(test, k);
			int[] rst = kRandomElementsFromStream();
			for (int e: rst) { 
				System.out.println(e);
				++check[e-1]; 
			}
//		}
//		System.out.println("occurence: ");
//		for (int i = 0; i < 10; ++i) { System.out.print((i+1) + ":" + check[i] + ", "); }
	}
	
	/* 
	 * Reservoir sampling algorithm.
	 * Since array index i is zero-based, the possibility should be 1/(i+1) which is 1/position.
	 */
	public static int singleRandomElementFromArray(int[] nums) {
		if (nums == null || nums.length == 0) { return 0; }
		
		int cur = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			if (ThreadLocalRandom.current().nextInt(0, i+1) == 0) { cur = nums[i]; }
		}
		return cur;
	}
	
	public static int[] kRandomElementsFromArray(int[] nums, int k) {
		if (k <= 0 || nums == null || nums.length == 0) { return new int[0]; }
		
		int[] rst = new int[k];
		for (int i = 0; i < k; ++i) { rst[i] = nums[i]; }
		for (int i = k; i < nums.length; ++i) {
			int j = ThreadLocalRandom.current().nextInt(0, i+1);
			if (j < k) { rst[j] = nums[i]; }
		}
		return rst;
	}
	
	/** 
	 * Ask: how to handle invalid input, like characters? current returns 0.
	 * The current stream is from standard input.
	 * The size of stream is not specified.
	 */
	public static int singleRandomElementFromStream() {
		Scanner in = new Scanner(System.in);
		int cur = 0;
		try {
			cur = in.nextInt();
			for (int i = 1; in.hasNext(); ++i) {
				if (ThreadLocalRandom.current().nextInt(0, i+1) == 0) { 
					cur = in.nextInt(); 
				}
			}
		} catch (InputMismatchException e) { 
			cur = 0; 
		} // regard invalid input as the shut
		in.close();
		return cur;
	}
	
	/**
	 * The k and the array of numbers should both be read from standard input
	 * The first line is k, and the rest lines are array numbers.
	 */
	public static int[] kRandomElementsFromStream() {
		Scanner in = new Scanner(System.in);
		try {
			int k = in.nextInt();
			int[] rst = new int[k];
			for (int i = 0; i < k; ++i) { rst[i] = in.nextInt(); }
			for (int i = k; in.hasNext(); ++i) { 
				int j = ThreadLocalRandom.current().nextInt(0, i+1);
				if (j < k) { rst[j] = in.nextInt(); }
			}
			in.close();
			return rst;
		} catch (Exception e) { 
			in.close();
			return new int[0]; 
		}
	}
}
