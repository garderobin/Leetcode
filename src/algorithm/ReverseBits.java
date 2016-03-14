package algorithm;

public class ReverseBits {
	
	/**
	 * 注意int取值上下限之间的转换。
	 * 构造unsigned二进制数从左到右的方法：加上当前位，左移补零，循环。
	 * 取unsigned二进制数最右边一位的办法： n&1
	 * @param n
	 * @return
	 */
	public int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result += n & 1;
			n >>>= 1;
			if (i < 31) { // Do not shift the last digit, for this step is to prepare for the next digit to add.
				result <<= 1;
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
//		int[] bits = new int[32];
//		int n = 31;
//		int m = n>>>1;
//		int l = n<<1;
//		String s0 = Integer.toBinaryString(n);
//		String s1 = Integer.toBinaryString(m);
//		String s2 = Integer.toBinaryString(l);
		
//		System.out.println(s0);
//		System.out.println(s1);
//		System.out.println(s2);
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE>>>1));
	}
}
