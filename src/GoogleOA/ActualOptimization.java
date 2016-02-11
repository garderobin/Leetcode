package GoogleOA;

public class ActualOptimization {
	
	/**
	 * 使用bottom查找，从左往右的办法，正确性已经经过验证。
	 * @param X
	 * @return
	 */
	private static int optimizeOA1(int X) {
		String s = X + "";
		int digitCount = s.length(), dupIndex = -1;
		boolean bottomFound = false;
		
		for (int i = 1, prev = s.charAt(0)-'0', digit = prev; i < digitCount; i++, prev = digit) {
			digit = s.charAt(i) - '0';
			if (digit == prev) {
				dupIndex = i; 
				if (bottomFound) { break; }
			} else if (digit > prev) {
				if (dupIndex == i-1) { break; }
				else { bottomFound = true; } // bottom found, but duplicate not found
			} else {}
		}
		
		int offset = 1, left = X / 10;
		for (int j = digitCount - dupIndex - 1; j > 0; j--) {
			offset *= 10;
			left /= 10;
		}
		return left * offset + (X % offset);
	}
	
	public static void main(String[] args) {
		int[] list = {887899, 8811, 8899, 201334226, 1110, 1112, 12223, 122243, 11111, 13324};
		long startTime1 = System.nanoTime();

//		for (int i = 0; i < 100000; i++) {
			for (int X: list) {
				System.out.println(optimizeOA1(X));	
//				System.out.println(optimizeOA1(88765699));	
//				ActualSubmitt.solutionOA1Actual(X);
			}
//		}
	
	// 88765699

		long endTime1 = System.nanoTime();
		System.out.println((endTime1 - startTime1)/1000000.0 + " ms");
		
	}
	
//	   54332100234
//	   5432100234
//	   5433210234
//	   5432101233422677
//	   543210123342677
//	   543210123422677
//	2101334226
//	210134226
//	210133426\
//	88765699
//	8765699
//	8876569
	
}
