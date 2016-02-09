package algorithm;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
	/**
	 * 还是那句话，正面想不通要想如何逆向思维
	 * 在现有基础上往左边添1，是比确定了最左边的1再对右边进行构造要好的办法。
	 * @param n
	 * @return
	 */
	public ArrayList<Integer> grayCodeDiscussion(int n) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(0);
        for(int i = 0; i < n; i++){
            int inc = 1 << i;
            for(int j = arr.size() - 1; j >= 0; j--){
                arr.add(arr.get(j) + inc);
            }
        }
        return arr;
    }
	
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(10));
		System.out.println(Integer.toBinaryString(6));
		System.out.println(Integer.toBinaryString(4));
		System.out.println(Integer.toBinaryString(3));
		System.out.println(Integer.toBinaryString(18));
	}
}
