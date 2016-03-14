package algorithm;

public class AddBinary {
	public static String addBinary(String a, String b) {
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
	
	public static void main(String args[]) {
		String s1 = "11";
		String s2 = "1";
//		char[] c = {'1', '1', '0'};
//		int[] d = {1, 1, 0};
		System.out.println(addBinary(s1, s2));
		//System.out.println((char)((3 % 2) + 48));
	}
}
