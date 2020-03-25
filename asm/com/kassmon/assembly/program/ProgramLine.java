package com.kassmon.assembly.program;

public class ProgramLine {
	
	private LineTypes lineTypes;
	private CommandTypes commandType = null;
	private String label = null;
	
	private Argument arguments[];
	private boolean hasArguments;
	
	public ProgramLine(CommandTypes commandType, Argument arguments[]) {
		this.commandType = commandType;
		this.arguments = arguments;
		this.hasArguments = true;
		this.lineTypes = LineTypes.COMMAND;
	}
	
	public ProgramLine(CommandTypes commandType) {
		this.commandType = commandType;
		this.hasArguments = false;
		this.lineTypes = LineTypes.COMMAND;
	}
	
	public ProgramLine(CommandTypes commandType, String label) {
		this.commandType = commandType;
		this.hasArguments = false;
		this.label = label;
		this.lineTypes = LineTypes.COMMAND;
	}
	
	public ProgramLine(String label){
		this.label = label;
		this.lineTypes = LineTypes.LABEL;
		this.hasArguments = false;
	}
	
	public LineTypes getLineType() {
		return lineTypes;
	}
	
	public CommandTypes getCommandType() {
		return commandType;
	}
	
	public int argumentNumber(){
		if (hasArguments) return this.arguments.length;
		return -1;
	}
	
	public Argument getArgument(int arg){
		return arguments[arg];
	}
	
	public String getLabel() {
		return label;
	}
	
	public Boolean hasArguments() {
		return this.hasArguments;
	}
	
	
}