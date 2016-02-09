package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import dataStructure.Interval;

public class InsertInterval {
//	public List<Interval> insert(List<Interval> intervals, Interval target) {
//		List<Interval> rst = new ArrayList<Interval>();
//		if (intervals == null || intervals.size() == 0) { return rst; }
//		int mergeStart, mergeEnd, i, len = intervals.size();
//        Interval left, right;
//        
//        
//        // Find the target's start position by start time.
//        for (; i < len; rst.add(left), i++) {
//        	left = intervals.get(i);
//        	if (left.end >= target.start) { break; }
//        }
//        mergeStart = Math.min(left.start, target.start);
//        
//        // Find the target's right most overlapping interval by end time.
//        for (mergeEnd = target.end; iter.hasNext(); mergeEnd = Math.max(right.end, target.end) ) {
//        	right = iter.next();
//        	if (right.start > target.end) { break; }
//        }
//        
//        // Add the merged interval into result.
//        rst.add(new Interval(mergeStart, mergeEnd));
//        
//        // Add the rest of the intervals into result.
//        while (iter.hasNext()) {
//        	rst.add(iter.next());
//        }
//        
//        return rst;
//    }
	
	public List<Interval> insert(List<Interval> intervals, Interval target) {
		List<Interval> rst = new ArrayList<Interval>();
		if (intervals == null || intervals.size() == 0) { return rst; }
        Iterator<Interval> iter = intervals.iterator();
        if (!iter.hasNext()) { return rst; }
        Interval left = iter.next(), right = left;
        int mergeStart, mergeEnd;
        
        // Find the target's start position by start time.
        for (; iter.hasNext(); rst.add(left)) {
        	left = iter.next();
        	if (left.end >= target.start) { break; }
        }
        if (!iter.hasNext()) {
        	rst.add(target);
        	return rst;
        }
        mergeStart = Math.min(left.start, target.start);
        
        // Find the target's right most overlapping interval by end time.
        for (mergeEnd = target.end; iter.hasNext(); mergeEnd = Math.max(right.end, target.end) ) {
        	right = iter.next();
        	if (right.start > target.end) { break; }
        }
        
        // Add the merged interval into result.
        rst.add(new Interval(mergeStart, mergeEnd));
        
        // Add the rest of the intervals into result.
        while (iter.hasNext()) {
        	rst.add(iter.next());
        }
        
        return rst;
    }
}
