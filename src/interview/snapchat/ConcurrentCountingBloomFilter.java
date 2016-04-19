package interview.snapchat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCountingBloomFilter<Key> {
	
	private List<Key> table;
	private HashFunc hashFunc;
	private ConcurrentHashMap<Integer, Byte> data;

	public ConcurrentCountingBloomFilter(Key[] keys, HashFunc hashFunc) {
		table = new ArrayList<Key>();
		for (Key k: keys) {
			table.add(k);
			this.put(k);
		}
		
		this.hashFunc = hashFunc;
		data = new ConcurrentHashMap<>();
	}
	
	public boolean contains(Key k) {
		for (int hash: hashFunc.hashcodes(k)) {
			if (!data.containsKey(hash)) return false;
		}
		return true;
	}
	
	public boolean put(Key k) {
		table.add(k);
		
		boolean overflow = false;
		for (int hash: hashFunc.hashcodes(k)) {
			int val = 1 + data.getOrDefault(hash, (byte)0);
			if (val > Byte.MAX_VALUE) {
				data.put(hash, (byte) val);
				overflow = true;
			} else {
				data.put(hash, (byte) val);
			}
		}
		return !overflow;
	}
	
	public boolean remove(Key k) {
		if (!contains(k)) return false;
		
		table.remove(k);
		
		for (int hash: hashFunc.hashcodes(k)) {
			byte val = (byte) (data.getOrDefault(hash, (byte)0) - 1);
			if (val <= 0) data.remove(hash);
			else data.put(hash, val);
		}
		
		return true;
	}
	
}
