package com.kassmon.assembly.program.commands;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.Tokenizer;

public class Rsr extends Command {
	
	@Override
	public Command parse(Tokenizer t) {
		return new Rsr();
	}
	
	@Override
	public String getPattern() {
		return "rsr";
	}
	
	public Rsr() {
		
	}
	
	@Override
	public void run(RunTime runtime) {
		runtime.setPc(runtime.popFromStack());
	}
	
}
