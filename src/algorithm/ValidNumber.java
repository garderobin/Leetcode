package algorithm;

public class ValidNumber {
	public boolean isNumber(String s) {
		return s.matches("[-+]?[0-9]*(.[0-9]+)?(e[-+]?[0-9]+)?");
    }
}
