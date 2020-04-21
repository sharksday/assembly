package com.kassmon.assembly.program;

import com.kassmon.assembly.commands.Command;

public class ProgramLine {
	
	private boolean isCommand = false;
	private String label;
	private Command command;
	
	public ProgramLine (String label) {
		this.isCommand = false;
		this.label = label;
	}
	
	public ProgramLine (Command command) {
		this.isCommand = true;
		this.command = command;
	}

	public boolean isCommand() {
		return isCommand;
	}

	public String getLabel() {
		return label;
	}

	public Command getCommand() {
		return command;
	}
	
	
	
}
