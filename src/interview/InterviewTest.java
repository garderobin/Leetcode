package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterviewTest {
	
	public static void main(String[] args) {
//		InterviewTest sol = new InterviewTest();
//		System.out.println(sol.solution());
		test();
	}
	
	public static void test() {
//		int test[] = {2, 3, 8, 10, 16, 19, 32, 36, 64, 69, 100};
//		int test[] = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
//		int[] test = {1, 3, 7, 14, 21, 31, 43, 57, 73, 91};
		int[] test = {0, 1, 2, 5, 8, 50, 61, 72, 85, 98};
//		HashSet<Integer> set = new HashSet<Integer>();
		List<Integer> sum = new ArrayList<Integer>();
		for (int i = 0, count = 0; i < 10; ++i) {
			for (int j = i+1; j < 10; ++j) {
//				if (!set.add(test[i] + test[j])) { System.out.println(test[i] + ", " + test[j]); }
				sum.add(test[i] + test[j]);
			}
		}
		Collections.sort(sum);
		System.out.println(sum);
	}
	
	public int solution() {
		return 0;
	}
}
