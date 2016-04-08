package algorithm;

public class Utils {
	
	public static String charArrayToString(char[] arr) {
		if (arr == null || arr.length == 0) return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append("[" + arr[0]);
		for (int i = 1; i < arr.length; ++i) {
//			sb.append(',');
			sb.append(arr[i]);
		}
		sb.append(']');
		return sb.toString();
	}
	
	public static String intArrayToString(int[] arr) {
		if (arr == null || arr.length == 0) return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append("[" + arr[0]);
		for (int i = 1; i < arr.length; ++i) {
			sb.append(',');
			sb.append(arr[i]);
		}
		sb.append(']');
		return sb.toString();
	}
	
	
}
