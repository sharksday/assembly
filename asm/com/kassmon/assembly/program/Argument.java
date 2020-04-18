package com.kassmon.assembly.program;

public class Argument {
	
	private boolean isNumber;
	private boolean isLabel;
	private String value;
	
	
	public Argument (boolean isNumber, boolean isLabel, String value) {
		this.isNumber = isNumber;
		this.isLabel = isLabel;
		this.value = value;
	}
	
	public boolean isNumber() {
		return isNumber;
	}
	
	public boolean isLabel() {
		return isLabel;
	}
	
	public String getValue() {
		return value;
	}
	
}
