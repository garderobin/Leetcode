package interview.snapchat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;


//XML parser, build a map to represent XML given tokenizer.Next() -> (open,name), (inner,content), (close,name), 就是一个 tree transversal
//题目： construct string from web page + XML parser，再次感谢面筋，不过有一点不同是需要设计data structure保存parser后的结果。
//每个token有如下结构，然后给了个API getNextToken()获取下一个token, 我表示应该还有个hasNextToken()。
//token {
//string name; // e.g. story, id, snaps
//string tag_type; // {open, close, test} 三种type
//};
// Ask: valid input guranteed?
// Ask: <div>里面的内容文字该怎么处理？我现在是把文字也当成是一种xml node
// Ask: 是否允许注释？
//int nestedOpenStart = Arrays.binarySearch(chars, '<'); //这个方法居然不行！为什么！
public class XMLParser2 {

	public static void main(String[] args) {
//		WebPageTokenizer tokenizer = new WebPageTokenizer();
////		Set<String> voidTags = XMLNode.voidTagGenerator();
//		while (tokenizer.hasNextToken()) {
//			Token token = tokenizer.getNextToken();
//			System.out.println(token.name + ",\t" + token.tag_type);
//		}
		XMLNode root = xmlParser();
		System.out.println(root);
	}
	
	//iteration
	public static XMLNode xmlParser() {
		WebPageTokenizer tokenizer = new WebPageTokenizer();
		Set<String> voidTags = XMLNode.voidTagGenerator();
		Stack<XMLNode> stack = new Stack<>();
		
		if (!tokenizer.hasNextToken()) return null;
		
		XMLNode root = new XMLNode(tokenizer.getNextToken().name, false), parent = root, cur = null;
		stack.push(root);
		while (tokenizer.hasNextToken()) {
			Token token = tokenizer.getNextToken();
//			System.out.println(token.name + ",\t" + token.tag_type);
			if (token.name.startsWith("<!--")) continue;
			cur = new XMLNode(token.name, false);
			
			switch (token.tag_type) {
				case Close: 
					stack.pop(); // empty exception 这里我忽略了
					parent = stack.peek(); // empty exception 这里我忽略了
					break;
				case Text: parent.content.add(cur); break;
				case Open: 
					parent.content.add(cur);
					if (!cur.isSelfClosed(voidTags)) {
						cur.needClose = true;
						stack.push(cur);
						parent = cur;
					}
					break;
				default:;
			}
			
		}
		
		return root;
	}
	

}


class WebPageTokenizer { // construct strings from web page
	Iterator<Token> iter;
	
	public WebPageTokenizer() {
		Deque<Token> tokens = new ArrayDeque<>();
		try {
			Process pr = Runtime.getRuntime().exec("/usr/bin/python /Users/jasmineliu/Code/Leetcode/LeetcodePython/HtmlCrawler.py");
			pr.waitFor();
			
			BufferedReader br = new BufferedReader(new FileReader("/Users/jasmineliu/Google Drive/GoogleMac/Hiring/Snapchat/snapchat.html"));
			boolean prevUnfinish = false; // last line's tag needs to be closed at this line or later.
			
			for (String line = null; (line = br.readLine()) != null; ) {
				lineHandler(line, tokens, prevUnfinish);
			}
			
			iter = tokens.iterator();
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Token getNextToken() {
		return  hasNextToken() ? iter.next() : null;
	}
	
	public boolean hasNextToken() {
		return iter != null && iter.hasNext();
	}
	
	private void singleTokenRecognizer(String s, int startIdx, char[] chars, Deque<Token> tokens) {
		int j = startIdx + 1;
		for (; j < chars.length && Character.isWhitespace(chars[j]); ++j);
		boolean isComment = j+2 < chars.length && chars[j] == '!' && chars[j+1] == '-' && chars[j+2] == '-';
		boolean isClose = j < chars.length && chars[j] == '/';
		if (!isComment) tokens.offerLast(new Token(s + ">", isClose ? TagType.Close : TagType.Open));
	}
	
	private void lineHandler(String line, Deque<Token> tokens, boolean prevLineTagUnfinish) {
		if ((line = line.trim()).isEmpty()) return;
		
		String[] splitByClose = line.split(">");
		
		for (int i = 0; i < splitByClose.length; ++i) {
			String s = splitByClose[i].trim();
			
			if (s.isEmpty()) continue;
			
			char[] chars = s.toCharArray();
			
			if (chars[0] == '<') { // TODO 这里我没有考虑一种特殊情况，上一行以'<'开头但是一直没有出现'>',这对于一个合法的html页面是不可能的。
				singleTokenRecognizer(s, 0, chars, tokens);
			} else {
				int nestedOpenStart = s.indexOf('<');
				String tokenName = (nestedOpenStart < 0) ? s : s.substring(0, nestedOpenStart);
				TagType tokenType = TagType.Text;
				if (i == 0 && prevLineTagUnfinish) { 
					Token lastToken = tokens.pollLast();
					tokenName = lastToken.name + tokenName;
					tokenType = lastToken.tag_type;
				}
				tokens.offerLast(new Token(tokenName, tokenType));
				if (nestedOpenStart >= 0) {
					singleTokenRecognizer(s, nestedOpenStart, chars, tokens);
				}
			}
			
		}
		
		prevLineTagUnfinish = line.charAt(line.length() - 1) != '>';
	}
	
}


enum TagType {
	Open, Close, Text
}


class Token {
	String name;	// e.g. story, id, snaps
	TagType tag_type;// {open, close, text}
	
	public Token(String name, TagType tag_type){
		this.name = name; 
		this.tag_type = tag_type;
	}
	
}

class XMLNode {
	String name;
	List<XMLNode> content;
	boolean needClose;
	
	public XMLNode(String name, boolean needClose) {
		this.name = name;
		content = new ArrayList<XMLNode>();
		this.needClose = needClose;
	}
	
	
	@Override
	public String toString() {
		return toString("");
	}
	
	private String toString(String prefix) {
		StringBuilder sb = new StringBuilder(prefix);
		sb.append(name);
		String childPre = prefix + "\t";
		for (XMLNode child: content) {
			sb.append("\n\t");
			sb.append(child.toString(childPre));
		}
		if (needClose) {
			sb.append("\n" + childPre + "</" + getTag() + ">");
		}
		return sb.toString();
	}
	

	public static Set<String> voidTagGenerator() {
		final String[] VOID_ELEMENTS = {"br", "hr", "img", "input", "link", "meta", "area", "base", "col", "command", "embed", "keygen"};
		Set<String> voidTags = new HashSet<>();
		for (String s: VOID_ELEMENTS) {
			voidTags.add(s);
		}
		return voidTags;
	}
	
	private String getTag() { 
		int n = name.length(), i = 0, j = 0;
		name = name.trim();
		if (name.charAt(0) != '<') return null;  // plain text
		
		for (; i < n && name.charAt(i) != '<'; ++i);
		for (j = ++i; j < n && (Character.isAlphabetic(name.charAt(j)) || Character.isDigit(name.charAt(j))); ++j);
		return name.substring(i, j);
	}
	
	public boolean isSelfClosed(Set<String> voidTags) {
		return (name.trim().endsWith("/>") || voidTags.contains(getTag()));
	}
}