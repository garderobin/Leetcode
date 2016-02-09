package dataStructure;

public class MyBinarySearch {
	public static int myBinarySearch(int[] a) {
		int i = 0, j = a.length - 1, m, x = 0, location;
		while (i < j) {
			m = (i + j) / 2;
			if (x > a[m]) {
				i = m + 1;
			} else {
				j = m;
			}
		}
		if (x == a[i]) {
			location = i;
		} else {
			location = 0;
		}
		
		return location;
	}
}	
