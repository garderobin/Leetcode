package interview.snapchat;

import java.util.BitSet;
import java.util.List;

public class CountingBloomFilter<Key> {
	private List<Key> table; // initial design was: private Key[] table
	private HashFunc hashFunc; // initial design was: private List<String> hashFunc;
	//我自己添加的数据结构,每4位合起来表示一个位置的counter, 因为对于绝大多数计算机来说不超过16的counter就足够了
	//1:hashFunc不是我自己写的，不知道hash最多需要占用多少位置，所以采取可增长的
	private BitSet data; // not thread safe!!!!!
	
	
	public CountingBloomFilter(Key[] keys, HashFunc hashFunc) {
		for (Key k: keys) {
			table.add(k);
			this.put(k); // Update data set
		}
		this.hashFunc = hashFunc;
		data = new BitSet();
	}
	
	public boolean contains(Key k) {
		if (table.isEmpty() || data.isEmpty()) return false; 
		
		for (int hash: hashFunc.hashcodes(k)) {
			if (!indexSet(hash)) return false;
		}
		return true;
	}
	
	public boolean put(Key k) { // initial design was: public void put(Key k)
		table.add(k);
		
		boolean overflow = false;
		for (int hash: hashFunc.hashcodes(k)) {
			overflow |= plusOne(hash);
		}
		return !overflow;
	}	
	
	public boolean remove(Key k) {
		if (!contains(k)) return false; // if k is not contained
		table.remove(k);
		boolean overflow = false;
		for (int hash: hashFunc.hashcodes(k)) {
			overflow |= minusOne(hash);
		}
		return overflow;
	}
	
	private boolean indexSet(int hash) {
		for (int startIdx = (hash << 2), i = startIdx + 3; i >= startIdx; --i) {
			if (data.get(i)) return true;
		}
		return false;
	}
	
	private boolean plusOne(int hash) {
		int carry = 0;
		for (int startIdx = (hash << 2), i = startIdx + 3; i >= startIdx; --i) {
			int val = (data.get(i) ? 1 : 0) - carry; // all possible val: 0, 1, 2
			carry = (val >> 1);
			if (0 == (val & 1)) data.clear(i);
		}
		return carry == 0;
	}
	
	private boolean minusOne(int hash) {
		boolean carry = false;
		for (int startIdx = (hash << 2), i = startIdx + 3; i >= startIdx; --i) {
			int val = (data.get(i) ? 1 : 0) - (carry ? 1 : 0);  // all possible val: -1, 0, 1
			carry = val < 0;
			if (val > 0) data.set(i);
			else data.clear(i);
		}
		return !carry;
	}
}


interface HashFunc {
	public int[] hashcodes(Object o);
}
