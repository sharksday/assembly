package com.kassmon.assembly.commands.outputCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Olf extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException {
		return new Olf();
	}
	
	@Override
	public String getPattern() {
		return "olf";
	}
	
	@Override
	public void run(RunTime runtime) {
		com.kassmon.assembly.io.OutputControler.output(System.lineSeparator());
	}
	
}
