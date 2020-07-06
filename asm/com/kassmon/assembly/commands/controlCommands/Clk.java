package com.kassmon.assembly.commands.controlCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.externalBuss.old.ExternalBusItem;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Clk extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
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
