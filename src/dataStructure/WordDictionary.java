package dataStructure;

public class WordDictionary {
	private TrieNode root;
	
	public WordDictionary() {
		root = new TrieNode();
	}
	
	// Adds a word into the data structure.
    public void addWord(String word) {
    	if (word == null || word.length() == 0) {
			return;
		}
		TrieNode node = root;
		char[] letters = word.toCharArray();
		for(int i = 0,len = word.length(); i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] == null) {
				node.son[pos] = new TrieNode(letters[i]);
			} else {
				node.son[pos].num++;
			}
			node = node.son[pos]; //这一句是关键
		}
		node.isEnd = true;
    }
    
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
    	if(word == null || word.length() == 0){
			return false;
		}
		char[] letters = word.toCharArray();
		return has(letters, root, 0);
    }
     
    private boolean has(char[] letters, TrieNode node, int wi) {
    	int pos;    	
    	for (int i = wi; i < letters.length; i++) {
    		if (letters[i] != '.') {
    			pos = letters[i] - 'a';
				if (node.son[pos] != null) {
					node = node.son[pos];
				} else {
					return false;
				}
    		} else {
    			inner: 
    			for (pos = 0; pos < node.SIZE; pos++) {
    				if (node.son[pos] != null) {
    					if (has(letters, node.son[pos], wi + 1)) {
    						return true;
    					} 
    					continue inner;
    				}
    			}
    		}
    	}
    	return node.isEnd;
    }
	
}
