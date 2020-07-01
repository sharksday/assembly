package com.kassmon.assembly.commands;

import static com.kassmon.library.log.Log.newLogEntry;

import com.kassmon.assembly.logic.RunTime;
import com.kassmon.assembly.program.Argument;
import com.kassmon.assembly.tokenizer.CommandTokenizer;
import com.kassmon.library.log.EntryType;

public class Add extends Command {
	private String path = "com.kassmon.assembly.program.commands.Add";
	
	@Override
	public Command parse(CommandTokenizer t) {
		Argument a1 = super.getArg(t);
		if (a1 != null) if (!a1.isLabel()) return new Add(a1);
		newLogEntry(EntryType.ERROR, path, "not a valid argument");
		return new Nop();
	}

	@Override
	public String getPattern() {
		return "add";
	}
	
	private Argument a1;
	
	public Add (Argument a1) {
		this.a1 = a1;
	}
	
	public Add() {
		
	}

	@Override
	public void run(RunTime runtime) {
		runtime.setAcc(runtime.getAcc() + super.getValue(runtime, a1));
	}

}
