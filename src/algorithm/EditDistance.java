package algorithm;

/**
 * case1: word1 == word2 return 0
 * case2: left + (insert) + right = word2
 *        left - (delete) + right = word2
 * case3: 
 * @author jasmineliu
 *
 */
public class EditDistance {
	
	public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] cur = new int[m+1]; // cur[j] records the minimum distance for the word1.substring(0, j) to transform to word2
        for (int i = 0; i < cur.length; i++) { cur[i] = i+1; } // the initial assume every character needs to be modified.
        for (int j = 1; j <= n; j++) {
            int pre = cur[0];
            cur[0] = j;
            for (int i = 1; i <= m; i++) {
            	int temp = cur[i];
            	if (word1.charAt(i - 1) == word2.charAt(j - 1)) { cur[i] = pre; }
                else cur[i] = Math.min(pre + 1, Math.min(cur[i] + 1, cur[i - 1] + 1));
                pre = temp;
            }
        	
        }
        return cur[m]; 
    }
}
