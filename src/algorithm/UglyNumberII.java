package algorithm;

public class UglyNumberII {
	
	public int nthUglyNumberDiscussion(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		int index2 = 0, index3 = 0, index5 = 0;
		int factor2 = 2, factor3 = 3, factor5 = 5;
		for (int i = 1; i < n; i++) {
			int min = Math.min(Math.min(factor2, factor3), factor5);
			ugly[i] = min;
			if (min == factor2) {
				factor2 = 2 * ugly[++index2];
			}
			if (min == factor3) {
				factor3 = 3 * ugly[++index3];
			}
			if (min == factor5) {
				factor5 = 5 * ugly[++index5];
			}
		}
		return ugly[n - 1];
	}
	
	public static int nthUglyNumberDiscussionTest(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		int index2 = 0, index3 = 0, index5 = 0;
		int factor2 = 2, factor3 = 3, factor5 = 5;
		for (int i = 1; i < n; i++) {
			
			int min = Math.min(Math.min(factor2, factor3), factor5);
			ugly[i] = min;
			System.out.println("f2 = " + factor2 +
					"\tf3 = " + factor3 + "\tf5 = " + factor5 + 
					"\tugly[" + i + "] = " + min + 
					"\ti2 = " + index2 + "\ti3 = " + index3 + "\ti5 = " + index5);
			if (min == factor2) {
				factor2 = 2 * ugly[++index2];
			}
			if (min == factor3) {
				factor3 = 3 * ugly[++index3];
			}
			if (min == factor5) {
				factor5 = 5 * ugly[++index5];
			}
		}
		return ugly[n - 1];
	}
	
	
	public int nthUglyNumber(int n) {
        if (n < 1) {
        	return 0;
        }
//        int[] ugly = new int[n]; // n个 ugly number.
//        ugly[0] = 1;
//        int count = 1, curIndex = 0;
//        while (count < n) {
//        	ugly[count] = ugly[curIndex] * 2;
//        	ugly[count + 1] = ugly[curIndex] * 3;
//        	ugly[count]
//        }
//        return ugly[n-1];
        int i = 1, count = 0;
        for (; count < n;i++) {
        	if (isUgly(i)) {
        		count++;
        	}
        }
        return i;
    }
	
	public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        if (num < 7) {
            return true;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        return Math.abs(num) == 1;
    }
	
	public static void main(String[] args) {
		System.out.println(nthUglyNumberDiscussionTest(20));
		
	}
}
