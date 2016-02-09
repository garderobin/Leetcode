package algorithm;

public class Candy {
	/**
	 * 一个类似greedy的题目。我的思路：
	 * 
	 * @param ratings
	 * @return
	 */
	public static int candy(int[] ratings) {
		/* x: the number of candies of the recent greatest rating child;*/
	    /* peek: the largest x in the current down sequence. */
		int sum = 1, x = 1, peek = 1;
		boolean up = false;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i-1] > ratings[i]) {
				if (!up) { //一直在下降
					sum += (x == peek - 1) ? (x = ++peek) : ++x;
				} else { //刚开始下降
					peek = x;
					sum += (x = 1);
				}
				up = false;
			} else if (ratings[i-1] < ratings[i]) {
				sum += (up) ? ++x : (x = 2);
				up = true;
			} else { //ratings[i-1] == ratings[i]
				peek = 1;
				sum += (x = 1);
				up = false;
			}
			
		}
		return sum;
	}
	
	public int candyDiscussion(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int total = 1, prev = 1, countDown = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] >= ratings[i-1]) {
                if (countDown > 0) {
                    total += countDown*(countDown+1)/2; // arithmetic progression
                    if (countDown >= prev) total += countDown - prev + 1;
                    countDown = 0;
                    prev = 1;
                }
                prev = ratings[i] == ratings[i-1] ? 1 : prev+1;
                total += prev;
            } else countDown++;
        }
        if (countDown > 0) { // if we were descending at the end
            total += countDown*(countDown+1)/2;
            if (countDown >= prev) total += countDown - prev + 1;
        }
        return total;
    }
	
	public static int candyV2(int[] ratings) {
		int sum = 1, x = 1, peek = 1; // x: the number of candies of the recent greatest rating child;
		boolean up = false;
		// peek: in a down sequence, the start (largest) child get current peek candies.
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i-1] > ratings[i]) {
				if (!up) { //一直在下降
					sum += (x == peek - 1) ? (x = ++peek) : ++x;
				} else { //刚开始下降
					peek = x;
					sum += (x = 1);
				}
				up = false;
			} else if (ratings[i-1] < ratings[i]) {
				sum += (up) ? ++x : (x = 2);
				up = true;
			} else { //ratings[i-1] == ratings[i]
				peek = 1;
				sum += (x = 1);
				up = false;
			}
			
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] ratings = {58,21,72,77,48,9,38,71,68,77,82,47,25,94,89,54,26,54,54,99,64,71,76,63,81,82,60,64,29,51,87,87,72,12,16,20,21,54,43,41,83,77,41,61,72,82,15,50,36,69,49,53,92,77,16,73,12,28,37,41,79,25,80,3,37,48,23,10,55,19,51,38,96,92,99,68,75,14,18,63,35,19,68,28,49,36,53,61,64,91,2,43,68,34,46,57,82,22,67,89};
		System.out.println(candyV2(ratings));
//		System.out.println(candyDiscussion(ratings));
	}
	
//	public int candy(int[] ratings) {
//        int sum = 0, i, j, x = 1; // x: the number of candies of the recent child
//        int[] candies = new int[ratings.length];
//        candies[0] = 1;
//        for (i = 1; i < ratings.length; i++) {
//        	if (ratings[i] < ratings[i - 1]) {
//        		if (x == 1) {
//        			for (j = i - 1; j >= 0 && ratings[j] > ratings[j + 1] && candies[j] == candies[j + 1]; j--) { //可能有相等错误问题
////            			sum++;
//        				++candies[j];
//            		}
//        		}
//        		x = 1;
//        	} else if (ratings[i] > ratings[i - 1]) {
//        		candies[i] = candies[i - 1] + 1;      		
//        	} else {
//        		candies[i] = 1;
//        	}       	
//        }
//        
//        for (i = 0; i < candies.length; i++) {
//        	sum += candies[i];
//        }
//        
//        return sum;        
//    }
	
}
