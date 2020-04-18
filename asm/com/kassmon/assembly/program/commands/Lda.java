package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Lda extends Command {

	@Override
	public Command parse(Tokenizer t) {
		return new Lda();
	}

	@Override
	public String getPattern() {
		return "lda";
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setPortA(runtime.getAcc());
	}

}
