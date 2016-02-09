package algorithm;

public class FirstBadVersion {
	public static int firstBadVersion(int n) {
        return getBadVersion(1, n);
    }
	
	private static int getBadVersion(int start, int end) {		
		if (start == end) {
			return (isBadVersion(start)) ? start : -1;
		} else if (start > end) {
			return -1;
		}
		int k = (end - start) / 2 + start, m;
		if (isBadVersion(k)) {
			return ((m = getBadVersion(start, k - 1)) == -1) ? k : m;
		} else {
			return ((m = getBadVersion(k + 1, end)) == -1) ? -1 : m;
		}
	}
	
	private static boolean isBadVersion(int version) {
		return version >= 1150769282;
	}
	
	public static void main(String[] args) {
		System.out.println(firstBadVersion(1420736637));
	}
}
