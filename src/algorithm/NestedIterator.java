package algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataStructure.NestedInteger;

public class NestedIterator implements Iterator<Integer> {
	List<Integer> result;
	int index;
	public NestedIterator(List<NestedInteger> nestedList) {
	    result = new ArrayList<Integer>();
	    index = 0;
	    dfs(nestedList, result);
	}

	@Override
	public Integer next() {
	    return result.get(index++);
	}

	@Override
	public boolean hasNext() {
	    return index != result.size();
	}

	private void dfs(List<NestedInteger> list, List<Integer> result) {
	    for(NestedInteger tmp : list) {
	        if(tmp.isInteger()) result.add(tmp.getInteger());
	        else dfs(tmp.getList(), result);
	    }
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
