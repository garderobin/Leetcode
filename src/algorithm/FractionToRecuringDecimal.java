package algorithm;

import java.util.HashMap;

public class FractionToRecuringDecimal {
	
	/**
	 * 
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public String fractionToDecimalDiscussion(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
	
	/**
	 * 尚未完成
	 * 数学要点：
	 * 1. 判断无限小数：分母只含有2或5的任意次方的分数都能化成有限小数
	 * 除此之外的分数都能化成无限循环小数
	 * 2. 求循环节：利用长除法可以将分数(有理数)转化为循环小数
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static String fractionToDecimal(int numerator, int denominator) {
		int d = denominator, a = numerator, b = denominator;		
		if (b == 0) {
			return null;
		}
		if (a == 0) {
			return "0";
		}		
		
		// Divisible
		if (a % b == 0) {
			return a / b + "";
		}
		
        // Finite decimal
		while (d % 5 == 0) {
			d /= 5;
		}
		while (d % 2 == 0) {
			d = d >> 1;
		}
		if (d == 1) { 
			return (double)(a / b) + "";
		}
		
		// Infinite decimal
		
		// The integer part
		if (a > b) {
			
		}
		// 
		
		return null;
    }
}
