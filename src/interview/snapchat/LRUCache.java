package interview.snapchat;

import java.util.Hashtable;

import dataStructure.DLinkedNode;

/*
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 */
public class LRUCache {
	/**
	 * 我想这里需要处理多线程，并发性，因此用Hashtable而不是HashMap
	 */
	private Hashtable<Integer, DLinkedNode> cache = new Hashtable<Integer, DLinkedNode>();
	private int count; //这个有些算法里面说用不着，但是目前来看还需要
	private int capacity;
	private DLinkedNode head, tail;

	public LRUCache(int capacity) {
		this.count = 0;
	    this.capacity = capacity;

	    head = new DLinkedNode();
	    head.prev = null; //这一步非常重要

	    tail = new DLinkedNode();
	    tail.post = null;

	    head.post = tail;
	    tail.prev = head;
    }
    
	/*
	 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	 */
    public int get(int key) {
    	DLinkedNode node = cache.get(key);
        if(node == null) { return -1; }

        // move the accessed node to the head;
        this.moveToHead(node);

        return node.value;
    }
    
  
	/*
     * set(key, value) - Set or insert the value if the key is not already present. 
     * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     */
    public void set(int key, int value) {
    	DLinkedNode node = cache.get(key);

        if(node == null){

            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if(count > capacity){
                // pop the tail
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.key);
                --count;
            }
        }else{
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }
    
    private DLinkedNode popTail() {
    	DLinkedNode res = tail.prev;
        this.removeNode(res);
        return res;
	}

	private void moveToHead(DLinkedNode node) {
    	this.removeNode(node);
        this.addNode(node);
    }

    /**
     * Always add the new node right after head;
     */
	private void addNode(DLinkedNode node) {
		node.prev = head;
	    node.post = head.post;

	    head.post.prev = node;
	    head.post = node;
	}

	/**
	 * Remove an existing node from the linked list.
	 */
	private void removeNode(DLinkedNode node) {
		DLinkedNode prev = node.prev;
	    DLinkedNode post = node.post;

	    prev.post = post;
	    post.prev = prev;
	}

    
    public Hashtable<Integer, DLinkedNode> getCache() {
		return cache;
	}

	public void setCache(Hashtable<Integer, DLinkedNode> cache) {
		this.cache = cache;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public DLinkedNode getHead() {
		return head;
	}

	public void setHead(DLinkedNode head) {
		this.head = head;
	}

	public DLinkedNode getTail() {
		return tail;
	}

	public void setTail(DLinkedNode tail) {
		this.tail = tail;
	}
    
  
}
