package com.kassmon.old.assembly.logic.commands.outputCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Omv extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException {
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
