package algorithm;

public class ReverseWordsInAStringII {
	/**
     * The idea is reverse the total string and then reverse each word back to its inital order.
     */ 
    public void reverseWords(char[] s) {
        // Reverse the whole string
    
    }
    
    private void reverseString(char[] s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            s[i] ^= s[j];
            s[j] ^= s[i];
            s[i] ^= s[j];
        }
    }
}
