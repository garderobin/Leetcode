package algorithm;

/**
 * Key idea, every time we move to right, we only need to consider 
 * whether using this new character as tail could produce new palindrome string of length (current length +1) or (current length +2)
 * @author jasmineliu
 *
 */
public class LongestPalindromeSubstring {
	
	public String longestPalindrome(String s) {
        String res = "";
        int currLength = 0;
        for(int i = 0; i < s.length(); i++){
            if(isPalindrome(s, i - currLength - 1,i)){
                res = s.substring(i - currLength - 1, i + 1);
                currLength = currLength + 2;
            }
            else if(isPalindrome(s, i - currLength,i)){
                res = s.substring(i - currLength, i + 1);
                currLength = currLength + 1;
            }
        }
        return res;
    }

    public boolean isPalindrome(String s, int begin, int end){
        if(begin<0) return false;
        while(begin<end){
            if(s.charAt(begin++)!=s.charAt(end--)) return false;
        }
        return true;
    }
}
