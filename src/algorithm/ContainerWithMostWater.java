package algorithm;

public class ContainerWithMostWater {
	
	/**
	 * 核心在于：一长一短组成的两边，把长边所在的位置往内收只会可能减小面积。
	 * 这里之所以能用O(N)解决，就在于使用双指针时每次只变化一个指针就能遍历所有情况。
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
        while (left < right) {
        	maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
        	if (height[left] < height[right]) { left++; }
        	else { right--; }
        }
        
        return maxArea;
    }
	
	public int maxAreaDiscussion(int[] height) {
	    int left = 0, right = height.length - 1;
	    int maxArea = 0;

	    while (left < right) {
	        maxArea = Math.max(maxArea, Math.min(height[left], height[right])
	                * (right - left));
	        if (height[left] < height[right])
	            left++;
	        else
	            right--;
	    }

	    return maxArea;
	}
}
