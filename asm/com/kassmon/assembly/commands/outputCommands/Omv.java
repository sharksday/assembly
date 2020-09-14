package com.kassmon.assembly.commands.outputCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Omv extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		return new Omv();
	}
	
	@Override
	public String getPattern() {
		return "omv";
	}
	
	@Override
	public void run(IRuntime runtime) {
		runtime.printMem();
	}
	
}
