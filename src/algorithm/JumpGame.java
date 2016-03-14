package algorithm;

/**
 * Test!!!!
 * @author jasmineliu
 *
 */
public class JumpGame {
	public boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        boolean[] f = new boolean[nums.length];
        int[] y = new int[nums.length];
        f[0] = true;
        y[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            y[i] = (nums[i-1] > y[i-1] ) ? nums[i-1] - 1 : y[i-1] - 1;
            f[i] = (f[i-1] && (y[i] >= 0));
            System.out.println("f[" + i + "] = " + f[i] + "; y[" + i + "] = " + y[i]);
        }
        return f[nums.length - 1];
    }
	
	public static int jump(int[] nums) {
		if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = 0, jumps = 0;
        while (end < nums.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i > farthest) {
                    farthest = nums[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
            System.out.println("start = " + start + ";\tend = " + end + ";\tfarthest = " + farthest);
        }
        return jumps;
	}
	
//	public static void main(String args[]) {
//		int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//		System.out.println(canJump(nums));
//	}
	
//	public static int jump(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return -1;
//        }
//        if (nums.length == 1) {
//            return 0;
//        }
//        int[] f = new int[nums.length];
//        int[] h = new int[nums.length];
//        int[] y = new int[nums.length];
//        int[] x = new int[nums.length];
//        f[0] = 0; h[0] = 0; y[0] = 0; x[0] = 0;
//        for(int i = 1; i < nums.length; i++) {            
//            if (nums[i-1] > y[i-1]) {
//            	h[i] = f[i-1] + 1; 
//            	x[i] = nums[i-1] - 1;
//            } else {
//            	h[i] = f[i-1];
//            	x[i] = y[i-1] - 1;
//            }
//            if (y[i-1] > 0) {
//                f[i] = f[i-1];
//                y[i] = y[i-1] - 1;                
//            } else if (x[i] > 0){
//            	
//            } else {
//                f[i] = f[i-1] + 1;
//                y[i] = nums[i-1] - 1;
//            }
//
//            System.out.println("nums[" + i + "] = " + nums[i] + "\t f[" + i + "] = " + f[i] + ";\t y[" + i + "] = " + y[i]);
//        }
//        
//        return f[nums.length - 1];
//    }
	
	
}
