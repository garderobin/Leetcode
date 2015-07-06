package algorithm;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.

 * The digits are stored such that the most significant digit is at the head of the list.
 * @author jasmineliu
 *
 */
public class PlusOne {
	public static int[] plusOne(int[] digits) {
		int[] rst = new int[digits.length + 1];
		int i = digits.length;
		int v;
		for (i = digits.length - 1; i >= 0; i--) {
			v = digits[i] + 1;			
			if (v < 10) {
				digits[i] = v;
				return digits;
			}
			digits[i] = 0;
			rst[i+1] = 0;			
		}
		rst[0] = 1;
		return rst;
        
    }
	
	public static void main(String args[]) {
		int[] test = {9};
		int[] rst = plusOne(test);
		for (int i = 0; i < rst.length; i++) {
			System.out.print(rst[i] + "\t");
		}
	}
}
