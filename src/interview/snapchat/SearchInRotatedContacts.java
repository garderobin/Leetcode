package interview.snapchat;


//假设不允许重名！！！！！
public class SearchInRotatedContacts {
	public static void main(String[] args) {
		Contact[] contacts = {
				new Contact("alice"),
				new Contact("amy"),
				new Contact("bob"),
				new Contact("cindy"),
				new Contact("denis"),
				new Contact("clara"),
				new Contact("bon"),
				new Contact("ann")
		};
		System.out.println(findPivotName(contacts));
		
		Contact[] contacts2 = {
				new Contact("denis"),
				new Contact("clara"),
				new Contact("bon"),
				new Contact("ann")
		};
		System.out.println(findPivotName(contacts2));
		
		Contact[] contacts3 = {
				new Contact("alice"),
				new Contact("amy"),
				new Contact("bob"),
				new Contact("cindy"),
				new Contact("denis")
		};
		System.out.println(findPivotName(contacts3));
	}
	
	
	public static String findPivotName(Contact[] contacts) {
		if (contacts == null || contacts.length < 2) return null;
		int idx = findPivotHelper(contacts, 0, contacts.length - 1);
		return (idx < 0 || idx == contacts.length - 1) ? null : contacts[idx].name;
	}
	
	
	private static int findPivotHelper(Contact[] contacts, int start, int end) {
		if (start == end) return start;
		
		int k = start + (end - start) / 2;
		int cmp = contacts[k].compareTo(contacts[k+1]);
		if (cmp > 0) 	return findPivotHelper(contacts, start, k);
		else 			return findPivotHelper(contacts, k+1, end);
	}
}

class Contact implements Comparable<Contact> {
	String name;
	
	public Contact(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(Contact o) {
		if (o == null) return 1;
		else if (name == null && o.name == null) return 0;
		else if (name == null && o.name != null) return -1;
		else if (name != null && o.name == null) return 1;
		else return name.compareTo(o.name);
	}
	
}
