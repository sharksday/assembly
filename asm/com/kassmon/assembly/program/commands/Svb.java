package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Svb extends Command {

	@Override
	public Command parse(Tokenizer t) {
		return new Svb();
	}

	@Override
	public String getPattern() {
		return "svb";
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setAcc(runtime.getPortB());
	}

}
