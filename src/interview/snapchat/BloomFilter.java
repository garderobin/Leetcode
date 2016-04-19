package interview.snapchat;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


/*
 * Ask: 需要考虑并发吗？
 * http://www.jiuzhang.com/interview/31/ 
 * 该数据结构类似于HashSet，put函数存key进去，contains查询是否存在，remove删除对应的key。它提供了一组hash函数列表hashFunc。
question:
1)该数据结构有个特点，contains函数如果return false，那key一定不存在Bloomfilter内，但如果return true，则可能存在，可能不存在，为什么？
2)实现这个数据结构的三个函数
3)这个数据结构有什么缺点？怎么Scale？写出代码

 * 我当下的设计既能导致false negative， 又会导致false positive.
 */
public class BloomFilter<Key extends Comparable<Key>> {
//	private Key[] table; // 只在初始化的时候有用 = =
	private List<Key> table;

	@SuppressWarnings("unused")
	private List<String> hashFunc; // 我认为这个hashFunc应该起到的作用是下面的hashCode(Key k)
	
	//我自己添加的数据结构
	//1:hashFunc不是我自己写的，不知道hash最多需要占用多少位置，所以采取可增长的
	private BitSet data; // not thread safe!!!!!
	
//	private int 
	
	
	public BloomFilter(Key[] input) {
		if (input == null || input.length == 0) {
			data = new BitSet();
		} else {
			int numKeys = input.length, eleLen = input[0].toString().length();
			data = new BitSet(numKeys * eleLen);
			table = new ArrayList<Key>(numKeys);
			
			for (Key k: input) {
				table.add(k);
				put(k);
			}
		}
	}

	public boolean contains(Key k) { // it's just a might contains
		int hash = k.hashCode(); // replace here with given hashFunc!!!
		for (int i = 0; hash > 0; hash >>= 1, ++i) {
			if ((hash & 1) == 1 && !data.get(i)) {
				return false;
			}
		}
		return true;
	}

	public void put(Key k) {
		int hash = k.hashCode(); // replace here with given hashFunc!!!
		for (int i = 0; hash > 0; hash >>= 1, ++i) {
			if ((hash & 1) == 1) {
				data.set(i); // causing possible false positive for contains();
			}
		}
		table.add(k);
	}

	public void remove(Key k) {
		int hash = k.hashCode(); // replace here with given hashFunc!!!
		for (int i = 0; hash > 0; hash >>= 1, ++i) {
			if ((hash & 1) == 1) {
				data.clear(i); // causing possible false negative for contains()
			}
		}
		//table.remove(k);
	}

}


