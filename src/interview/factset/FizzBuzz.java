package interview.factset;

public class FizzBuzz {
	public static void PrintFizzBuzz() {
		System.out.print("1");
		for (int i = 2; i < 101; i++) {
			if (i % 15 == 0) {
				System.out.print(", Fizz Buzz");
			} else if (i % 3 == 0) {
				System.out.print(", Fizz");
			} else if (i % 5 == 0) {
				System.out.print(", Buzz");
			} else {
				System.out.print(", " + i);
			}
		}
	}
	
	public static void main(String[] args) {
		PrintFizzBuzz();
	}
}
