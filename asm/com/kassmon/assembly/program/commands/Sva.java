package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Sva extends Command {

	@Override
	public Command parse(Tokenizer t) {
		return new Sva();
	}

	@Override
	public String getPattern() {
		return "sva";
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setAcc(runtime.getPortA());
	}

}
