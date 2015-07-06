package algorithm;

import java.util.ArrayList;

public class PermutationSequance {
	public static String getPermutation(int n, int k) {		
		if (k < 0) {
			return "";
		}
        char[] rst = new char[n];
        int m, p, q, x, y;        
        ArrayList<Integer> full = new ArrayList<Integer>(n);
        
        for (x = 0; x < n; x++) {
        	full.add(x+1);
        }
        p = 1;
        for (q = 2; q < n && p <= k; q++) {
        	p *= q;          	
        }        
        if (k < p) {
        	p /= (q-1);
        	q -= 2;
        } else if (k > p * q) {
    		return "";
    	} else {        	
        	q--;
        } // p = q! <= k < (q+1)!  
        m = n - q - 1;
        for (y = 0; y < m; y++) {  // Fix the start part.  
        	full.remove(0);
        	rst[y] = (char)(y + 49);
        } // now, y = n-q-1
        
        while(y < n - 1) {
        	m = (int)Math.ceil((double)k / (double)p) - 1;
        	if (m < 0) {
        		m = 0;
        	}
        	x = full.get(m);
        	rst[y] = (char)(x + 48);
        	full.remove(m);
        	k = k % p;
        	p /= q; 
        	q--;
        	y++;
        } 

        rst[y] = (char)(full.get(0) + 48);
        
        return String.valueOf(rst);
    }
	
	public static void main(String args[]) {
//		int[] list = {1,2,3,4,5,6,7,8,9};
//		ArrayList<Integer> test = new ArrayList<Integer>();
//		test.add(1);
//		test.add(2);
//		test.add(3);
//		test.add(4);
//		test.add(5);
//		test.add(6);
//		test.add(7);
//		test.add(8);
//		test.add(9);
//		//test.remove(5);
//		for( int i = 0; i < 3; i++) {
//			test.remove(0);
//		}
//		
//		for( int i = 0; i < test.size(); i++) {
//			System.out.print(test.get(i)+"\t");
//		}
//		
		System.out.println(getPermutation(3, 5));
		
	}
}
