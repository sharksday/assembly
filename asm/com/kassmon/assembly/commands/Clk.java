package com.kassmon.assembly.commands;

import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Clk extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) {
		return new Clk();
	}
	
	@Override
	public String getPattern() {
		return "clk";
	}
	
	@Override
	public void run(RunTime runtime) {
		for (ExternalBusItem obj: runtime.getBus()) {
			obj.clock();
		}
	}
	
}
