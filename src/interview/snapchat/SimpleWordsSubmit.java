package interview.snapchat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class SimpleWordsSubmit {
	/*
     * Find all simple words from an array of words
     * Idea: traverse words from short to long, offer each simple word to a dictionary after a BFS compound check.
     * O(N^3) time, O(NlogN) space.
     */
    static String[] simpleWords(String[] words) {
    	if (words.length == 1) { return words; }
		Map<String, Integer> posMap = new HashMap<>(); // back up word positions before sorting
		for (int i = 0; i < words.length; ++i) posMap.put(words[i], i); 
		
		// Sort words by length, O(NlogN) time, O(NlogN) space
		Arrays.sort(words, (String o1, String o2) -> o1.length()-o2.length());
		
		// Check compounds, O(N^3) time, O(N) space
		Set<String> dict = new HashSet<>();
        List<String> simpleList = new ArrayList<>();
    	for (int i = 0; i < words.length; ++i) {
    		String word = words[i];
    		if (!isCompound(word, dict)) { 
    			simpleList.add(word); 
    			dict.add(word); // our dictionary only stores simple words
    		} 
        }
    	
    	// Sort simple words by initial occurrence order, O(NlogN) time, O(NlogN) space
    	simpleList.sort((String o1, String o2) -> posMap.get(o1) - posMap.get(o2));
    	
    	return simpleList.toArray(new String[simpleList.size()]);
	}
    

    /*
     * Check whether a word is compound in a dictionary.
     * Idea: use BFS, a dynamic growing queue to limit space growth;
     * use a set to record checked index to avoid repeated work to reduce the running time to O(N^2).
     * O(N^2) time, O(N) space.
     */
    static boolean isCompound(String s, Set<String> dict) {
	    Queue<Integer> queue = new ArrayDeque<Integer>();
	    queue.offer(0);
	    Set<Integer> visited = new HashSet<Integer>();
	    visited.add(0);
	    while (!queue.isEmpty()) {
	        int curIdx = queue.poll();
	        for (int i = curIdx+1; i <= s.length(); ++i) {
	            if (visited.contains(i)) continue;
	            if (dict.contains(s.substring(curIdx, i))) {
	                if (i == s.length()) return true;
	                queue.offer(i);
	                visited.add(i);
	            }
	        }
	    }
	    return false;
	}
    
    
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;
        
        int _words_size = 0;
        _words_size = Integer.parseInt(in.nextLine().trim());
        String[] _words = new String[_words_size];
        String _words_item;
        for(int _words_i = 0; _words_i < _words_size; _words_i++) {
            try {
                _words_item = in.nextLine();
            } catch (Exception e) {
                _words_item = null;
            }
            _words[_words_i] = _words_item;
        }
        
        res = simpleWords(_words);
        for(int res_i=0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }
        
        bw.close();
        in.close();
    }
}
