package interview.snapchat;
/*
 * http://iwillgetthatjobatgoogle.tumblr.com/post/32583376161/writing-biginteger-better-than-jdk-one
 */
public class BigInteger implements Comparable<BigInteger> {
	boolean negative;
	byte[] digits; //每个byte存一个十进制的digit, 正序从左到右存储, 不存符号位
	
	public static final BigInteger ZERO = new BigInteger(new byte[]{0});
    public static final BigInteger ONE = new BigInteger(new byte[]{1});
	
	public BigInteger(byte[] digits) {
        this.digits = digits;
        negative = false;
    }
	
	public BigInteger(byte[] digits, boolean negative) {
        this.digits = digits;
        this.negative = negative;
    }
	

	public BigInteger(String s) {
		s = s.trim();
		int offset = (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0, n = s.length();
		digits = new byte[n - offset +  1];
		negative = s.charAt(0) == '-'; 
		for (int i = offset, j = 1; i < n; ++i) {
			digits[j++] = (byte)((s.charAt(i) - '0'));
		}
	}
	
	
	@Override
	public String toString() {
		if (digits == null || digits.length == 0) return "";
		boolean trailingZero = true;
		StringBuilder sb = new StringBuilder();
		
		if (negative) sb.append('-');
		
		for (int i = 0; i < digits.length; ++i) {
			trailingZero &= (digits[i] == 0);
			if (!trailingZero) sb.append(digits[i]);
		}
		if (sb.length() == 0 || (sb.length() == 1 && sb.charAt(0) == '-')) sb.append('0');
		return sb.toString();
	}
	
	
	public BigInteger add(BigInteger other) { 
		boolean neg1 = this.isNegative(), neg2 = other.isNegative();
		if (neg1 ^ neg2) return neg1 ? other.substract(this, true) : this.substract(other, true);
		else return new BigInteger(this.add(other.getDigits()), neg1);
    }

	
	public BigInteger substract(BigInteger other) {
		return substract(other, false);
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
		else return compareAbs(other) * (isNegative() ? (-1) : 1);
    }
	
	
	public int compareAbs(BigInteger other) {
		byte[] d1 = this.digits, d2 = other.getDigits();
        int i = 0, j = 0;
        while (i < d1.length && d1[i++] == 0);
        while (j < d2.length && d2[j++] == 0);
        int len1 = d1.length - i + 1, len2 = d2.length - j + 1;
        if (len1 != len2) return len1 - len2;
        else {
        	for (i -= 1, j -= 1; i < d1.length && d1[i++] == d2[j++];);
        	return (i == d1.length) ? 0 : d1[i-1] - d2[j-1];
        }
	}
	
	byte[] add(byte[] otherDigits) {
		int l1 = this.digits.length, l2 = otherDigits.length;
		byte[] d1 = (l1 > l2) ? this.digits : otherDigits, d2 = (l1 > l2) ? otherDigits: this.digits;

		int carry = 0;
		byte[] rst = new byte[d1.length + 1];
		for (int i = d1.length, j = d2.length; i > 0; --i, --j) {
			int r = d1[i-1] +  (j < 1 ? 0 : d2[j-1]) + carry;
			carry = r > 9 ? 1 : 0;
			rst[i] = (byte) (r % 10);
		}
		rst[0] = (byte) carry;
		return rst;
	}
	
	
	BigInteger substract(BigInteger other, boolean reverse) {
		if (negative ^ other.isNegative() ^ reverse) {
			return new BigInteger(this.add(other.getDigits()), negative);
		} else {
			int compareAbs = compareAbs(other);
			if (compareAbs == 0) return new BigInteger(new byte[]{0});
			boolean rstNeg = (!negative && compareAbs < 0) || (negative && compareAbs > 0);
			byte[] rstBytes = (compareAbs < 0) ? other.substract(digits) : substract(other.digits);
			return new BigInteger(rstBytes, rstNeg);
		}
    }
	
	
	byte[] substract(byte[] d2) { //被减数一定是正数且一定大于减数
		byte[] d1 = this.digits;
	    byte[] rst = new byte[d1.length];
	    int i = d1.length - 1;
	    for (int c = 0, j = d2.length - 1; j >= 0 || c == 1; --i, --j, c = c < 10 ? 1 : 0) {
	        c = 10 + d1[i] - (j < 0 ? 0 : d2[j]) - c;
	        rst[i] = (byte) (c % 10);
	    }
	    for (; i >= 0; --i) rst[i] = d1[i];
	    return rst;
	}

	public byte[] getDigits() {
		return digits;
	}

	public void setDigits(byte[] digits) {
		this.digits = digits;
	}
	
	public boolean isNegative() {
		return negative;
	}
	
	public void setNegative(boolean negative) {
		this.negative = negative;
	}
	
}
