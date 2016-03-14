package algorithm;
//import org.javatuples.Pair;

public class MajorityElement {
	public static int majorityElement(int[] nums) {
//		int k = nums.length / 2;
		//int left = maforityElement(0)
        return -1;
    }
	
//	public static Pair<Integer, Integer> findMajorityElement(int start, int end, int[] nums) {
//		if (start == end) {
//			return Pair.with(nums[start], 1);
//		}
//		int k = (end - start)/2 + start;
//		Pair<Integer, Integer> left = findMajorityElement(start, k, nums);
//		Pair<Integer, Integer> right = findMajorityElement(k+1, end, nums);
//		if (left.getValue0() == right.getValue0()) {
//			return Pair.with(left.getValue0(), left.getValue1() + right.getValue1());
//		} else if (left.getValue0() >= right.getValue0()) {
//			return left;
//		} 
//		
//		return right;
//	}
	
	/**
	 * 利用的原理非常简单：遇重则加，不同则减。只有众数永远减不到0。
	 * @param nums
	 * @return
	 */
	public static int majorityElementDiscussion(int[] nums) {
		int major = nums[0], count = 1;		
        for(int i = 1; i < nums.length; i++){
            if (count == 0){
                count++;
                major = nums[i];
            } else if (major == nums[i]){
                count++;
            } else {
                count--;
            }
            System.out.println("nums[" + i + "] = " + nums[i] + "; major = " + major + "; count = " + count);
        }
        return major;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,3,4,5,6,2,2,2,2,2,2,2,2,2,4,2,3,2,5,2,2};
		System.out.println(majorityElementDiscussion(nums));
	}
}
