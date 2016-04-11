package interview.others;

public class IntegerComplement {
	public static void main(String[] args) {
		System.out.println(0 << 1);
		System.out.println(getIntegerComplement(50));
		System.out.println(getIntegerComplement(100));
	}
	
	public static int getIntegerComplement(int n) {
	    int rst = 0, bar = 1;
	    for (; n > 0; bar <<= 1, n >>= 1) {
	        rst += (((n & 1) ^ 1) == 0) ? 0 : bar;
	    }
	    return rst;
	}
}
