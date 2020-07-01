package com.kassmon.assembly.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Not extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) {
		return new Not();
	}
	
	@Override
	public String getPattern() {
		return "not";
	}
	
	@Override
	public void run(RunTime runtime) {
		runtime.setAcc(runtime.getAcc() * -1);
	}
	
}
