package interview.snapchat;

public class AddBinary {
	public static void main(String args[]) {
		String s1 = "11";
		String s2 = "1";
		System.out.println(addBinaryV1(s1, s2));
		System.out.println("100");
	}
	
	public static String addBinaryV1(String a, String b) { // leetcode 4ms
        if (a == null || a.isEmpty()) return b;
        if (b == null || b.isEmpty()) return a;
        
        int n = a.length(), m = b.length();
        char[] s = new char[(n > m) ? n+1 : m+1]; 
        for (int c = 0, i = n-1, j = m-1, k = s.length-1; i >= 0 || j >= 0 || c == 1; c /= 2) {
            c += i >= 0 ? a.charAt(i--) - '0' : 0;
            c += j >= 0 ? b.charAt(j--) - '0' : 0;
            s[k--] = (char)((c&1) + '0'); //为操作符优先级低于加减，使用的时候必须加上括号。
        }
        return (new String(s)).trim(); //第一位可能没有赋值, 默认值是\x，所以必须trim()
    }
	
	
	public static String addBinaryV0(String a, String b) {
		if (a == null && b == null) {
			return "";
		} 
		if (a == null || a.length() == 0) {
			return b;
		}
		if (b == null || b.length() == 0) {
			return a;
		}
		char[] q1, q2; // q1 is shorter or equals length with q2
        int i, j, v1, v2, v, carry;
       
        if (a.length() < b.length()) { //default: qa is longer or equal length to qb
        	q1 = a.toCharArray();
        	q2 = b.toCharArray();
        } else {
        	q1 = b.toCharArray();
        	q2 = a.toCharArray();
        }
        i = q1.length - 1;
        j = q2.length - 1;        
        char[] list =  new char[q2.length];  //if need another 1, add it at last.
        carry = 0;
        
        while (i >= 0) {       
        	v1 = (int)q1[i] - 48;
        	v2 = (int)q2[j] - 48;
        	v = v1 + v2 + carry;
        	list[j] = (char)((v % 2) + 48);
        	carry = v / 2;
        	i--;
        	j--;
        }
        
        while (j >= 0) {
        	if (carry > 0) {
        		v = (int)q2[j] - 48 + carry;
            	list[j] = (char)((v % 2) + 48);
            	carry = v / 2;
        	} else {
        		list[j] = q2[j];
        	}       	
        	j--;
        }       
        
        return (carry > 0) ? "1" + String.valueOf(list) : String.valueOf(list);
    }
	
	
}
