package com.kassmon.assembly.commands.controlCommands;

import com.kassmon.assembly.commands.Command;
import com.kassmon.assembly.exceptions.ParcerException;
import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.tokenizer.CommandTokenizer;

public class Rpc extends Command {

	@Override
	public Command parse(CommandTokenizer t) throws ParcerException{
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
