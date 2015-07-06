package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMedianTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) {
            return 0;
        } else if (nums1 == null || nums1.length == 0) {
            return findMedian(nums2, 0, nums2.length - 1);
        } else if (nums2 == null || nums2.length == 0) {
            return findMedian(nums1, 0, nums1.length - 1);
        } else {
            return myFindMedianSortedArrays(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1);
        }
        
    }
    
    public double myFindMedianSortedArrays(int[] nums1, int[] nums2, int s1, int e1, int s2, int e2) {
        int l1 = e1 - s1 + 1;
        int l2 = e2 - s2 + 1;
        int ns1 = s1; int ne1 = e1; int ns2 = s2; int ne2 = e2;
        double m1 = findMedian(nums1, s1, e1);
        double m2 = findMedian(nums2, s2, e2);
        if (l1 < 4 || l2 < 4) {
            int[] lastArray = mergeSortedSubArrays(nums1, nums2, s1, e1, s2, e2);
            return findMedian(lastArray, 0, lastArray.length - 1);
        } 
        if (m1 == m2) {
            return m1;
        } else if (m1 > m2) {
        	if (l1 < l2) {
        		ne1 = s1 + (e1 - s1)/2 + 1;
        		ns2 = s2 + e1 - ne1;
        	} else {
        		ns2 = s2 + (e2 - s2 + 1)/2 - 1;
        		ne1 = e1 - (ns2 - s2);
        	}        	        	
            return myFindMedianSortedArrays(nums1, nums2, s1, ne1, ns2, e2);
        } else {
        	if (l1 < l2) {
        		ns1 = s1 + (e1 - s1 + 1)/2 - 1;
        		ne2 = e2 - (ns1 - s1);
        	} else {
        		ne2 = s2 + (e2 - s2)/2 + 1;
        		ns1 = s1 + e2 - ne2;
        	}  
            return myFindMedianSortedArrays(nums1, nums2, ns1, e1, s2, ne2);
        }
        
    }
    
    public double findMedian(int[] nums, int s, int e) {
        if ( (e-s) % 2 == 0)  { // (e-s+1) odd, length odd
            return nums[s + (e-s)/2];
        }
        double d1 = nums[s + (e-s)/2];
        double d2 = nums[s + (e-s)/2 + 1];
        return ( d1 + d2 )/2;
    }
    
    public int[] mergeSortedSubArrays(int[] nums1, int[]nums2, int s1, int e1, int s2, int e2) {
        int i,j,k;
        List<Integer> res = new ArrayList<Integer>();
        for (i = s1; i < e1 + 1; i++) {
            res.add(nums1[i]);
        }
        for (j = s2; j < e2 + 1; j++) {
            res.add(nums2[j]);
        }
        Collections.sort(res); 
        int len = res.size();
        int[] list = new int[len];
        for (k = 0; k < len; k++) {
        	list[k] = res.get(k);
        }
        
        return list;
    }
}