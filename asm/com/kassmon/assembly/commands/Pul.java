package com.kassmon.assembly.commands;

import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Pul extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) {
		return new Pul();
	}
	
	public Pul() {
		
	}
	
	@Override
	public String getPattern() {
		return "pul";
	}
	
	@Override
	public void run(RunTime runtime) {
		for (ExternalBusItem obj: runtime.getBus()) {
			if (obj.hasOutput(runtime.getAdr())) {
				runtime.setAcc(obj.pull(runtime.getAdr()));
			}
		}
	}
	
}