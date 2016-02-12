package GoogleOA;

public class SuperUglyNumber {
	public static int nthSuperUglyNumber(int n, int[] primes) {
		int[] rst = new int[n], pos = new int[primes.length];
        rst[0] = 1;
	
	    for (int i = 1; i < n; i++) {
	    	rst[i] = Integer.MAX_VALUE;
	        for (int j = 0; j < primes.length; j++) {
	            rst[i] = Math.min(rst[i], primes[j] * rst[pos[j]]);
	        }
	
	        for (int j = 0; j < pos.length; j++) { 
	            if (rst[i] == primes[j] * rst[pos[j]]){ // 用过的不再用了，这个index加就是为了下一次乘法的时候往后面选择对象
	                pos[j]++; //这里不能break, 因为这种情况可能出现不止一次，每一次都不能再用了。
	            }
	        }
	    }

       return rst[n - 1];
    }
	
	public static int nthSuperUglyNumberForTest(int n, int[] primes) {
		int[] ret = new int[n];
        ret[0] = 1;

        int[] indexes  = new int[primes.length];
	
	    for (int i = 1; i < n; i++) {
//	    	System.out.println("\ni = " + i + ": ");
//	    	for (int k: indexes) {
//	    		System.out.print(k + ", ");
//	    	}
//	    	System.out.println();
	        ret[i] = Integer.MAX_VALUE;
	
	        for (int j = 0; j < primes.length; j++) {
//	        	System.out.print("indexes[" + j + "]=" + indexes[j]);
//	        	System.out.print(" ,\tret[" + i + "]=" + ret[i]);
//	        	System.out.print(" ,\tprimes[" + j + "]=" + primes[j]);
//	        	System.out.print(" ,\tret[" + indexes[j] + "]=" + ret[indexes[j]]);
//	        	System.out.println(",\t multi = " + primes[j] * ret[indexes[j]]);
	            ret[i] = Math.min(ret[i], primes[j] * ret[indexes[j]]);
	        }
	
	        for (int j = 0; j < indexes.length; j++) { 
//	        	System.out.print("ret[" + i + "]=" + ret[i]);
//	        	System.out.println(",\t multi = " + primes[j] * ret[indexes[j]]);
	            if (ret[i] == primes[j] * ret[indexes[j]]){ // 用过的不再用了，这个index加就是为了下一次乘法的时候往后面选择对象
//	            	System.out.println("plus indexes[" + j + "]=" + indexes[j]);
	                indexes[j]++;
	            }
	        }
	    }

       return ret[n - 1];
    }
	
	public static void main(String[] args) {
		int[] primes = {2, 7, 13, 19};
		nthSuperUglyNumber(12, primes);
//		PriorityQueue<Integer> queue = new PriorityQueue<>(10);
//        queue.offer(1);
//        System.out.println(queue.size());
	}
}
