package com.kassmon.assembly.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Rpc extends Command {

	@Override
	public Command parse(CommandTokenizer t) {
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
