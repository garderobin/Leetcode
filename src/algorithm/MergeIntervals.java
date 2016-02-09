package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dataStructure.Interval;

public class MergeIntervals {
	
	/**
	 * O(n) space, using iterator.
	 * @param intervals
	 * @return
	 */
	public static List<Interval> mergeV2(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0) { return new LinkedList<Interval>(); }
		int size = intervals.size();
		if (size == 1) { return intervals; }
		List<Interval> rst = new LinkedList<Interval>();
        Comparator<Interval> comparator = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				if (o1.start == o2.start) { return o1.end - o2.end; }
				return o1.start - o2.start;
			}
        };
        
        Collections.sort(intervals, comparator);
        
        Iterator<Interval> iterator = intervals.iterator();
        Interval prev = iterator.next(), cur, merge;
        rst.add(prev);
        while (iterator.hasNext()) {
        	cur = iterator.next();
        	if (cur.start <= prev.end) { //merge
        		merge = new Interval(prev.start, Math.max(prev.end, cur.end)); // 注意这里prev和cur的end不知道谁大
        		rst.remove(rst.size() - 1);
        		rst.add(merge);
        		prev = merge;
        	} else { 
        		prev = cur;
        		rst.add(cur);
        	}
        }
        
        return rst;
    }
	
	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,4));
		intervals.add(new Interval(0,0));
		List<Interval> rst = mergeV2(intervals);
		for (Interval itv : rst) {
			System.out.println("[" + itv.start + ", " + itv.end + "]");
		}
	}
	
	/**
	 * O(1) space. No iterator.
	 * 用开始时间排序是处理interval最好的办法。accepted.
	 * 如果要对Collection的原始数据做修改的话，不要用iterator.
	 * iterator只适合于检索遍历，不适合任何内部改动。
	 * @param intervals
	 * @return
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0) { return new ArrayList<Interval>(); }
		if (intervals.size() == 1) { return intervals; }
        Comparator<Interval> comparator = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				if (o1.start == o2.start) {
					return o1.end - o2.end;
				}
				return o1.start - o2.start;
			}
        	
        };
        
        Collections.sort(intervals, comparator);
        
        Interval prev = intervals.get(0), cur, merge;
        for (int i = 1; i < intervals.size();) {
        	cur = intervals.get(i);
        	if (cur.start <= prev.end) { //merge
        		merge = new Interval(prev.start, Math.max(prev.end, cur.end));
        		intervals.remove(i-1);
        		intervals.remove(i-1);
        		intervals.add(i-1, merge);
        		prev = merge;
        	} else { 
        		prev = cur;
        		i++;
        	}
        }
        
        return intervals;
    }
}
