package algorithm;

import java.util.LinkedList;

public class MinStack {
	int min = Integer.MAX_VALUE;
	LinkedList<Integer> list = new LinkedList<Integer>();
	LinkedList<Integer> minList = new LinkedList<Integer>();
	
	public void push(int x) {
        list.push(x);
        if (x < min) {
        	min = x;
        	minList.addFirst(x);
        } else {
        	int index = binarySearchInSortedList(x, 0, minList.size() - 1);
        	minList.add(index, x);        
        }
    }

    private int binarySearchInSortedList(int x, int start, int end) {
		if (start >= end) {
			return end;
		} 

		if (x >= minList.get(end)) {
			//minList.add(x);
			return end + 1;
		}
		if (x <= minList.get(start)) {
			return start - 1;
		}
		int k = (end + 1 - start) / 2;
		if (x < minList.get(k)) {
			return binarySearchInSortedList(x, start, k - 1);
		} else if (x > minList.get(k)){
			return binarySearchInSortedList(x, k + 1, end);
		}
		
		return 0;
	}

	public void pop() {
        list.pop();
    }

    public int top() {
        return list.peek();
    }

    public int getMin() {        
    	return min;
    }
}
