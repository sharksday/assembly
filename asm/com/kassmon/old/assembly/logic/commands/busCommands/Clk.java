package com.kassmon.old.assembly.logic.commands.busCommands;

import com.kassmon.assembly.exceptions.ParserException;
import com.kassmon.assembly.externalBuss.ExternalBusItem;
import com.kassmon.old.assembly.logic.IRuntime;
import com.kassmon.old.assembly.logic.commands.Command;
import com.kassmon.old.assembly.logic.tokenizer.CommandTokenizer;

public class Clk extends Command {
	
	@Override
	public Command parse(CommandTokenizer t) throws ParserException{
		return new Clk();
	}
	
	@Override
	public String getPattern() {
		return "clk";
	}
	
	@Override
	public void run(IRuntime runtime) {
		for (ExternalBusItem obj: runtime.getBus()) {
			obj.clock();
		}
	}
	
}
