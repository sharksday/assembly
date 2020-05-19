package com.kassmon.assembly.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Wpc extends Command {

	@Override
	public Command parse(CommandTokenizer t) {
		return new Wpc();
	}

	@Override
	public String getPattern() {
		return "wpc";
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setPc(runtime.getAcc());
	}

}
