package algorithm;

public class DivideTwoIntegers {
	
	/**
	 * 至今没明白原理。
	 * bit运算学得太差了。
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide(int dividend, int divisor) {
        if (divisor == 0) { return Integer.MAX_VALUE; }        
        if (dividend == 0) { return 0; }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) { return Integer.MAX_VALUE; }
            else if(divisor == 1)  { return dividend; }
            else { return ((divisor&1)==1)? divide(dividend+1,divisor) : divide(dividend>>1,divisor>>1); }
        } 
        if (divisor==Integer.MIN_VALUE) { return 0; }
        
        int sign = ((dividend>0 && divisor<0) || (dividend<0 && divisor>0)) ? -1 : 1 ;
        long dvd = Math.abs(dividend);
        long dvs = Math.abs(divisor);
        int res = 0;
        while (dvd >= dvs) { 
            long temp = dvs, multiple = 1;
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            res += multiple;
        }
        return sign == 1 ? res : -res; 
    }
}
		