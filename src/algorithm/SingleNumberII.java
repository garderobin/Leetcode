package algorithm;

public class SingleNumberII {
	
	/**
	 * 总体的方向是通过异或用第二次出现对第一次出现实现抵消，
	 * 再通过异或取反用第三次对第二次出现实现抵消
	 * two temp的想法。
	 * 然而这题的细节我还不理解，自己无法写出来，还要重看！
	 * @param A
	 * @return
	 */
	public static int singleNumber(int[] A) {
	    int ones = 0, twos = 0;
	    for(int i = 0; i < A.length; i++){
	        
//	    	String s0 = "\nA[" + i + "] = " + Integer.toBinaryString(A[i]),
//	    		s1 = "\nones = " + Integer.toBinaryString(ones),
//	    		s2 = "\ntwos = " + Integer.toBinaryString(twos),
//	    		s3 = "\nones ^ A[" + i + "] = " + Integer.toBinaryString(ones ^  A[i]),
//	    		s4 = "\n~twos = " + Integer.toBinaryString(~twos),
//	    		s5 = "\n(ones ^ A[" + i + "]) & ~twos = " + Integer.toBinaryString((ones ^ A[i]) & ~twos),	    		
//	    		s6 = "\ntwos ^ A[" + i + "] = " + Integer.toBinaryString(twos ^ A[i]),
//	    		s7 = "\n~ones = " + Integer.toBinaryString(~ones),
//	    		s8 = "\n(twos ^ A[" + i + "]) & ~ones = " + Integer.toBinaryString((twos ^ A[i]) & ~ones);
//	    	System.out.println(s0 + s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8);
	    	ones = (ones ^ A[i]) & ~twos;
	        twos = (twos ^ A[i]) & ~ones;
	    	
	    }
	    return ones;
	}
	
	public static void main(String[] args) {
		int[] A = {3, 1, 2, 2, 1, 2, 1, 3};
		System.out.println(singleNumber(A));
	}
}
