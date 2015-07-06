package algorithm;

import java.util.*;

public class MergeSort
{
	private static int mergeSortCounter = 0;
	private static int mergeCounter = 0;
	public static void main(String[] args)
	{
		Integer[] a = {2, 6, 3, 5, 1};
		mergeSort(a);
		System.out.println(Arrays.toString(a));
	}

	public static void mergeSort(Comparable[] a)
	{
		Comparable[] tmp = new Comparable[a.length];
		mergeSort(a, tmp,  0,  a.length - 1);
	}


	private static void mergeSort(Comparable [ ] a, Comparable [ ] tmp, int left, int right)
	{
		mergeSortCounter++;
		int pn = mergeSortCounter;
		System.out.println("MergeSort: " + mergeSortCounter + ";\na = " + printArray(a) + "; temp = " + printArray(tmp) + "; left = " + left + "; right = " + right);
		if( left < right )
		{
			int center = (left + right) / 2;
			System.out.println("\nParent method number: " + pn);
			mergeSort(a, tmp, left, center);
			System.out.println("\nParent method number: " + pn);
			mergeSort(a, tmp, center + 1, right);
			merge(a, tmp, left, center + 1, right);
		}
	}


    private static void merge(Comparable[ ] a, Comparable[ ] tmp, int left, int right, int rightEnd )
    {
    	mergeCounter++;
    	System.out.println("Merge: " + mergeCounter + ";\na = " + printArray(a) + "; temp = " + printArray(tmp) + "; left = " + left + "; right = " + right + "; rightEnd = " + rightEnd);
		
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
    
    private static String printArray(Comparable[ ] a) {
    	String res = "{";
    	int i = 0;
    	for (i = 0; i < a.length - 1; i++ ) {
    		res += a[i] + ", ";
    	}
    	res += a[i] + "}";
    	return res;    	
    }
 }