package dataStructure;

public class TrieNode {
//	protected int SIZE = 26;
//	protected int num;//有多少单词通过这个节点,即节点字符出现的次数
//	protected TrieNode[]  son;//所有的儿子节点
//	protected boolean isEnd;//是不是最后一个节点
//	protected char val;//节点的值
	protected int SIZE = 26;
	public int num;//有多少单词通过这个节点,即节点字符出现的次数
	public TrieNode[]  son;//所有的儿子节点
	public boolean isEnd;//是不是最后一个节点
	public char val;//节点的值
	 
	public TrieNode(){
		num = 1;
		son = new TrieNode[SIZE];
		isEnd = false;
	}
	
	public TrieNode(char v) {
		num = 1;
		son = new TrieNode[SIZE];
		isEnd = false;
		val = v;
	}


	public int getSIZE() {
		return SIZE;
	}

	public void setSIZE(int sIZE) {
		SIZE = sIZE;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public TrieNode[] getSon() {
		return son;
	}

	public void setSon(TrieNode[] son) {
		this.son = son;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public char getVal() {
		return val;
	}

	public void setVal(char val) {
		this.val = val;
	}
	
	
}
