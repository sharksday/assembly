package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Ldb extends Command {

	@Override
	public Command parse(Tokenizer t) {
		return new Ldb();
	}

	@Override
	public String getPattern() {
		return "ldb";
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setPortB(runtime.getAcc());
	}

}
