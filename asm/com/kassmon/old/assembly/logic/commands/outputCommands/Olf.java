package com.kassmon.old.assembly.logic.commands.outputCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Olf extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException {
		return new Olf();
	}
	
	@Override
	public String getPattern() {
		return "olf";
	}
	
	@Override
	public void run(IRuntime runtime) {
		com.kassmon.old.assembly.logic.io.OutputControler.output(System.lineSeparator());
	}
	
}
