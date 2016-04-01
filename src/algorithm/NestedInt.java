package algorithm;

import java.util.List;

import dataStructure.NestedInteger;

public class NestedInt implements NestedInteger {
	boolean isInteger;
	int val;
	List<NestedInteger> list;
	
	public NestedInt(int val) {
		isInteger = true;
		this.val = val;
	}
	
	public NestedInt(NestedInteger obj) {
		isInteger = obj.isInteger();
		this.val = obj.getInteger();
		this.list = obj.getList();
	}
	
	@Override
	public boolean isInteger() {
		return isInteger;
	}

	@Override
	public Integer getInteger() {
		return val;
	}

	@Override
	public List<NestedInteger> getList() {
		return list;
	}

}
