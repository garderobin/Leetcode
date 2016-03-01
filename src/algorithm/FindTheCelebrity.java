package algorithm;

import java.util.ArrayList;
import java.util.List;

public class FindTheCelebrity {

	public int findCelebrityDiscussion(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){ // find candidate
            if(knows(candidate, i))
                candidate = i;
        }
        for(int i = 0; i < n; i++){ // check candidate real
            if(i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
	
	public int findCelebrity(int n) {
		List<Integer> cds = new ArrayList<Integer>(); // candidate list
		for (int i = 0; i < n; i++) { cds.add(i); }
        while (cds.size() > 1) {
        	for (int k = 1; k < cds.size(); k += 2) { 
        		removeCandidate(cds, k); 
        	}     
        }
        
        if (cds.isEmpty()) { return -1; }
        int lastCd = cds.get(0), lastIn = 0;
        for (int i = 0; i < n && lastIn != n-1; i++) {
        	if (i == lastCd) { continue; }
        	if (knows(lastCd, i)) { return -1; }
        	if (knows(i, lastCd)) { ++lastIn; }
        }
        return (lastIn == n-1) ? lastCd : -1;
    }
	

	private void removeCandidate(List<Integer> cds, int pos) {
		int i = cds.get(pos-1), j = cds.get(pos);
		boolean b1 = knows(i, j), b2 = knows(j, i);
		if (b1) {  cds.remove(--pos); } 
		if (b2) {  cds.remove(pos); }
		if (!(b1 || b2)) { 
			cds.remove(--pos);
			cds.remove(pos);
		}  
	}
	
	private boolean knows(int i, int j) {
//		if (i == 0 && j == 1) { return true; }
//		if (i == 2 && j == 0) { return true; }
//		if (i == 2 && j == 1) { return true; }
		if (i == 1 && j == 0) { return true; }
		return false;
	}
	

}
