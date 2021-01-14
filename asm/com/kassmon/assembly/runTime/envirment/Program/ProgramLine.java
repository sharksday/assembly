package com.kassmon.assembly.runTime.envirment.Program;

import com.kassmon.assembly.runTime.objects.commands.Command;

public class ProgramLine {
	
	private boolean isCommand;
	private String label;
	private Command command;
	
	public ProgramLine (Command command) {
		this.isCommand = true;
		this.command = command;
	}
	
	public ProgramLine (String label) {
		this.isCommand = false;
		this.label = label;
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
