package com.kassmon.assembly.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Nop extends Command {

	@Override
	public Command parse(Tokenizer t) {
		return new Nop();
	}

	@Override
	public String getPattern() {
		return "nop";
	}

	@Override
	public void run(RunTime runtime) {
		
	}

}
