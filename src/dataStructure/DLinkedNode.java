package dataStructure;

public class DLinkedNode {
	int key; 
	int value;
	DLinkedNode prev;
	DLinkedNode post;
	
	public DLinkedNode() {}
	
	public DLinkedNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}
