package com.kassmon.assembly.commands.branchingCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Rsr extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		return new Rsr();
	}
	
	@Override
	public String getPattern() {
		return "rsr";
	}
	
	public Rsr() {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		runtime.setPc(runtime.popFromStack());
	}
	
}
