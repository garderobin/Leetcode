package interview;

public class KangranAkuna {
	
	private static String solution(String s1, String s2) {
		int[] a1 = new int[256], a2 = new int[256];
		
		for (int i = 0, len = s1.length(); i < len; i++) {
			++a1[s1.charAt(i)];
		}
		for (int i = 0, len = s2.length();i < len; i++) {
			++a2[s2.charAt(i)];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 'a'; i < 'z' + 1; i++) {
			if (a1[i] > 0 && a2[i] > 0) {
				for (int j = 0, l = Math.min(a1[i], a2[i]); j < l; j++) {
					sb.append((char)i);
				}
			}
		}
		for (int i = 'A'; i < 'Z' + 1; i++) {
			if (a1[i] > 0 && a2[i] > 0) {
				for (int j = 0, l = Math.min(a1[i], a2[i]); j < l; j++) {
					sb.append((char)i);
				}
			}
		}
		return sb.toString();
	}
	
	public static void testIntersection() {
		String s1 = "abababadabadbad", s2 = "werwerwewerwerababa"; 
		System.out.println(solution(s1, s2));
	}
	
	@SuppressWarnings("unused")
	private static int largestProductV2(int[] nums) {
		int left = 0, first = 0, second = 0, third = 0, e = nums.length; 
		for (int i = 0; i < e; i++) {
			if (nums[i] >= nums[first]) { 
				left = first;
				second = first;
				first = i;
			} else if (nums[i] >= nums[second]) { 
				third = second;
				if (nums[i] != nums[second]) { second = i; }
			} else if (nums[i] >= nums[third]) {
				if (nums[i] != nums[third]) { third = i; }
			} 
		}
		
		return (second < first) ? nums[left] * nums[first] : Math.max(nums[left] * nums[first], nums[second] * nums[third]);
	}
	
	public static int largestProductV1(int[] nums) {
		return helper(nums, 0, -1);
	}
	
	private static int helper(int[] nums, int s, int maxPos) {
		if (s == maxPos || s == nums.length) { return Integer.MIN_VALUE; }
		int left = s, first = s, second = s, e = nums.length; 
		for (int i = s; i < e; i++) {
			if (nums[i] >= nums[first]) { 
				left = first;
				second = first;
				first = i;
			} else if (nums[i] > nums[second]) { 
				second = i;
			} 
		}

		return (second < first) ? nums[left] * nums[first] : Math.max(nums[left] * nums[first] , helper(nums, first+1, second));
	}
	
	
	
	
	
	public static void test() {
//		int nums[] = {4,2,3,7};
//		int[] nums = {1, 2, 6, 2, 3, 7};
//		int[] nums = {1, 2, 6, 2, 3, 7, 4, 2};
//		int[] nums = {1, 2, 4, 2, 3, 7, 6, 2};
//		int[] nums = {1, 2, 4, 2, 3, 7, 5, 6, 2};
		int[] nums = {1, 2, 4, 2, 3, 7, 6, 5, 2};
		System.out.println(largestProductV1(nums));
	}
	
	public static void main(String[] args) {
		testIntersection();
	}

}
