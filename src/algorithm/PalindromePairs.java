package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePairs {
	String[] defaultWords = {"abcd","dcba","lls","s","sssll"};
	
	class TrieNode {
	    TrieNode[] next;
	    int index;
	    List<Integer> list;

	    TrieNode() {
	        next = new TrieNode[26];
	        index = -1;
	        list = new ArrayList<>();
	    }
	}

	public List<List<Integer>> palindromePairs(String[] words) {
	    List<List<Integer>> res = new ArrayList<>();

	    TrieNode root = new TrieNode();

	    for (int i = 0; i < words.length; i++) {
	        addWord(root, words[i], i);
	    }

	    for (int i = 0; i < words.length; i++) {
	        search(words, i, root, res);
	    }

	    return res;
	}

	private void addWord(TrieNode root, String word, int index) {
	    for (int i = word.length() - 1; i >= 0; i--) {
	        if (root.next[word.charAt(i) - 'a'] == null) {
	            root.next[word.charAt(i) - 'a'] = new TrieNode();
	        }

	        if (isPalindrome(word, 0, i)) {
//	        	if (root.index == -1) {
//	    	    	System.out.println("addWord_" + i + ": \troot " + word + " adds " + index + ": \t" + defaultWords[index]);
//	    	    } else {
//	    	    	System.out.println("addWord_" + i + ": \troot " + defaultWords[root.index] + " adds " + index + ": \t" + defaultWords[index]);
//	    		    
//	    	    }
	        	root.list.add(index);
//	            System.out.println(root.list);
	        }

	        root = root.next[word.charAt(i) - 'a'];
	    }
	    
	    root.list.add(index);
	    
	    root.index = index;
	    
//	    if (root.index == -1) {
//	    	System.out.println("addWord_f: \troot " + word + " adds " + index + ": \t" + defaultWords[index]);
//	    } else {
//	    	System.out.println("addWord_f: \troot " + defaultWords[root.index] + " adds " + index + ": \t" + defaultWords[index]);
//	    }
	    System.out.println(root.list);
	}

	private void search(String[] words, int i, TrieNode root, List<List<Integer>> list) {
	    for (int j = 0; j < words[i].length(); j++) {   
	        if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
	        	System.out.println("search_" + i + "." + j +": " + words[i] + " -> " + words[root.index]);
	            list.add(Arrays.asList(i, root.index));
	        }

	        root = root.next[words[i].charAt(j) - 'a'];
	        if (root == null) return;
	    }

	    for (int j : root.list) {
	        if (i == j) continue;
	        System.out.println("search_" + j + ": " + words[i] + " -> " + words[j]);
            
	        list.add(Arrays.asList(i, j));
	    }
	}

	private boolean isPalindrome(String word, int i, int j) {
	    while (i < j) {
	        if (word.charAt(i++) != word.charAt(j--)) return false;
	    }

	    return true;
	}
	
	public static void main(String[] args) {
		PalindromePairs pp = new PalindromePairs();
		String[] words = {"abcd","dcba","lls","s","sssll"};
		List<List<Integer>> rst = pp.palindromePairs(words);
		System.out.println(rst);
	}

}
