package com.kassmon.assembly.program;

public class Argument {
	
	private String name;
	private boolean isNumber;
	private String value;
	
	public Argument (String name ,boolean isNumber, String value) {
		this.name = name;
		this.isNumber = isNumber;
		this.value = value;
	}
	
	public Argument (boolean isNumber, String value) {
		this.isNumber = isNumber;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isNumber() {
		return isNumber;
	}
	
	public String getValue() {
		return value;
	}
	
}
