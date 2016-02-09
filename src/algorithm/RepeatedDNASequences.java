package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {
	
	/**
	 * 1. 利用位运算构造四进制，每个长度为10的序列是一个十位的四进制数，有唯一对应的十进制值。
	 * 以此为每个长度为10单位选择只有四种的序列创造了唯一的整形映射值。
	 * 2. words存储出现过一次的序列映射值， doubleWords存储过出现过两次的序列映射值。
	 * 用doubleWords的目的是防止第三，四，……次出现时重复添加相同的序列。
	 * @param s
	 * @return
	 */
	public static List<String> findRepeatedDnaSequences(String s) {
		Set<Integer> words = new HashSet<>();
	    Set<Integer> doubleWords = new HashSet<>();
	    List<String> rv = new ArrayList<>();
	    int[] map = new int[26];
	    //map['A' - 'A'] = 0;
	    map['C' - 'A'] = 1;
	    map['G' - 'A'] = 2;
	    map['T' - 'A'] = 3;

	    for(int i = 0; i < s.length() - 9; i++) {
	        int v = 0;
	        for(int j = i; j < i + 10; j++) {
	            v <<= 2; //四进制向前进位
	            v |= map[s.charAt(j) - 'A']; //将四进制数最后一位赋为当前值
	        }
	        if(!words.add(v) && doubleWords.add(v)) {
	            rv.add(s.substring(i, i + 10));
	        }
	        
	    }
	    return rv;
    }
	
	public static void main(String[] args) {
		String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		System.out.println(findRepeatedDnaSequences(s));
	}
}
