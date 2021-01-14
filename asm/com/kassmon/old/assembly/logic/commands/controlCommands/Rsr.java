package com.kassmon.old.assembly.logic.commands.controlCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Rsr extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
		return new Rsr();
	}
	
	@Override
	public String getPattern() {
		return "rsr";
	}
	
	public Rsr() {
		
	}
	
	@Override
	public void run(IRuntime runtime) {
		runtime.setPc(runtime.popFromStack());
	}
	
}
