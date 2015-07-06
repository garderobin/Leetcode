package algorithm;

public class CompareVersionNumber {
	public static int compareVersion(String version1, String version2) {
		int v1, v2, i1, i2;
		String s1, s2;
		char c = '.';

		while (true) {
			i1 = version1.indexOf(c);
			i2 = version2.indexOf(c);			
			s1 = (i1 < 0) ? version1 : version1.substring(0, i1);
			s2 = (i2 < 0) ? version2 : version2.substring(0, i2);
			v1 = Integer.parseInt(s1);
			v2 = Integer.parseInt(s2);						
			if (v1 > v2) {
				return 1;
			} else if (v1 < v2) {
				return -1;
			} else if (i1 < 0 && i2 < 0) {
				return 0;
			} else if (i1 > 0 && i2 < 0) {
				while (i1 > 0) {
					version1 = version1.substring(i1 + 1);
					i1 = version1.indexOf(c);
					if (i1 > 0) {
						v1 = Integer.parseInt(version1.substring(0, i1));
						if (v1 > 0) {
							return 1;
						}
					} else {
						v1 = Integer.parseInt(version1);
						if (v1 > 0) {
							return 1;
						} else {
							return 0;
						}
					}
					
				}
			} else if (i1 < 0 && i2 > 0) {
				while (i2 > 0) {
					version2 = version2.substring(i2 + 1);
					i2 = version2.indexOf(c);
					if (i2 > 0) {
						v2 = Integer.parseInt(version2.substring(0, i2));
						if (v2 > 0) {
							return -1;
						}
					} else {
						v2 = Integer.parseInt(version2);
						if (v2 > 0) {
							return -1;
						} else {
							return 0;
						}
					}
					
				}
			}
						
			version1 = version1.substring(i1 + 1, version1.length());
			version2 = version2.substring(i2 + 1, version2.length());			
		}
	}
	
	public static void main(String[] args) {
		System.out.println(compareVersion("3.2", "3.2.1.9.8144"));
	}
}
