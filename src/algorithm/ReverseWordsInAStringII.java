package algorithm;

public class ReverseWordsInAStringII {
	/**
     * The idea is reverse the total string and then reverse each word back to its inital order.
     */ 
    public void reverseWords(char[] s) {
        // reverse the whole char array
        reverseString(s, 0, s.length-1);
        int i = 0;
        // reverse back each word
        for (int k = 0; k < s.length; k++) {
            if (s[k] == ' ') { 
                reverseString(s, i, k-1); 
                i = k+1;
            } 
        }
        // reverse the last word
        reverseString(s, i, s.length-1);
    }
    
    private void reverseString(char[] s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            s[i] ^= s[j];
            s[j] ^= s[i];
            s[i] ^= s[j];
        }
    }
}
