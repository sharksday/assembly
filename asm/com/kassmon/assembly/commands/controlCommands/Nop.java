package com.kassmon.assembly.commands.controlCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.IRuntime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Nop extends Command {

	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
		return new Nop();
	}

	@Override
	public String getPattern() {
		return "nop";
	}

	@Override
	public void run(IRuntime runtime) {
		
	}

}
