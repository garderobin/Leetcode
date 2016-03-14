package algorithm;

import java.util.ArrayList;

public class CountPrimes {
	public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        ArrayList<Integer> primes = new ArrayList<Integer>();
        boolean[] composite = new boolean[n + 1];
        primes.add(2);
        composite[0] = false;
        composite[1] = false;
        composite[2] = false;
       
        int limit = (int)Math.sqrt(n);
        int count = primes.size();
        int p, i, j, k, m = 0;
        outer:
        for (i = 3; i < n; i++) {
        	if (composite[i]) {
        		continue outer;
        	}
        	count = primes.size();
        	
        	inner:
        	for (j = 0; j < count; j++) {
            	p = primes.get(j);        	
            	if (p > limit) {            		
            		break inner;
            	}
            	if (i % p == 0) {
            		continue outer;
            	}              	
            }
        	m = n/i;
     	    for (k = i; k <= m; k++) {
     	    	composite[k*i] = true;
     	    }
        	primes.add(i);
        }
        
        return primes.size();
    }
	
	public static int countPrimesV2(int n) {
        if (n < 3) {
            return 0;
        }
        ArrayList<Integer> primes = new ArrayList<Integer>();
        boolean[] composite = new boolean[n + 1];
        primes.add(2);
        composite[0] = false;
        composite[1] = false;
        composite[2] = false;
       
        int limit = (int)Math.sqrt(n);
        int p, i, j, k, m = 0;
        outer:        	
        for (j = 0; j < primes.size(); j++) {
        	p = primes.get(j);
        	for (i = j + 1; i < limit; i++) {
                if (composite[i]) { continue outer; }            	        	
            	if (i % p == 0) { continue outer; }              	
            }
        	m = n/i;
     	    for (k = i; k <= m; k++) {
     	    	composite[k*i] = true;
     	    }
        	primes.add(i);
        }
        
        return primes.size();
    }
	
	public static void main(String args[]) {
		System.out.println(countPrimes(30));
	}
}
