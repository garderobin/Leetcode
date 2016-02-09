package algorithm;

public class MergeSortedArrays {
	/**
	 * 双指针法能够避免extra space，牢记。
	 * 从后往前遍历能够避免中间过程中的交叉覆盖。
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void mergeDiscussion(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while(i >= 0 && j >= 0)
        {
            if(nums1[i] > nums2[j])
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }
        while(j>=0)
            nums1[k--] = nums2[j--];
    }
	
	public static void merge(int[] nums1, int m, int[] nums2, int n) {		
		if (n == 0) { return; }
		if (m == 0) {
			System.arraycopy(nums2, 0, nums1, 0, n);
		}
		int i = 0, j = 0, k = 0, ti, tk;
		for (; i < m && j < n; k++) {
			ti = i + n;
			tk = k + n;
			if (i == k) {
				if (nums1[i] > nums2[j]) {
					if (tk < m) {
						System.arraycopy(nums1, tk, nums1, tk + n, m - tk);
					}
					nums1[tk] = nums1[k];				
					nums1[k] = nums2[j];
					j++;
				} else {
					i++;
				}
			} else { //i < k
				System.out.println(ti + ", " + j);
				if (nums1[ti] > nums2[j]) {
					if (tk < m) {
						System.arraycopy(nums1, tk, nums1, tk + n, m - tk);
					}
					if (k < m) {
						nums1[tk] = nums1[k];
					}	
					nums1[k] = nums2[j];
					j++;
				} else {
					if (tk < m) {
						System.arraycopy(nums1, tk, nums1, tk + n, m - tk);
					}
					if (k < m) {
						nums1[tk] = nums1[k];
					}
					nums1[k] = nums1[ti];
					i++;
				}
			}
			
		}
		for (; j < n; j++, k++) {
			nums1[k] = nums2[j];
		}
    }
	
	public static void main(String[] args) {
		int nums1[] = {0,0,3,0,0,0,0,0,0};
		int nums2[] = {-1,1,1,1,2,3};
		int m = 3, n = 6;
		merge(nums1, m, nums2, n);
		for (int e : nums1) {
			System.out.print(e + ", ");
		}
	}
}
