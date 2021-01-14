package com.kassmon.old.assembly.logic.commands.controlCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Nop extends Command {

	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
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
