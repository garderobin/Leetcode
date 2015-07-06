package algorithm;
import java.lang.StringBuilder;
public class IntReverseBit {
	// you need treat n as an unsigned value
    public int reverseBits(int n) {

        //String s1 = Integer.toUnsignedString(n,2);
    	String s1 = Integer.toString(n,2);
        String s2 = new StringBuilder(s1).reverse().toString();
        //int res =  Integer.parseUnsignedInt(s2, 2);
        int res =  Integer.parseInt(s2, 2);
        return res;
    }
}