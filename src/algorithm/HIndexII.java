package algorithm;

public class HIndexII {
	public static int hIndex(int[] citations) {
        int len = citations.length;

        int first = 0;
        int mid = 0;
        int count = len;
        int step = 0;;

        while (count > 0) {
//        	System.out.println("begin: first=" + first + ",\tmid=" + mid + 
//        			",\tstep=" + step + ",\tcount=" + size);
            step = count / 2;
            mid = first + step;
            if (citations[mid] < len - mid) {
                first = mid + 1;
                count -= (step + 1);
            }
            else {
                count = step;
            }
//            System.out.println("after: first=" + first + ",\tmid=" + mid + 
//        			",\tstep=" + step + ",\tcount=" + size);
        }

        return len - first;
    }
	
	public static void main(String[] args) {
		int[] citations = {0,1,3,5,6};
		System.out.println(hIndex(citations));
	}
}
