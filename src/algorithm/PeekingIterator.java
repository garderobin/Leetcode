package algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PeekingIterator implements Iterator<Integer> {
	Deque<Integer> list;
//	int index;
	
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
		list = new LinkedList<Integer>();
	    while (iterator.hasNext()) {
	    	list.add(iterator.next());
	    }
//	    index = 0;
	}
	
	 // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (list == null || list.size() == 0) { return -1; }
		return list.peek();
	}
	
	@Override
	public boolean hasNext() {
		return !(list == null || list.size() == 0);
	}

	@Override
	public Integer next() {
		if (list == null || list.size() == 0) { return -1; }
		return list.poll();
	}

	@Override
	public void remove() {
		if (list == null || list.size() == 0) { return; }
		list.removeLast();
	}

}
