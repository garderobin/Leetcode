package interview.snapchat;

import java.util.List;
import java.util.ArrayList;

/*
 * 一棵树，代表上司和员工的关系，然后每个节点都有一个对应的权值。
 * 然后公司开了一个party，为了让员工们更好的交流，有个规定，如果上司去参加party，那么他的直接下属（直接孩子节点)就不去（同理，如果下属去了，直接上司，父节点就不去）。
 * 然后设计一个算法，参加party的人的权值总和最高。
 * 这是一道动态规划题，思路是每次计算一个员工去的权值之和与不去的权值之和，从叶子开始。
 */
public class EmployeeParty {
	public static void main(String[] args) {
		Employee e1 = new Employee(1, 13);
//		Employee e1 = new Employee(1, 3);
	    Employee e2 = new Employee(2, 9);
	    Employee e3 = new Employee(3, 2);
	    Employee e4 = new Employee(4, 3);
	    Employee e5 = new Employee(5, 5);
	    Employee e6 = new Employee(6, 8);
	    Employee e7 = new Employee(7, 6);
	    Employee e8 = new Employee(8, 7);

	    e1.subordinates.add(e2);
	    e1.subordinates.add(e3);
	    e3.subordinates.add(e6);
	    e2.subordinates.add(e4);
	    e2.subordinates.add(e5);
	    e5.subordinates.add(e7);
	    e5.subordinates.add(e8);
	      
	    List<Employee> attendants = maxWeightParty(e1);
	    double totalWeight = 0;
	    for (Employee e: attendants) {
	    	totalWeight += e.weight;
	    	System.out.print(e.id + ", ");
	    }
	    System.out.println("weight = " + totalWeight);
	}
	
	public static List<Employee> maxWeightParty(Employee boss) {
		List<Employee> attendants = new ArrayList<>();
		
		if (boss == null) return attendants;
		
		updateSelectWeight(boss); 
		updateAttend(attendants, boss, false);
		
//		System.out.println(attendants);
//	    System.out.println(Math.max(boss.select,boss.unselect));
		return attendants;
	}
	
	private static void updateAttend(List<Employee> attendants, Employee employee, boolean mentorAttend) {
		if (!mentorAttend && employee.select > employee.unselect) {
			employee.attend = true;
			attendants.add(employee);
		}
		for (Employee sub: employee.subordinates) {
			updateAttend(attendants, sub, employee.attend);
		}
	}

	private static void updateSelectWeight(Employee mentor) {
		if (mentor == null) return;
		
		for (Employee e: mentor.subordinates) {
			updateSelectWeight(e);
			mentor.select += e.unselect;
			mentor.unselect += (e.unselect > e.select) ? e.unselect: e.select;
		}
	}
	
}

class Employee {
	int id;
	double weight;
	double select;
	double unselect;
	boolean attend;
	List<Employee> subordinates;
	
	public Employee(int id, double weight) {
		this.id = id;
		this.weight = weight;
		this.select = weight;
		this.unselect = 0;
		subordinates = new ArrayList<>();
	}
	
}
