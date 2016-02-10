package GoogleOA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZigzagIterator {
	
	/**
	 * 这种做法也是O(1) space
	 */
//	 private Iterator<Integer> i, j, tmp;
//
//	    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
//	        i = v2.iterator();
//	        j = v1.iterator();
//	    }
//
//	    public int next() {
//	        if (j.hasNext()) { tmp = j; j = i; i = tmp; }
//	        return i.next();
//	    }
//
//	    public boolean hasNext() {
//	        return i.hasNext() || j.hasNext();
//	    }
	
	
	Iterator<Integer> iz;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    	if (v1 == null) { iz = v2.iterator(); }
    	else if (v2 == null) { iz = v1.iterator(); }
    	else {
    		Iterator<Integer> i1 = v1.iterator(), i2 = v2.iterator();
    		List<Integer> vz = new ArrayList<Integer>();
    		while (i1.hasNext() && i2.hasNext()) {
    			vz.add(i1.next());
    			vz.add(i2.next());
    		}
    		while (i1.hasNext()) { vz.add(i1.next()); }
    		while (i2.hasNext()) { vz.add(i2.next()); }
    		iz = vz.iterator();
    	}
        
    }

    public int next() {
        return iz.next();
    }

    public boolean hasNext() {
        return iz.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */