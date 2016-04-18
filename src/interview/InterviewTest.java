package interview;

import java.util.Arrays;
import java.util.List;

import interview.snapchat.BigInteger;

public class InterviewTest {
	
	public static void main(String[] args) {
		testBloomFilter();
	}
	
	public static void testBloomFilter() {
//		String s = "127.0.0.1";
//		Integer a = 1;
		String a = "ß∂åƒœ∑®†©åß∂";
		String b = a.toString();
		System.out.println(b);
	}
	
	public static void testBigInteger() {
		List<String[]> test = Arrays.asList(
				new String[]{"-5000", "-1234"},
				new String[]{"-1234", "-5000"},
				new String[]{"01234", "-5000"},
				new String[]{"05000", "-1234"},
				new String[]{"-5000", "+1234"},
				new String[]{"1234", "5000"}
				); 
				
		for (String[] ss: test) {
			BigInteger b1 = new BigInteger(ss[0]), b2 = new BigInteger(ss[1]);
//			byte[] d1 = b1.getDigits(), d2 = b2.getDigits();
			
//			for (int i = 0; i < d1.length; ++i) System.out.print(d1[i]);
//			System.out.println("\n" + b1.toString());
//			for (int i = 0; i < d2.length; ++i) System.out.print(d2[i]);
//			System.out.println("\n" + b2.toString());
			BigInteger b3 = b1.add(b2), b4 = b1.substract(b2);
			System.out.println(b3.toString() + ",\t" + b4.toString());
//			System.out.println(b1.compareAbs(b2));
//			System.out.println(b1.compareTo(b2));
		}
	}
	
	
	
}
