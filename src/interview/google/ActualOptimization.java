package interview.google;

public class ActualOptimization {
	
	/**
	 * 纯数学，从左往右的办法查找bottom, 目前效率比小苗的方法稍差，但是如果对于输入值的限制不只是十位数字，这个算法的效率会显著提高。
	 * digit=remain/mods[--j]; 这句话放在for循环内部与放在for循环尾部条件里非常不一样，放内部是先检查边界条件再执行下一句
	 * 放尾部条件是先执行这一句，再检查for边界条件，因此会产生--j造成的ArrayIndexOutOfBounds.
	 * @param X
	 * @return
	 */
	private static int optimizeOA1(int X) {
		int[] mods = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
		boolean bottomFound = false;
		int len = 0, dupIndex = -1;
		for (len = mods.length; len > 0 && X < mods[len-1]; len--) {} // find the digit size.
		for (int i=1, j=len-1, prev=X/mods[j], remain=X%mods[j], digit=-1; i<len; i++, prev=digit, remain%=mods[j] ) {
			digit=remain/mods[--j]; 
			if (digit == prev) { 
				dupIndex = i; 
				if (bottomFound) {  return ((X/mods[j+1]) * mods[j]) + (X%mods[j]); } 
			} else if (digit > prev) {
				if (dupIndex == i-1) { return (X/mods[j+2]) * (mods[j+1]) + (X%mods[j+1]); }
				else { bottomFound = true; } // bottom found, but duplicate not found
			} else {}
		}
		int j = len - dupIndex;
		return ((X/mods[j+1]) * mods[j]) + (X%mods[j]); 
	}
	
	/**
	 * 使用String 和 bottom查找，从左往右的办法，正确性已经经过验证。
	 * @param X
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int optimizeOA1String(int X) {
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
//		int[] list = {887899, 8811, 8899, 201334226, 1110, 1112, 12223, 122243, 11111, 13324};
		int[] list = {117899111};
		long startTime1 = System.nanoTime();

		for (int i = 0; i < 100000; i++) {
			for (int X: list) {
				optimizeOA1(X);	
//				System.out.println(optimizeOA1(88765699));	
//				ActualSubmit.solutionOA1Actual(X);
			}
		}
	
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
