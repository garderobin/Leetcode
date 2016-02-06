package amazon_test;

public class Ad{
	private int startTime;
	private int endTime;
	private String ID;
	private int value;
	private String area;
	
	public Ad(int startTime, int endTime, String ID, int value, String area){
		this.startTime = startTime;
		this.endTime = endTime;
		this.ID = ID;
		this.value = value;
		this.area = area;
	}
	
	public int getStart(){
		return this.startTime;
	}
	
	public int getEnd(){
		return this.endTime;
	}
	
	public String getID(){
		return this.ID;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getArea(){
		return this.area;
	}
	
	public String toString(){
		return "[ startTime=" + this.startTime + ", endTime=" + this.endTime + ", ID=" + this.ID + ", value=" + this.value + ", area=" + area + " ]";
	}
}
