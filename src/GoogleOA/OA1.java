package GoogleOA;

public class OA1 {
	
	public static int maxDuplicate2(int X) {
    	int trail = 1, max = 0, num = X;
    	while(trail <= num) {
    		trail *= 10;
    		int second = num % trail, first = (num - second) * 10, between = second / (trail / 10);
    		int result = first + second + between * trail;
    		if(result > max) {
    			max = result;
    		}
    	}
    	return max;
    }
	
	public static int maxDuplicate(int num) {
    	int trail = 1, max = 0;
    	while(trail <= num) {
    		trail *= 10;
    		int second = num % trail, first = (num - second) * 10, between = second / (trail / 10);
    		int result = first + second + between * trail;
    		if(result > max) {
    			max = result;
    		}
    		System.out.println("X = " + num + ",\t trail = " + trail + ",\t first = " + first + ",\t second = " + second + 
    				",\t between = " + between + ",\t result = " + result + ",\t max = " + max);
    	}
    	return max;
    }
	
	/**
	 * Task 1 in first round OA.
	 * Find the largest number that can be obtained from X by duplicating one if its digits.
	 * 这道题的关键是找到从左边开始第一个peek点，即正数从这个点往后开始下降，负数从这个点往后开始上升。
	 * @param X
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int solution1(int X) {
		if (X == 0) { 
			return 0; 
		} else {
			int sign = (X > 0) ? 1 : -1;
			int Y = Math.abs(X);
			if (Y < 10) {
				return (Y * 10 + Y) * sign;
			} else {
				// Initialization
				int digitCount = (int) Math.log10((double) Y ) + 1;				
				int[] digits = new int[digitCount];
				int[] values = new int[digitCount];
				for (int i = digitCount - 1, y = Y, mod = 1; i >= 0; i--, y /= 10, mod *= 10) {
					digits[i] = y % 10;
					values[i] = digits[i] * mod;
				}
				
				// Find the first peak point
				for (int left = values[0], i = 1; i < digitCount; i++) {
					if (digits[i] * sign < digits[i-1] * sign) { // peak point found
						int rst = left * 10;
						for (int j = i-1; j < digitCount; j++) {
							rst += values[j];
						}
						return rst * sign;
					} else {
						left += values[i];
					}
						
				}
	
				return (Y * 10 + values[digitCount-1]) * sign;
			}
		}
	}
	
//	private static void testSolution1() {
//		int X = 0;
//        Scanner in = new Scanner(System.in);
//        X = in.nextInt();
//        System.out.println(solution1(X));
//	}
//	
	public static void main(String[] args) {
		int[] list = {1110, 1112, 12223, 122243, 54321, 11111, 53421, 13324};
		for (int X: list) {
//			System.out.println(maxDuplicate(X));	
			System.out.println(((-1) * X) % (-10));
		}
		
	}

}
