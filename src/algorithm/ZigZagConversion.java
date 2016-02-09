package algorithm;

public class ZigZagConversion {
	class ZigZagData {
        int pos;
        int index;        
        
        /**
         * returns true if this has same or higher priority than z
         */ 
        public boolean compareTo(ZigZagData z) {
            return this.index >= z.index;
        }
        
    }
	public static String convertDiscussion(String s, int numRows) {
		char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
           for (int idx = 0; idx < numRows && i < len; idx++) // vertically down
               sb[idx].append(c[i++]);
           for (int idx = numRows-2; idx >= 1 && i < len; idx--) // obliquely up
        	   sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
	}
	
	public static String convert(String s, int numRows) {
		if (s == null) {
			return null;
		}
		if (numRows < 2 || numRows >= s.length()) {
			return s;
		}
		
        StringBuilder sb = new StringBuilder();
        int a = s.length(), n = numRows, i = 0, j = 0, k = 0;
        int m = 2 * (a / (2*n - 2) + 1); // number of columns
        char[][] ch = new char[n][m];
        boolean up = false;
        
        for (j = 0; j < m && k < a; up = !up, j++) {
        	if (up) {        		
        		for (i = n - 2; i > 0 && k < a; i--, k++ ) {
        			ch[i][j] = s.charAt(k);
        		}        		
        	} else {
        		for (i = 0; i < n && k < a; i++, k++) {
        			ch[i][j] = s.charAt(k);
        		}        		
        	}
        	
        }
        
        for (i = 0; i < n; i++) {
        	for (j = 0; j < m; j++) {
        		if (ch[i][j] != '\0') {
        			sb.append(ch[i][j]);
        		}
        		        		
        	}
        }
        return sb.toString().trim();
    }
	
	public static void testCharArrayDefaultValue() {
		char[] c = new char[10];
		for (char e: c) {
			System.out.print(e);
		}
	}
	
	public static void main(String[] args) {
		String s = "0123456789";
		int numRows = 5;
		System.out.println(convert(s, numRows));
	}
	
}
