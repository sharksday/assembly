package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Rpc extends Command {

	@Override
	public Command parse(Tokenizer t) {
		return new Rpc();
	}

	@Override
	public String getPattern() {
		return "rpc";
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setAcc(runtime.getPc());
	}

}
