package interview.snapchat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanCoding {
	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("abbabc");
		strings.add("");
		strings.add("1234");
		strings.add("1243");
		strings.add("Í≈Ωç√∫åß∂f");
		for (String string : strings) {
			System.out.println(string + " is encoded as " + encode(string));
		}
	}


	static class Node{
		Character label;
		char code;
	    int freq;
	    Node left;
	    Node right;
	    public Node(char cha, int freq) {
	    	this.label = cha;
	    	this.freq = freq;
	    }
	    public Node(Node left, Node right) {
	    	this.code = '-';
	    	this.freq = left.freq + right.freq;
	    	left.code = '0';
	    	right.code = '1';
	    	this.left = left;
	    	this.right = right;
	    }
	}
	
	/*
	 * 用HashMap能够处理Unicode字符，用array[256]只能处理ASCII.
	 * 原来的算法太过于粗暴，纯粹就是每个数都是1开头后面跟若干个零，弃用。
	 */
	public static String encode(String input) {
	    if (input == null) { return ""; }
		Map<Character, Integer> freqMap = new HashMap<>();
		Map<Character, String> encodeDict = new HashMap<>();
		// Count frequency for each character, O(N)
		for (int pos = 0; pos < input.length(); pos++) {
			char cur = input.charAt(pos);
			freqMap.put(cur, freqMap.containsKey(cur) ? freqMap.get(cur) + 1 : 1);
		}		
		
		// Build Huffman-tree
		Node huffman = freqMapToHuffmanTreeByTwoQueues(freqMap);
//		Node huffman = freqMapToHuffmanTreeByPriorityQueue(freqMap);
		
		// Get the encoding dictionary from the Huffman-tree, O(logN)
		encodeDict = huffmanTreeToDictionary(huffman, encodeDict, new StringBuilder());
	
		// Encode the initial string using our Huffman-coding strategy, O(N^2)
	    StringBuilder res = new StringBuilder(); 
	    for (int pos = 0; pos < input.length(); ++pos) {
	    	res.append(encodeDict.get(input.charAt(pos)));
	    }
	
	    return res.toString();
	
	}
	
	/**
	 * 使用优先队列（PriorityQueue）创建霍夫曼树。
	 * ⒈把n个终端节点加入优先队列，则n个节点都有一个优先权Pi，1 ≤ i ≤ n
	 * ⒉如果队列内的节点数>1，则：
	 *	⑴从队列中移除两个最大的Pi节点，即连续做两次remove（max(Pi）, Priority_Queue)
     *	⑵产生一个新节点，此节点为（1）之移除节点之父节点，而此节点的权重值为（1）两节点之权重和
	 *	⑶把（2）产生之节点加入优先队列中
	 * ⒊最后在优先队列里的点为树的根节点（root）
	 */
	@SuppressWarnings("unused")
	private static Node freqMapToHuffmanTreeByPriorityQueue(Map<Character, Integer> freqMap) {
		PriorityQueue<Node> q = new PriorityQueue<>((Node a, Node b) -> a.freq - b.freq);
		// Sort characters by frequency, O(NlogN) time and space
		for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) { //避免遍历keyset()再在里面用键取值，那样效率最低。
		    q.offer(new Node(entry.getKey(), entry.getValue()));
		}  
		// Build the Huffman-tree by merging the smallest two nodes at each time until only one node left.
		while (q.size() > 1) { // O(NlogN) time
			Node n0 = q.poll(), n1 = q.poll(), merge = new Node(n0, n1);
			q.offer(merge);
		}
		
		return q.poll();
	}
	
	/**
	 * 使用两个队列（Queue）创建霍夫曼树。第一个队列用来存储n个符号（即n个终端节点）的权重，第二个队列用来存储两两权重的合（即非终端节点）。此法可保证第二个队列的前端（Front）权重永远都是最小值，且方法如下：
	 * ⒈把n个终端节点加入第一个队列（依照权重大小排列，最小在前端）（这一步是O(NlogN), 下面的步骤是线性的。）
	 * ⒉如果队列内的节点数>1，则：
	 *	⑴从队列前端移除两个最低权重的节点
	 *	⑵将（1）中移除的两个节点权重相加合成一个新节点
	 *	⑶加入第二个队列
	 * ⒊最后在第一个队列的节点为根节点
	 */
	private static Node freqMapToHuffmanTreeByTwoQueues(Map<Character, Integer> freqMap) {
		Queue<Node> q1 = new PriorityQueue<Node>((Node a, Node b) -> a.freq - b.freq); // single nodes queue
		Deque<Node> q2 = new ArrayDeque<Node>();	// merged nodes deque
		// Sort characters by frequency, O(NlogN)
		for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) { //避免遍历keyset()再在里面用键取值，那样效率最低。
		    q1.offer(new Node(entry.getKey(), entry.getValue()));
		}
		// Build the Huffman-tree by comparing the front of two queues, O(N) time.
		while (q1.size() + q2.size() > 1) {
			Node[] children = new Node[2];
			for (int i = 0; i < 2; ++i) {
				if (q1.isEmpty()) children[i] = q2.poll();
				else if (q2.isEmpty()) children[i] = q1.poll();
				else children[i] = (q1.peek().freq < q2.peekFirst().freq) ? q1.poll() : q2.pollFirst(); // merged node first.
			}		
			q2.offerLast(new Node(children[0], children[1]));
		}
		
		return q2.poll();
	}
	
	/* O(logN) time */
	private static Map<Character, String> huffmanTreeToDictionary(Node root, Map<Character, String> encodeDict, StringBuilder sb) {
		if (root == null) return encodeDict; // 这一行实际上是不可能的
		if (root.code != '-') sb.append(root.code);
		if (root.label != null) {
			encodeDict.put(root.label, sb.toString());
			return encodeDict;
		} else {
			encodeDict = huffmanTreeToDictionary(root.left, encodeDict, new StringBuilder(sb));
			encodeDict = huffmanTreeToDictionary(root.right, encodeDict, new StringBuilder(sb));
			return encodeDict;
		}
	}

//	/*
//	 * 用HashMap能够处理Unicode字符，用array[256]只能处理ASCII.
//	 */
//	public static String encode(String input) {
//	    if (input == null) { return ""; }
//		HashMap<Character, Integer> freqMap = new HashMap<>();
//		HashMap<Character, String> encodeDict = new HashMap<>();
//		for (int pos = 0; pos < input.length(); pos++) {
//			char cur = input.charAt(pos);
//			freqMap.put(cur, freqMap.containsKey(cur) ? freqMap.get(cur) + 1 : 1);
//		}
//		
//		List<Node> list = new ArrayList<>();
//		for (Character key : freqMap.keySet()) {
//			list.add(new Node(key, freqMap.get(key)));
//		}
//		
//		Collections.sort(list, (Node a, Node b) -> b.freq - a.freq);
//		
//		StringBuilder code = new StringBuilder("1");
//		for (Node node: list) {
//			encodeDict.put(node.cha, code.toString());
//			code.insert(0, "0");
//		}
//	
//		//O(n^2)
//	    StringBuilder res = new StringBuilder();
//	    for (int pos = 0; pos < input.length(); pos++) {
//	    	res.append(encodeDict.get(input.charAt(pos)));
//	    }
//	
//	    return res.toString();
//	
//	}
}
