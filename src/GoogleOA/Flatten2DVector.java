package GoogleOA;

import java.util.Iterator;
import java.util.List;

public class Flatten2DVector {
	private Iterator<List<Integer>> i;
    private Iterator<Integer> j;
	
	public Flatten2DVector(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    public int next() {
        hasNext(); //这里完成迭代和初始化，高明。
        return j.next();
    }

    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && (i != null && i.hasNext())) {
        	j = i.next().iterator();
        }
        return j != null && j.hasNext();
    }
}
