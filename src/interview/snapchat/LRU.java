package interview.snapchat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRU<K extends Comparable<K>, V> {
	Map<K, DLinkedNode<K, V>> cache;
	int size;
	int capacity;
	DLinkedNode<K, V> head, tail;
	
	public LRU(int capacity) {
		this.capacity = capacity;
		this.cache = new ConcurrentHashMap<>(capacity);
		this.size = 0;
		this.head = new DLinkedNode<K, V>();
		this.head.prev = null; // the dummy head;
		this.tail = new DLinkedNode<K, V>();
		this.tail.next = null; // the dummy tail;
	}
	
	public V get(K key) {
		 if (!cache.containsKey(key)) return null;
		 else {
			 DLinkedNode<K, V> node = cache.get(key);
			 popTail();
			 moveToHead(node);
			 return node.value;
		 }
	}
	

	public void set(K key, V value) {
		DLinkedNode<K, V> node = cache.get(key);
		if (node == null) {
			popTail();
			node = new DLinkedNode<K, V>(key, value, head.next, head);
		} else {
			node.value = value;
			popTail();
			moveToHead(node);
		}
			
	}
	
	// Check if the capacity is full, pop the tail. Else do nothing.
	private DLinkedNode<K, V> popTail() {
		if (cache.size() >= capacity) {
			DLinkedNode<K, V> node = tail.prev;
//			if (node == null) {}
			cache.remove(node.key);
			removeNode(node);
			return node;
		} else {
			return tail.prev;
		}
	} 
	 

	private void removeNode(DLinkedNode<K, V> node) {
		if (node == null) return;
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
	}

	private void moveToHead(DLinkedNode<K, V> node) {
		if (node == null) return;
		removeNode(node);
		if (head.next != null) {
			node.next = head.next.next;
		}
		head.next = node;
		node.prev = head;
	}
}

class DLinkedNode<K extends Comparable<K>, V> {
	K key;
	V value;
	DLinkedNode<K, V> prev, next;
	
	public DLinkedNode() {} // for dummy nodes uses.
	
	public DLinkedNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public DLinkedNode(K key, V value, DLinkedNode<K, V> prev, DLinkedNode<K, V> next) {
		this.key = key;
		this.value = value;
		this.prev = prev;
		this.next = next;
	}
}
