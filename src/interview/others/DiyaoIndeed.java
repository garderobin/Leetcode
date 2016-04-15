package interview.others;

public class DiyaoIndeed {
	
	public static void main(String[] args) {
		String[] arr = {"2", "3 9", "17 24"};
		count_Squares(arr);
	}
	static void count_Squares(String[] arr) {
//		int numCases = Integer.parseInt(arr[0]);
		for (int i = 0; i < arr.length; ++i) {
			String[] split = arr[i].split(" ");
			int lowerBound = Integer.parseInt(split[0]), upperBound = Integer.parseInt(split[1]);
			int l0 = (int)Math.sqrt((double)lowerBound), l1 = (int)Math.sqrt((double)(lowerBound - 1));
			int r0 = (int)Math.sqrt((double)upperBound);
			int count = (l0 - l1) + (r0 - l0);
			
			System.out.println(count);
		}
	}
}
