package GoogleOA;

import java.util.HashMap;
import java.util.HashSet;

public class UniqueWordAbbreviation {
//	HashSet<String> abbrSet;
//	HashSet<String> wordSet;
//	
//	public UniqueWordAbbreviation(String[] dictionary) {
//        abbrSet = new HashSet<String>(dictionary.length);
//        for (String word: dictionary) {
//        	abbrSet.add(toAbbr(word));
//        	wordSet.add(word);
//        }
//    }
//	
	
//
//    public boolean isUnique(String word) {
//        return !wordSet.add(word) || abbrSet.add(toAbbr(word));
//    }
	HashSet<String> wordSet;
	HashMap<String, Integer> abbrMap;
	public UniqueWordAbbreviation(String[] dictionary) {		
		abbrMap = new HashMap<String, Integer>();
		wordSet = new HashSet<String>();
		if (dictionary == null) {}
        for (String word: dictionary) {
        	String abbr = toAbbr(word);  
//        	if (wordSet.add(word)) {
//        		abbrMap.put(abbr, (abbrMap.containsKey(abbr)) ? abbrMap.get(abbr) + 1 : 1);
//        	} else {
//        		abbrMap.put(abbr,  abbrMap.get(abbr));
//        	}
        	abbrMap.put(abbr, ( wordSet.add(word) ? ((abbrMap.containsKey(abbr)) ? abbrMap.get(abbr) + 1 : 1) : abbrMap.get(abbr)) );
        	}
        System.out.println(wordSet.contains("hello"));
    }
	
	private String toAbbr(String word) {
		if (word == null) { return ""; }
		int len = word.length(), midLen = len - 2;
    	return (len < 2) ? word : "" + word.charAt(0) + midLen + word.charAt(len - 1);
	}

    public boolean isUnique(String word) {
    	String abbr = toAbbr(word);
    	if (wordSet.add(word)) {
    		wordSet.remove(word);
    		return !abbrMap.containsKey(abbr);
    	} 
    	
    	return abbrMap.get(abbr) == 1;
    }
    
    public static void main(String[] args) {
    	String[] dic = {"hello", "hello"};
    	UniqueWordAbbreviation ua = new UniqueWordAbbreviation(dic);
    	System.out.println(ua.isUnique("hello"));
    }
}
