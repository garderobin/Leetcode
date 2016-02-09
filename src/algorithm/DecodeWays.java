package algorithm;

public class DecodeWays {
	public static int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] c = s.toCharArray();
        int[] f = new int[c.length + 1]; //dummy for 0
        boolean b1 = (c[0] == '0');
        boolean b2;
        f[0] = 0;
        f[1] = (b1) ? 0 : 1;
        for (int i = 2; i <= c.length; i++) {
        	b1 = (c[i-1] == '0');        	
        	if (b1) {
        		f[i] = (c[i-2] - '0' < 3 && c[i-2] - '0' >= 0) ? f[i-2] + 1 : f[i-1];
        	} else {
        		b2 = (c[i-2] - '0' == 1) || (c[i-2] == '2' && c[i-1] - '0' < 7);
            	f[i] = f[i-1] + ((b2) ? f[i-2] : 0);
        	}
        	
        }
        return f[c.length];
    }
}
