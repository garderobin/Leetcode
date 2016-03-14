package interview.amazon;
/**
 * There are n persons and k different type of dishes. Each person has some preference for each dish. Either he likes it or not. We need to feed all people. Every person should get atleast one dish of his chioce. What is the minimum number of different type of dishes we can order? 

Input is n x k matrix boolean matrix.For each person a row represent his likes or not likes each row. 

n = 6 k = 7 
1 0 0 0 1 0 0 
1 0 0 0 0 1 0 
1 0 0 0 0 0 1 
0 1 0 0 1 0 0 
0 0 1 0 0 1 0 
0 0 0 1 0 0 1 

Output 
3 

Explanation 
Take dish number 5,6,7.


 * @author jasmineliu
 *
 */
public class AmazonServingDishes {
	public static int minDishes(boolean[][] pref) {
		if (pref == null || pref.length == 0) {
			return 0;
		}
		int n = pref.length;
		int k = pref[0].length;
		
		
		return k;
	}
}
