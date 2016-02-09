package algorithm;

public class UglyNumber1 {
	public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        if (num < 7) {
            return true;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        return Math.abs(num) == 1;
    }
}
