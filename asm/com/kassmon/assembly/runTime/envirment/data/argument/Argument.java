package com.kassmon.assembly.runTime.envirment.data.argument;

public class Argument {
		
	private ArgumentType type;
	private String value;
	
	public Argument(ArgumentType type, String value) {
		this.type = type;
		this.value = value;
	}
	public ArgumentType getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	
	
	
}
