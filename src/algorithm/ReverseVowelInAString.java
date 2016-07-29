package algorithm;


public class ReverseVowelInAString {

	public String reverseVowels(String s) {
        if (s == null) return "";
        if (s.length() < 2) return s;
        
        char[] chs = s.toCharArray();
        for (int i = 0, j = chs.length - 1; i < j; ) {
        	while (i < j && !isVowel(chs[i])) ++i;
        	while (j > i && !isVowel(chs[j])) --j;
        	if (i == j) break;
        	
        	chs[i] ^= chs[j];
        	chs[j] ^= chs[i];
        	chs[i++] ^= chs[j--];
        	
        }
        
        return new String(chs);
    }
	
	private boolean isVowel(char c) {
		switch (c) {
		case 'a': 
		case 'e': 
		case 'i': 
		case 'o': 
		case 'u': 
		case 'A': 
		case 'E': 
		case 'I': 
		case 'O': 
		case 'U': return true;
		default: return false;
		}
	}
}
