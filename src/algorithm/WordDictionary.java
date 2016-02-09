package algorithm;

//Your WordDictionary object will be instantiated and called as such:
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("word");
//wordDictionary.search("pattern");
public class WordDictionary {
	
	StringBuilder sb;
	
	public WordDictionary() {
		sb = new StringBuilder();
	}

	
	// Adds a word into the data structure.
    public void addWord(String word) {
        sb.append(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
    	if (word == null || word.length() == 0) {
    		return true;
    	}
    	if (sb == null || sb.length() == 0) {
    		return false;
    	}
        int l1 = sb.length();
        int l2 = word.length();
        int p1 = 0;
        int p2 = 0;
        char c1 = sb.charAt(p1);
        char c2 = word.charAt(p2);
        outer:
        while (p1 < l1 - l2 + 1 && p2 < l2) {
        	//p1 = l1 - l2; p2 = 0;
        	//p1 = l1 - 1; p2 = l2 - 1
        	while (!charEquals(c1, c2) && p1 < l1 - l2) { 
            	c1 = sb.charAt(++p1);
            }            
            if (!charEquals(c1, c2) && p1 == l1 - l2) {
            	return false;
            }
            
            while (p1 < l1 && p2 < l2) {
            	c1 = sb.charAt(p1);
            	c2 = sb.charAt(p2);
            	if (!charEquals(c1, c2)) {
            		p2 = 0;
            		c2 = word.charAt(p2);
            		continue outer;
            	}
            	p1++;
            	p2++;
            }
            return p2 == l2;
        }
        
        
        return false;
    }
    
    public boolean charEquals(char a, char b) {
    	return a == b || a == '.' || b == '.';
    }
}
