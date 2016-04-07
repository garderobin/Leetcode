package interview.snapchat;
/*
 * http://iwillgetthatjobatgoogle.tumblr.com/post/32583376161/writing-biginteger-better-than-jdk-one
 */
public class BigInteger implements Comparable<BigInteger> {
	byte[] digits; //每个byte存一个十进制的digit, 逆序从右到左存储
	
	public static final BigInteger ZERO = new BigInteger(new byte[]{0});
    public static final BigInteger ONE = new BigInteger(new byte[]{1});
	
	
	public BigInteger(byte[] digits) {
        this.digits = digits;
    }
//	public BigInteger(byte[] digits, boolean isNegative) {
//        this.digits = digits;
////        this.isNegative = isNegative;
//    }
	
	public BigInteger(String s) {
		s = s.trim();
		int offset = (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0, n = s.length(), sign = (s.charAt(0) == '-') ? -1 : 1;
		this.digits = new byte[n - offset];
		for (int i = offset, j = n - offset - 1; i < n; ++i) {
			digits[j--] = (byte)((s.charAt(i) - '0') * sign);
		}
	}
	
	public boolean isNegative() {
		return digits != null && digits.length > 0 && digits[digits.length - 1] < 0;
	}
	
	@Override
	public String toString() {
		if (digits == null || digits.length == 0) return "";
		boolean isNegative = digits[digits.length - 1] < 0, trailingZero = true;
		StringBuilder sb = new StringBuilder();
		if (isNegative) sb.append('-');
		for (int i = digits.length - 1; i >= 0; --i) {
			trailingZero &= (digits[i] == 0);
			if (!trailingZero) sb.append(isNegative ? (-digits[i]) : digits[i]);
		}
		return sb.toString();
	}
	
	public BigInteger add(BigInteger other) {
        byte[] digits1, digits2;
        if (this.digits.length >= other.digits.length) {
            digits1 = this.digits;
            digits2 = other.digits;
        } else {
            digits1 = other.digits;
            digits2 = this.digits;
        }
        int carry = 0, l1 = digits1.length, l2 = digits2.length;
        byte[] resultDigits = new byte[l1+1];
        for (int i = 0; i < l1; ++i) {
            int r = digits1[i] +  (i >= l2 ? 0 : digits2[i]) + carry;
            switch (carry/10) {
            case 0:
            case 1:
            case -1:
            }
            carry = r > 9 ? 1 : 0;
            resultDigits[i] = (byte) (r % 10);
        }
        resultDigits[l1] = (byte) carry;
        return new BigInteger(resultDigits);
    }
	
	
	public BigInteger substract(BigInteger other) {
//        boolean isNegative = this.compareTo(other) < 0;
        byte[] d1 = this.digits, d2 = other.digits;
        boolean isNeg = this.compareTo(other) < 0;
        byte c = 0;
        int n = d1.length, m = d2.length;
        byte[] rst = new byte[(n>m) ? n+1 : m+1];
        for (int i = 0, j = 0, k = 0; i < n || j < m || c == -1; ++i, ++j) {
            byte r = (byte) (10 + d1[i] - d2[j] - c);
            c = (byte) (r < 10 ? 1 : 0);
            rst[k++] = (byte)( Math.abs(r%10) * (isNeg ? (-1):1)); //为操作符优先级低于加减，使用的时候必须加上括号。
        }
//        for (int i = 0; i < l1; ++i) {
//            int r = digits1[i] +  (i >= l2 ? 0 : digits2[i]) + carry;
//            carry = r > 9 ? 1 : 0;
//            resultDigits[i] = (byte) (r % 10);
//        }
//        resultDigits[l1] = (byte) carry;
        return new BigInteger(rst);
//        byte[] digits1, digits2;
//        if (!isNegative) {
//            digits1 = this.digits;
//            digits2 = other.digits;
//        } else {
//            digits1 = other.digits;
//            digits2 = this.digits;
//        }
//        int carry = 0, l1 = digits1.length, l2 = digits2.length;
//        byte[] resultDigits = new byte[l1+1];
//        for (int i = 0; i < l1; i++) {
//            int digit1 = digits1[i];
//            int digit2 = i >= l2 ? 0 : digits2[i];
//            int r = 10 + digit1 - digit2 - carry;
//            carry = r > 9 ? 1 : 0;
////            resultDigits[i] = (byte) (r % 10);
//            resultDigits[i] = (byte)(r % 10);
//        }
//        resultDigits[l1] = (byte) carry;
////        return new BigInteger(resultDigits, isNegative);
//        return new BigInteger(resultDigits);
    }
	
	@Override
    public boolean equals(Object other) {
        if (!(other instanceof BigInteger)) return false;
        byte[] digits1 = this.digits;
        byte[] digits2 = ((BigInteger) other).getDigits();
        if (digits1.length != digits2.length) return false;
        for (int i = 0; i < digits1.length; i++) {
            if (digits1[i] != digits2[i]) return false;
        }
        return true;
    }
	
	@Override
    public int compareTo(BigInteger other) {
		if (isNegative()  && !other.isNegative()) return -1;
		if (!isNegative()  && other.isNegative()) return 1;
		
        byte[] digits1 = this.digits;
        byte[] digits2 = other.getDigits();
        int len1 = digits1.length;
        int len2 = digits2.length;
        for (int i = len1 - 1; i >= 0; --i) {
            if (digits1[i] != 0) break;
            --len1;
        }
        for (int i = len2 - 1; i >= 0; --i) {
            if (digits2[i] != 0) break;
            --len2;
        }
        if (len1 != len2) return isNegative() ? len2 - len1 : len1 - len2;
        else {
            for (int i = len1 - 1; i >= 0; --i) {
                if (digits1[i] != digits2[i]) 
                	return digits1[i] - digits2[i];
            }
        }
        return 0;
    }

	public byte[] getDigits() {
		return digits;
	}

	public void setDigits(byte[] digits) {
		this.digits = digits;
	}

	public static BigInteger getZero() {
		return ZERO;
	}

	public static BigInteger getOne() {
		return ONE;
	}

	
}
