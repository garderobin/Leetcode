package GoogleOA;

public class OA1 {
	
	/**
	 * Find the largest number that can be obtained from X by duplicating one if its digits.
	 * 这道题的关键是找到从左边开始第一个peek点，即正数从这个点往后开始下降，负数从这个点往后开始上升。
	 * 等于也视作找到了断点
	 * @param X
	 * @return
	 */
	private static int solution(int X) {
		if (X == 0) { 
			return 0; 
		} else if (X > 0) { 
			if (X < 10) {
				return X * 10 + X;
			}
			int digitCount = (int) Math.log10((double)X) + 1;
			// Get all digits
			int[] digits = new int[digitCount];
			int[] values = new int[digitCount];
			for (int i = digitCount - 1, y = X, mod = 1; i >= 0; i--, y /= 10, mod *= 10) {
				digits[i] = y % 10;
				values[i] = digits[i] * mod;
			}
			
			// Find the first peak point
			for (int left = values[0], i = 1; i < digitCount; i++) {
				if (digits[i] < digits[i-1]) { // peak point found
					int rst = left * 10;
					for (int j = i-1; j < digitCount; j++) {
						rst += values[j];
					}
					return rst;
				} else {
					left += values[i];
				}
				
			}

			return X * 10 + values[digitCount-1];
				
			
			
		} else { // X < 0
			if (X > -10) {
				return X * 10 + X;
			}
			
			return 0;
		}
	}
	
	public static void main(String[] args) {
//		int[] list = {12511, 12345, 31245, 123, 12, 1, 0};
		int[] list = {12223, 122243, 54321, 11111, 53421, 13324};
		for (int X: list) {
			System.out.println(solution(X));
//			int digitCount = (int) Math.log10((double)X) + 1;
//			int[] digits = new int[digitCount];
//			int[] values = new int[digitCount];
//			for (int i = digitCount - 1, y = X, mod = 1; i >= 0; i--, y /= 10, mod *= 10) {
//				digits[i] = y % 10;
//				values[i] = digits[i] * mod;
//			}
//			
//			for (int e: values) {
//				System.out.println(e);
//			}
			
		}
		
	}
	
//	int tenMod = (int) Math.pow(10, digitCount - 1); // X=12511 -> tenMod = 10000
//	double m = (double) X / tenMod;
//	int prev = (int) m; 
//	int left = prev *  tenMod * 10;
//	int cur = (int) ((m - prev) * 10);
//	// 寻找下降点
//	for (m = (m - prev) * 10; cur > prev && tenMod >= 1; tenMod /= 10) {				
//		left += cur * tenMod;									
//		prev = cur;
//		m = (m - prev) * 10;
//		cur = (int) m;				
//	}
//	if (tenMod < 1) {
//		return left + prev;
//	}
//	return left + (X - (int)(left / 10)) + prev * tenMod;

}
