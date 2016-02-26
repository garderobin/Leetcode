package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {
	
	/**
	 * 答案的区别和我唯一的不同在于，在数组迭代中用jump的方式，相当于长度可伸缩的窗口
	 * 每次不是i++而是从i开始构造一个window,构造好了跳过这一整个window.
	 * 这是bottom-up，而我是被backtrack鬼迷了心窍的top-down，代码量自然要大得多。
	 */
	public List<String> fullJustifyDiscussion(String[] words, int L) {
        List<String> list = new LinkedList<String>();

        for (int i = 0, w; i < words.length; i = w) {
            int len = -1;
            for (w = i; w < words.length && len + words[w].length() + 1 <= L; w++) {
                len += words[w].length() + 1;
            }

            StringBuilder strBuilder = new StringBuilder(words[i]);
            int space = 1, extra = 0;
            if (w != i + 1 && w != words.length) { // not 1 char, not last line
                space = (L - len) / (w - i - 1) + 1;
                extra = (L - len) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) strBuilder.append(' ');
                if (extra-- > 0) strBuilder.append(' ');
                strBuilder.append(words[j]);
            }
            int strLen = L - strBuilder.length();
            while (strLen-- > 0) strBuilder.append(' ');
            list.add(strBuilder.toString());
        }

        return list;
    }
	
	public static List<String> fullJustify(String[] words, int maxWidth) {
		List<String> rst = new ArrayList<String>(), line = new ArrayList<String>();
		if (words == null || words.length == 0) { return rst; }
		int lineLen = 0, wordCount = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i] == null) { continue; } // ignore empty word
			int wordLen = words[i].length(), curLen = wordLen + lineLen + wordCount;
			if (curLen > maxWidth) { // not enough rest space for the new word
				rst.add(paddingOneLine(line, lineLen, maxWidth, false));
				line = new ArrayList<String>();
				line.add(words[i]);
				lineLen = wordLen;
				wordCount = 1;
			} else if (curLen > maxWidth - 2) { // only enough for this new word and the line becomes full
				line.add(words[i]);
				rst.add(paddingOneLine(line, lineLen + wordLen, maxWidth, i == words.length-1));
				line = new ArrayList<String>();
				lineLen = 0;
				wordCount = 0;
			} else { // more than one space left after adding this new word to the current line
				line.add(words[i]);
				lineLen += wordLen;
				++wordCount;
			}
			
		}
		
		if (wordCount > 0) { rst.add(paddingOneLine(line, lineLen, maxWidth, true)); }
		
		return rst;
    }
	
	
	private static String paddingOneLine(List<String> lineWords, int lineLen, int maxWidth, boolean leftAligned) {
		Iterator<String> iter = lineWords.iterator();
		StringBuilder sb = new StringBuilder(iter.next());
		int n = lineWords.size() - 1;
		if (n == 0) { // single world in one line 
			generateConsecutiveSpacesAndWord(sb, maxWidth - lineLen, "");
		} else if (leftAligned) { // left-alignment
			while (iter.hasNext()) { generateConsecutiveSpacesAndWord(sb, 1, iter.next()); }
			generateConsecutiveSpacesAndWord(sb, maxWidth - sb.length(), "");
		} else { // center-aligned
			int totalSpaces = maxWidth - lineLen, plus = totalSpaces % n, right = totalSpaces / n, left = right + 1;
			for (int i = 0; i < plus; i++) { generateConsecutiveSpacesAndWord(sb, left, iter.next()); }
			for (int i = 1; i < n; i++) { generateConsecutiveSpacesAndWord(sb, right, iter.next()); }
		}
		
		return sb.toString();
	}
	
	private static void generateConsecutiveSpacesAndWord(StringBuilder sb, int len, String word) {
		for (int i = 0; i < len; i++) { sb.append(' '); }
		sb.append(word);
	}
	
	

	public static void main(String[] args) {
//		String words[] = {"What","must","be","shall","be."};
//		int maxWidth = 12;
		String words[] = {"Listen","to","many,","speak","to","a","few."};
		int maxWidth = 6;
		System.out.println(fullJustify(words, maxWidth));
	}
	
}
