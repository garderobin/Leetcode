package algorithm;

public class BitwiseANDOfNumbersRange {
	
	/**
	 * 思想相同：找到m和n第一个不相同的位数，从这一位起一直到最右边全部置0，左边相同的部分保留
	 * 但是乘法委实绝妙
	 * @param m
	 * @param n
	 * @return
	 */
	public int rangeBitwiseAndDiscussion(int m, int n) {
        if(m == 0){
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }
	
	/**
	 * 位运算的优先级比加减法要小，所有位运算必须加括号！
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static int rangeBitwiseAnd(int m, int n) {
        if (m == n || m == 0) { return m; }
        int p = m, q = n;
        for (; p > 1; p >>= 1, q >>= 1) {}
        if (q > 1) { return 0; } //如果m和二进制n位数不同，最终结果一定是0
        
        // 找到m和n第一个不相同的位数，从这一位起一直到最右边全部置0，左边相同的部分保留
        for (p = m ^ n, q = 1; p > 1; p >>= 1, q++) {}
        
        return (m>>q)<<q;
    }
	
	public static void main(String[] args) {
//		System.out.println(rangeBitwiseAnd(6, 7));
//		int bits = 1, i = 0;
//		int a = 2 & 3, b = (1<<(bits+1)-1);
//		System.out.println(((1<<(bits-i)) - 1) << (i+1));
//		System.out.println(((1<<(bits+1))-1));
		System.out.println(20 ^ 22);
		
		
//		System.out.println(1<<1);
//		System.out.println((1<<1)-1);
//		System.out.println(((1<<1)-1) << 1);
	}
}
