package algorithm;

import java.util.Arrays;

public class StrobogrammaticNumberIII {
	
	public static void main(String[] args) {
		String low = "10000001";
		String high = "20000000";
		System.out.println(strobogrammaticInRange(low, high));
		System.out.println(strobogrammaticInRange("0", "1680"));
		System.out.println(strobogrammaticInRange("0", "100"));
		System.out.println(strobogrammaticInRange("50", "100"));
		System.out.println(strobogrammaticInRange("100", "50"));
		System.out.println(strobogrammaticInRange("100", "1000"));
		System.out.println(strobogrammaticInRange("0", "1000"));
//		System.out.println("[0," + low + ") = " + countByCeil(count, mapping, low, 0, lowLen-1, true));
//		System.out.println("[0," + high + "] = " + countByCeil(count, mapping, high, 0, highLen-1, false));
	}
	public static int strobogrammaticInRange(String low, String high) {
		if (low.equals(high)) { return isStrobogrammatic(low, 0, low.length()-1) ? 1 : 0; }
		else if (high.equals("0") || high.length() < low.length()) { return 0; }
        // 0-0, 1-1, 6-9, 8-8. 9-6
		int lowLen = low.length(), highLen = high.length();
		int[][] count = new int[highLen + 1][2]; 
		// f[i][0]: the strobogrammatic count for length i which does not allow 0 as border. f[i][1] does not allow 0 borders.		
		
		int[][] mapping = {{0,-1}, {1,1}, {-9,2}, {-9,2}, {-9,2}, {-9,2}, {9,2}, {-9,3}, {8,3}, {6,4}};
		// mapping[i][0]: the corresponding strobogrammatic number for single number i. 
		// mapping[i][1]: how many strobogrammatic number lower than i.		
		
		count[0][0] = 0; count[0][1] = 1; count[1][0] = 0; count[1][1] = 3; 
		for (int i = 2; i < count.length; ++i) {
//			count[i][0] = count[i-2][1] * 4; // for borders type.
			count[i][0] = count[i-1][0] + count[i-1][1];
			count[i][1] = count[i-2][1] * 4; // 0018100, 0168910
		}
		
		return Math.max(0, countByCeil(count, mapping, high, 0, highLen-1, false, false) - 
		    ((low.equals("0")) ? 0 : countByCeil(count, mapping, low, 0, lowLen-1, true, false)));
    }
	
	private static int countByCeil(int[][] count, int[][] mapping, String ceil, 
			int startIndex, int endIndex, boolean strict, boolean evenSensitive) {
//		System.out.println("enter: ceil= " + ceil + ",\tstart = "+ startIndex + ",\tend=" + endIndex);
		if (startIndex > endIndex) { return 0; }
		if (ceil.charAt(startIndex) == '0') { 
			if (ceil.charAt(endIndex) == '0') { 
				return countByCeil(count, mapping, ceil, startIndex+1, endIndex-1, strict, evenSensitive);  
			}
			else if (!evenSensitive) { 
				return countByCeil(count, mapping, ceil, startIndex+1, endIndex, strict, false); 
			}
			else { 
				return countByCeil(count, mapping, ceil, startIndex+1, endIndex-1, strict, evenSensitive); 
			}
		}
		int startDigit = (int)(ceil.charAt(startIndex)) - '0', len = endIndex - startIndex + 1;
		int expectEnd = mapping[startDigit][0];
		int endDigit = (int)(ceil.charAt(endIndex)) - '0';
		
		if (len == 1) { 
//			System.out.println("countByCeil returns " + (mapping[startDigit][1] - (startDigit > 6 ? 1 : 0)));
			return mapping[startDigit][1] - (startDigit > 6 ? 1 : 0); 
		} else if (len == 2) { //only 00, 11, 69, 88, 96
			int[] validForLenTwo = {0, 11, 69, 88, 96};
			int val = ((int)ceil.charAt(endIndex-1) - '0')*10 + endDigit;
			int insert = Arrays.binarySearch(validForLenTwo, val);
			if (insert < 0) { insert = -1 - insert; }
			else { insert = strict ? insert-1 : insert; }
			
			if (!evenSensitive) { insert += 2; }
//			else return insert + 3; // possible: 1, 2, 3, 4, 5
//			System.out.println("ceil=" + ceil + ",\tstart = "+ startIndex + 
//					",\tend=" + endIndex + ",\tinsert returns " + insert);
			return insert;
		}  
 		
//		int nonStrict = (expectEnd < endDigit || (expectEnd == 1 && endDigit == 0))? base : base + count[len-2][1];
		int nonStrict = 0, base = count[len][0] + (mapping[startDigit][1]-1) * count[len-2][1];
		
		if (endDigit == expectEnd) {
			nonStrict =  base + countByCeil(count, mapping, ceil, startIndex+1, endIndex-1, false, true);
//			return (strict && isStrobogrammatic(ceil, startIndex, endIndex) ? nonStrict-1 : nonStrict);
			nonStrict = (strict && isStrobogrammatic(ceil, startIndex, endIndex) ? nonStrict-1 : nonStrict);
		} else if (endDigit > expectEnd) {
//			return  base + countByCeil(count, mapping, ceil, startIndex+1, endIndex-1, false);
			nonStrict = base + countByCeil(count, mapping, ceil, startIndex+1, endIndex-1, false, true);
		} else if (expectEnd >=0 ) {
			StringBuilder newEnding = new StringBuilder();
			newEnding.append('9');
			int i = endIndex-1;
			for (; i > startIndex && ceil.charAt(i) == '0'; --i) {
				newEnding.append('9');
			}
			char c = (char)((int)(ceil.charAt(i)) - 1);
			newEnding.insert(0, c);
			String newCeil = ceil.substring(startIndex, len-newEnding.length()) + newEnding;
//			int newEnding = ((int)ceil.charAt(endIndex-1) - '1')*10 + 9;
//			if (newEnding > 0) {
//			return base + countByCeil(count, mapping, ceil.substring(startIndex, endIndex-1) + newEnding, 0, len-2, false);
			nonStrict = countByCeil(count, mapping, newCeil, 0, len-1, false, false);
//			} else {
////				for (int i = )
//			}
		} else { // expectEnd < 0, startDigit = 2, 3, 4, 5, 7
			nonStrict = base;
//			return base;
		}
		
//		System.out.println("ceil=" + ceil + ",\tstart = "+ startIndex + 
//				",\tend=" + endIndex + ",\tcountByCeil returns " + nonStrict);
		return nonStrict;
//		return (strict && isStrobogrammatic(ceil, startIndex, endIndex) ? nonStrict-1 : nonStrict);
	}
	
	public static boolean isStrobogrammatic(String num, int startIndex, int endIndex) {
		for (int i=startIndex, j=endIndex; i <= j; i++, j--)
	        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
	            return false;
	    return true;
	}
	
	
//	private int countByCeil(int[][] count, int[][] mapping, String ceil, int startIndex, int endIndex, boolean strict) {
//		if (startIndex < endIndex) { return 0; }
//		if (ceil.charAt(0) == '0') { // ceil string starts with 0
//			return countByCeil(count, mapping, ceil, startIndex + 1, endIndex, strict);
//		} 
//		int startDigit = (int)(ceil.charAt(startIndex)), len = endIndex - startIndex + 1;
//		int base = mapping[startDigit][1] * count[len-1][1], expectEnd = mapping[startDigit][0];
//		if (expectEnd < 0) { // ceil starts with 2, 3, 4, 5, 7
//			return base;
//		} else { // ceil string starts with 1, 6, 8, 9
//			int endDigit = (int)(ceil.charAt(startIndex)) - (strict ? 1 : 0); // 按照可以等于来考虑
//			if (expectEnd >= endDigit) {
//				return base + count[len-2][1];
//			} else {
//				return base;
//			}
//		}
//		return -1;
//	}

}
