package algorithm;

public class ShortestWordDistance {
	public int shortestDistance(String[] words, String word1, String word2) {
        int distance = words.length, p1 = distance, p2 = -1 - distance;
        for (int i = 0; i < words.length; i++) {
        	if (words[i].equals(word1)) { p1 = i; }
        	else if (words[i].equals(word2)) { p2 = i; }
        	distance = Math.min(distance, Math.abs(p1-p2));
        }
        return distance;
    }
}
