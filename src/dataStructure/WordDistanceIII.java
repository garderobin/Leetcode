package dataStructure;

public class WordDistanceIII {
	public int shortestWordDistance(String[] words, String word1, String word2) {
		int distance = words.length, p1 = -1 - distance, p2 = distance;
		if (word1.equals(word2)) {
        	for (int i = 0; i < words.length; i++) {
        		if (words[i].equals(word1)) { continue; }
        		p1 = i;
        		distance = Math.min(distance, i - p1);
        	}
        } else {
            for (int i = 0; i < words.length; i++) {
            	if (words[i].equals(word1)) { p1 = i; }
            	else if (words[i].equals(word2)) { p2 = i; }
            	distance = Math.min(distance, Math.abs(p1-p2));
            }
        }
		return distance;
    }
}
