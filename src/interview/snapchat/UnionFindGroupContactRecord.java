package interview.snapchat;

import java.util.*;

public class UnionFindGroupContactRecord {
	public static void main(String[] args){
		ArrayList<LinkedList<Record>> res = new ArrayList<LinkedList<Record>>();
		ArrayList<Record> records = new ArrayList<>();
		records.add(new Record("ABC",123));
		records.add(new Record("ABC",456));
		records.add(new Record("BCD",456));

		records.add(new Record("AD",342));
		records.add(new Record("AddD",11));
		records.add(new Record("DFX",34));
		records.add(new Record("AD",34));
		records.add(new Record("AD",11));

		res = GroupRecord(records);
		System.out.println(res);
	}
	
	public static ArrayList<LinkedList<Record>> GroupRecord(ArrayList<Record> records){
		UnionFind<Record> uf = new UnionFind<Record>(records);
		Map<String,Record> nameMap = new HashMap<>();
		Map<Integer,Record> numberMap = new HashMap<>();
		
		for(Record record: records){
			Record frecord = uf.find(record);
			
			if (nameMap.containsKey(record.name)) {
				Record fname = uf.find(nameMap.get(record.name));	
				if(!frecord.equals(fname)){ 	
					uf.union(frecord,fname);	
				}
			} else{
				nameMap.put(record.name,record);	
			}
			
			if (numberMap.containsKey(record.number)) {
				Record fnumber = uf.find(numberMap.get(record.number));
				if (!frecord.equals(fnumber)) {	
					uf.union(frecord,fnumber);
				}
			}
			else{
				numberMap.put(record.number,record);
			}
		}

		ArrayList<LinkedList<Record>> result = getGroup(uf, records);
		return result;

	} 
	public static ArrayList<LinkedList<Record>> getGroup (UnionFind<Record>uf, ArrayList<Record>records){
		Map<Record, LinkedList<Record>> groupMap = new HashMap<Record, LinkedList<Record>>();
		ArrayList<LinkedList<Record>> result = new ArrayList<LinkedList<Record>>();
		for(Record record : records){
			Record people = uf.find(record);	

			if(!groupMap.containsKey(people)){
				groupMap.put(people,new LinkedList<Record>());
			}
			groupMap.get(people).add(record);
		}
		
		for(Record people : groupMap.keySet()){
			result.add(groupMap.get(people));
			System.out.print(people.name + "\t" + people.number+",\t");
			System.out.println(groupMap.get(people).size());	
		}
		return result;
		
	}
	
	public static class Record{
		String name;
		int number;
		Record(String name, int number){
			this.name = name;
			this.number = number;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + number;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Record other = (Record) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (number != other.number)
				return false;
			return true;
		} 
		
	}
	
}

class UnionFind<T>{
	Map<T, T> map;
	UnionFind(ArrayList<T> records){
		map = new HashMap<>();
		for(T record: records){
			map.put(record, record);
		}
	}
	
	public void union(T a, T b){
		T fa = find(a);
		T fb = find(b);
		if(!fa.equals(fb))
			map.put(fa,fb);	
	}
	
	public T find(T a){
		T root = map.get(a);	
		while (!root.equals(map.get(root))) {
			root = map.get(root);
		}
		T x = a;
		while (!map.get(x).equals(root)) { // change all points in the path into root.
			T tmp = map.get(x);
			map.put(x,root);
			x = tmp;
		}
		return root;
	}
}
