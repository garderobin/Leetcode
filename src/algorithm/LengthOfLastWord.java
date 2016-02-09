package algorithm;

public class LengthOfLastWord {
	public int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0 || (s = s.trim()).length() == 0) {
			return 0;
		}
		String[] splits = s.split(" ");
		String word = "";
        for (int i = splits.length - 1; i > -1 && (word = splits[i].trim()).length() == 0; i--) {}
        return word.length();
    }	
}
